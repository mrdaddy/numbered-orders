package com.rw.numbered.orders.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ParameterDao {
    private static final String PARAM_INFO = "SELECT VALUE FROM ETICKET.PARAMETERS WHERE CODE=:CODE";

    public final static String TICKET_GP_FREE_TARIFF = "TICKET_GP_FREE_TARIFF";
    public final static String TICKET_GP_CHILD_TARIFF = "TICKET_GP_CHILD_TARIFF";
    public final static String TICKET_GP_ADULT_TARIFF = "TICKET_GP_ADULT_TARIFF";
    public final static String TICKET_CHILD_TICKET_BUYING = "TICKET_CHILD_TICKET_BUYING";
    public final static String PASSENGER_ADD_FIELDS = "TICKET_PASSENGER_ADD_FIELDS";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int getParameterByCodeInt(String code) {
        int val = 0;
        String valueStr = getParameterByCode(code,"0");
        if(!StringUtils.isEmpty(valueStr)) {
            val = Integer.parseInt(valueStr,10);
        }
        return val;
    }

    public boolean getParameterByCodeB(String code) {
        return getParameterByCodeB(code, false);
    }

    public boolean getParameterByCodeB(String code, boolean defaultValue) {
        boolean val = defaultValue;
        String valueStr = getParameterByCode(code,"0");
        if(valueStr != null && valueStr.equals("1")) {
            val = true;
        }
        return val;
    }

    public String getParameterByCode(String code) {
        return getParameterByCode(code, null);
    }

    public String getParameterByCode(String code, String defaultValue) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("CODE", code);
        String param = jdbcTemplate.queryForObject(
                PARAM_INFO, params, (rs, rowNum) -> rs.getString("VALUE"));
        if(param == null) {
            param = defaultValue;
        }
        return param;
    }

    public String getTicketGpFreeTariff() {
        return getParameterByCode(TICKET_GP_FREE_TARIFF, "50");
    }

    public String getTicketGpChildTariff() {
        return getParameterByCode(TICKET_GP_CHILD_TARIFF, "73");
    }

    public String getTicketGpAdultTariff() {
        return getParameterByCode(TICKET_GP_ADULT_TARIFF, "72");
    }

    public boolean getTicketChildTicketBuying() {
        return getParameterByCodeB(TICKET_CHILD_TICKET_BUYING, true);
    }

    public boolean getPassengerAddFields() { return getParameterByCodeB(PASSENGER_ADD_FIELDS, false);
    }
}
