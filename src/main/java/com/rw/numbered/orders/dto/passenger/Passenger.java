package com.rw.numbered.orders.dto.passenger;

import lombok.Data;

import java.util.Date;

@Data
public class Passenger {
    private int type;
    private int tarif;
    private String firstname;
    private String patronymic;
    private String lastname;
    private int placesCount=1;
    private String sex;
    private boolean useSex;
    private Date birthday;
    private int passNum;
    private String country = "BLR";
    PassengerDoc passengerDoc;
}
