package com.sample.examplestatemachine.exception;

/**
 * @author Saransh Kumar
 */

public class ReachedFinalStateException extends RuntimeException {

    public ReachedFinalStateException() {
        super("Already reached the final state");
    }
}
