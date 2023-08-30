package com.sample.examplestatemachine.exception;

/**
 * @author Saransh Kumar
 */

public class ReachedErrorStateException extends RuntimeException {

    public ReachedErrorStateException() {
        super("Already reached the error state");
    }
}
