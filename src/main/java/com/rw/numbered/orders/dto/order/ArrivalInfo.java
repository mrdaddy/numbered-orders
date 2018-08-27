package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(description = "Информация о прибытии пассажира")
public class ArrivalInfo {
    @ApiModelProperty(example = "201Б", required = true, value = "Номер поезда прибытия", dataType = "String")
    private String train;

    @ApiModelProperty(example = "2018-12-02", required = true, value = "Дата прибытия", dataType = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ApiModelProperty(example = "14:02", required = true, value = "Время прибытия (местное)", dataType = "String")
    private String time;

    @ApiModelProperty(example = "МИНСК-ПАССАЖИРСКИЙ", required = true, value = "Наименование станции прибытия на языке пользователя в момент создания заказа", dataType = "String")
    private String stationName;

    @ApiModelProperty(example = "2100182", required = true, value = "Код станции прибытия", dataType = "String")
    private String stationCode;
}
