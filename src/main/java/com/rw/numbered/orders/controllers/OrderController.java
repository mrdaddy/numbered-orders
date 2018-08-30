package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.request.OrderingInformation;
import com.rw.numbered.orders.dto.order.PreOrder;
import com.rw.numbered.orders.dto.request.TripInformation;
import com.rw.numbered.orders.service.OrderService;
import com.rw.numbered.orders.validators.TripInformationValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="orders", description="Сервис базовых операций с заказами пользователя", tags = "Основные операции с заказами пользователя", basePath="/orders")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders")
public class OrderController extends BaseController{

    @Autowired
    OrderService orderService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new TripInformationValidator());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/preorder")
    @ApiOperation(value = "Получение бизнес-правил, необходимых для выполнения при оформлении и оплате заказа")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public PreOrder preOffer(@ApiParam TripInformation tripInformation) {
        return orderService.getPreOrderInfo(tripInformation);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Создание нового заказа")
    @ResponseStatus( HttpStatus.CREATED )
    public Order createOrder(@RequestBody @ApiParam OrderingInformation orderingInformation) {
        return orderService.createOrder(orderingInformation);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{orderId}")
    @ApiOperation(value = "Удаление неоплаченного заказа из корзины с аннулированием в АСУ Экспресс")
    @ResponseStatus( HttpStatus.ACCEPTED)
    public void deleteOrder(@PathVariable("orderId") @ApiParam(value="Уникальный идентификатор записи заказа", example = "1") long orderId) {
        orderService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    @ApiOperation(value = "Получение информации о заказе пользователя")
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
    @ApiOperation(value = "Получение информации о заказах пользователя")
    @ResponseStatus( HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",
                    responseHeaders = {
                            @ResponseHeader(name = "ETag", response = String.class, description = "Хеш для кэширования")}),
            @ApiResponse(code = 304, message = "Not Modified")
    })
    public List<Order> getOrders(@RequestParam @ApiParam(value="Фильтр для получения списка заказов пользователя. Значение: пусто - все заказы, upcoming - заказы с предстоящими поездками, past - заказы с прошедшими поездками, returned - возвращённые и частично возвращённые заказы", example = "past", defaultValue = "upcoming", allowableValues = "upcoming, past, returned") String filter, @RequestHeader(name="IF-NONE-MATCH", required = false) @ApiParam(name="IF-NONE-MATCH", value = "ETag из предыдущего закэшированного запроса") String inm) {
        return orderService.getOrders(filter);
    }

}
