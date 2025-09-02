package com.gyarsilalsolanki011.journeymate.exception;

public class TripServiceException extends RuntimeException {
    public TripServiceException(String message) {
        super(message);
    }

    public TripServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
