package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(description = "Информация об отправлении пассажира")
public class DepartureInfo {
    @ApiModelProperty(example = "201Б", required = true, value = "Номер поезда отправления", dataType = "String")
    private String train;

    @ApiModelProperty(example = "2018-12-02", required = true, value = "Дата отправления", dataType = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ApiModelProperty(example = "14:02", required = true, value = "Время отправления (местное)", dataType = "String")
    private String time;

    @ApiModelProperty(example = "МИНСК-ПАССАЖИРСКИЙ", required = true, value = "Наименование станции отправления", dataType = "String")
    private String stationName;

    @ApiModelProperty(example = "2100182", required = true, value = "Код станции отправления", dataType = "String")
    private String stationCode;

    @ApiModelProperty(example = "2018-12-02", required = true, value = "Дата отправления поезда с начальной станции", dataType = "Date")
    private Date startingDate;

    @ApiModelProperty(example = "2018-12-02", required = true, value = "Реальные дата и время отправления поезда (время сервера)", dataType = "Datetime")
    private Date realDatetime;
}
