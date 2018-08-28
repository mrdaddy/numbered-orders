package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.dto.ErrorMessage;
import com.rw.numbered.orders.dto.ReturnInfo;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.service.ReturnService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value="orders/return", description="Сервис операций, связанных с возвратом заказов и билетов", tags = "Операции с заказами и ЭПД, связанные с возвратом", basePath="/orders/return")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders/return")

@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessage.class, responseContainer = "List")
})
public class ReturnController {
    @Autowired
    ReturnService returnService;

    @RequestMapping(method = RequestMethod.GET, path = "/check/{orderId}")
    @ApiOperation(value = "Проверка возможности и получение суммы возврата заказа")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public ReturnInfo checkReturnOrder(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId) {
        return returnService.checkReturnOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/check/{orderId}/tickets/{ticketId}")
    @ApiOperation(value = "Проверка возможности и получение суммы возврата ЭПД")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public ReturnInfo checkReturnOrder(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId, @PathVariable(value = "ticketId") @ApiParam(example = "1", value = "Уникальный идентификатор записи билета", required = true) long ticketId) {
        return returnService.checkReturnTicket(orderId, ticketId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{orderId}")
    @ApiOperation(value = "Возврат заказа")
    @ResponseStatus( HttpStatus.ACCEPTED )
    public Order returnOrder(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId) {
        return returnService.returnOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{orderId}/tickets/{ticketId}")
    @ApiOperation(value = "Возврат билета")
    @ResponseStatus( HttpStatus.ACCEPTED )
    public Order returnTicket(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId, @PathVariable(value = "ticketId") @ApiParam(example = "1", value = "Уникальный идентификатор записи билета", required = true) long ticketId) {
        return returnService.returnTicket(orderId, ticketId);
    }
}
