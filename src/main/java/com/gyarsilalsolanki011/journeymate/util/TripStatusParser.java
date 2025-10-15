package com.gyarsilalsolanki011.journeymate.util;

import com.gyarsilalsolanki011.journeymate.model.enums.TripStatus;
import com.gyarsilalsolanki011.journeymate.exception.InternalServiceException;

public class TripStatusParser {
    public static TripStatus fromString(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Trip status cannot be empty");
        }
        try {
            return TripStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InternalServiceException("Invalid trip status: " + status);
        }
    }
}

