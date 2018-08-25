package com.rw.numbered.orders.dto.trip;

import com.rw.numbered.orders.dto.passenger.Passenger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "Данные для создания заказа о поездке и пассажирах")
public class TripInformation {
    @ApiModelProperty(example = "74835926988082", required = false, value = "Идентификатор корзины, куда добавить заказ (если первый заказ заполнять не нужно)", dataType = "String")
    private String basket;

    @ApiModelProperty(example = "001А", required = true, value = "Номер поезда", dataType = "String")
    @NotNull @Size(min=4,max=5)
    private String train;

    @ApiModelProperty(example = "2018-08-24", required = true, value = "Дата отправления со станции пассажира", dataType = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    @ApiModelProperty(example = "22:11", required = true, value = "Время отправления со станции пассажира", dataType = "Time")
    private String departureTime;

    @ApiModelProperty(example = "ВИТЕБСК-ПАССАЖИРСКИЙ", required = true, value = "Наименование станция отправления на выбранном языке пользователя", dataType = "String")
    @NotNull @Size(min=2,max=64)
    private String depStationName;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции отправления", dataType = "String")
    @NotNull @Size(min=7,max=8)
    private String depStationCode;

    @ApiModelProperty(example = "ВИТЕБСК-ПАССАЖИРСКИЙ", required = true, value = "Наименование станция назначения на языке пользователя", dataType = "String")
    @NotNull @Size(min=2,max=64)
    private String arrStationName;

    @ApiModelProperty(example = "2100276", required = true, value = "Код станции назначения", dataType = "String")
    @NotNull @Size(min=7,max=8)
    private String arrStationCode;

    @ApiModelProperty(example = "скорый фирменный", required = true, value = "Характеристики поезда (через пробел) на языке пользователя", dataType = "String")
    @NotNull @Size(max=64)
    private String trainType;

    @NotNull @ApiModelProperty(example = "false", required = true, value = "Флаг, указывающий, что продажа на данный поезд производится по глобальным ценам", dataType = "boolean")
    private boolean isGlobalPrice;

    @NotNull @ApiModelProperty(example = "true", required = true, value = "Флаг, указывающий, нужно ли выполнить электронную регистрацию для этого заказа", dataType = "boolean")
    private boolean isRegistrationNeeded;

    @NotNull @ApiModelProperty(required = true)
    private TripCarriage tripCarriage;

    @NotNull @ApiModelProperty(required = false)
    private TripSeatDetail tripSeatDetail;

    @NotNull @ApiModelProperty(required = true)
    private List<TripPassenger> passengers;

}
