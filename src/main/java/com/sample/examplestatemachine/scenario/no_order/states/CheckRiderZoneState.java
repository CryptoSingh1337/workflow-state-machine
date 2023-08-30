package com.sample.examplestatemachine.scenario.no_order.states;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Saransh Kumar
 */

@Slf4j
public class CheckRiderZoneState extends State {

    public CheckRiderZoneState() {
        super(CheckRiderZoneState.class.getSimpleName());
        this.setFinal(true);
        this.setRequireUserInput(false);
    }

    @Override
    public void action(Context context) {
        context.remove("nextEvent");
        context.put("output", "Move/stay near to the hotspot");
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
        if (context.contains("idStatus") && "active".equals(context.get("idStatus"))) {
            return true;
        } else {
            context.put("error", "Required id to be active");
            return false;
        }
    }
}
