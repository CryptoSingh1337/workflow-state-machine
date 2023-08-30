package com.sample.examplestatemachine.scenario.no_order.states;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;
import com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents;
import lombok.extern.slf4j.Slf4j;

import static com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents.REST_OF_INDIA;

/**
 * @author Saransh Kumar
 */

@Slf4j
public class CheckCityState extends State {

    public CheckCityState() {
        super(CheckCityState.class.getSimpleName());
        this.setFinal(false);
        this.setRequireUserInput(false);
    }

    @Override
    public void action(Context context) {
        context.put("nextEvent", REST_OF_INDIA.name());
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
        if (context.contains("idStatus") && "inactive".equals(context.get("idStatus"))) {
            return true;
        } else {
            context.put("error", "Required id to be inactive");
            return false;
        }
    }
}
