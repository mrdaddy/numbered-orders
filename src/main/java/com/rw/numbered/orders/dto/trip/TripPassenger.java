package com.rw.numbered.orders.dto.trip;

import com.rw.numbered.orders.dto.passenger.Passenger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "Данные о пассажире для формирования заказа")
public class TripPassenger extends Passenger {
    public enum PASSENGER_TYPE {ADULT, CHILD_WITH_PLACE, CHILD_WITHOUT_PLACE}
    @ApiModelProperty(example = "1", required = true, value = "Тип пассажира: ADULT - взрослый, CHILD_WITH_PLACE - ребенок с местом, CHILD_WITHOUT_PLACE - ребенок без места", dataType = "int")
    @NotNull
    private PASSENGER_TYPE passengerType;

    @ApiModelProperty(example = "1", required = false, value = "Номер купэ для вагонов, где места продаются по 2 и больше одного пассажира в заказе", dataType = "int")
    private int coupe2MNumber;
}
