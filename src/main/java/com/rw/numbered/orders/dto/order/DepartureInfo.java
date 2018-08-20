package com.rw.numbered.orders.dto.order;

import lombok.Data;

import java.util.Date;

@Data
public class DepartureInfo {
    private String train;
    private Date date;
    private String time;
    private String stationName;
    private String stationCode;
    private Date startingDate;
    private Date realDatetime;
}
