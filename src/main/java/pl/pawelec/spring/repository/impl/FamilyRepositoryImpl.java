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
import pl.pawelec.spring.exception.FamilyNotFoundException;
import pl.pawelec.spring.model.Family;
import pl.pawelec.spring.model.Father;
import pl.pawelec.spring.model.PersonData;
import pl.pawelec.spring.repository.FamilyRepository;
import pl.pawelec.spring.repository.FatherRepository;

/**
 *
 * @author mirek
 */
@Repository
public class FamilyRepositoryImpl implements FamilyRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FatherRepository fatherRepository;
    
    @Transactional(readOnly = true)
    public Family get(Serializable id) {
        try{
            String selectSql = "SELECT id, father_id FROM family WHERE id=?";
            return jdbcTemplate.queryForObject(
                    selectSql, 
                    new Object[]{id}, 
                    (rs, i)->{
                        return new Family(rs.getLong("id"), fatherRepository.get(rs.getLong("father_id")));
                    });
        }catch(EmptyResultDataAccessException ere){
            throw new FamilyNotFoundException("No data found!");
        }
    }

    @Transactional(readOnly = true)
    public List<Family> getAll() {
        try{
            String selectSql = "SELECT id, father_id FROM family";
            return jdbcTemplate.query(
                    selectSql,
                    (rs, i)->{ 
                        return new Family(rs.getLong("id"), fatherRepository.get(rs.getLong("father_id")));
                    });
        }catch(EmptyResultDataAccessException ere){
            throw new FamilyNotFoundException("No data found!");
        }
    }

    public Family create(Family entity) {
        String insertSql = "INSERT INTO family (father_id) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if(entity.getFather().getId()!=null && entity.getFather().getId()>0){
            jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                        ps.setLong(1, entity.getFather().getId());
                        return ps;
                    }
                }, 
                keyHolder);
        }else{
            StringBuffer sb = new StringBuffer();
            if(entity.getFather().getId()==null || entity.getFather().getId()==0) sb.append("father_id, ");
            throw new IllegalArgumentException("Not all required fields have been given ["+sb.toString().substring(0, sb.length()-2) +"]!");
        }
        Long newFamilyId = keyHolder.getKey().longValue();
        entity.setId(newFamilyId);
        return entity;
    }

    public boolean update(Family entity) {
        String updateSql = "UPDATE family SET father_id=? WHERE id=?";
        try{
            return jdbcTemplate.update(updateSql, entity.getFather().getId(), entity.getId())>0;
        }catch(DataAccessException e){
            return false;
        }
    }

    public boolean delete(Family entity) {
        String deleteSql = "DELETE FROM family WHERE id=?";
        try{
            return jdbcTemplate.update(deleteSql, entity.getId())>0;
        }catch(DataAccessException e){
            return false;
        }
    }
    
}
