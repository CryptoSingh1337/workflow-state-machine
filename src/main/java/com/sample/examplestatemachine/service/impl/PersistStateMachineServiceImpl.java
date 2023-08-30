package com.sample.examplestatemachine.service.impl;

import com.sample.examplestatemachine.entity.MachineState;
import com.sample.examplestatemachine.generic.Context;
import com.sample.examplestatemachine.generic.State;
import com.sample.examplestatemachine.generic.StateMachine;
import com.sample.examplestatemachine.repository.PersistStateMachineRepository;
import com.sample.examplestatemachine.service.PersistStateMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Saransh Kumar
 */

@RequiredArgsConstructor
@Service
public class PersistStateMachineServiceImpl implements PersistStateMachineService {

    private final PersistStateMachineRepository persistStateMachineRepository;

    @Override
    public void save(String id, State currentState, Context context) {
        // TODO: use Optional API and throw exception on not found case
        MachineState machineState = persistStateMachineRepository.findByMachineId(id);
        if (machineState != null) {
            machineState.setClazz(currentState.getClass().getName());
            machineState.setState(currentState.getID());
            machineState.setContext(context.getCtx());
        } else {
            machineState = MachineState.builder()
                    .machineId(id)
                    .state(currentState.getID())
                    .clazz(currentState.getClass().getName())
                    .context(context.getCtx())
                    .build();
        }
        persistStateMachineRepository.save(machineState);
    }
}
