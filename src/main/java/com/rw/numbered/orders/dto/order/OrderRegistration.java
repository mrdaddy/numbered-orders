package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Информация об электронной регистрации из заказа")
public class OrderRegistration {
    @ApiModelProperty(required = true, example = "true", value = "Признак, указывающий, разрешена ли электронная регистрация на поезд", dataType = "boolean")
    private boolean isAllowed;

    @ApiModelProperty(required = true, example = "true", value = "Признак, указывающий, необходимо ли выполнение электронной регистрации при оплате.", dataType = "boolean")
    private boolean isNeeded;

    @ApiModelProperty(required = true, example = "true", value = "Статус электронной регистрации (код) заказа. Значения: " +
            "0 – не выполнена, " +
            "1 – выполнена, " +
            "3 – регистрация невозможна, " +
            "4 – выполнена частично, ", dataType = "int")
    private int status;

    @ApiModelProperty(required = false, value = "Дата и время изменения статуса электронной регистрации заказа", dataType = "Datetime")
    private Date regTime;

    @ApiModelProperty(required = false, value = "Дата и время, после которого изменить статус электронной регистрации невозможно", dataType = "Datetime")
    private Date stopTime;

    @ApiModelProperty(required = false, example = "false", value = "Признак, указывающий, оборудован ли поезд АСКПП", dataType = "boolean")
    private boolean isWithASMBP;
}
