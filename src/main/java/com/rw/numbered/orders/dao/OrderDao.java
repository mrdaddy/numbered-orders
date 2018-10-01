package com.rw.numbered.orders.dao;

import by.iba.railway.eticket.xml.objs.response.type.buyticket.PassengerType;
import by.iba.railway.eticket.xml.objs.response.type.buyticket.TicketType;
import by.iba.railway.eticket.xml.objs.response.type.buyticket.TicketsType;
import by.iba.railway.eticket.xml.objs.response.type.common.buyreturnticket.IIType;
import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.order.OrderShort;
import com.rw.numbered.orders.dto.passenger.Passenger;
import com.rw.numbered.orders.dto.request.OrderingInformation;
import com.rw.numbered.orders.dto.request.SearchOrderFilter;
import com.rw.numbered.orders.dto.request.TripPassenger;
import com.rw.numbered.orders.security.User;
import com.rw.numbered.orders.service.decorators.BuyTicketResponseDecorator;
import com.rw.numbered.orders.service.utils.DBUtils;
import com.rw.numbered.orders.service.utils.PriceFormatter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.w3c.dom.DocumentType;
import sun.misc.FloatingDecimal;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
@Slf4j
public class OrderDao implements SQLQueries{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    MessageSource messageSource;

    public List<OrderShort> getOrders(SearchOrderFilter searchOrderFilter, User user) {

        List<OrderShort> orders = new ArrayList<>();
        orders.add(new Order());
        return orders;
    }

    public void addTransLogBuyingError(User user) {
        String databaseError = messageSource.getMessage("databaseError", null, new Locale(commentLanguage));
        Object[] objs = {user.getLogin(), databaseError};
        String buyTicketError = messageSource.getMessage("buyTicketError", objs, new Locale(commentLanguage));
        addTransactionLog(user.getId(), user.getIp(),
                0, OperationsAndStatuses.OP_TYPE_ORDER_BUYING,
                OperationsAndStatuses.OP_STATUS_UNSUCCESS,
                buyTicketError, 0);
    }

    public Order getOrder(long id, User user) {
        Order order = new Order();

        return order;
    }

    @Transactional(rollbackFor = Exception.class)
    public long addOrder(BuyTicketResponseDecorator etInfo, OrderingInformation orderingInformation, User user) throws Exception {
        long orderId = 0;
        MapSqlParameterSource params = new MapSqlParameterSource();
        String date = "";
        Calendar cal = Calendar.getInstance();
        try {
            boolean isReturnAllowed = isReturnAllowed(orderingInformation.getTrain(),orderingInformation.getTripCarriage().getTypeCode());
            params.addValue("USER_ID", user.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
            java.util.Date d = sdf1.parse(etInfo.getOrder()
                    .getCreateDate());
            date = sdf.format(d);

            params.addValue("ORDER_NO", etInfo.getOrder().getId().trim());

            params.addValue("ORDER_EXPRESS_NO", etInfo.getOrder()
                    .getExpressID().trim());

            params.addValue("DEPARTURE_TRAIN", etInfo.getDeparture()
                    .getTrain());
            params.addValue("DEPARTURE_STATION", etInfo.getDeparture()
                    .getStation());
            params.addValue("DEPARTURE_STATION_CODE", etInfo.getDeparture().getStationCode());

            params.addValue("IS_NEW", "1");

            d = sdf1.parse(etInfo.getDeparture().getDate());
            date = sdf.format(d);
            params.addValue("DEPARTURE_DATE", date);
            params.addValue("DEPARTURE_TIME", etInfo.getDeparture()
                    .getTime()
                    + ":00");
            SimpleDateFormat FULL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date realDate = FULL_FORMAT.parse(date+" "+etInfo.getDeparture().getTime());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(realDate);
            cal1.add(Calendar.HOUR_OF_DAY, (-1)*etInfo.getTimeOffset()/60);
            params.addValue("REAL_DEPARTURE_DATETIME", cal1.getTime());
            String trainNum = "";
            if (StringUtils.isEmpty(etInfo.getArrival().getTrain()))
                trainNum = etInfo.getDeparture().getTrain();
            else
                trainNum = etInfo.getArrival().getTrain();

            params.addValue("ARRIVAL_TRAIN", trainNum);
            params.addValue("ARRIVAL_STATION_CODE", etInfo.getArrival().getStationCode());
            params.addValue("ARRIVAL_STATION", etInfo.getArrival()
                    .getStation());
            d = sdf1.parse(etInfo.getArrival().getDate());
            date = sdf.format(d);
            params.addValue("ARRIVAL_DATE", date);
            params.addValue("ARRIVAL_TIME", etInfo.getArrival()
                    .getTime()
                    + ":00");
            String type = etInfo.getCar().getType();
            params.addValue("CARRIAGE_NO", etInfo.getCar()
                    .getNumber());
            params.addValue("CARRIAGE_TYPE", etInfo.getCarTypeLetter());
            params.addValue("RETURN_ALLOWED", DBUtils.toString(isReturnAllowed));
            params.addValue("CARRIAGE_TYPE_NAME", etInfo.getCarTypeName());
            params.addValue("CARRIAGE_TYPE_DESC",
                    etInfo.getCarTypeName());
            String serviceClass = "";
            if(etInfo.getCar().getClassService() != null && !StringUtils.isEmpty(etInfo.getCar().getClassService().getType())) {
                serviceClass = etInfo.getCar().getClassService().getType();
            }
            params.addValue("CARRIAGE_SERVICE_CLASS",
                    serviceClass);
            params.addValue("CARRIAGE_SERVICE_CLASS_DESC",
                    serviceClass);

            String serviceClassInt = "";
            if(!StringUtils.isEmpty(orderingInformation.getTripCarriage().getServiceClassIntCode())) {
                serviceClassInt = orderingInformation.getTripCarriage().getServiceClassIntCode();
            }
            params.addValue("CARRIAGE_SERVICE_CLASS_INT",serviceClassInt);

            params.addValue("CARRIAGE_OWNER", etInfo.getCar()
                    .getOwner() != null ? etInfo.getCar()
                    .getOwner().getType() : "");
            params.addValue("CARRIAGE_CARRIER", etInfo.getCar()
                    .getCarrier() != null ? etInfo.getCar()
                    .getCarrier().getName() : "");
            params.addValue("CARRIAGE_GENDER_SIGN", etInfo.getSignR());
            params.addValue("CARRIAGE_ADD_SIGNS", etInfo.getCar()
                    .getAddSigns());

            float cost = (float) etInfo.getTariff();
            params.addValue("COST", cost);
            if(etInfo.getTariffEuro()!=null) {
                params.addValue("COST_EURO", etInfo.getTariffEuro());
            } else {
                params.addValue("COST_EURO", 0);
            }
            params.addValue("CURRENCY_CODE", etInfo.getCurrency());

            if(etInfo.getCar().getTripClass()!=null) {
                params.addValue("TRIP_CLASS", String.valueOf(etInfo.getCar().getTripClass()));
            } else {
                params.addValue("TRIP_CLASS", "");
            }
            params.addValue("IS_GLOBAL_PRICE", DBUtils.toString(orderingInformation.isGlobalPrice()));
            params.addValue("PAYMENT_METHOD",  etInfo.getPaymentType());
            params.addValue("SEAT_COUNT", etInfo.getSeats().getCount());
            params.addValue("SEATS", etInfo.getSeats().getValue());
            String addSigns = etInfo.getSignGA();
            String returnForbidden = messageSource.getMessage("returnForbidden", null, new Locale(user.getLanguage()));
            if(!isReturnAllowed) {
                if(!StringUtils.isEmpty(addSigns)) {
                    addSigns+=". "+returnForbidden;
                } else {
                    addSigns=returnForbidden;
                }
            }
            if (addSigns != null)
                params.addValue("ADD_SIGNS", addSigns);
            else
                params.addValue("ADD_SIGNS", "");
            etInfo.setSignGA(addSigns);
            params.addValue("TIME_DESCRIPTION", etInfo.getSignGB());

            if (orderingInformation.isRegistrationAllowed()) {
                params.addValue("REGISTRATION_ALLOWED", 1);
            } else {
                params.addValue("REGISTRATION_ALLOWED", 0);
            }
            params.addValue("REGISTRATION_STATUS",
                    OperationsAndStatuses.REG_STATUS_NOTREGISTERED);
            params.addValue("REGISTRATION_NEEDED", "0");
            params.addValue("STATUS",
                    OperationsAndStatuses.ORDER_STATUS_BUY_EXPRESS);

            cal = Calendar.getInstance();
            cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + etInfo.getPaymentTime());
            params.addValue("PAYMENT_END_TIME", cal.getTime());
            params.addValue("TRAIN_TYPE", orderingInformation.getTrainType());
            params.addValue("COMPARTMENT_TYPE",etInfo.getSignR());
            /*if(buyTicket.getSelTicketBookingOptions().isAllowERegistrationASKPP()) {
                params.addValue("IS_TRAIN_WITH_ASMBP","1");
            } else {
                params.addValue("IS_TRAIN_WITH_ASMBP","0");
            }*/
            params.addValue("IS_TRAIN_WITH_ASMBP","0");

            if(etInfo.getDepartureTrain()!=null && etInfo.getDepartureTrain().getDate()!=null) {
                Date depTrainDate = sdf1.parse(etInfo.getDepartureTrain().getDate());
                params.addValue("STARTING_DEPARTURE_DATETIME",depTrainDate);
            } else {
                params.addValue("STARTING_DEPARTURE_DATETIME",null);
            }


        } catch (Exception e2) {
            //log.error("addOrder2: " + e2.getMessage(), e2);
            return 0;
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(ADD_ORDER,
                params, keyHolder);
        orderId = keyHolder.getKey().longValue();
        if (etInfo != null && etInfo.getTickets() != null) {
            for (TicketType ticket : etInfo.getTickets().getTicket()) {
                addOrderTickets(user, orderId, ticket,
                        orderingInformation.getTripPassengers(), etInfo);
            }
        }
        Object[] objs = {user.getLogin(), etInfo.getOrder().getExpressID()};
        String comment = cutCommentForDB(messageSource.getMessage("buyTicket", objs, new Locale(commentLanguage)));
        addOrderHistoryIiLocal(user.getId(), user.getIp(),
                orderId, OperationsAndStatuses.OP_TYPE_ORDER_BUYING,
                OperationsAndStatuses.OP_STATUS_SUCCESS, comment,etInfo.getIi());

        return orderId;
    }

    private void addOrderTickets(User user, long orderId, TicketType ticket,
                                 List<TripPassenger> passengers, BuyTicketResponseDecorator etInfo) throws Exception {
        MapSqlParameterSource params = new MapSqlParameterSource();
        try {
            params.addValue("USER_ID", user.getId());
            params.addValue("ORDER_ID", orderId);
            params.addValue("TICKET_NO", ticket.getNumber());
            params.addValue("TICKET_ID", ticket.getId());
            params.addValue("TICKET_EXPRESS_NO", ticket.getExpressID());
            params.addValue("RESERVATION_NO", ticket.getNumberReservation());
            params.addValue("COST", ticket.getTariff());

            params.addValue("COST_EURO", ticket.getTariffEuro()!=null?ticket.getTariffEuro():0f);
            params.addValue("CURRENCY_CODE", etInfo.getCurrency());

            float serviceTariff = ticket.getTariffService()!=null?ticket.getTariffService():0;

            float tariff = (ticket.getTariffBP()!=null?ticket.getTariffBP():0)-serviceTariff;
            BigDecimal tarifBd = new BigDecimal(tariff);
            tarifBd = tarifBd.setScale(2, RoundingMode.HALF_UP);
            params.addValue("TARIFF", tarifBd);

            float ticketTariff = (ticket.getTariffB()!=null?ticket.getTariffB():0);
            BigDecimal tarifTicketBd = new BigDecimal(ticketTariff);
            tarifTicketBd = tarifTicketBd.setScale(2, RoundingMode.HALF_UP);
            params.addValue("TICKET_TARIFF", tarifTicketBd);

            double tarifNds = PriceFormatter.getFloatAsDouble(ticket.getTariffNDS()!=null?ticket.getTariffNDS():0f);
            double serviceNds = PriceFormatter.getFloatAsDouble(ticket.getTariffServiceNDS()!=null?ticket.getTariffServiceNDS():0f);
            double commissionNds = PriceFormatter.getFloatAsDouble(ticket.getTariffComNDS()!=null?ticket.getTariffComNDS():0f);
            tarifNds = tarifNds - serviceNds - commissionNds;
            BigDecimal tarifNdsBd = new BigDecimal(tarifNds);
            tarifNdsBd = tarifNdsBd.setScale(2, RoundingMode.HALF_UP);
            /*if(!ParameterConst.getParameterBoolean(ParameterConst.DT_DENOMINATION_PASSED, true)) {
                tarifNdsBd = tarifNdsBd.multiply(new BigDecimal(1000));
            }*/
            params.addValue("TARIFF_VAT", tarifNdsBd);

            float reservedTariff = (ticket.getTariffP()!=null?ticket.getTariffP():0)-serviceTariff;
            BigDecimal tarifReservedBd = new BigDecimal(reservedTariff);
            tarifReservedBd = tarifReservedBd.setScale(2, RoundingMode.HALF_UP);
            if(!etInfo.isDenominationPassed()) {
                tarifNdsBd = tarifNdsBd.multiply(new BigDecimal(1000));
            }
            params.addValue("RESERVED_SEAT_TARIFF", tarifReservedBd);

            params.addValue("INSURANCE_TARIFF", ticket.getTariffInsurance()!=null?ticket.getTariffInsurance():0);
            params.addValue("SERVICE_TARIFF", ticket.getTariffService()!=null?ticket.getTariffService():0);
            if(!etInfo.isDenominationPassed()) {
                params.addValue("SERVICE_TARIFF_VAT", serviceNds*1000);
            } else {
                params.addValue("SERVICE_TARIFF_VAT", serviceNds);
            }
            params.addValue("COMMISSION_FEE", ticket.getTariffCom()!=null?ticket.getTariffCom():0);
            if(!etInfo.isDenominationPassed()) {
                params.addValue("COMMISSION_VAT", commissionNds*1000);
            } else {
                params.addValue("COMMISSION_VAT", commissionNds);
            }
            params.addValue("TLIST_ISSUED", ticket.getListPassIssued()!=null?1:0);
            params.addValue("RESERVATION_NO", ticket.getNumberReservation());
            String seats = (ticket.getSeats()!=null && !ticket.getSeats().equals("000"))?ticket.getSeats():"";
            int seatsCount = 0;
            if(!StringUtils.isEmpty(seats)) {
                seatsCount = ticket.getPassCount();
            } else {
                seatsCount = 0;
            }
            params.addValue("SEATS", seats);
            params.addValue("SEAT_COUNT", seatsCount);
            params.addValue("SEAT_DESCRIPTION", ticket.getSeatsType()!=null?ticket.getSeatsType():"");
            params.addValue("PASSENGER_COUNT", ticket.getPassCount());

            params.addValue("TICKET_STATUS", OperationsAndStatuses.ORDER_STATUS_BUY_EXPRESS);
            params.addValue("REGISTRATION_STATUS",
                    OperationsAndStatuses.REG_STATUS_NOTREGISTERED);


            if (ticket.getTariffType().toLowerCase().equals("полный") || ticket.getTariffType().startsWith("72"))
                params.addValue("TARIFF_TYPE", "T");
            else if (ticket.getTariffType().toLowerCase().equals("дет")|| ticket.getTariffType().startsWith("73"))
                params.addValue("TARIFF_TYPE", "C");
            else
                params.addValue("TARIFF_TYPE", "B");

            // dumpMap(params.getValues());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(SQLQueries.ADD_ORDER_TICKET,
                    params, keyHolder);
            for (PassengerType passDoc : ticket.getPassenger()) {
                boolean hasPass = false;
                Passenger pass = null;
                for(int k=0;k<2;k++) {
                    for (int j = 0; j < passengers.size(); j++) {

                        String doc = getDocumentTypeCodeExpress(passengers.get(j).getDocumentType())
                                + passengers.get(j).getDocumentNumber();
                        doc = prepareDocNumber(doc);
                        TripPassenger.PASSENGER_TYPE tariffType;
                        if (ticket.getTariffType().toLowerCase().equals("полный") || ticket.getTariffType().startsWith("72"))
                            tariffType = TripPassenger.PASSENGER_TYPE.ADULT;
                        else if (ticket.getTariffType().toLowerCase().equals("дет")|| ticket.getTariffType().startsWith("73"))
                            tariffType = TripPassenger.PASSENGER_TYPE.CHILD_WITH_PLACE;
                        else
                            tariffType = TripPassenger.PASSENGER_TYPE.CHILD_WITHOUT_PLACE;
                        if (doc.equals(passDoc.getDoc()) && passengers.get(j).getPassengerType().equals(tariffType)) {
                            String name = passengers.get(j).getLastName().trim()+"="+passengers.get(j).getFirstName().trim()+"="+passengers.get(j).getPatronymic().trim();
                            if(k==0 && name.toUpperCase().equals(passDoc.getName()) || k==1) {
                                pass = passengers.get(j);
                                addOrderPassenger(pass, keyHolder.getKey().longValue(),
                                        user.getId());
                                hasPass = true;
                                break;
                            }
                        }
                    }
                    if(hasPass) {
                        break;
                    }
                }
                if (hasPass == false) {
                    log.error("addOrderTicket: error adding passenger: "
                            + passDoc.getDoc()+", passengers list: "+passengers.toString());

                    throw new Exception("error adding passenger: "
                            + passDoc.getDoc());
                }
            }
        } catch (Exception e) {
            log.error("addOrderTickets params: " + params.getValues());
            throw e;
        }
    }

    private void addOrderPassenger(Passenger pass, long orderTicketId,
                                   long user_id) throws Exception {
        MapSqlParameterSource params = new MapSqlParameterSource();
        try {
            params.addValue("LAST_NAME", pass.getLastName().toUpperCase());
            params.addValue("ORDER_TICKET_ID", orderTicketId);
            params.addValue("USER_ID", user_id);
            params.addValue("USER_ID", user_id);
            params.addValue("FIRST_NAME", pass.getFirstName().toUpperCase());
            params.addValue("PATRONYMIC", pass.getPatronymic().toUpperCase());
            params.addValue("COUNTRY", pass.getCountry());
            if (pass.getSex() != null && !pass.getSex().equals(""))
                params.addValue("SEX", pass.getSex());
            else
                params.addValue("SEX", null);
            if (pass.getBirthday() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(pass.getBirthday());
                params.addValue("BIRTH_DATE", date);
            } else
                params.addValue("BIRTH_DATE", null);
            params.addValue("DOCUMENT_TYPE", pass.getDocumentType());
            params.addValue("DOCUMENT_TYPE_NAME", pass.getDocumentType());
            params.addValue("DOCUMENT_NO", pass.getDocumentNumber());
            jdbcTemplate.update(SQLQueries.ADD_ORDER_PASSENGER,
                    params);
        } catch (Exception e) {
            log.error("addOrderPassenger params: " + params.getValues());
            throw e;
        }
    }

    private boolean isReturnAllowed(String train, String carType) {
        boolean returnAllowed = true;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("TRAIN_NO", (train.length()>4?train.substring(0, 4):train)+"%");
        params.put("CARRIAGE_TYPE", carType);
        String query = CHECK_RETURN_FORBIDDEN;
        try {
            int id = jdbcTemplate.queryForObject(query, params,Integer.class);
            if(id>0) {
                returnAllowed = false;
            }
        } catch (EmptyResultDataAccessException e1) {
        }/* catch (Exception e1)
        {
            log.error("checkReturnAllowed", e1);
        }*/
        return returnAllowed;
   }

   private String getDocumentTypeCodeExpress(String type) {
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("CODE", type);
       String query = DOCUMENT_TYPE_EXPRESS;
       String expressType = null;
       try {
           expressType = jdbcTemplate.queryForObject(query, params,String.class);
       } catch (EmptyResultDataAccessException e1) {
       }
       if(expressType == null) {
           expressType = type;
       }
       return expressType;
   }

    private String prepareDocNumber(String doc) {
        if(doc!=null) {
            doc = doc.toUpperCase();
            doc = doc.replaceAll("C", "С");
            doc = doc.replaceAll("C", "С");
            doc = doc.replaceAll("B", "В");
            doc = doc.replaceAll("O", "О");
            doc = doc.replaceAll("P", "Р");
            doc = doc.replaceAll("M", "М");
            doc = doc.replaceAll("E", "Е");
            doc = doc.replaceAll("Ё", "Е");
            doc = doc.replaceAll("A", "А");
            doc = doc.replaceAll("H", "Н");
            doc = doc.replaceAll("K", "К");
            doc = doc.replaceAll("X", "Х");
            doc = doc.replaceAll("T", "Т");
        }
        return doc;
    }

    private void addOrderHistoryIiLocal(long userId, String ip, long orderId, String type, String status, String comment, IIType ii) throws Exception
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        try {
            params.addValue("ORDER_ID", orderId);
            params.addValue("TYPE", type);
            params.addValue("STATUS", status);
            params.addValue("COMMENT", comment);

            params.addValue("SEAT_ISSUING_CENTER", ii!=null && ii.getWb()!=null?ii.getWb():null);
            params.addValue("ORDER_ISSUING_CENTER", ii!=null && ii.getWm()!=null?ii.getWm():null);
            params.addValue("SALE_POINT", ii!=null && ii.getHp()!=null?ii.getHp():null);
            params.addValue("SALE_RAILWAY", ii!=null && ii.getDb()!=null?ii.getDb():null);
            params.addValue("SALE_TERMINAL", ii!=null && ii.getHt()!=null?ii.getHt():null);

            jdbcTemplate.update(ADD_ORDER_HISTORY_BUYING, params);
        } catch(Exception e) {
            log.error("addOrderHistoryIiLocal params: " + params.getValues());
            throw e;
        }
        addTransactionLogLoc(userId,ip,orderId,type,status,comment, 0);
    }

    private void addTransactionLogLoc(long userId, String ip, long orderId,String type, String status,String comment, long ticketId) throws Exception
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        comment = cutCommentForDB(comment);
        try {
            params.addValue("IP_ADDRESS", ip);
            if(userId>0)
                params.addValue("USER_ID", userId);
            else
                params.addValue("USER_ID", null);
            if (orderId > 0)
                params.addValue("ORDER_ID", orderId);
            else
                params.addValue("ORDER_ID", null);
            if (ticketId > 0)
                params.addValue("TICKET_ID", ticketId);

            params.addValue("OPERATION_TYPE", type);
            params.addValue("STATUS", status);
            params.addValue("OPERATION_COMMENT", comment);
            jdbcTemplate.update(
                    ticketId>0?SQLQueries.ADD_TRANSACTION_LOGS_T:SQLQueries.ADD_TRANSACTION_LOGS, params);
        } catch(Exception e) {
            log.error("addTransactionLogLoc params: " + params.getValues());
            throw e;
        }
    }

    private void addTransactionLog(long userId, String ip, long orderId,String type, String status,String comment,long ticketId) {
        try {
            addTransactionLogLoc(userId, ip, orderId, type, status, comment, ticketId);
        } catch (Exception e) {
        }
    }

    protected String cutCommentForDB(String comment) {
        try {
            byte[] bites = comment.getBytes("UTF-8");
            if (bites.length > 1024) {
                byte[] newBites = Arrays.copyOf(bites, 1024);
                comment = new String(newBites, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {/*NOP*/}
        return comment;
    }

}
