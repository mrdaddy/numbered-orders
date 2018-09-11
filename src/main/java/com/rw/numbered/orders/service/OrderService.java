package com.rw.numbered.orders.service;

import by.iba.railway.eticket.xml.exception.BusinessSystemException;
import by.iba.railway.eticket.xml.objs.response.eticket.BuyTicketResponse;
import com.rw.numbered.orders.dao.OrderDao;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.request.OrderingInformation;
import com.rw.numbered.orders.dto.request.TripInformation;
import com.rw.numbered.orders.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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
    XMLGateService xmlGateService;

    public Order createOrder(@Valid OrderingInformation orderingInformation) {
        BuyTicketResponse etInfo = xmlGateService.buyTicket(orderingInformation);
        return new Order();
    }

    public void deleteOrder(@Valid @Min(1) long orderId) {

    }

    public Order getOrder(@Valid @Min(1) long orderId) {
        return new Order();
    }

    public List<Order> getOrders(@Valid @Size(max = 30) String orderType , @Valid Date departureDateMin, @Valid Date departureDateMax, @Valid @Size( max=6) String train,
                                 @Valid @Size(max=8) String departureStationCode, @Valid @Size(max=8) String  arrivalStationCode, @Valid @NotNull User user) throws BusinessSystemException {

        return orderDao.getOrders(orderType ,departureDateMin, departureDateMax, train, departureStationCode, arrivalStationCode);
    }

}
