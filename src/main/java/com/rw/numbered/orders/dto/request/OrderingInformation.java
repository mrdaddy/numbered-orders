package com.rw.numbered.orders.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(description = "Данные для создания заказа о поездке и пассажирах")
public class OrderingInformation extends TripInformation {
    @ApiModelProperty(example = "74835926988082", required = false, value = "Идентификатор корзины, куда добавить заказ (если первый заказ заполнять не нужно)", dataType = "String")
    @Size(max=20)
    private String basketId;

    @ApiModelProperty(example = "СК СКР ФИРМ", required = true, value = "Характеристики поезда из АСУ Экспресс", dataType = "String")
    @NotNull @Size(max=64)
    private String trainType;

    @NotNull @ApiModelProperty(example = "true", required = true, value = "Флаг, указывающий, нужно ли выполнить электронную регистрацию для этого заказа", dataType = "boolean")
    private boolean isRegistrationNeeded;

    @NotNull @ApiModelProperty(required = true)
    private TripCarriage tripCarriage;
}
