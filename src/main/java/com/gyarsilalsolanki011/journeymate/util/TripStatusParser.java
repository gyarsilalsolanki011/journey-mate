package com.gyarsilalsolanki011.journeymate.util;

import com.gyarsilalsolanki011.journeymate.enums.TripStatus;
import com.gyarsilalsolanki011.journeymate.exception.InvalidTripStatusException;

public class TripStatusParser {
    public static TripStatus fromString(String status) {
        try {
            return TripStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidTripStatusException(status);
        }
    }
}

