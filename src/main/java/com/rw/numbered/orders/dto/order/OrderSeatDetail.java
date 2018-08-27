package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Информация о местах в заказе")
public class OrderSeatDetail {
    public enum SEX_TYPES {MALE, FEMALE, MIXED}

    @ApiModelProperty(example = "1", required = true, value = "Количество мест", dataType = "int")
    private int seatCount;

    @ApiModelProperty(example = "017;018;019;020", required = false, value = "Номера мест (через точку с запятой)", dataType = "String")
    private String seats;

    @ApiModelProperty(example = "MALE", required = false, value = "Тип купэ для М/Ж вагонов: MALE - мужское, FEMALE - женское, MIXED - смешанное", dataType = "String")
    private SEX_TYPES compartmentType;
}
