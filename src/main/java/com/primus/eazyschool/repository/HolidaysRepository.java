package com.primus.eazyschool.repository;

import com.primus.eazyschool.model.Holiday;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidaysRepository {

    public final JdbcTemplate jdbcTemplate;

    public HolidaysRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHoliday(){

        String sql = "SELECT * from HOLIDAYS";

        var rowMapper = new BeanPropertyRowMapper<>(Holiday.class);

        return jdbcTemplate.query(sql,rowMapper);
    }
}
