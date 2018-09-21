package com.rw.numbered.orders.service;

import by.iba.railway.eticket.xml.RailWayServiceFactory;
import by.iba.railway.eticket.xml.exception.BusinessSystemException;
import by.iba.railway.eticket.xml.exception.XmlParserSystemException;
import by.iba.railway.eticket.xml.objs.request.type.buyticket.BlankType;
import by.iba.railway.eticket.xml.objs.request.type.buyticket.PassengerType;
import by.iba.railway.eticket.xml.objs.request.type.buyticket.PassengersType;
import by.iba.railway.eticket.xml.objs.request.type.buyticket.RequirementsType;
import by.iba.railway.eticket.xml.objs.request.type.common.CarType;
import by.iba.railway.eticket.xml.objs.request.type.common.TrainType;
import by.iba.railway.eticket.xml.objs.response.eticket.BuyTicketResponse;
import by.iba.railway.eticket.xml.services.EticketService;
import by.iba.railway.eticket.xml.services.ExpressService;
import com.rw.numbered.orders.dao.OrderDao;
import com.rw.numbered.orders.dao.ParameterDao;
import com.rw.numbered.orders.dto.request.OrderingInformation;
import com.rw.numbered.orders.dto.request.TripPassenger;
import com.rw.numbered.orders.dto.request.TripSeatDetail;
import com.rw.numbered.orders.security.User;
import com.rw.numbered.orders.service.utils.DateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class AddOrderService {
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

    @Autowired
    ParameterDao parameterDao;

    @Autowired
    OrderDao orderDao;

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

    public BuyTicketResponse buyTicket(OrderingInformation orderingInformation, User user) throws XmlParserSystemException, BusinessSystemException {

        RequirementsType requirements = createRequirements(orderingInformation);
        PassengersType passengersType = createPassengers(orderingInformation, user.getLanguage());
        connectToService();
        BuyTicketResponse etInfo = wsP.getBuyTicketInfo(orderingInformation.getDepStationCode(),
                orderingInformation.getArrStationCode(),
                DateTimeConverter.getDateFullString(orderingInformation.getDepartureDate()),
                orderingInformation.getDepartureTime(),
                createTrainType(orderingInformation.getTrain(), orderingInformation.getTripCarriage().getTrainLetter()),
                createCarType(orderingInformation),
                requirements,
                "",
                passengersType);
        return etInfo;
    }

    private TrainType createTrainType(String train, String carTrainLetter) {
        TrainType trainType = new TrainType();
        trainType.setNumber(train);

        if(!StringUtils.isEmpty(carTrainLetter)) {
            String trainNumShort = train.substring(0,3);
            trainType.setNumber(trainNumShort+carTrainLetter);
        }
        return trainType;
    }

    private CarType createCarType(OrderingInformation orderingInformation) {
        CarType carType = new CarType();
        carType.setType(orderingInformation.getTripCarriage().getTypeCode());
        carType.setNumber(String.valueOf(orderingInformation.getTripCarriage().getNo()));
        carType.setClassService(orderingInformation.getTripCarriage().getServiceClassCode());
        if(orderingInformation.isGlobalPrice() && !StringUtils.isEmpty(orderingInformation.getTripCarriage().getServiceClassIntCode())) {
            carType.setClassServiceInt(orderingInformation.getTripCarriage().getServiceClassIntCode());
        }
        return carType;
    }

    private RequirementsType createRequirements(OrderingInformation orderingInformation) {
        RequirementsType requirements = new RequirementsType();
        TripSeatDetail tripSeatDetail = orderingInformation.getTripSeatDetail();
        if(tripSeatDetail.getPartType()!=null) {
            if(tripSeatDetail.getPartType().equals(TripSeatDetail.PART_TYPES.ONE_COUPE)) {
                requirements.setSeatsComp("К");
            } else {
                requirements.setSeatsComp("О");
            }
        }

        if (tripSeatDetail.isIncludedBedding()) {
            requirements.setWoBedding("");
        }


        if (tripSeatDetail.getNumFrom()>0 && tripSeatDetail.getNumTo()>0) {
            int fromPlaces = tripSeatDetail.getNumFrom();
            int toPlaces = tripSeatDetail.getNumTo();
            requirements.setSeatsRange("" + fromPlaces + "-" + toPlaces);
        }

        if (tripSeatDetail.getBottomCount()>0) {
            requirements.setSeatsBottom(String.valueOf(tripSeatDetail.getBottomCount()));
        }

        if (tripSeatDetail.getTopCount()>0) {
            requirements.setSeatsTop(String.valueOf(tripSeatDetail.getTopCount()));
        }

        if (tripSeatDetail.getCompartmentType() != null) {
            if (tripSeatDetail.getCompartmentType().equals(TripSeatDetail.SEX_TYPES.MALE))
                requirements.setCompType("М");
            else if(tripSeatDetail.getCompartmentType().equals(TripSeatDetail.SEX_TYPES.FEMALE))
                requirements.setCompType("Ж");
            else
                requirements.setCompType("С");
        }
        
        return requirements;
    }

    private PassengersType createPassengers(OrderingInformation orderingInformation, String language) {
        PassengersType passengersType = new PassengersType();

        boolean childrenWithParentBlank = parameterDao.getTicketChildTicketBuying();
        boolean needAddFields = getPassengerAddFields(orderingInformation.isGlobalPrice());
        passengersType.setBlank(new ArrayList<BlankType>());
        if (!orderingInformation.getTripCarriage().isSaleOnTwo()) {
            BlankType blank = null;
            for (int i = 0; i < orderingInformation.getTripPassengers().size(); i++) {
                TripPassenger pass = orderingInformation.getTripPassengers().get(i);
                if (!pass.getPassengerType().equals(TripPassenger.PASSENGER_TYPE.CHILD_WITHOUT_PLACE) || !childrenWithParentBlank) {
                    blank = new BlankType();
                    blank.setSeatsCount(String.valueOf(pass.getPlacesCount()));
                    blank.setPassenger(new ArrayList<PassengerType>());
                    passengersType.getBlank().add(blank);
                }
                blank.getPassenger().add(getPassengersDocuments(pass, needAddFields, orderingInformation.isGlobalPrice()));
                if(orderingInformation.isGlobalPrice()) {
                    String globalTarifType = "";
                    switch(pass.getPassengerType()) {
                        case ADULT:
                            globalTarifType = parameterDao.getTicketGpAdultTariff();
                            break;
                        case CHILD_WITH_PLACE:
                            globalTarifType = parameterDao.getTicketGpChildTariff();
                            break;
                        case CHILD_WITHOUT_PLACE:
                            globalTarifType = parameterDao.getTicketGpFreeTariff();
                            break;
                    }
                    blank.setTariffCode(String.valueOf(globalTarifType));
                }

                if (pass.getPassengerType().equals(TripPassenger.PASSENGER_TYPE.CHILD_WITH_PLACE) && !orderingInformation.isGlobalPrice()) {
                    blank.setTariff("Д");
                }
                else if (pass.getPassengerType().equals(TripPassenger.PASSENGER_TYPE.CHILD_WITHOUT_PLACE)) {
                    if(childrenWithParentBlank) {
                        blank.setTariff("+ДЕТ5");
                    } else {
                        if(!orderingInformation.isGlobalPrice()) {
                            blank.setTariff("/ДЕТ5/20");
                        }
                    }
                }
            }
        } else {
            int[][] coupes = { { -1, -1, -1, -1, -1, -1 },
                    { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
                    { -1, -1, -1, -1, -1, -1 } };
            int coupeCnt = 0;

            for (int i = 0; i < orderingInformation.getTripPassengers().size(); i++) {
                if (orderingInformation.getTripPassengers().get(i).getPassengerType().equals(TripPassenger.PASSENGER_TYPE.CHILD_WITHOUT_PLACE)) {
                    int cnt = orderingInformation.getTripPassengers().get(0).getPlacesCount();
                    if (coupes[cnt - 1][0] == -1) {
                        coupes[cnt - 1][0] = i;
                        coupeCnt++;
                    } else if (coupes[cnt - 1][1] == -1) {
                        coupes[cnt - 1][1] = i;
                    } else if (coupes[cnt - 1][2] == -1) {
                        coupes[cnt - 1][2] = i;
                    } else if (coupes[cnt - 1][3] == -1) {
                        coupes[cnt - 1][3] = i;
                    } else if (coupes[cnt - 1][4] == -1) {
                        coupes[cnt - 1][4] = i;
                    } else if (coupes[cnt - 1][5] == -1) {
                        coupes[cnt - 1][5] = i;
                    }
                } else {
                    int cnt = orderingInformation.getTripPassengers().get(i).getPlacesCount();

                    if (coupes[cnt - 1][0] == -1) {
                        coupes[cnt - 1][0] = i;
                        coupeCnt++;
                    } else if (coupes[cnt - 1][1] == -1) {
                        coupes[cnt - 1][1] = i;
                    }
                }
            }
            int j = 0;
            for (int i = 0; i < coupeCnt; i++) {
                BlankType blank = new BlankType();
                passengersType.getBlank().add(blank);
                while (coupes[j][0] == -1) {
                    j++;
                }
                TripPassenger pass = orderingInformation.getTripPassengers().get(coupes[j][0]);
                PassengerType passengerDocument = getPassengersDocuments(pass, needAddFields,orderingInformation.isGlobalPrice());
                blank.setPassenger(new java.util.LinkedList<>());
                if (coupes[j][1] != -1) {
                    int maxCount = 2;
                    if (coupes[j][2] != -1)
                        maxCount = 3;
                    if (coupes[j][3] != -1)
                        maxCount = 4;
                    if (coupes[j][4] != -1)
                        maxCount = 5;
                    if (coupes[j][5] != -1)
                        maxCount = 6;
                    blank.getPassenger().add(0, passengerDocument);
                    for (int k = 1; k < maxCount; k++) {
                        pass = orderingInformation.getTripPassengers().get(coupes[j][k]);
                        passengerDocument = getPassengersDocuments(pass, needAddFields,orderingInformation.isGlobalPrice());
                        blank.getPassenger().add(k, passengerDocument);
                    }
                } else {
                    blank.getPassenger().add(0, passengerDocument);
                }
                blank.setSeatsCount("2");
                if(orderingInformation.isGlobalPrice()) {
                    String globalTarifType = "";
                    switch(pass.getPassengerType()) {
                        case ADULT:
                            globalTarifType = parameterDao.getTicketGpAdultTariff();
                            break;
                        case CHILD_WITH_PLACE:
                            globalTarifType = parameterDao.getTicketGpChildTariff();
                            break;
                        case CHILD_WITHOUT_PLACE:
                            globalTarifType = parameterDao.getTicketGpFreeTariff();
                            break;
                    }
                    blank.setTariffCode(String.valueOf(globalTarifType));
                }
                if (pass.getPassengerType().equals(TripPassenger.PASSENGER_TYPE.CHILD_WITH_PLACE) && !orderingInformation.isGlobalPrice()) {
                    blank.setTariff("Д");
                }
                else if (pass.getPassengerType().equals(TripPassenger.PASSENGER_TYPE.CHILD_WITHOUT_PLACE)) {
                    if(childrenWithParentBlank) {
                        blank.setTariff("+ДЕТ5");
                    } else {
                        if(!orderingInformation.isGlobalPrice()) {
                            blank.setTariff("/ДЕТ5/20");
                        }
                    }
                }
                j++;
            }

        }

        return passengersType;
    }

    private PassengerType getPassengersDocuments(TripPassenger pass, boolean addFields, boolean isGlobalPrice) {
        PassengerType passDoc = new PassengerType();
        boolean det5 = (pass.getPassengerType().equals(TripPassenger.PASSENGER_TYPE.CHILD_WITHOUT_PLACE) && !isGlobalPrice)?true:false;
        String name = pass.getLastName() + "=" + pass.getFirstName();
        if(!isGlobalPrice) {
            name+="=" + (!StringUtils.isEmpty(pass.getPatronymic()) ? pass.getPatronymic() : "-");
        }
        passDoc.setName(name);
        passDoc.setDocType(orderDao.getExpressExpressDocType(pass.getDocumentType()));

        if(pass.getBirthday() != null) {
            passDoc.setBirthday(DateTimeConverter.getDateFullString(pass.getBirthday()));
        }
        if(addFields) {
            passDoc.setCitizenship(pass.getCountry());
            passDoc.setBirthplace("");
        }
        if(pass.getSex() != null) {
            passDoc.setSex(pass.getSex().name());
        }

        passDoc.setDoc(pass.getDocumentNumber());
        if(det5) {
            passDoc.setDet5("");
        }
        return passDoc;
    }

    private boolean getPassengerAddFields(boolean isGlobalPrice) {
        boolean addFields = parameterDao.getPassengerAddFields();
        if(isGlobalPrice) {
            addFields = true;
        }
        return addFields;
    }

}
