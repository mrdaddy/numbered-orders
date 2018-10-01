package com.rw.numbered.orders.controllers;

import by.iba.railway.eticket.xml.exception.BusinessSystemException;
import com.rw.numbered.orders.dto.order.OrderShort;
import com.rw.numbered.orders.dto.request.SearchOrderFilter;
import com.rw.numbered.orders.security.User;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.request.OrderingInformation;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

@RestController
@Api(value="orders", description="Сервис базовых операций с нумерованными заказами пользователя", tags = "Основные операции с заказами пользователя", basePath="/orders")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/${service.version}/numbered/orders")
public class OrderController extends BaseController{

    @Autowired
    OrderService orderService;

    @InitBinder("orderingInformation")
    protected void initOrderingInformationBinder(WebDataBinder binder) {
        binder.addValidators(new OrderingInformationValidator());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/auth")
    @ApiOperation(value = "Создание нового заказа авторизованным пользователем", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.CREATED )
    @PreAuthorize("hasRole('U')")
    public Order createOrderAuth(@RequestBody @ApiParam OrderingInformation orderingInformation,
                                 @RequestAttribute(value = "user", required = false) @ApiIgnore User user) {
        return orderService.createOrderAuth(orderingInformation, user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/notauth")
    @ApiOperation(value = "Создание нового заказа неавторизованным пользователем")
    @ResponseStatus( HttpStatus.CREATED)
    public Order createOrderNotAuth(@RequestBody @ApiParam(required = true) OrderingInformation orderingInformation,
                                    @RequestParam @ApiParam(required = true, example = "test@test.com", value = "Email пользователя") String email,
                                    @RequestParam @ApiParam(example = "+375295544333", value = "Телефон пользователя") String phone) {
        return orderService.createOrderNotAuth(orderingInformation, email, phone);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{orderId}")
    @ApiOperation(value = "Удаление неоплаченного заказа из корзины с аннулированием в АСУ Экспресс", authorizations = @Authorization("jwt-auth"))
    @ResponseStatus( HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('U')")
    public void deleteOrder(@PathVariable("orderId") @ApiParam(value="Уникальный идентификатор записи заказа", example = "1", required = true) long orderId,
                            @RequestAttribute(value = "user", required = false) @ApiIgnore User user) {
        orderService.deleteOrder(orderId, user);
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
    @PreAuthorize("hasRole('U') or hasRole('L')")
    public Order getOrder(@PathVariable("orderId") @ApiParam(value="Уникальный идентификатор записи заказа", example = "1", required = true) long orderId,
                          @RequestParam @ApiParam(value="Признак, указывающий, заполнять ли блоки TariffDetail и ReturnTariffDetail", example = "true", defaultValue = "false") boolean isFullData,
                          @RequestHeader(name="IF-NONE-MATCH", required = false) @ApiParam(name="IF-NONE-MATCH", value = "ETag из предыдущего закэшированного запроса") String inm,
                          @RequestAttribute(value = "user", required = false) @ApiIgnore User user) {
        return orderService.getOrder(orderId, user);
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
    @PreAuthorize("hasRole('U') or hasRole('L')")
    public List<OrderShort> getOrders(@ApiParam(name="searchOrderFilter", required = false) SearchOrderFilter searchOrderFilter,
                                      @RequestHeader(name="IF-NONE-MATCH", required = false) @ApiParam(name="IF-NONE-MATCH", value = "ETag из предыдущего закэшированного запроса") String inm,
                                      @RequestAttribute(value = "user", required = false) @ApiIgnore User user)  throws BusinessSystemException {
        return orderService.getOrders(searchOrderFilter, user);
    }

}
