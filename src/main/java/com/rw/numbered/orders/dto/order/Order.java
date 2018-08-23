package com.rw.numbered.orders.dto.order;

import com.rw.numbered.orders.dto.ticket.Ticket;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    @ApiModelProperty(example = "1", required = true, value = "Уникальный идентификатор записи", dataType = "int")
    @Min(1)
    private long id;
    private String expressNo;
    private String status;
    private Date createTime;
    private Date updateTime;
    private Date orderingTime;

    private DepartureInfo departureInfo;
    private ArrivalInfo arrivalInfo;

    private OrderSeatDetail orderSeatDetail;
    private OrderCarriage orderCarriage;

    private String tripClass;
    private String addSign;
    private String timeDesc;
    private String trainType;
    private boolean globalPrice;

    private OrderRegistration orderRegistration;

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
