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

    @ApiModelProperty(example = "2К", required = false, value = "Класс обслуживания - rjl", dataType = "String")
    private String serviceClassCode;

    @ApiModelProperty(example = "2/3", required = false, value = "Класс обслуживания по международной классификации", dataType = "String")
    private String serviceClassIntCode;

    @ApiModelProperty(example = "БЧ", required = false, value = "Принадлежность вагона", dataType = "String")
    private String owner;

    @ApiModelProperty(example = "БЧ", required = false, value = "Перевозчик", dataType = "String")
    private String carrier;

    @ApiModelProperty(example = "true", required = false, value = "Признак, указывающий, имеет ли вагон признак М/Ж", dataType = "boolean")
    private boolean genderSign;

    @ApiModelProperty(example = "У1", required = false, value = "Дополнительные признаки вагона (признаки у вагона повышенной комфортности)", dataType = "String")
    private String addSign;
}
