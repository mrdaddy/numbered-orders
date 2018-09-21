package com.rw.numbered.orders.dao;

public interface SQLQueries {
    static String DOCUMENT_TYPE_EXPRESS_NO = "SELECT EXPRESS_CODE FROM ETICKET.DOCUMENT_TYPES WHERE CODE = :CODE";

}
