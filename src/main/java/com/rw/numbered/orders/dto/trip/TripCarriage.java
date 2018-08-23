package com.rw.numbered.orders.dto.trip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(description = "Информация о выбранном вагоне")
public class TripCarriage {
    @ApiModelProperty(example = "01", required = true, value = "Номер выбранного вагона", dataType = "int")
    @NotNull @Max(10000)
    private int no;

    @ApiModelProperty(example = "К", required = true, value = "Буква типа вагона", dataType = "String")
    @NotNull
    @Size(min=1, max = 1)
    private String typeCode;

    @ApiModelProperty(example = "Купейный", required = false, value = "Наименование типа вагона на языке пользователя", dataType = "String")
    @Size(max = 64)
    private String typeName;

    @ApiModelProperty(example = "4-х местные купе с местами для лежания. В вагоне до 40 мест", required = false, value = "Описание типа вагона на языке пользователя", dataType = "String")
    @Size(max = 512)
    private String typeDesc;

    @ApiModelProperty(example = "1Б", required = false, value = "Код класса обслуживания", dataType = "String")
    @Size(max = 3)
    private String serviceClassCode;

    @ApiModelProperty(example = "Бизнес-класс (вагон СВ с услугами)", required = false, value = "Описание класса обслуживания на языке пользователя", dataType = "String")
    @Size(max = 512)
    private String serviceClassDesc;

    @ApiModelProperty(example = "1/1", required = false, value = "Код класса обслуживания по международной классификации", dataType = "String")
    @Size(max = 512)
    private String serviceClassIntCode;

    @ApiModelProperty(example = "1-й класс (одноместное купе)", required = false, value = "Описание класса обслуживания по международной классификации", dataType = "String")
    @Size(max = 512)
    private String serviceClassIntDesc;

    @ApiModelProperty(example = "false", required = false, value = "В данном вагоне места продаются только по 2", dataType = "boolean")
    private String only2m;

}
