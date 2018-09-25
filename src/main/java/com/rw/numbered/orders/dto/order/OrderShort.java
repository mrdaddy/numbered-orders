package com.rw.numbered.orders.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(description = "Краткая информация о заказе пользователя")
@NoArgsConstructor
@AllArgsConstructor
public class OrderShort {
    @ApiModelProperty(example = "1", required = true, value = "Уникальный идентификатор записи заказа", dataType = "long")
    private long id;

    @ApiModelProperty(example = "74835926988082", required = true, value = "Номер корзины, куда добавлен заказ, при создании нового заказа необходимо передать в функцию формирования заказа", dataType = "String")
    private String basketId;

    @ApiModelProperty(example = "74835926988083", required = true, value = "Номер заказа в системе Экспресс", dataType = "String")
    private String expressNum;

    @ApiModelProperty(required = true)
    private DepartureInfo departureInfo;

    @ApiModelProperty(required = true)
    private ArrivalInfo arrivalInfo;

    @ApiModelProperty(example = "TO", required = false, value = "Направление (необходимо при оформлении туда-обратно). Значения: THERE - `Туда`, BACK - `Туда`", dataType = "String")
    private Order.DIRECTION directionType;

    @ApiModelProperty(example = "74835926988083", required = false, value = "Номер заказа в системе Экспресс, купленного как `Туда` при получении информации о заказе, купленном как `Обратно`", dataType = "String")
    private String expressThereNum;

    @ApiModelProperty(example = "B", required = true, value = "Статус заказа. Значения: B - куплен в АСУ Экспресс, I - в оплате, P - оплачен, R - возвращён, Y - частично возвращён", dataType = "String")
    private Order.ORDER_STATUS status;

    @ApiModelProperty(required = true, value = "Время создания заказа в СППД", dataType = "Datetime")
    private Date createTime;

    @ApiModelProperty(required = true, example = "23.45", value = "Стоимость заказа", dataType = "double")
    private double cost;

    @ApiModelProperty(required = true, example = "23.45", value = "Возвращённая сумма заказа (если заказ возвращён или частично возвращён)", dataType = "double")
    private double returnAmount;

    @ApiModelProperty(required = true, example = "BYN", value = "Код валюты заказа - всегда BYN", dataType = "String")
    private String currencyCode;

    @ApiModelProperty(required = false, example = "Время отправления местное", value = "Описатель времени на языке пользователя в момент создания заказа", dataType = "String")
    private String timeDesc;

    private OrderRegistration orderRegistration;

    @ApiModelProperty(required = true, example = "true", value = "Признак, указывающий, разрешен ли возврат по заказу", dataType = "boolean")
    private boolean isReturnAllowed;

    @ApiModelProperty(example = "1", required = true, value = "Количество мест", dataType = "int")
    private int seatCount;
}
