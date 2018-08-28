package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.dto.ErrorMessage;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.trip.OrderingInformation;
import com.rw.numbered.orders.service.RegistrationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value="orders/reg", description="Сервис операций, связанных с электронной регистрацией с заказами пользователя", tags = "Операции с заказами и ЭПД, связанные с электронной регистрацией", basePath="/orders/reg")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders/reg")

@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessage.class, responseContainer = "List")
})
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.PUT, path = "/{orderId}")
    @ApiOperation(value = "Изменение статуса электронной регистрации заказа")
    @ResponseStatus( HttpStatus.ACCEPTED )
    public Order changeOrderRegStatus(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId, @RequestParam @ApiParam(example = "true") boolean isRegistered) {
        return registrationService.changeTicketRegisterStatus(orderId, isRegistered);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{orderId}/tickets/{ticketId}")
    @ApiOperation(value = "Изменение статуса электронной регистрации ЭПД")
    @ResponseStatus( HttpStatus.ACCEPTED )
    public Order changeTicketRegStatus(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId, @PathVariable(value = "ticketId", required = false) @ApiParam(example = "1", value = "Уникальный идентификатор записи ЭПД", required = true) long ticketId, @RequestParam @ApiParam(example = "true") boolean isRegistered) {
        return registrationService.changeOrderRegisterStatus(orderId, ticketId, isRegistered);
    }

}
