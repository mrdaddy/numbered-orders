package com.rw.numbered.orders.dao;

import com.rw.numbered.orders.service.utils.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TranslateDao implements SQLQueries {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getStationNameByCode(String code, String lang) {
        Map<String, Object> params = new HashMap<>();
        params.put("STATION_CODE", code);
        String query = DBUtils.formatQueryWithParams(STATION_NAME_BY_CODE, lang.toUpperCase());
        return jdbcTemplate.queryForObject(
                query, params, (rs, rowNum) -> rs.getString("NAME"));
    }

    public String getExpressError(String expressError,String lang) {
        Map<String, Object> params = new HashMap<>();
        params.put("EXPRESS_ERROR", expressError);
        String query = DBUtils.formatQueryWithParams(EXPRESS_ERROR, lang.toUpperCase());
        try {
            expressError = (jdbcTemplate.queryForObject(
                    query, params, (rs, rowNum) -> rs.getString("SYSTEM_ERROR")));
        } catch (EmptyResultDataAccessException e) {
        }
        return expressError;
    }

    public String getCarTypeName(String type,String lang) {
        Map<String, Object> params = new HashMap<>();
        params.put("CODE", type);
        String query = DBUtils.formatQueryWithParams(CAR_TYPE_BY_CODE, lang.toUpperCase());
        try {
            type = (jdbcTemplate.queryForObject(
                    query, params, (rs, rowNum) -> rs.getString("NAME")));
        } catch (EmptyResultDataAccessException e) {
        }
        return type;
    }

}
