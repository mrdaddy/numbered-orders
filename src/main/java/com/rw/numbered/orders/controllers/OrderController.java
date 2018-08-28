package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.dto.ErrorMessage;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.trip.OrderingInformation;
import com.rw.numbered.orders.dto.trip.PreOrder;
import com.rw.numbered.orders.dto.trip.TripInformation;
import com.rw.numbered.orders.service.OrderService;
import com.rw.numbered.orders.validators.TripInformationValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value="orders", description="Сервис базовых операций с заказами пользователя", tags = "Основные операции с заказами пользователя", basePath="/orders")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders")

@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessage.class, responseContainer = "List")
})
public class OrderController {

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
    public PreOrder preOffer(@Valid @ApiParam TripInformation tripInformation) {
        return orderService.getPreOrderInfo(tripInformation);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Оформление нового заказа")
    @ResponseStatus( HttpStatus.CREATED )
    public Order createOrder(@Valid @RequestBody @ApiParam OrderingInformation orderingInformation) {
        return orderService.createOrder(orderingInformation);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{orderId}")
    @ApiOperation(value = "Удаление неоплаченного заказа из корзины с аннулированием в АСУ Экспресс")
    @ResponseStatus( HttpStatus.ACCEPTED)
    public void deleteOrder(@Valid @PathVariable("orderId") @NotNull @ApiParam(value="Уникальный идентификатор записи заказа", example = "1") long orderId) {
        orderService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    @ApiOperation(value = "Получение информации о заказе пользователя")
    @ResponseStatus( HttpStatus.OK)
    public Order getOrder(@Valid @PathVariable("orderId") @NotNull @ApiParam(value="Уникальный идентификатор записи заказа", example = "1") long orderId, @RequestParam @ApiParam(value="Признак, указывающий, заполнять ли блоки TariffDetail и TicketReturnDetail", example = "true") boolean isFullData) {
        return orderService.getOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Получение информации о заказах пользователя")
    @ResponseStatus( HttpStatus.OK)
    public List<Order> getOrders(@RequestParam @ApiParam(value="Фильтр для получения списка заказов пользователя. Значение: пусто - все заказы, upcoming - заказы с предстоящими поездками, past - заказы с прошедшими поездками, returned - возвращённые и частично возвращённые заказы", example = "past", defaultValue = "upcoming", allowableValues = "upcoming, past, returned") String filter) {
        return orderService.getOrders(filter);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidRequest(MethodArgumentNotValidException e, WebRequest request) {
        List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
        if(e.getBindingResult().hasErrors()) {
            for(ObjectError oe: e.getBindingResult().getAllErrors()) {
                ErrorMessage errorMessage = new ErrorMessage(oe.getCodes()[0],oe.getDefaultMessage());
                errors.add(errorMessage);
            }
        }
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }}
