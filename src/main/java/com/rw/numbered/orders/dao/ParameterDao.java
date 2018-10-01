package com.rw.numbered.orders.dao;

import com.rw.numbered.orders.service.utils.DBUtils;
import com.rw.numbered.orders.service.utils.TimeZoneOffsetCalculator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ParameterDao implements SQLQueries{
    public final static String TICKET_GP_FREE_TARIFF = "TICKET_GP_FREE_TARIFF";
    public final static String TICKET_GP_CHILD_TARIFF = "TICKET_GP_CHILD_TARIFF";
    public final static String TICKET_GP_ADULT_TARIFF = "TICKET_GP_ADULT_TARIFF";
    public final static String TICKET_CHILD_TICKET_BUYING = "TICKET_CHILD_TICKET_BUYING";
    public final static String PASSENGER_ADD_FIELDS = "TICKET_PASSENGER_ADD_FIELDS";
    public static final String NATIONAL_CURRENCY_CODE="NATIONAL_CURRENCY_CODE";
    public final static String NATIONAL_CARRIER_COUNTRY = "TICKET_NATIONAL_CARRIER_COUNTRY";

    public static final String DENOMINATION="DENOMINATION";
    public static final String DT_DENOMINATION_PASSED="DT_DENOMINATION_PASSED";
    public static final String DT_DENOMINATION_DATE="DT_DENOMINATION_DATE";
    public static final String DT_DENOMINATION_RATE="DT_DENOMINATION_RATE";
    public static final String DT_NATIOMAL_CURRENCY_CODE="DT_NATIOMAL_CURRENCY_CODE";
    public static final String DT_COST_FORMAT="DT_COST_FORMAT";
    public static final String DT_IS_TWO_COST_SHOWN="DT_IS_TWO_COST_SHOWN";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int getParameterByCodeInt(String code) {
        return getParameterByCodeInt(code, 0);
    }

    public int getParameterByCodeInt(String code, int defaultValue) {
        int val = defaultValue;
        String valueStr = getParameterByCode(code,""+val);
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
        String valueStr = getParameterByCode(code,val?"1":"0");
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

    public String getExpressExpressDocType(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("CODE", code);
        String expressCode = jdbcTemplate.queryForObject(DOCUMENT_TYPE_EXPRESS_NO, params, (rs, rowNum) -> rs.getString("EXPRESS_CODE"));
        return  expressCode;
    }

    public int getCountryTimeZoneOffset(String stationCode, final java.util.Date selDate, String language) {
        String nationalCarrierCountryCode = getNationalCarrierCountryCode();
        String countryCode = !StringUtils.isEmpty(stationCode)?stationCode.substring(0,2):nationalCarrierCountryCode;
        Integer offset = null;
        try {
            if(!countryCode.equals(nationalCarrierCountryCode)) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("CODE", countryCode);
                String query = DBUtils.formatQueryWithParams(COUNTRY_TIME_ZONE, language.toUpperCase());
                offset = jdbcTemplate
                        .queryForObject(query,
                                params, (rs, rowNum) -> {
                                    int offset1 = TimeZoneOffsetCalculator.calculate(rs.getString("TIME_ZONE"), selDate);
                                    return offset1;
                                });
            }
        } catch (EmptyResultDataAccessException e1) {
        }
        if(offset == null) {
            offset = 0;
        }
        return offset;
    }

    public int getDefaultPaymentTime() {
        Integer defaultPaymentTime = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("IS_DEFAULT", "1");
            defaultPaymentTime = jdbcTemplate
                    .queryForObject(DEFAULT_PAYMENT_TYPE,
                            params, (rs, rowNum) -> rs.getInt("ST_PAYMENT_TIME"));
        } catch (EmptyResultDataAccessException e1) {
        }
        if(defaultPaymentTime == null) {
            defaultPaymentTime = 20;
        }
        return defaultPaymentTime;
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

    public String getNationalCurrencyCode() {
        return getParameterByCode(NATIONAL_CURRENCY_CODE, "BYN");
    }

    public String getNationalCarrierCountryCode() {
        return getParameterByCode(NATIONAL_CARRIER_COUNTRY, "21");
    }

    public boolean isDenominationPassed() {
        Denomination dem = getDenomination();
        boolean demIsPassed = true;
        if(dem!=null && dem.getDenominationDate().getTime()>java.util.Calendar.getInstance().getTimeInMillis()) {
            demIsPassed = false;
        } else {
            demIsPassed = true;
        }
        return demIsPassed;
    }

    public float getDenominationRate() {
        float rate = 0;
        Denomination dem = getDenomination();
        if(getParameterByCodeB(DENOMINATION, false) && dem!=null && dem.isTwoCostShown()) {
            boolean demIsPassed = true;
            if(dem.getDenominationDate().getTime()>java.util.Calendar.getInstance().getTimeInMillis()) {
                demIsPassed = false;
            } else {
                demIsPassed = true;
            }
            rate = !demIsPassed?
                    (1/((float)dem.getDenominationRate())):(float)dem.getDenominationRate();
        }
        return rate;
    }

    private Denomination getDenomination() {
        Map<String, String> params = new HashMap<String, String>();
        Denomination dem = null;
        try {
            dem = jdbcTemplate.queryForObject(
                    DENOMINATION_INFO, params, (rs, rowNum) -> {
                        Denomination dem1 = new Denomination();
                        dem1.setDenominationDate(rs.getDate("DENOMINATION_DATE"));
                        dem1.setDenominationRate(rs.getInt("DENOMINATION_RATE"));
                        dem1.setCostFormat(rs.getString("COST_FORMAT"));
                        dem1.setNationalCurrencyCode(rs.getString("NATIOMAL_CURRENCY_CODE"));
                        dem1.setTwoCostShown("1".equals(rs.getString("IS_TWO_COST_SHOWN")));
                        return dem1;
                    });
        } catch (EmptyResultDataAccessException e) {
        }
        return  dem;
        /*if(dem!=null) {
            if(dem.getDenominationDate().getTime()>java.util.Calendar.getInstance().getTimeInMillis()) {
                params.put(ParameterConst.DT_DENOMINATION_PASSED, "0");
            } else {
                params.put(ParameterConst.DT_DENOMINATION_PASSED, "1");
            }
            params.put(ParameterConst.DT_DENOMINATION_DATE, DateTimeConverter.getDateFullString(dem.getDenominationDate()));
            params.put(ParameterConst.DT_DENOMINATION_RATE, String.valueOf(dem.getDenominationRate()));
            params.put(ParameterConst.DT_NATIOMAL_CURRENCY_CODE, dem.getNationalCurrencyCode());
            params.put(ParameterConst.DT_COST_FORMAT, dem.getCostFormat());
            params.put(ParameterConst.DT_IS_TWO_COST_SHOWN, dem.isTwoCostShown()?"1":"0");

        }*/
    }

    @Data
    public class Denomination {
        private Date denominationDate;
        private int denominationRate;
        private String nationalCurrencyCode;
        private String costFormat;
        private boolean twoCostShown;
    }
}
