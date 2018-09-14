package com.rw.numbered.orders.controllers;

import by.iba.railway.eticket.xml.exception.BusinessSystemException;
import com.rw.numbered.orders.security.User;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.request.OrderingInformation;
import com.rw.numbered.orders.dto.request.TripInformation;
import com.rw.numbered.orders.service.OrderService;
import com.rw.numbered.orders.validator.OrderingInformationValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(value="orders", description="Сервис базовых операций с нумерованными заказами пользователя", tags = "Основные операции с заказами пользователя", basePath="/orders")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders")
public class OrderController extends BaseController{

    @Autowired
    OrderService orderService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new OrderingInformationValidator());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Создание нового заказа авторизованным пользователем", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.CREATED )
    @PreAuthorize("hasRole('U')")
    public Order createOrderAuth(@RequestBody @ApiParam OrderingInformation orderingInformation) {
        return orderService.createOrderAuth(orderingInformation);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Создание нового заказа неавторизованным пользователем")
    @ResponseStatus( HttpStatus.CREATED)
    public Order createOrderNotAuth(@RequestBody @ApiParam(required = true) OrderingInformation orderingInformation, @RequestParam @ApiParam(required = true, example = "test@test.com", value = "Email пользователя") String email, @RequestParam @ApiParam(example = "+375295544333", value = "Телефон пользователя") String phone) {
        return orderService.createOrderNotAuth(orderingInformation, email, phone);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{orderId}")
    @ApiOperation(value = "Удаление неоплаченного заказа из корзины с аннулированием в АСУ Экспресс", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.ACCEPTED)
    public void deleteOrder(@PathVariable("orderId") @ApiParam(value="Уникальный идентификатор записи заказа", example = "1") long orderId) {
        orderService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    @ApiOperation(value = "Получение информации о заказе пользователя", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",
                    responseHeaders = {
                            @ResponseHeader(name = "ETag", response = String.class, description = "Хеш для кэширования")}),
            @ApiResponse(code = 304, message = "Not Modified")
    })
    public Order getOrder(@PathVariable("orderId") @ApiParam(value="Уникальный идентификатор записи заказа", example = "1") long orderId, @RequestParam @ApiParam(value="Признак, указывающий, заполнять ли блоки TariffDetail и TicketReturnDetail", example = "true") boolean isFullData, @RequestHeader(name="IF-NONE-MATCH", required = false) @ApiParam(name="IF-NONE-MATCH", value = "ETag из предыдущего закэшированного запроса") String inm) {
        return orderService.getOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Получение информации о заказах пользователя", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",
                    responseHeaders = {
                            @ResponseHeader(name = "ETag", response = String.class, description = "Хеш для кэширования")}),
            @ApiResponse(code = 304, message = "Not Modified")
    })
    public List<Order> getOrders(@RequestParam @ApiParam(value="Фильтр для получения списка заказов пользователя по типу заказа. Значение: пусто - все заказы, upcoming - заказы с предстоящими поездками, past - заказы с прошедшими поездками, returned - возвращённые и частично возвращённые заказы", example = "past", defaultValue = "upcoming", allowableValues = "upcoming, past, returned") String orderType,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с датой отправления больше либо равно указанной", example = "2018-11-12") @DateTimeFormat(pattern="yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) Date departureDateMin,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с датой отправления меньше либо равно указанной", example = "2018-11-22") @DateTimeFormat(pattern="yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE) Date departureDateMax,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с указанным поездом", example = "001А") String train,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с указанной станцией отправления", example = "2100276") String departureStationCode,
                                 @RequestParam(required = false) @ApiParam(value="Фильтр для получения списка заказов с указанной станцией прибытия", example = "2100276") String arrivalStationCode,
                                 @RequestHeader(name="IF-NONE-MATCH", required = false) @ApiParam(name="IF-NONE-MATCH", value = "ETag из предыдущего закэшированного запроса") String inm,
                                 @RequestAttribute(value = "user", required = false) User user) throws BusinessSystemException {
        return orderService.getOrders(orderType ,departureDateMin, departureDateMax, train, departureStationCode, arrivalStationCode, user);
    }

}
