package com.rw.numbered.orders.service;

import by.iba.railway.eticket.xml.RailWayServiceFactory;
import by.iba.railway.eticket.xml.objs.response.eticket.BuyTicketResponse;
import by.iba.railway.eticket.xml.services.EticketService;
import by.iba.railway.eticket.xml.services.ExpressService;
import com.rw.numbered.orders.dto.trip.TripInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class XMLGateService {
    private ExpressService ws = null;
    private EticketService wsP = null;

    @Value("${sppdpars.gateway.url}")
    private String gatewayUrl;

    @Value("${sppdpars.gateway.login}")
    private String gatewayLogin;

    @Value("${sppdpars.gateway.password}")
    private String gatewayPassword;

    @Value("${sppdpars.gateway.schedule.timeout}")
    private int scheduleTimeout;

    @Value("${sppdpars.gateway.ticket.timeout}")
    private int ticketTimeout;

    protected void connectToService() {
        if (ws == null) {

            /*by.iba.buyticket.model.service.StatisticService statisticService = null;
            if(trainService!=null) {
                statisticService = trainService.getStatisticService();
            }*/
            RailWayServiceFactory rw = new RailWayServiceFactory(ticketTimeout,null);
            ws = rw.getExpressService(gatewayUrl, gatewayLogin, gatewayPassword,true);
            wsP = rw.getEticketService(gatewayUrl, gatewayLogin, gatewayPassword,true);
        }
    }

    public BuyTicketResponse buyTicket(TripInformation tripInformation) {
        BuyTicketResponse etInfo = new BuyTicketResponse();
        /*BuyTicketResponse etInfo = wsP.getBuyTicketInfo(stationFrom,
                stationTo, depDate, depTime, train, car, requirements, "",
                passengersType);*/
        return etInfo;
    }

}
