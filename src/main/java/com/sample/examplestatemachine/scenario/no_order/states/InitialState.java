package com.sample.examplestatemachine.scenario.no_order.states;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;
import lombok.extern.slf4j.Slf4j;

import static com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents.USER_ID;

/**
 * @author Saransh Kumar
 */

@Slf4j
public class InitialState extends State {

    public InitialState() {
        super(InitialState.class.getSimpleName());
        this.setFinal(false);
        this.setRequireUserInput(false);
    }

    @Override
    public void action(Context context) {
        context.put("nextEvent", USER_ID.name());
    }

    @Override
    public State transition(Context context) {
        if (this.guard(context)) {
            return this;
        }
        return null;
    }

    @Override
    public boolean guard(Context context) {
        return true;
    }
}
