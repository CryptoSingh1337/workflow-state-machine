package com.sample.examplestatemachine.service;

import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;
import com.sample.examplestatemachine.generic.StateMachine;

/**
 * @author Saransh Kumar
 */

public interface PersistStateMachineService {

    void save(String id, State currentState, Context context);
}
