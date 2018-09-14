package com.rw.numbered.orders.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@ApiModel(description = "Данные о поездке")
@NoArgsConstructor
@AllArgsConstructor
public class TripInformation {
    public enum DIRECTION {THERE, BACK}
    @ApiModelProperty(example = "001А", required = true, value = "Номер поезда", dataType = "String")
    @NotNull @Size(min=4,max=5)
    private String train;

    @ApiModelProperty(example = "2018-08-24", required = true, value = "Дата отправления со станции пассажира", dataType = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @ApiModelProperty(example = "22:11", required = true, value = "Время отправления со станции пассажира", dataType = "Time")
    private String departureTime;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции отправления", dataType = "String")
    @NotNull @Size(min=7,max=8)
    private String depStationCode;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции назначения", dataType = "String")
    @NotNull @Size(min=7,max=8)
    private String arrStationCode;

    @NotNull @ApiModelProperty(example = "false", required = true, value = "Флаг, указывающий, что продажа на данный поезд производится по глобальным ценам", dataType = "boolean")
    private boolean isGlobalPrice;

    @NotNull @ApiModelProperty(example = "true", required = true, value = "Флаг, указывающий, разрешена ли для данного вагона и поезда электронная регистрация", dataType = "boolean")
    private boolean isRegistrationAllowed;

    @ApiModelProperty(example = "THERE", required = false, value = "Направление (необходимо при оформлении туда-обратно). Значения: THERE - туда, BACK - обратно", dataType = "String")
    private DIRECTION directionType;

    @ApiModelProperty(example = "74835926988082", required = false, value = "Идентификатор заказа \"туда\" в системе Экспресс, необходим для оформления билетов туда-обратно", dataType = "String")
    @Size(max=14)
    private String thereExpressNum;

}
