package com.rw.numbered.orders.dto.passenger;

import lombok.Data;

import java.util.Date;

@Data
public class Passenger {
    private int id;
    private String firstName;
    private String patronymic;
    private String lastName;
    private int placesCount=1;
    private String sex;
    private Date birthday;
    private String country = "BLR";
    private String documentType ;
    private String documentNumber;
}
