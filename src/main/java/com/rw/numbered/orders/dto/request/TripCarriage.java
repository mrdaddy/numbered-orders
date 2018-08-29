package com.rw.numbered.orders.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(description = "Информация о выбранном вагоне")
public class TripCarriage {
    @ApiModelProperty(example = "1", required = true, value = "Номер выбранного вагона", dataType = "int")
    @NotNull @Max(10000)
    private int no;

    @ApiModelProperty(example = "К", required = true, value = "Тип вагона - код", dataType = "String")
    @NotNull
    @Size(min=1, max = 1)
    private String typeCode;

    @ApiModelProperty(example = "1Б", required = false, value = "Класс обслуживания - код", dataType = "String")
    @Size(max = 3)
    private String serviceClassCode;

    @ApiModelProperty(example = "1/1", required = false, value = "Класс обслуживания по международной классификации - код", dataType = "String")
    @Size(max = 3)
    private String serviceClassIntCode;

    @ApiModelProperty(example = "false", required = false, value = "В данном вагоне места продаются только по 2", dataType = "boolean")
    private String only2m;

    @ApiModelProperty(example = "У0", required = false, value = "Дополнительные признаки вагона", dataType = "String")
    @NotNull
    @Size(max = 16)
    private String addSigns;
}
