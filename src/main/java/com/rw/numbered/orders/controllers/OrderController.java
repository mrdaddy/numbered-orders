package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.dto.ErrorMessage;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.service.OrderService;
import com.rw.numbered.orders.validators.OrderNewValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value="orders", description="Сервис работы с заказами пользователя", tags = "Проверка аттрибутов заказа", basePath="/orders")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders")

public class OrderController {

    @Autowired
    OrderService orderService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new OrderNewValidator());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Оформление нового заказа")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Order.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorMessage.class)
    })
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestBody @ApiParam  Order order) {
       // List<ErrorMessage> orderNewValidator.validate("", order);
       // if (errors.hasErrors()) {
          //  return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
       // }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private String createErrorString(Errors errors) {
        return errors.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.joining(","));
    }
}
