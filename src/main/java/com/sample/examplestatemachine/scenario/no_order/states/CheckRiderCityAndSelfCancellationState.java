package com.sample.examplestatemachine.scenario.no_order.states;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;

/**
 * @author Saransh Kumar
 */

public class CheckRiderCityAndSelfCancellationState extends State {
    public CheckRiderCityAndSelfCancellationState() {
        super(CheckRiderCityAndSelfCancellationState.class.getSimpleName());
        this.setFinal(true);
        this.setRequireUserInput(false);
    }

    @Override
    public void action(Context context) {
        context.remove("nextEvent");
        context.put("output", "Due to high cancellation, your ID is permanently blocked");
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
        if (context.contains("selfCancellation")) {
            double selfCancellation = (Double) context.get("selfCancellation");
            return selfCancellation >= 2.0;
        } else {
            context.put("error", "Self cancellation percentage must be equal to or greater than 2%");
            return false;
        }
    }
}
