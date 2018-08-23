package com.rw.numbered.orders.dto.trip;

import com.rw.numbered.orders.dto.passenger.Passenger;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class TripInformation {
    @NotNull
    private String train;
    private Date departureDate;
    private String departureTime;
    private String depStationName;
    private String depStationCode;
    private String arrStationName;
    private String arrStationCode;

    private String trainType;
    private boolean globalPrice;

    private TripCarriage tripCarriage;
    private TripSeatDetail tripSeatDetail;
    private boolean registrationNeeded;
    private List<Passenger> passengers;

}
