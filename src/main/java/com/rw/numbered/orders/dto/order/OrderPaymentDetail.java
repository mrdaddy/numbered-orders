package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Информация об оплате заказа")
public class OrderPaymentDetail {
    @ApiModelProperty(required = false, example = "ERIP", value = "Платежная система, через которую был оплачен или оплачивается заказ. " +
            "Значения: " +
            "BB_IB – оплата через систему “Интернет-банкинг” ОАО “АСБ Беларусбанк”, " +
            "BB_IA - оплата через систему АПК ПР, " +
            "ERIP – оплата через платежную систему “Расчет”", dataType = "String")
    private String paymentSystem;

    @ApiModelProperty(required = false, example = "23456", value = "Номер платежа в платежной системе (используется в ERIP)", dataType = "String")
    private String paymentNum;

    @ApiModelProperty(required = false, value = "Дата и время начала оплаты", dataType = "Datetime")
    private Date paymentStartTime;

    @ApiModelProperty(required = false, value = "Дата и время, когда заказ должен быть оплачен", dataType = "Datetime")
    private Date paymentEndTime;
}
