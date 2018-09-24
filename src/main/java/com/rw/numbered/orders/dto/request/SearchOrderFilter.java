package com.rw.numbered.orders.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@ApiModel(description = "Фильтр для поиска заказав")
@NoArgsConstructor
@AllArgsConstructor
public class SearchOrderFilter {
    public enum ORDER_TYPE {UPCOMING, PAST, RETURNED}
    @ApiParam(value="Фильтр для получения списка заказов пользователя по типу заказа. Значение: пусто - все заказы, upcoming - заказы с предстоящими поездками, past - заказы с прошедшими поездками, returned - возвращённые и частично возвращённые заказы", example = "past", defaultValue = "upcoming")
    private ORDER_TYPE orderType;

    @ApiParam(value="Фильтр для получения списка заказов с датой отправления больше либо равно указанной", example = "2018-11-12") @DateTimeFormat(pattern="yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private Date departureDateMin;

    @ApiParam(value="Фильтр для получения списка заказов с датой отправления меньше либо равно указанной", example = "2018-11-12") @DateTimeFormat(pattern="yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private Date departureDateMax;

    @ApiParam(value="Фильтр для получения списка заказов с указанным поездом", example = "2100276")
    private @Size( max=6) String train;

    @ApiParam(value="Фильтр для получения списка заказов с указанной станцией отправления", example = "2100276")
    private @Size(max=8) String departureStationCode;

    @ApiParam(value="Фильтр для получения списка заказов с указанной станцией прибытия", example = "2100276")
    private @Size(max=8) String  arrivalStationCode;
}
