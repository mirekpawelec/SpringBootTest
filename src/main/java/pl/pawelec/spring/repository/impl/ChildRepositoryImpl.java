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
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.spring.exception.ChildNotFoundException;
import pl.pawelec.spring.model.Child;
import pl.pawelec.spring.model.PersonData;
import pl.pawelec.spring.repository.ChildRepository;
import pl.pawelec.spring.repository.FamilyRepository;
import pl.pawelec.spring.repository.PersonDataRepository;

/**
 *
 * @author mirek
 */
@Repository
public class ChildRepositoryImpl implements ChildRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate; 
    @Autowired
    private PersonDataRepository personDataRepository;
    @Autowired
    private FamilyRepository familyRepository;
    
    @Transactional(readOnly = true)
    public Child get(Serializable id) throws ChildNotFoundException{
        try{
            String selectOneSql = "SELECT id, sex, person_data_id, family_id FROM child WHERE id=?";
            return jdbcTemplate.queryForObject(
                        selectOneSql, 
                        new Object[]{id},
                        (rs, i) -> {
                            return new Child(
                                    rs.getLong("id"), 
                                    personDataRepository.get(rs.getLong("person_data_id")), 
                                    rs.getString("sex"), 
                                    familyRepository.get(rs.getLong("family_id"))
                            );
                        }
            );
        }catch(EmptyResultDataAccessException ere){
            throw new ChildNotFoundException("No data found!");
        }
    }

    @Transactional(readOnly = true)
    public List<Child> getAll() throws ChildNotFoundException{
        try{
            String selectAllSql = "SELECT id, sex, person_data_id, family_id FROM child";
            return jdbcTemplate.query(
                        selectAllSql, 
                        (rs, i) -> {
                            return new Child(
                                    rs.getLong("id"), 
                                    personDataRepository.get(rs.getLong("person_data_id")), 
                                    rs.getString("sex"), 
                                    familyRepository.get(rs.getLong("family_id"))
                            );
                        }
            );
        }catch(EmptyResultDataAccessException ere){
            throw new ChildNotFoundException("No data found!");
        }
    }

    public Child create(Child entity) throws IllegalArgumentException{
        String insertSql = "INSERT INTO child (person_data_id, sex, family_id) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if(entity.getPersonData().getId()!=null && entity.getSex()!=null && entity.getFamily().getId()!=null){
            jdbcTemplate.update(
                    new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                            PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                            ps.setLong(1, entity.getPersonData().getId());
                            ps.setString(2, entity.getSex());
                            ps.setLong(3, entity.getFamily().getId());
                            return ps;
                        }
                    }, 
                    keyHolder);
        }else{
            StringBuffer sb = new StringBuffer();
            if(entity.getPersonData().getId()==null) sb.append("person_data_id, ");
            if(entity.getSex()==null) sb.append("sex, ");
            if(entity.getFamily().getId()==null) sb.append("family_id, ");
            throw new IllegalArgumentException("Not all required fields have been given ["+sb.toString().substring(0, sb.length()-2) +"]!");
        }
        Long newChildId = keyHolder.getKey().longValue();
        entity.setId(newChildId);
        return entity;
    }

    public boolean update(Child entity) {
        String updateSql = "UPDATE child SET person_data_id=?, sex=?, family_id=? WHERE id=?";
        try{
            return jdbcTemplate.update(updateSql, entity.getPersonData().getId(), entity.getSex(), entity.getFamily().getId())>0;
        }catch(DataAccessException e){
            return false;
        }
    }
 
    public boolean delete(Child entity) {
        String deleteSql = "DELETE FROM child WHERE id=?";
        try{
            return jdbcTemplate.update(deleteSql, entity.getId())>0;
        }catch(DataAccessException e){
            return false;
        }
    }
    
}
