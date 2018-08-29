package com.rw.numbered.orders.validators;

import com.rw.numbered.orders.dto.request.OrderingInformation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TripInformationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return OrderingInformation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {

        OrderingInformation orderingInformation = (OrderingInformation) target;
        if(orderingInformation.getArrStationCode().equals("string")) {
            e.reject("gvCode","test validation");
        }
    }}
