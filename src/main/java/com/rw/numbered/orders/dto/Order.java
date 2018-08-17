package com.rw.numbered.orders.dto;

import java.util.Date;
import java.util.List;

public class Order {
    private long id;
    private String expressNo;
    private String status;
    private Date createTime;
    private Date updateTime;
    private Date orderingTime;

    private String departureTrain;
    private Date departureDate;
    private String departureTime;
    private String departureStationName;
    private String departureStationCode;
    private Date startingDepartureDate;
    private Date realDepartureTime;

    private String arrivalTrain;
    private Date arrivalDate;
    private String arrivalTime;
    private String arrivalStationName;
    private String arrivalStationCode;

    private String carriageNo;
    private String carriageTypeCode;
    private String carriageTypeName;
    private String carriageTypeDesc;
    private String carriageServiceClassCode;
    private String carriageServiceClassDesc;
    private String carriageServiceClassIntCode;
    private String carriageOwner;
    private String carriageCarrier;
    private String carriageGenderSign;
    private String carriageGenderSignAdd;
    private String carriageAddSign;


    private int seatCount;
    private String seats;
    private String tripClass;
    private String addSign;
    private String timeDesc;
    private String trainType;
    private String compartmentType;

    private boolean registrationAllowed;
    private boolean registrationNeeded;
    private String registrationStatus;
    private Date registrationTime;
    private Date registrationStopTime;

    private boolean globalPrice;
    private boolean withASMBP;
    private boolean returnAllowed;

    private Date paymentStartTime;
    private Date paymentEndTime;
    private double cost;
    private String currencyCode;
    private double costEuro;
    private String paymentSystem;
    private String paymentNo;

    private List<Ticket> tickets;
}
