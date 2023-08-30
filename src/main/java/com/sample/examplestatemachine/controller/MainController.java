package com.sample.examplestatemachine.controller;

import com.sample.examplestatemachine.entity.MachineState;
import com.sample.examplestatemachine.scenario.no_order.NoOrderStateMachine;
import com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents;
import com.sample.examplestatemachine.service.MachineStateService;
import com.sample.examplestatemachine.service.PersistStateMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Saransh Kumar
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/grievance/no-order")
public record MainController(ApplicationContext applicationContext,
                             MachineStateService machineStateService) {

    @PostMapping("/{userId}")
    public ResponseEntity<?> executeNoOrderWorkflow(@PathVariable Integer userId,
                                                    @RequestBody Map<String, Object> inputs) {
        NoOrderStateMachine noOrderStateMachine = applicationContext.getBean(NoOrderStateMachine.class);
        noOrderStateMachine.start();
        noOrderStateMachine.put("userId", userId);
        inputs.forEach(noOrderStateMachine::put);
        noOrderStateMachine.executeWorkflow();
        return ResponseEntity.ok(Map.of(
                "machineId", noOrderStateMachine.getID(),
                "context", noOrderStateMachine.getContext()
        ));
    }

    @PostMapping("/id/{machineId}/event/{event}")
    public ResponseEntity<?> continueNoOrderWorkflow(@PathVariable String machineId,
                                                     @PathVariable NoOrderEvents event,
                                                     @RequestBody Map<String, Object> inputs) {
        NoOrderStateMachine noOrderStateMachine = applicationContext.getBean(NoOrderStateMachine.class);
        machineStateService.reinitializeStateMachine(machineId, noOrderStateMachine);
        if (noOrderStateMachine.getCurrentState() == null) {
            throw new RuntimeException("Machine is not re-initialized");
        }
        inputs.forEach(noOrderStateMachine::put);
        log.info("Re-initialized state machine's state: {}", noOrderStateMachine.getCurrentState());
        noOrderStateMachine.executeWorkflow(event);
        return ResponseEntity.ok(Map.of(
                "context", noOrderStateMachine.getContext(),
                "currentState", noOrderStateMachine.getCurrentState()
        ));
    }
}
