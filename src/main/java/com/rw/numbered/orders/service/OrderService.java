package com.rw.numbered.orders.service;

import by.iba.railway.eticket.xml.objs.response.eticket.BuyTicketResponse;
import com.rw.numbered.orders.dao.OrderDao;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.trip.TripInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

   /* @Autowired
    XMLGateService xmlGateService;
*/
    public Order createOrder(TripInformation tripInformation) {
       // BuyTicketResponse etInfo = xmlGateService.buyTicket(tripInformation);
        return new Order();
    }

}
