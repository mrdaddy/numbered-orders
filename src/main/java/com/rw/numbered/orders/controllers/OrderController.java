package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.ErrorMessage;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.trip.TripInformation;
import com.rw.numbered.orders.service.OrderService;
import com.rw.numbered.orders.validators.TripInformationValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value="orders", description="Сервис работы с заказами пользователя", tags = "Заказы пользователя", basePath="/orders")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders")

public class OrderController {

    @Autowired
    OrderService orderService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new TripInformationValidator());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Оформление нового заказа")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorMessage.class, responseContainer = "List")
    })
    @ResponseBody
    @ResponseStatus( HttpStatus.CREATED )
    public Order create(@Valid @RequestBody @ApiParam TripInformation tripInformation) {
        return orderService.createOrder(tripInformation);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<?> handleInvalidRequest(MethodArgumentNotValidException e, WebRequest request) {
        List<com.rw.numbered.orders.ErrorMessage> errors = new ArrayList<com.rw.numbered.orders.ErrorMessage>();
        if(e.getBindingResult().hasErrors()) {
            for(ObjectError oe: e.getBindingResult().getAllErrors()) {
                ErrorMessage errorMessage = new ErrorMessage(oe.getCodes()[0],oe.getDefaultMessage());
                errors.add(errorMessage);
            }
        }
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }}
