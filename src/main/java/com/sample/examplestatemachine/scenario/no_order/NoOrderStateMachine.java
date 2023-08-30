package com.sample.examplestatemachine.scenario.no_order;

import com.sample.examplestatemachine.generic.*;
import com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents;
import com.sample.examplestatemachine.scenario.no_order.listeners.StateChangeListenerImpl;
import com.sample.examplestatemachine.scenario.no_order.states.*;
import com.sample.examplestatemachine.service.PersistStateMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents.*;

/**
 * @author Saransh Kumar
 */

@Component
@Scope(scopeName = "prototype")
public final class NoOrderStateMachine extends StateMachine {

    private PersistStateMachineService persistStateMachineService;

    public NoOrderStateMachine() {
        super(UUID.randomUUID().toString());
        config();
    }

    @Override
    public void config() {
        EnumSet.allOf(NoOrderEvents.class)
                .forEach(event -> events.put(event.name(), event));

        InitialState initialState = new InitialState();
        CheckRiderIDState checkRiderIDState = new CheckRiderIDState();
        CheckRiderZoneState checkRiderZoneState = new CheckRiderZoneState();
        CheckCityState checkCityState = new CheckCityState();
        CheckReasonForIdBlockState checkReasonForIdBlockState = new CheckReasonForIdBlockState();
        CheckRiderUndertakingAndDepositStatusState checkRiderUndertakingAndDepositStatusState =
                new CheckRiderUndertakingAndDepositStatusState();
        CheckRiderCityAndSelfCancellationState checkRiderCityAndSelfCancellationState =
                new CheckRiderCityAndSelfCancellationState();
        ErrorState errorState = new ErrorState();

        states.put(InitialState.class.getSimpleName(), initialState);
        states.put(CheckRiderIDState.class.getSimpleName(), checkRiderIDState);
        states.put(CheckRiderZoneState.class.getSimpleName(), checkRiderZoneState);
        states.put(CheckCityState.class.getSimpleName(), checkCityState);
        states.put(CheckReasonForIdBlockState.class.getSimpleName(), checkReasonForIdBlockState);
        states.put(CheckRiderUndertakingAndDepositStatusState.class.getSimpleName(),
                checkRiderUndertakingAndDepositStatusState);
        states.put(CheckRiderCityAndSelfCancellationState.class.getSimpleName(),
                checkRiderCityAndSelfCancellationState);
        states.put(ErrorState.class.getSimpleName(), errorState);

        transitions.put(USER_ID, List.of(initialState, checkRiderIDState));
        transitions.put(ID_ACTIVE, List.of(checkRiderIDState, checkRiderZoneState));
        transitions.put(ID_INACTIVE, List.of(checkRiderIDState, checkCityState));
        transitions.put(ID_BLOCK, List.of(checkRiderIDState, checkReasonForIdBlockState));
        transitions.put(REST_OF_INDIA, List.of(checkCityState, checkRiderUndertakingAndDepositStatusState));
        transitions.put(MISSED_ORDER, List.of(checkReasonForIdBlockState,
                checkRiderCityAndSelfCancellationState));

        stateChangeListener = new StateChangeListenerImpl();
    }

    @Override
    public State getCurrentState() {
        return this.currentState;
    }

    @Override
    public void start() {
        this.currentState = states.get(InitialState.class.getSimpleName());
        this.currentState.action(context);
    }

    @Override
    public void stop() {
        this.currentState = null;
        this.context.clear();
    }

    @Override
    public void sendEvent(Event event) {
        if (transitions.containsKey(event)) {
            List<State> states = transitions.get(event);
            State source = states.get(0), target = states.get(1);
            if (!currentState.equals(source)) {
                currentState = this.states.get(ErrorState.class.getSimpleName());
                currentState.action(context);
                changeState(event, List.of(source, this.states.get(ErrorState.class.getSimpleName())));
                return;
            }
            currentState = target.transition(context);
            if (currentState == null) {
                currentState = this.states.get(ErrorState.class.getSimpleName());
                changeState(event, List.of(source, this.states.get(ErrorState.class.getSimpleName())));
            }
            currentState.action(context);
            changeState(event, List.of(source, target));
        } else {
            State source = currentState;
            currentState = states.get(ErrorState.class.getSimpleName());
            currentState.action(context);
            changeState(event, List.of(source, currentState));
        }
    }

    public void executeWorkflow() {
        while (true) {
            if (ErrorState.class.getSimpleName().equals(currentState.getID()) ||
                    currentState.isFinal() || currentState.getRequireUserInput()) {
                return;
            }
            if (context.contains("nextEvent")) {
                Event event =  events.get((String) context.get("nextEvent"));
                sendEvent(event);
            } else {
                sendEvent(ERROR);
            }
        }
    }

    public void executeWorkflow(Event event) {
        sendEvent(event);
        executeWorkflow();
    }

    private void changeState(Event event, List<State> states) {
        if (stateChangeListener != null) {
            stateChangeListener.onStageChange(ID, event, states, context);
            persistStateMachineService.save(ID, currentState, context);
        }
    }

    @Autowired
    public void setPersistStateMachineService(PersistStateMachineService persistStateMachineService) {
        this.persistStateMachineService = persistStateMachineService;
    }
}
