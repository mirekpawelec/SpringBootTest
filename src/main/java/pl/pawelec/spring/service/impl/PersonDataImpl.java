/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pawelec.spring.model.PersonData;
import pl.pawelec.spring.repository.PersonDataRepository;
import pl.pawelec.spring.service.PersonDataService;

/**
 *
 * @author mirek
 */
@Service
public class PersonDataImpl implements PersonDataService{
    private PersonDataRepository personDataRepository;
    
    @Autowired
    public PersonDataImpl(PersonDataRepository personDataRepository) {
        this.personDataRepository = personDataRepository;
    }

    public PersonData get(Serializable id) {
        return personDataRepository.get(id);
    }

    public List<PersonData> getAll() {
        return personDataRepository.getAll();
    }

    public PersonData create(PersonData entity) {
        return personDataRepository.create(entity);
    }

    public boolean update(PersonData entity) {
        return personDataRepository.update(entity);
    }   

    public boolean delete(PersonData entity) {
        return personDataRepository.delete(entity);
    }
    
}
