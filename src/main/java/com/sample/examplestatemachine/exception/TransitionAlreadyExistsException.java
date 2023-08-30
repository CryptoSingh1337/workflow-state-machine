package com.sample.examplestatemachine.exception;

/**
 * @author Saransh Kumar
 */

public class TransitionAlreadyExistsException extends Exception {

    public TransitionAlreadyExistsException() {
        super("Transition already exists");
    }
}
