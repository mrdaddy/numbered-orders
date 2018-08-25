package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Информация о местах в заказе")
public class OrderSeatDetail {
    private int seatCount;
    private String seats;
    private String compartmentType;
}
