package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Информация о вагоне из заказа")
public class OrderCarriage {
    @ApiModelProperty(example = "1", required = true, value = "Номер вагона", dataType = "int")
    private int no;

    @ApiModelProperty(example = "К", required = true, value = "Тип вагона - код на языке пользователя в момент создания заказа", dataType = "String")
    private String typeCode;

    @ApiModelProperty(example = "Купейный", required = true, value = "Тип вагона - наименование на языке пользователя в момент создания заказа", dataType = "String")
    private String typeName;

    @ApiModelProperty(example = "4-х местные купе с местами для лежания. В вагоне до 40 мест", required = false, value = "Тип вагона - описание на языке пользователя в момент создания заказа", dataType = "String")
    private String typeDesc;

    @ApiModelProperty(example = "2К", required = false, value = "Класс обслуживания - rjl", dataType = "String")
    private String serviceClassCode;

    @ApiModelProperty(example = "Купейный (вагон без услуг)", required = false, value = "Класс обслуживания - описание на языке пользователя в момент создания заказа", dataType = "String")
    private String serviceClassDesc;

    @ApiModelProperty(example = "2/3", required = false, value = "Класс обслуживания по международной классификации", dataType = "String")
    private String serviceClassIntCode;

    @ApiModelProperty(example = "БЧ", required = false, value = "Принадлежность вагона", dataType = "String")
    private String owner;

    @ApiModelProperty(example = "БЧ", required = false, value = "Перевозчик", dataType = "String")
    private String carrier;

    @ApiModelProperty(example = "МУЖ", required = false, value = "Признак вагона М/Ж", dataType = "String")
    private String genderSign;

    @ApiModelProperty(example = "У1", required = false, value = "Дополнительные признаки вагона (признаки у вагона повышенной комфортности)", dataType = "String")
    private String addSign;
}
