package com.rw.numbered.orders.dao;

public class Monitoring {
    public enum Type {

        ETICKET_XMLGATEWAY_ERR_MONITOR,
        XMLGATEWAY_EXPRESS_ERR_MONITOR,
        DB2_ERR_MONITOR,
        DB2_ERR2_MONITOR,
        DB2_ERR3_MONITOR,
        DB2_ERR4_MONITOR,
        DB2_ERR5_MONITOR,
        ORDER_CANCEL_MONITOR,
        ORDER_ERIP_ERR_MONITOR,
        ORDER_ERIP_ERR2_MONITOR,
        ORDER_ERIP_ERR3_MONITOR,
        ORDER_IBB_ERR_MONITOR,
        ETICKET_EXPRESS_STATUS_MONITOR,
        ETICKET_EXPRESS_ER_STATUS_MONITOR,
        ORDER_ASSIST_ERR_MONITOR,
        ORDER_ONLINE_RETURN_ERR_MONITOR,
        ORDER_EXPRESS_ERR_MONITOR
        ;

        public int getIntValue() {
            int val = 0;
            switch(this) {
                case ETICKET_XMLGATEWAY_ERR_MONITOR:
                    val = 0;
                    break;
                case XMLGATEWAY_EXPRESS_ERR_MONITOR:
                    val = 1;
                    break;
                case DB2_ERR_MONITOR:
                    val = 2;
                    break;
                case ORDER_CANCEL_MONITOR:
                    val = 3;
                    break;
                case ORDER_ERIP_ERR_MONITOR:
                    val = 4;
                    break;
                case ORDER_ERIP_ERR2_MONITOR:
                    val = 5;
                    break;
                case DB2_ERR2_MONITOR:
                    val = 6;
                    break;
                case ORDER_IBB_ERR_MONITOR:
                    val = 7;
                    break;
                case ORDER_ASSIST_ERR_MONITOR:
                    val = 8;
                    break;
                case ORDER_ONLINE_RETURN_ERR_MONITOR:
                    val = 9;
                    break;
                case DB2_ERR4_MONITOR:
                    val = 10;
                    break;
                case DB2_ERR5_MONITOR:
                    val = 11;
                    break;
                case ORDER_EXPRESS_ERR_MONITOR:
                    val = 12;
                    break;
            }
            return val;
        }
    }
}
