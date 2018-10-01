package com.rw.numbered.orders.service.utils;

public class CarNumFormatter {
    public static String format(int num) {
        String numStr = "";
        if(num<10) {
            numStr = "0"+num;
        } else {
            numStr += num;
        }
        return numStr;
    }
}
