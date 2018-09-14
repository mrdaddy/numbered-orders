package com.rw.numbered.orders.dto.ticket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "Информация о местах в билете")
@NoArgsConstructor
@AllArgsConstructor
public class TicketSeatDetail {
    public enum SEAT_DESC {TOP, DOWN, MIDDLE}

    @ApiModelProperty(example = "1", required = true, value = "Количество мест", dataType = "int")
    private int seatCount;

    @ApiModelProperty(example = "017;018", required = false, value = "Номера мест (через точку с запятой)", dataType = "String")
    private String seats;

    @ApiModelProperty(example = "TOP", required = false, value = "Характеристика места. Значения: TOP - Верхнее, DOWN - Нижнее, MIDDLE - Среднее", dataType = "String")
    private SEAT_DESC seatDesc;
}
