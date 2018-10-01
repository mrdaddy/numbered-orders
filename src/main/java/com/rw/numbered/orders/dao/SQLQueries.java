package com.rw.numbered.orders.dao;

public interface SQLQueries {
    String PARAM_INFO = "SELECT VALUE FROM ETICKET.PARAMETERS WHERE CODE=:CODE";
    String COUNTRY_TIME_ZONE = "SELECT CODE, TIME_ZONE, DEPARTURE_MSG_{0} AS DEPARTURE_MSG, ARRIVAL_MSG_{0} AS ARRIVAL_MSG FROM ETICKET.COUNTRIES WHERE CODE=:CODE WITH UR";
    String DEFAULT_PAYMENT_TYPE = "SELECT ST_PAYMENT_TIME FROM ETICKET.PAYMENT_SYSTEMS WHERE IS_DEFAULT=:IS_DEFAULT WITH UR";
    String DOCUMENT_TYPE_EXPRESS = "SELECT EXPRESS_CODE FROM ETICKET.DOCUMENT_TYPES WHERE CODE=:CODE WITH UR";

    String DOCUMENT_TYPE_EXPRESS_NO = "SELECT EXPRESS_CODE FROM ETICKET.DOCUMENT_TYPES WHERE CODE = :CODE";
    String STATION_NAME_BY_CODE = "SELECT NAME_{0} AS NAME FROM ETICKET.STATIONS WHERE STATION_CODE = :STATION_CODE WITH UR";
    String EXPRESS_ERROR = "SELECT SYSTEM_ERROR_{0} AS SYSTEM_ERROR FROM ETICKET.EXPRESS_ERRORS WHERE EXPRESS_ERROR=:EXPRESS_ERROR WITH UR";
    String CAR_TYPE_BY_CODE = "SELECT NAME_{0} AS NAME FROM ETICKET.CARRIAGE_TYPES WHERE CODE=:CODE WITH UR";
    String CHECK_RETURN_FORBIDDEN = "SELECT ID FROM ETICKET.TRAINS_WITH_FORBIDDEN_RETURN WHERE TRAIN_NO LIKE :TRAIN_NO AND CARRIAGE_TYPE = :CARRIAGE_TYPE";
    String CHECK_MONITORING = "SELECT PARAMETER_VALUE FROM ETICKET.MONITORING WHERE MONITORING_PARAMETER = :MONITORING_PARAMETER WITH UR";
    String MONITORING_ADMINS = "SELECT * FROM ETICKET.MONITORING_ADMINS WITH UR";
    String DENOMINATION_INFO = "SELECT * FROM ETICKET.DENOMINATIONS WHERE CURRENT_DATE BETWEEN TWO_COST_PERIOD_FROM AND TWO_COST_PERIOD_TO WITH UR";

    String commentLanguage = "ru";

    String ADD_ORDER = "INSERT INTO ETICKET.ORDERS (" +
            "USER_ID," +
            "CREATE_TIME," +
            "ORDER_NO," +
            "ORDER_EXPRESS_NO," +
            "DEPARTURE_TRAIN," +
            "DEPARTURE_STATION," +
            "DEPARTURE_DATE," +
            "DEPARTURE_TIME," +
            "ARRIVAL_TRAIN," +
            "ARRIVAL_STATION," +
            "ARRIVAL_DATE," +
            "ARRIVAL_TIME," +
            "CARRIAGE_NO," +
            "CARRIAGE_TYPE," +
            "CARRIAGE_TYPE_NAME," +
            "CARRIAGE_TYPE_DESC," +
            "CARRIAGE_SERVICE_CLASS," +
            "CARRIAGE_SERVICE_CLASS_DESC," +
            "CARRIAGE_OWNER," +
            "CARRIAGE_CARRIER," +
            "CARRIAGE_GENDER_SIGN," +
            "CARRIAGE_ADD_SIGNS," +
            "COST," +
            "SEAT_COUNT," +
            "SEATS," +
            "ADD_SIGNS," +
            "TIME_DESCRIPTION," +
            "STATUS," +
            "REGISTRATION_ALLOWED," +
            "REGISTRATION_STATUS," +
            "REGISTRATION_NEEDED," +
            "PAYMENT_START_TIME," +
            "PAYMENT_END_TIME," +
            "REAL_DEPARTURE_DATETIME," +
            "CURRENCY_CODE," +


            "STARTING_DEPARTURE_DATETIME,"+
            "IS_TRAIN_WITH_ASMBP,"+
            "TRAIN_TYPE,"+
            "COMPARTMENT_TYPE,"+
            "DEPARTURE_STATION_CODE," +
            "ARRIVAL_STATION_CODE," +
            "IS_NEW," +
            "IS_GLOBAL_PRICE," +
            "COST_EURO," +
            "TRIP_CLASS," +
            "PAYMENT_METHOD," +
            "CARRIAGE_SERVICE_CLASS_INT," +
            "RETURN_ALLOWED," +
            "UPDATE_TIME " +
            ") " +
            "VALUES (" +
            ":USER_ID," +
            "CURRENT TIMESTAMP," +
            ":ORDER_NO," +
            ":ORDER_EXPRESS_NO," +
            ":DEPARTURE_TRAIN," +
            ":DEPARTURE_STATION," +
            ":DEPARTURE_DATE," +
            ":DEPARTURE_TIME," +
            ":ARRIVAL_TRAIN," +
            ":ARRIVAL_STATION," +
            ":ARRIVAL_DATE," +
            ":ARRIVAL_TIME," +
            ":CARRIAGE_NO," +
            ":CARRIAGE_TYPE," +
            ":CARRIAGE_TYPE_NAME," +
            ":CARRIAGE_TYPE_DESC," +
            ":CARRIAGE_SERVICE_CLASS," +
            ":CARRIAGE_SERVICE_CLASS_DESC," +
            ":CARRIAGE_OWNER," +
            ":CARRIAGE_CARRIER," +
            ":CARRIAGE_GENDER_SIGN," +
            ":CARRIAGE_ADD_SIGNS," +
            ":COST," +
            ":SEAT_COUNT," +
            ":SEATS," +
            ":ADD_SIGNS," +
            ":TIME_DESCRIPTION," +
            ":STATUS," +
            ":REGISTRATION_ALLOWED," +
            ":REGISTRATION_STATUS," +
            ":REGISTRATION_NEEDED," +
            "CURRENT TIMESTAMP," +
            ":PAYMENT_END_TIME," +
            ":REAL_DEPARTURE_DATETIME," +
            ":CURRENCY_CODE," +

            ":STARTING_DEPARTURE_DATETIME,"+
            ":IS_TRAIN_WITH_ASMBP,"+
            ":TRAIN_TYPE,"+
            ":COMPARTMENT_TYPE,"+
            ":DEPARTURE_STATION_CODE," +
            ":ARRIVAL_STATION_CODE," +
            ":IS_NEW," +
            ":IS_GLOBAL_PRICE," +
            ":COST_EURO," +
            ":TRIP_CLASS," +
            ":PAYMENT_METHOD," +
            ":CARRIAGE_SERVICE_CLASS_INT," +
            ":RETURN_ALLOWED," +

            "CURRENT TIMESTAMP" +
            ")";

    public static final String ADD_ORDER_PASSENGER = "INSERT INTO ETICKET.ORDER_PASSENGERS (" +
            "USER_ID," +
            "ORDER_TICKET_ID," +
            "FIRST_NAME," +
            "PATRONYMIC," +
            "LAST_NAME," +
            "DOCUMENT_TYPE," +
            "DOCUMENT_TYPE_NAME," +
            "DOCUMENT_NO," +
            "BIRTH_DATE," +
            "COUNTRY," +
            "SEX" +
            ") VALUES (" +
            ":USER_ID," +
            ":ORDER_TICKET_ID," +
            ":FIRST_NAME," +
            ":PATRONYMIC," +
            ":LAST_NAME," +
            ":DOCUMENT_TYPE," +
            ":DOCUMENT_TYPE_NAME," +
            ":DOCUMENT_NO," +
            ":BIRTH_DATE," +
            ":COUNTRY," +
            ":SEX" +
            ")";
    public static final String ADD_ORDER_TICKET = "INSERT INTO ETICKET.ORDER_TICKETS (" +
            "ORDER_ID," +
            "USER_ID," +
            "TICKET_NO," +
            "TICKET_ID," +
            "COST," +
            "CURRENCY_CODE," +

            "TICKET_EXPRESS_NO," +
            "COST_EURO," +
            "TARIFF," +
            "TICKET_TARIFF," +
            "RESERVED_SEAT_TARIFF," +
            "INSURANCE_TARIFF," +
            "SERVICE_TARIFF," +
            "SEATS," +
            "SEAT_DESCRIPTION," +
            "PASSENGER_COUNT," +
            "TICKET_STATUS," +
            "REGISTRATION_STATUS," +
            "SEAT_COUNT," +

            "TARIFF_VAT," +
            "SERVICE_TARIFF_VAT," +
            "COMMISSION_FEE," +
            "COMMISSION_VAT," +
            "TLIST_ISSUED," +
            "RESERVATION_NO," +


            "TARIFF_TYPE" +
            ") VALUES (" +
            ":ORDER_ID," +
            ":USER_ID," +
            ":TICKET_NO," +
            ":TICKET_ID," +
            ":COST," +
            ":CURRENCY_CODE," +

            ":TICKET_EXPRESS_NO," +
            ":COST_EURO," +
            ":TARIFF," +
            ":TICKET_TARIFF," +
            ":RESERVED_SEAT_TARIFF," +
            ":INSURANCE_TARIFF," +
            ":SERVICE_TARIFF," +
            ":SEATS," +
            ":SEAT_DESCRIPTION," +
            ":PASSENGER_COUNT," +
            ":TICKET_STATUS," +
            ":REGISTRATION_STATUS," +
            ":SEAT_COUNT," +

            ":TARIFF_VAT," +
            ":SERVICE_TARIFF_VAT," +
            ":COMMISSION_FEE," +
            ":COMMISSION_VAT," +
            ":TLIST_ISSUED," +
            ":RESERVATION_NO," +

            ":TARIFF_TYPE" +
            ")";


    public static final String ADD_ORDER_HISTORY_BUYING = "INSERT INTO ETICKET.ORDER_HISTORY (" +
            "ORDER_ID," +
            "\"TYPE\"," +
            "STATUS," +
            "\"COMMENT\"," +

            "SEAT_ISSUING_CENTER," +
            "ORDER_ISSUING_CENTER," +
            "SALE_POINT," +
            "SALE_RAILWAY," +
            "SALE_TERMINAL," +

            "UPDATE_TIME" +
            ") VALUES (" +
            ":ORDER_ID," +
            ":TYPE," +
            ":STATUS," +
            ":COMMENT," +
            ":SEAT_ISSUING_CENTER," +
            ":ORDER_ISSUING_CENTER," +
            ":SALE_POINT," +
            ":SALE_RAILWAY," +
            ":SALE_TERMINAL," +
            "CURRENT TIMESTAMP" +
            ")";

    public static final String ADD_ORDER_HISTORY = "INSERT INTO ETICKET.ORDER_HISTORY (" +
            "ORDER_ID," +
            "\"TYPE\"," +
            "STATUS," +
            "\"COMMENT\"," +
            "UPDATE_TIME" +
            ") VALUES (" +
            ":ORDER_ID," +
            ":TYPE," +
            ":STATUS," +
            ":COMMENT," +
            "CURRENT TIMESTAMP" +
            ")";

    public static final String ADD_ORDER_HISTORY_T = "INSERT INTO ETICKET.ORDER_HISTORY (" +
            "ORDER_ID," +
            "TICKET_ID," +
            "\"TYPE\"," +
            "STATUS," +
            "\"COMMENT\"," +
            "UPDATE_TIME" +
            ") VALUES (" +
            ":ORDER_ID," +
            ":TICKET_ID," +
            ":TYPE," +
            ":STATUS," +
            ":COMMENT," +
            "CURRENT TIMESTAMP" +
            ")";

    public static final String ADD_TRANSACTION_LOGS = "INSERT INTO ETICKET.TRANSACTION_LOGS (" +
            "IP_ADDRESS," +
            "ORDER_ID," +
            "USER_ID," +
            "OPERATION_TYPE," +
            "STATUS," +
            "OPERATION_COMMENT," +
            "TIME" +
            ") VALUES (" +
            ":IP_ADDRESS," +
            ":ORDER_ID," +
            ":USER_ID," +
            ":OPERATION_TYPE," +
            ":STATUS," +
            ":OPERATION_COMMENT," +
            "CURRENT TIMESTAMP" +
            ")";

    public static final String ADD_TRANSACTION_LOGS_T = "INSERT INTO ETICKET.TRANSACTION_LOGS (" +
            "IP_ADDRESS," +
            "ORDER_ID," +
            "TICKET_ID," +
            "USER_ID," +
            "OPERATION_TYPE," +
            "STATUS," +
            "OPERATION_COMMENT," +
            "TIME" +
            ") VALUES (" +
            ":IP_ADDRESS," +
            ":ORDER_ID," +
            ":TICKET_ID," +
            ":USER_ID," +
            ":OPERATION_TYPE," +
            ":STATUS," +
            ":OPERATION_COMMENT," +
            "CURRENT TIMESTAMP" +
            ")";

}
