package com.rw.numbered.orders.service.utils;

import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTimeZone;

public class TimeZoneOffsetCalculator {
    public static int calculate(String timeZone,Date date) {
        int diffMinutes=0;
        if(date==null) {
            date = Calendar.getInstance().getTime();
        }
        DateTimeZone zone1 = DateTimeZone.forID(timeZone);
        DateTimeZone zone2 = DateTimeZone.forID("Europe/Minsk");

        diffMinutes = (zone1.getOffset(date.getTime()) - zone2.getOffset(date.getTime()))/(60*1000);
        return diffMinutes;

    }

}