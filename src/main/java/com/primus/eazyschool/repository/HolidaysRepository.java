package com.primus.eazyschool.repository;

import com.primus.eazyschool.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, String> {

    /*public final JdbcTemplate jdbcTemplate;

    public HolidaysRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHoliday(){

        String sql = "SELECT * from holidays";

        var rowMapper = new BeanPropertyRowMapper<>(Holiday.class);

        return jdbcTemplate.query(sql,rowMapper);
    }*/

    List<Holiday> findAll();

}
