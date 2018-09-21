package com.rw.numbered.orders.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@ApiModel(description = "Информация о выбранных местах")
@NoArgsConstructor
@AllArgsConstructor
public class TripSeatDetail {
    public enum PART_TYPES {ONE_SECTION, ONE_COUPE}
    public enum SEX_TYPES {MALE, FEMALE, MIXED}

    @ApiModelProperty(example = "1", required = false, value = "Номер первого места интервала", dataType = "int")
    @Max(999)
    private int numFrom;

    @ApiModelProperty(example = "2", required = false, value = "Номер последнего места интервала", dataType = "int")
    @Max(999)
    private int numTo;

    @ApiModelProperty(example = "1", required = false, value = "Предполагаемое количество нижних мест", dataType = "int")
    @Max(2)
    private int bottomCount;

    @ApiModelProperty(example = "1", required = false, value = "Предполагаемое количество верхних мест", dataType = "int")
    @Max(4)
    private int topCount;

    @ApiModelProperty(example = "MALE", required = false, value = "Тип купэ для М/Ж вагонов: MALE - мужское, FEMALE - женское, MIXED - смешанное", dataType = "String")
    private SEX_TYPES compartmentType;

    @ApiModelProperty(example = "ONE_SECTION", required = false, value = "Признак объединения мест в одно купэ или отсек: ONE_SECTION - мужское, ONE_COUPE - женское", dataType = "String")
    private PART_TYPES partType;

    @ApiModelProperty(example = "true", required = false, value = "Признак, включено ли в стоимость постельное бельё", dataType = "boolean")
    private boolean isIncludedBedding;
}
