package com.rw.numbered.orders.dao;

import com.rw.numbered.orders.dto.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //public Order createOr
}
