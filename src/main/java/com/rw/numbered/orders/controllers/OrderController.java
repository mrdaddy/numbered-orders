package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.service.OrderValidationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="order-validation", description="Сервис проверки аттрибутов заказа", tags = "Проверка аттрибутов заказа", basePath="/order/validate")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)

public class OrderController {

    @Autowired
    OrderValidationService orderValidationService;


}
