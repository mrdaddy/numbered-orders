package com.rw.numbered.orders.dto.trip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(description = "Данные для создания заказа о поездке и пассажирах")
public class OrderingInformation extends TripInformation {
    @ApiModelProperty(example = "74835926988082", required = false, value = "Идентификатор корзины, куда добавить заказ (если первый заказ заполнять не нужно)", dataType = "String")
    private String basket;

    @ApiModelProperty(example = "ВИТЕБСК-ПАССАЖИРСКИЙ", required = true, value = "Наименование станция отправления на выбранном языке пользователя", dataType = "String")
    @NotNull
    @Size(min=2,max=64)
    private String depStationName;

    @ApiModelProperty(example = "ВИТЕБСК-ПАССАЖИРСКИЙ", required = true, value = "Наименование станция назначения на языке пользователя", dataType = "String")
    @NotNull @Size(min=2,max=64)
    private String arrStationName;

    @ApiModelProperty(example = "скорый фирменный", required = true, value = "Характеристики поезда (через пробел) на языке пользователя", dataType = "String")
    @NotNull @Size(max=64)
    private String trainType;

    @NotNull @ApiModelProperty(example = "true", required = true, value = "Флаг, указывающий, нужно ли выполнить электронную регистрацию для этого заказа", dataType = "boolean")
    private boolean isRegistrationNeeded;

    @NotNull @ApiModelProperty(required = true)
    private TripCarriage tripCarriage;
}
