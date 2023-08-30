package com.sample.examplestatemachine.repository;

import com.sample.examplestatemachine.entity.MachineState;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Saransh Kumar
 */

public interface PersistStateMachineRepository extends JpaRepository<MachineState, Long> {

    MachineState findByMachineId(String machineId);
}
