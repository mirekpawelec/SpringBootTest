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
import pl.pawelec.spring.exception.PersonDataNotFoundException;
import pl.pawelec.spring.model.Father;
import pl.pawelec.spring.repository.FatherRepository;
import pl.pawelec.spring.service.FatherService;
import pl.pawelec.spring.service.PersonDataService;

/**
 *
 * @author mirek
 */
@Service
public class FatherServiceImpl implements FatherService{
    private FatherRepository fatherRepository;
    private PersonDataService personDataService;
    
    @Autowired
    public FatherServiceImpl(FatherRepository fatherRepository, PersonDataService personDataService) {
        this.fatherRepository = fatherRepository;
        this.personDataService = personDataService;
    }

    public Father get(Serializable id) {
        return fatherRepository.get(id);
    }

    public List<Father> getAll() {
        return fatherRepository.getAll();
    }

    public Father create(Father entity) {
        return fatherRepository.create(entity);
    }

    public boolean update(Father entity) {
        return fatherRepository.update(entity);
    }

    public boolean delete(Father entity) {
        return fatherRepository.delete(entity);
    }
    
}
