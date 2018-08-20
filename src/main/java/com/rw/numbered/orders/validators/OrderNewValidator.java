package com.rw.numbered.orders.validators;

import com.rw.numbered.orders.dto.order.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class OrderNewValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {

        //ValidationUtils.rejectIfEmpty(e, "name", "name.empty");

        Order order = (Order) target;
        if(order.getId()==1) {
            e.reject("test validation");
        }
        //perform additional checks
        //if name already exists or ?
    }}
