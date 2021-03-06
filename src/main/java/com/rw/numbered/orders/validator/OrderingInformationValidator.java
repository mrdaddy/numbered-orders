package com.rw.numbered.orders.validator;

import com.rw.numbered.orders.dto.request.OrderingInformation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OrderingInformationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return OrderingInformation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        if(target instanceof OrderingInformation) {
            OrderingInformation orderingInformation = (OrderingInformation) target;
            if (orderingInformation.getArrStationCode().equals("string")) {
                e.reject("gvCode", "test validation");
            }
        }
    }
}

