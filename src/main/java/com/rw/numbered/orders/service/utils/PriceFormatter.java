package com.rw.numbered.orders.service.utils;

public class PriceFormatter {

    public static double getFloatAsDouble(float value) {
        return Double.valueOf(Float.valueOf(value).toString()).doubleValue();
    }
}
