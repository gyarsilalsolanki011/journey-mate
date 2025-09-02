package com.gyarsilalsolanki011.journeymate.exception;

public class InvalidTripStatusException extends RuntimeException {
    public InvalidTripStatusException(String status) {
        super("Invalid trip status: " + status);
    }
}
