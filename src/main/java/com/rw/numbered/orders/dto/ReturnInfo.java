package com.rw.numbered.orders.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnInfo {
    @ApiModelProperty(example = "10.21", required = true, value = "Сумма возврата на момент проверки", dataType = "double")
    private double amount;

    @ApiModelProperty(example = "ДАННУЮ ОПЕРАЦИЮ ВЫПОЛНЯТЬ ПОЗДНО", required = false, value = "Сообщение о причине невозможности возврата", dataType = "String")
    private String errorMessage;

    @ApiModelProperty(example = "true", required = true, value = "Признак, указывающий разрешён ли возврат", dataType = "boolean")
    private boolean isReturnAllowed;

}
