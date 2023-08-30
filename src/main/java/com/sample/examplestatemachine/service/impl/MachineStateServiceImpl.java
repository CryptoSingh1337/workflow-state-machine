package com.sample.examplestatemachine.service.impl;

import com.sample.examplestatemachine.entity.MachineState;
import com.sample.examplestatemachine.generic.ErrorState;
import com.sample.examplestatemachine.generic.State;
import com.sample.examplestatemachine.generic.StateMachine;
import com.sample.examplestatemachine.repository.PersistStateMachineRepository;
import com.sample.examplestatemachine.service.MachineStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;

/**
 * @author Saransh Kumar
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class MachineStateServiceImpl implements MachineStateService {

    public final PersistStateMachineRepository persistStateMachineRepository;

    @Override
    public void reinitializeStateMachine(String machineId, StateMachine createdStateMachine) {
        MachineState machineState = persistStateMachineRepository.findByMachineId(machineId);
        if (machineState != null && StringUtils.hasText(machineState.getClazz())) {
            log.info("Class Name: {}", machineState.getClazz());
            State state = createNewInstance(machineState.getClazz());
            if (!ErrorState.class.getSimpleName().equals(state.getID())) {
                createdStateMachine.setCurrentState(state);
            }
            createdStateMachine.setID(machineId);
            machineState.getContext().forEach(createdStateMachine::put);
        }
    }

    private State createNewInstance(String fullyQualifiedClassName) {
        State state;
        try {
            Class<?> clazz = Class.forName(fullyQualifiedClassName);
            Constructor<?> constructor = clazz.getConstructor();
            Object object = constructor.newInstance();
            state = (State) object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return state;
    }
}
