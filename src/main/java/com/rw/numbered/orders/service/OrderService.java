package com.rw.numbered.orders.service;

import by.iba.railway.eticket.xml.exception.BusinessSystemException;
import by.iba.railway.eticket.xml.exception.XmlParserSystemException;
import by.iba.railway.eticket.xml.objs.response.eticket.BuyTicketResponse;
import com.rw.numbered.orders.dao.OrderDao;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.request.OrderingInformation;
import com.rw.numbered.orders.dto.request.SearchOrderFilter;
import com.rw.numbered.orders.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    UserService userService;

    @Autowired
    AddOrderService addOrderService;

    public Order createOrderAuth(@Valid OrderingInformation orderingInformation, @Valid User user) {
        try {
            BuyTicketResponse etInfo = addOrderService.buyTicket(orderingInformation, user);
        } catch (XmlParserSystemException e) {
            throw new RuntimeException(e);
        } catch (BusinessSystemException e) {
            throw new RuntimeException(e);
        }
        return new Order();
    }

    public Order createOrderNotAuth(@Valid OrderingInformation orderingInformation, @Valid @NotNull @Email @Size(max=64) String email, @Valid @Size(max=255) String phone) {
        User user = userService.createUser(email, phone);
        try {
            BuyTicketResponse etInfo = addOrderService.buyTicket(orderingInformation, user);
        } catch (XmlParserSystemException e) {
            throw new RuntimeException(e);
        } catch (BusinessSystemException e) {
            throw new RuntimeException(e);
        }
        return new Order();
    }

    public void deleteOrder(@Valid @Min(1) long orderId, @Valid User user) {

    }

    public Order getOrder(@Valid @Min(1) long orderId, @Valid User user) {
        return new Order();
    }

    public List<Order> getOrders(@Valid SearchOrderFilter searchOrderFilter,
                                 @Valid @NotNull User user) throws BusinessSystemException {

        if(searchOrderFilter == null) {
            searchOrderFilter = SearchOrderFilter.builder().build();
        }
        return orderDao.getOrders(searchOrderFilter, user);
    }

}
