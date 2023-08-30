package com.sample.examplestatemachine.scenario.no_order.states;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Saransh Kumar
 */

@Slf4j
public class EndState extends State {

    public EndState() {
        super(EndState.class.getSimpleName());
    }

    @Override
    public void action(Context context) {
        log.info("State change to End State");
        log.info("Context: {}", context);
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
