package com.sample.examplestatemachine.scenario.no_order;

import com.sample.examplestatemachine.exception.TransitionAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents.ID_ACTIVE;
import static com.sample.examplestatemachine.scenario.no_order.events.NoOrderEvents.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class NoOrderStateMachineTest {

    @Test
    public void test() throws TransitionAlreadyExistsException {
        NoOrderStateMachine noOrderStateMachine = new NoOrderStateMachine();

        noOrderStateMachine.start();
        System.out.println("Current state: " + noOrderStateMachine.getCurrentState());

        noOrderStateMachine.sendEvent(USER_ID);
//        assertEquals(noOrderStateMachine.getCurrentState().getID(), ErrorState.class.getSimpleName());

        noOrderStateMachine.stop();

        /////////

        noOrderStateMachine.start();
        System.out.println("Current state: " + noOrderStateMachine.getCurrentState());

        noOrderStateMachine.put("userId", "User-1");
        noOrderStateMachine.printContext();

        noOrderStateMachine.sendEvent(USER_ID);

        noOrderStateMachine.sendEvent(ID_ACTIVE);

        noOrderStateMachine.stop();

        ///////

        noOrderStateMachine.start();
        noOrderStateMachine.printContext();
        noOrderStateMachine.executeWorkflow();
        noOrderStateMachine.stop();
    }
}
