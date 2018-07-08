/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.repository.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.spring.exception.PersonDataNotFoundException;
import pl.pawelec.spring.model.PersonData;
import pl.pawelec.spring.repository.PersonDataRepository;

/**
 *
 * @author mirek
 */
@Repository
public class PersonDataRepositoryImpl implements PersonDataRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Transactional(readOnly = true)
    public PersonData get(Serializable id) {
        try{
            String selectSql = "SELECT * FROM person_data WHERE id=?";
            return jdbcTemplate.queryForObject(
                        selectSql, 
                        new Object[]{id}, 
                        (rs, i) -> {
                            return new PersonData(rs.getLong("id"), 
                                                  rs.getString("first_name"), 
                                                  rs.getString("second_name"), 
                                                  rs.getString("pesel"));
                        }
            );
        }catch(EmptyResultDataAccessException ere){
            throw new PersonDataNotFoundException("No data found!");
        }
    }
    
    @Transactional(readOnly = true)
    public List<PersonData> getAll() {
        try{
            String selectSql = "SELECT * FROM person_data";
            return jdbcTemplate.query(
                        selectSql, 
                        (rs, i) -> {
                            return new PersonData(rs.getLong("id"), 
                                                  rs.getString("first_name"), 
                                                  rs.getString("second_name"), 
                                                  rs.getString("pesel"));
                        }
            );
        }catch(EmptyResultDataAccessException ere){
            throw new PersonDataNotFoundException("No data found!");
        }
    }

    public PersonData create(PersonData entity) throws IllegalArgumentException {
        String insertSql = "INSERT INTO person_data (first_name, second_name, pesel) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if(entity.getFirstName()!=null && !entity.getFirstName().isEmpty() && 
           entity.getSecondName()!=null && !entity.getSecondName().isEmpty() &&
           entity.getPesel()!=null && !entity.getPesel().isEmpty()){ 
            jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, entity.getFirstName());
                        ps.setString(2, entity.getSecondName());
                        ps.setString(3, entity.getPesel());
                        return ps;
                    }
                }, 
                keyHolder
            );
        }else{
            StringBuffer sb = new StringBuffer();
            if(entity.getFirstName()==null || entity.getFirstName().isEmpty()) sb.append("first_name, ");
            if(entity.getSecondName()==null || entity.getSecondName().isEmpty()) sb.append("second_name, ");
            if(entity.getPesel()==null || entity.getPesel().isEmpty()) sb.append("pesel, ");
            throw new IllegalArgumentException("Not all required fields have been given ["+sb.toString().substring(0, sb.length()-2) +"]!");
        }
        Long newPersonDataId = keyHolder.getKey().longValue();
        entity.setId(newPersonDataId); 
        return entity;                  
    }

    public boolean update(PersonData entity) {
        String updateSql = "UPDATE person_data SET first_name=?, second_name=?, pesel=? WHERE id=?";
        try{
            return jdbcTemplate.update(updateSql, entity.getFirstName(), entity.getSecondName(), entity.getPesel(), entity.getId())>0;
        }catch(DataAccessException e){
            return false;
        }
    }

    public boolean delete(PersonData entity) {
        String deleteSql = "DELETE FROM person_data WHERE id=?";
        try{
            return jdbcTemplate.update(deleteSql, entity.getId())>0;
        }catch(DataAccessException e){
            return false;
        }
    }
    
}
