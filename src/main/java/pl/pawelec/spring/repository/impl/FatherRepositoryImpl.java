/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.repository.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.spring.exception.FatherNotFoundException;
import pl.pawelec.spring.model.Father;
import pl.pawelec.spring.model.PersonData;
import pl.pawelec.spring.repository.FatherRepository;
import pl.pawelec.spring.repository.PersonDataRepository;

/**
 *
 * @author mirek
 */
@Repository
public class FatherRepositoryImpl implements FatherRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PersonDataRepository personDataRepository;
    
    @Transactional(readOnly = true)
    public Father get(Serializable id) {
        try{
            String selectSql= "SELECT id, person_data_id, birth_date FROM father WHERE id = ?";
            return jdbcTemplate.queryForObject(
                        selectSql, 
                        new Object[]{id},
                        (rs, i) -> {
                            return new Father(
                                rs.getLong("id"), 
                                personDataRepository.get(rs.getLong("person_data_id")), 
                                rs.getDate("birth_date")
                            );
                        }
            );
        }catch(EmptyResultDataAccessException ere){
            throw new FatherNotFoundException("No data found!");
        }
    }

    @Transactional(readOnly = true)
    public List<Father> getAll() {
        try{
            String selectSql= "SELECT id, person_data_id, birth_date FROM father";
            return jdbcTemplate.query(
                        selectSql,
                        (rs, i) -> {
                            return new Father(
                                rs.getLong("id"), 
                                personDataRepository.get(rs.getLong("person_data_id")),
                                rs.getDate("birth_date"));
                        }
            );
        }catch(EmptyResultDataAccessException ere){
            throw new FatherNotFoundException("No data found!");
        }
    }

    public Father create(Father entity) throws IllegalArgumentException {
        String insertSql = "INSERT INTO father (person_data_id, birth_date) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if(entity.getPersonData().getId()!=null && entity.getBirthDate()!=null){
            jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                        ps.setLong(1, entity.getPersonData().getId());
                        ps.setDate(2, new java.sql.Date(entity.getBirthDate().getTime()));
                        return ps;
                    }
                },
                keyHolder
            );
        }else{
            StringBuffer sb = new StringBuffer();
            if(entity.getPersonData().getId()==null) sb.append("person_data_id, ");
            if(entity.getBirthDate()==null) sb.append("birth_date, ");
            throw new IllegalArgumentException("Not all required fields have been given ["+sb.toString().substring(0, sb.length()-2) +"]!");
        }
        Long newFatherId = keyHolder.getKey().longValue();
        entity.setId(newFatherId);
        return entity;
    }

    public boolean update(Father entity) {
        String updateSql = "UPDATE father SET person_data_id=?, birth_date=? WHERE id=?";
        try{
            return jdbcTemplate.update(updateSql, entity.getPersonData().getId(), entity.getBirthDate(), entity.getId())>0;
        }catch(DataAccessException e){
            return false;
        }
    }

    public boolean delete(Father entity) {
        String deleteSql = "DELETE FROM father WHERE id=?";
        try{
            return jdbcTemplate.update(deleteSql, entity.getId())>0;
        }catch(DataAccessException e){
            return false;
        }
    }
    
}
