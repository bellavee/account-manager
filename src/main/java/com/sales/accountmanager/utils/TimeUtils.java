package com.sales.accountmanager.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeUtils {

    public static Double convertTimeToDecimal(LocalTime startTime, LocalTime endTime) {
        long minutesBetween;
        if (endTime.isBefore(startTime)) {
            minutesBetween = ChronoUnit.MINUTES.between(startTime, LocalTime.MAX) + 1 // Up to 00:00
                    + ChronoUnit.MINUTES.between(LocalTime.MIDNIGHT, endTime);
        } else {
            minutesBetween = ChronoUnit.MINUTES.between(startTime, endTime);
        }
        BigDecimal hours = new BigDecimal(minutesBetween).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP);
        return hours.doubleValue();
    }

    public static String calculateDeltaTimeName(LocalTime time1, LocalTime time2) {
        long hours, minutes;
        if (time2.isBefore(time1)) {
            Duration untilMidnight = Duration.between(time1, LocalTime.MAX);
            Duration afterMidnight = Duration.between(LocalTime.MIDNIGHT, time2);
            Duration totalDuration = untilMidnight.plus(afterMidnight).plusMinutes(1);
            hours = totalDuration.toHours();
            minutes = totalDuration.minusHours(hours).toMinutes();
        } else {
            Duration duration = Duration.between(time1, time2);
            hours = duration.toHours();
            minutes = duration.minusHours(hours).toMinutes();
        }
        return String.format("%2dh%02d", hours, minutes);
    }

    public static String formatTimeStr(BigDecimal time) {
        int hours = time.intValue();
        int minutes = (int) ((time.subtract(new BigDecimal(hours))).multiply(new BigDecimal(60)).intValue());

        if (minutes == 0) {
            return hours + "h";
        } else {
            return hours + "h" + String.format("%02d", minutes);
        }
    }



}
