package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.security.User;
import com.rw.numbered.orders.service.RegistrationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value="orders/reg", description="Сервис операций, связанных с электронной регистрацией с заказами пользователя", tags = "Операции с заказами и ЭПД, связанные с электронной регистрацией", basePath="/orders/reg")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders/reg")
@PreAuthorize("hasRole('U')")

public class RegistrationController extends BaseController {
    @Autowired
    RegistrationService registrationService;

    @RequestMapping(method = RequestMethod.PUT, path = "/{orderId}")
    @ApiOperation(value = "Изменение статуса электронной регистрации заказа", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.ACCEPTED )
    public Order changeOrderRegStatus(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId,
                                      @RequestParam @ApiParam(example = "true") boolean isRegistered,
                                      @RequestAttribute(value = "user", required = false) @ApiIgnore User user) {
        return registrationService.changeTicketRegisterStatus(orderId, isRegistered, user);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{orderId}/tickets/{ticketId}")
    @ApiOperation(value = "Изменение статуса электронной регистрации ЭПД", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.ACCEPTED )
    public Order changeTicketRegStatus(@PathVariable(value = "orderId") @ApiParam(example = "1", value = "Уникальный идентификатор записи заказа", required = true) long orderId,
                                       @PathVariable(value = "ticketId", required = false) @ApiParam(example = "1", value = "Уникальный идентификатор записи ЭПД", required = true) long ticketId,
                                       @RequestParam @ApiParam(example = "true") boolean isRegistered,
                                       @RequestAttribute(value = "user", required = false) @ApiIgnore User user) {
        return registrationService.changeOrderRegisterStatus(orderId, ticketId, isRegistered, user);
    }

}
