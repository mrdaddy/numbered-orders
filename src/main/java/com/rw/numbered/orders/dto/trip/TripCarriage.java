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

    @ApiModelProperty(example = "1Б", required = false, value = "Код класса обслуживания", dataType = "String")
    @Size(max = 3)
    private String serviceClassCode;

    @ApiModelProperty(example = "1/1", required = false, value = "Код класса обслуживания по международной классификации", dataType = "String")
    @Size(max = 512)
    private String serviceClassIntCode;

    @ApiModelProperty(example = "false", required = false, value = "В данном вагоне места продаются только по 2", dataType = "boolean")
    private String only2m;

}
