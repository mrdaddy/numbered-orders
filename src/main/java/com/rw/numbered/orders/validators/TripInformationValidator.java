package com.rw.numbered.orders.validators;

import com.rw.numbered.orders.dto.trip.TripInformation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TripInformationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return TripInformation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {

        //ValidationUtils.rejectIfEmpty(e, "name", "name.empty");

        TripInformation tripInformation = (TripInformation) target;
        if(tripInformation.getArrStationCode().equals("string")) {
            e.reject("gvCode","test validation");
        }
        //perform additional checks
        //if name already exists or ?
    }}
