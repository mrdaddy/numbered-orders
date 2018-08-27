package com.rw.numbered.orders.dto.ticket;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Дополнительная информация о возврате, заполняется только в случае, если есть фильтр TicketReturnDetail")
public class TicketReturnDetail {
    @ApiModelProperty(required = false, example = "05:21", value = "Время до отправления поезда в момент возврата заказа", dataType = "String")
    private String timeBeforeDeparture;

    @ApiModelProperty(required = false, example = "42835701336594", value = "Номер электронной квитанции разных сборов", dataType = "String")
    private String expressNo;

    @ApiModelProperty(required = false, example = "10.23", value = "Возвращенная сумма по проездному документу", dataType = "double")
    private double amount;

    @ApiModelProperty(required = false, example = "10.23", value = "Возвращенная сумма билета", dataType = "double")
    private double ticketTariff;

    @ApiModelProperty(required = false, example = "10.23", value = "Возвращенная сумма плацкарты", dataType = "double")
    private double reservedSeatTariff;

    @ApiModelProperty(required = false, example = "10.23", value = "Возвращенная сумма сервисного сбора", dataType = "double")
    private double serviceTariff;

    @ApiModelProperty(required = false, example = "10.23", value = "Возвращенная сумма страхового сбора", dataType = "double")
    private double insuranceTariff;

    @ApiModelProperty(required = false, example = "10.23", value = "Возвращенная сумма комиссионного сбора", dataType = "double")
    private double commissionFee;

    @ApiModelProperty(required = false, example = "10.23", value = "Комиссионный сбор за возврат", dataType = "double")
    private double commission;

    @ApiModelProperty(required = false, value = "Дата и время возврата проездного документа в АСУ “Экспресс-3”", dataType = "Datetime")
    private Date datetime;

    @ApiModelProperty(required = false, example = "true", value = "Признак, указывающий, как выполняется возврат (частичная отмена оплаты) в платежной системе: true - онлайн, как Ассист, false - через реестры, как Интернет-Банкинг", dataType = "boolean")
    private boolean online;

    @ApiModelProperty(required = false, example = "false", value = "Признак, указывающий, выполнен ли онлайн возврат в платежной системе или нет", dataType = "boolean")
    private boolean inPaymentSystem;

}
