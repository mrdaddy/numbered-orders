package com.rw.numbered.orders.dao;

import com.rw.numbered.orders.dto.order.Order;
import com.rw.numbered.orders.dto.request.SearchOrderFilter;
import com.rw.numbered.orders.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.DocumentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.*;

@Transactional
@Repository
public class OrderDao implements SQLQueries{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Order> getOrders(SearchOrderFilter searchOrderFilter, User user) {

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        return orders;
    }

    public String getExpressExpressDocType(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("CODE", code);
        String expressCode = jdbcTemplate.queryForObject(DOCUMENT_TYPE_EXPRESS_NO, params, (rs, rowNum) -> rs.getString("EXPRESS_CODE"));
        return  expressCode;
    }



}
