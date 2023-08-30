package com.sample.examplestatemachine.scenario.no_order.states;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;
import com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents;
import lombok.extern.slf4j.Slf4j;

import static com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents.MISSED_ORDER;

/**
 * @author Saransh Kumar
 */

@Slf4j
public class CheckReasonForIdBlockState extends State {

    public CheckReasonForIdBlockState() {
        super(CheckReasonForIdBlockState.class.getSimpleName());
        this.setFinal(false);
        this.setRequireUserInput(true);
    }

    @Override
    public void action(Context context) {
        log.info("State change to Check reason for ID blocked");
        context.put("nextEvent", MISSED_ORDER.name());
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
        if (context.contains("idStatus") && "blocked".equals(context.get("idStatus"))) {
            return true;
        } else {
            context.put("error", "Required id to be blocked");
            return false;
        }
    }
}
