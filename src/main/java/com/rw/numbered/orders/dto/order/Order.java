package com.rw.numbered.orders.dto.order;

import com.rw.numbered.orders.dto.request.TripInformation;
import com.rw.numbered.orders.dto.ticket.Ticket;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "Информация о заказе пользователя")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    public enum ORDER_STATUS {B, I, P, Y, R}
    public enum DIRECTION {THERE, BACK}

    @ApiModelProperty(example = "1", required = true, value = "Уникальный идентификатор записи заказа", dataType = "long")
    private long id;

    @ApiModelProperty(example = "74835926988082", required = true, value = "Номер корзины, куда добавлен заказ, при создании нового заказа необходимо передать в функцию формирования заказа", dataType = "String")
    private String basketId;

    @ApiModelProperty(example = "74835926988083", required = true, value = "Номер заказа в системе Экспресс", dataType = "String")
    private String expressNum;

    @ApiModelProperty(example = "THERE", required = false, value = "Направление (необходимо при оформлении туда-обратно). Значения: THERE - `Туда`, BACK - `Туда`", dataType = "String")
    private TripInformation.DIRECTION directionType;

    @ApiModelProperty(example = "74835926988083", required = false, value = "Номер заказа в системе Экспресс, купленного как `Туда` при получении информации о заказе, купленном как `Обратно`", dataType = "String")
    private String expressThereNum;

    @ApiModelProperty(example = "B", required = true, value = "Статус заказа. Значения: B - куплен в АСУ Экспресс, I - в оплате, P - оплачен, R - возвращён, Y - частично возвращён", dataType = "String")
    private ORDER_STATUS status;

    @ApiModelProperty(required = true, value = "Время создания заказа в СППД", dataType = "Datetime")
    private Date createTime;

    @ApiModelProperty(required = true, value = "Время последнего обновления заказа в СППД", dataType = "Datetime")
    private Date updateTime;

    @ApiModelProperty(required = true, value = "Время оплаты заказа в АСУ Экспресс", dataType = "Datetime")
    private Date orderingTime;

    private DepartureInfo departureInfo;
    private ArrivalInfo arrivalInfo;

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

    @ApiModelProperty(required = false, example = "Время отправления местное", value = "Описатель времени на языке пользователя в момент создания заказа", dataType = "String")
    private String timeDesc;

    @ApiModelProperty(required = false, example = "СК СКР ФИРМ", value = "Тип поезда (скорый, скоростной, фирменный) через пробел на языке пользователя в момент создания заказа", dataType = "String")
    private String trainType;


    @ApiModelProperty(required = true, example = "false", value = "Признак, указывающий, является ли заказ заказом, оформленным по “глобальным ценам”", dataType = "boolean")
    private boolean isGlobalPrice;

    private OrderRegistration orderRegistration;

    @ApiModelProperty(required = true, example = "true", value = "Признак, указывающий, разрешен ли возврат по заказу", dataType = "boolean")
    private boolean isReturnAllowed;

    @ApiModelProperty(required = true, example = "23.45", value = "Стоимость заказа", dataType = "double")
    private double cost;

    @ApiModelProperty(required = true, example = "BYN", value = "Код валюты заказа - всегда BYN", dataType = "String")
    private String currencyCode;

    @ApiModelProperty(required = false, example = "23.45", value = "Стоимость заказа в евро. Используется для оформления проездных документов по “глобальным ценам”", dataType = "double")
    private double costEuro;

    OrderPaymentDetail orderPaymentDetail;

    private List<Ticket> tickets;

    @ApiModelProperty(required = false, value = "JWT токен для авторизации. Заполняется в случае вызова функции покупки билета без авторизации по email")
    private String jwtToken;
}
