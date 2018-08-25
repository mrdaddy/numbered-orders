package com.rw.numbered.orders.dto.order;

import com.rw.numbered.orders.dto.ticket.Ticket;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    public enum ORDER_STATUS {B, I, P}

    @ApiModelProperty(example = "1", required = true, value = "Уникальный идентификатор записи", dataType = "int")
    private long id;

    @ApiModelProperty(example = "74835926988082", required = true, value = "Номер корзины, куда добавлен заказ, при создании нового заказа необходимо передать в функцию формирования заказа", dataType = "String")
    private String basket;

    @ApiModelProperty(example = "74835926988083", required = true, value = "Номер заказа из системы Экспресс", dataType = "String")
    private String expressNo;

    @ApiModelProperty(example = "B", required = true, value = "Статус заказа B - куплен в АСУ Экспресс, I - в оплате, P - оплачен", dataType = "String")
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

    @ApiModelProperty(required = true, value = "Информация о вагоне", dataType = "String")
    private OrderCarriage orderCarriage;

    private String tripClass;
    private String addSign;
    private String timeDesc;
    private String trainType;
    private boolean isGlobalPrice;

    private OrderRegistration orderRegistration;

    private boolean isReturnAllowed;

    private Date paymentStartTime;
    private Date paymentEndTime;
    private double cost;
    private String currencyCode;
    private double costEuro;
    private String paymentSystem;
    private String paymentNo;

    private List<Ticket> tickets;
}
