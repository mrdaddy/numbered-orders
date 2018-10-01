package com.rw.numbered.orders.dao;

public interface OperationsAndStatuses {
    public static String ORDER_STATUS_BUY_EXPRESS = "B";
    public static String ORDER_STATUS_PAYED_EXPRESS = "P";
    public static String ORDER_STATUS_CANCELLED = "C";
    public static String ORDER_STATUS_IN_PROGRESS = "I";
    public static String ORDER_STATUS_IN_CANCELING = "N";
    public static String ORDER_STATUS_RETURNED = "R";
    public static String ORDER_STATUS_RETURNED_PART = "Y";

    public static int REG_STATUS_REGISTERED=1;
    public static int REG_STATUS_NOTREGISTERED=0;
    public static int REG_STATUS_IMPOSSIBLE = 3;

    public static String OP_TYPE_ORDER_BUYING="ORDER_BUYING";
    public static String OP_TYPE_USER_LOGIN="USER_LOGIN";
    public static String OP_ORDER_CANCELING="ORDER_CANCELING";
    public static String OP_ORDER_PAYMENT="ORDER_PAYMENT";
    public static String OP_ORDER_AFTER_PAYMENT = "ORDER_AFTER_PAYMENT";
    public static String OP_ORDER_IN_PAYMENT="ORDER_IN_PAYMENT";
    public static String OP_ORDER_TO_BUYING ="ORDER_TO_BUYING";
    public static String OP_ORDER_EXPRESS_PAYMENT="ORDER_EXPRESS_PAYMENT";
    public static String OP_ORDER_PAYMENT_INVALID="ORDER_PAYMENT_INVALID";
    public static String OP_ORDER_PAYMENT_CANCELING="ORDER_PAYMENT_CANCELING";
    public static String OP_ORDER_PAYMENT_CANCELING_INVALID = "ORDER_PAYMENT_CANCELING_INVALID";
    public static String OP_ORDER_PAYMENT_IN_CANCELING="ORDER_PAYMENT_IN_CANCELING";
    public static String OP_TICKET_RETURN = "TICKET_RETURN";

    public static String OP_ORDER_REGISTRATION = "ORDER_REGISTRATION";
    public static String OP_ORDER_REGISTRATION_CANCELING = "ORDER_REGISTRATION_CANCELING";

    public static String OP_STATUS_SUCCESS="S";
    public static String OP_STATUS_UNSUCCESS="U";

}
