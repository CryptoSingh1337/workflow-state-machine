package com.sample.examplestatemachine.scenario.no_order.states;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;

/**
 * @author Saransh Kumar
 */

public class CheckRiderUndertakingAndDepositStatusState extends State {


    public CheckRiderUndertakingAndDepositStatusState() {
        super(CheckRiderUndertakingAndDepositStatusState.class.getSimpleName());
        this.setFinal(true);
        this.setRequireUserInput(false);
    }

    @Override
    public void action(Context context) {
        context.remove("nextEvent");
        context.put("output", "Wait for 24 hours");
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
