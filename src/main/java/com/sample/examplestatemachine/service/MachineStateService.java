package com.sample.examplestatemachine.service;

import com.sample.examplestatemachine.generic.StateMachine;

/**
 * @author Saransh Kumar
 */

public interface MachineStateService {

    void reinitializeStateMachine(String machineId, StateMachine createdStateMachine);
}
