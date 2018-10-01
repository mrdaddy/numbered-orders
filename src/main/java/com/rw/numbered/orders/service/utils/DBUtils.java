package com.rw.numbered.orders.service.utils;

import java.text.MessageFormat;

public class DBUtils {
    public static String formatQueryWithParams(String query, String... params) {
        return MessageFormat.format(query,params);
    }

    public static boolean toBoolean(Integer value) {
        if(value == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static String toString(boolean value) {
        if(value) {
            return "1";
        } else {
            return "0";
        }
    }

}
