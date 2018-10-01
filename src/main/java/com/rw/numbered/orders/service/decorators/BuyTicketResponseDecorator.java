package com.rw.numbered.orders.service.decorators;

import by.iba.railway.eticket.xml.objs.response.eticket.BuyTicketResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class BuyTicketResponseDecorator {
    @Delegate
    private BuyTicketResponse originResponse;
    @Getter @Setter
    private String depStationName;
    @Getter @Setter
    private String arrStationName;
    @Getter @Setter
    private String carTypeName;
    @Getter @Setter
    private String currency;
    @Getter @Setter
    private int timeOffset;
    @Getter @Setter
    private int paymentTime;
    @Getter @Setter
    private boolean denominationPassed;

    public String getCarTypeLetter() {
        String carTypeLetter = "";
        if(originResponse.getCar()!=null && !StringUtils.isEmpty(originResponse.getCar().getType())) {
            carTypeLetter = originResponse.getCar().getType().substring(0,1);
        }
        return carTypeLetter;
    }
    public BuyTicketResponseDecorator(BuyTicketResponse response) {
        this.originResponse = response;
    }

    public List<String> getSignsList() {
        if(originResponse.getCar() != null && !StringUtils.isEmpty(originResponse.getCar().getAddSigns())) {
            return Arrays.asList(originResponse.getCar().getAddSigns().split(" "));
        } else {
            return null;
        }
    }

}
