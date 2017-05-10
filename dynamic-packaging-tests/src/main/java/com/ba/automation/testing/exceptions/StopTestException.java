package com.ba.automation.testing.exceptions;

/**
 * Created by n448846 on 25/08/2016.
 * Exception which shall be thrown in order to stop execution of current test
 */
public class StopTestException extends Exception {
    public StopTestException(String message) {
        super(message);
    }

}
