package com.sample.examplestatemachine.generic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Saransh Kumar
 */

@Slf4j
public class ErrorState extends State {

    public ErrorState() {
        super(ErrorState.class.getSimpleName());
    }

    @Override
    public void action(Context context) {
        log.info("State change to Error State");
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
