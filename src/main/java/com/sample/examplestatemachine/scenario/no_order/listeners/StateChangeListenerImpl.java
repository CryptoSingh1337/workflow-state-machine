package com.sample.examplestatemachine.scenario.no_order.listeners;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.Event;
import com.sample.examplestatemachine.generic.State;
import com.sample.examplestatemachine.generic.StateChangeListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Saransh Kumar
 */

@Slf4j
public class StateChangeListenerImpl implements StateChangeListener {

    @Override
    public void onStageChange(String id, Event event, List<State> transition, Context context) {
        log.info("State changed - ID: {}, Event: {}, Transition: {}, Context: {}", id, event, transition, context);
    }
}
