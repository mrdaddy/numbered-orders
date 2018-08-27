package com.rw.numbered.orders.dto.ticket;

import com.rw.numbered.orders.dto.passenger.Passenger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "Информация о билете")
public class Ticket {
    public enum TICKET_STATUS {B, I, P, R}

    @ApiModelProperty(example = "1", required = true, value = "Уникальный идентификатор записи билета", dataType = "long")
    private long id;

    @ApiModelProperty(example = "1", required = true, value = "Уникальный идентификатор записи заказа", dataType = "long")
    private long orderId;

    @ApiModelProperty(example = "74835926988083", required = true, value = "Номер билета в системе Экспресс", dataType = "String")
    private String expressNo;

    @ApiModelProperty(example = "1", required = true, value = "Номер билета в рамках заказа", dataType = "int")
    private int ticketNo;

    @ApiModelProperty(example = "B", required = true, value = "Статус билета. Значения: B - куплен в АСУ Экспресс, I - в оплате, P - оплачен, R - возвращён", dataType = "String")
    private TICKET_STATUS status;


    @ApiModelProperty(example = "false", required = true, value = "Признак, выдана ли ведомость проездных документов по поезду в момент покупки", dataType = "boolean")
    private boolean isTListIssued;

    @ApiModelProperty(example = "false", required = true, value = "Признак, сгенерирован ли QR код для билета", dataType = "boolean")
    private boolean hasQrCode;

    @ApiModelProperty(required = true, example = "23.45", value = "Стоимость билета", dataType = "double")
    private double cost;

    @ApiModelProperty(required = false, example = "23.45", value = "Стоимость билета в евро. Используется для оформления проездных документов по “глобальным ценам”", dataType = "double")
    private double costEuro;

    @ApiModelProperty(required = true, example = "BYN", value = "Код валюты билета - всегда BYN", dataType = "String")
    private String currencyCode;

    public enum TARIFF_TYPE {FULL, CHILD, FREE};
    @ApiModelProperty(example = "FULL", required = false, value = "Тип тарифа. Значения : FULL - полный, CHILD - детский, FREE - безденежный", dataType = "String")
    private TARIFF_TYPE tariffType;

    private TariffDetail tariffDetail;

    @ApiModelProperty(example = "1", required = true, value = "Количество пассажиров в билете", dataType = "int")
    private int passengerCount;

    @ApiModelProperty(required = true)
    private TicketSeatDetail ticketSeatDetail;

    @ApiModelProperty(required = true, example = "true", value = "Статус электронной регистрации (код) билета. Значения: " +
            "0 – не выполнена, " +
            "1 – выполнена, " +
            "2 – регистрация невозможна, выдан бумажный документ, " +
            "3 – регистрация невозможна, " +
            "5 – Невозможна, выполнен возврат в кассе, " +
            "-1 – Неопределенный", dataType = "int")
    private int registrationStatus;
    @ApiModelProperty(required = false, value = "Дата и время изменения статуса электронной регистрации билета", dataType = "Datetime")
    private Date registrationTime;

    private TicketReturnDetail ticketReturn;

    @ApiModelProperty(required = true, value = "Список пассажиров билета")
    private List<Passenger> passengers;
}
