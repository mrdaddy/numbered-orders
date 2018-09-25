package com.rw.numbered.orders.dto.order;

import com.rw.numbered.orders.dto.ticket.Ticket;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "Полная информация о заказе пользователя")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends OrderShort{
    public enum ORDER_STATUS {B, I, P, Y, R}
    public enum DIRECTION {TO, BACK}

    @ApiModelProperty(required = true, value = "Время последнего обновления заказа в СППД", dataType = "Datetime")
    private Date updateTime;

    @ApiModelProperty(required = true, value = "Время оплаты заказа в АСУ Экспресс", dataType = "Datetime")
    private Date orderingTime;

    @ApiModelProperty(required = true)
    private OrderSeatDetail orderSeatDetail;

    @ApiModelProperty(required = true)
    private OrderCarriage orderCarriage;

    @ApiModelProperty(required = false, example = "00", allowableValues = "00, 01, 02, 03, 04, 05, 06, 07", value = "Класс поездки (код). Используется для оформления проездных документов по “глобальным ценам”. " +
            "00 - 1-й класс (сидячий), " +
            "01 - 2-й класс (сидячий), " +
            "02 - 2-й класс (сидячий, пассажиры с животными), " +
            "03 - double, " +
            "04 - 2-й класс (спальный), " +
            "05 - single, " +
            "06 – double (VIP), " +
            "07 – single (VIP), ", dataType = "String")
    private String tripClass;

    @ApiModelProperty(required = false, example = "ТРЕБУЕТСЯ ПАСПОРТ.", value = "Дополнительные признаки из АСУ Экспресс (например, транзит через СНГ)", dataType = "String")
    private String addSign;

    @ApiModelProperty(required = false, example = "СК СКР ФИРМ", value = "Тип поезда (скорый, скоростной, фирменный) через пробел", dataType = "String")
    private String trainType;


    @ApiModelProperty(required = true, example = "false", value = "Признак, указывающий, является ли заказ заказом, оформленным по “глобальным ценам”", dataType = "boolean")
    private boolean isGlobalPrice;

    @ApiModelProperty(required = false, example = "23.45", value = "Стоимость заказа в евро. Используется для оформления проездных документов по “глобальным ценам”", dataType = "double")
    private double costEuro;

    OrderPaymentDetail orderPaymentDetail;

    private List<Ticket> tickets;

    @ApiModelProperty(required = false, value = "JWT токен для авторизации. Заполняется в случае вызова функции покупки билета без авторизации по email")
    private String jwtToken;
}
