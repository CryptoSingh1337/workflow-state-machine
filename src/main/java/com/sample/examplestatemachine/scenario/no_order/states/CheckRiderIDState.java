package com.sample.examplestatemachine.scenario.no_order.states;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;
import lombok.extern.slf4j.Slf4j;

import static com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents.*;

/**
 * @author Saransh Kumar
 */

@Slf4j
public final class CheckRiderIDState extends State {

    public CheckRiderIDState() {
        super(CheckRiderIDState.class.getSimpleName());
        this.setFinal(false);
        this.setRequireUserInput(false);
    }

    @Override
    public void action(Context context) {
        String idStatus = (String) context.get("idStatus");
        if ("active".equalsIgnoreCase(idStatus)) {
            context.put("nextEvent", ID_ACTIVE.name());
        } else if ("inactive".equalsIgnoreCase(idStatus)) {
            context.put("nextEvent", ID_INACTIVE.name());
        } else if ("blocked".equalsIgnoreCase(idStatus)) {
            context.put("nextEvent", ID_BLOCK.name());
        }
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
        if (context.contains("userId") && context.contains("idStatus")) {
            return true;
        } else {
            context.put("error", "User ID and ID status are required");
            return false;
        }
    }
}
