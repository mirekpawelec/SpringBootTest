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
import pl.pawelec.spring.exception.FatherNotFoundException;
import pl.pawelec.spring.exception.PersonDataNotFoundException;
import pl.pawelec.spring.model.Family;
import pl.pawelec.spring.repository.FamilyRepository;
import pl.pawelec.spring.service.FamilyService;
import pl.pawelec.spring.service.FatherService;
import pl.pawelec.spring.service.PersonDataService;

/**
 *
 * @author mirek
 */
@Service
public class FamilyServiceImpl implements FamilyService{
    private FamilyRepository  familyRepository;
    private FatherService fatherService;
    
    @Autowired
    public FamilyServiceImpl(FamilyRepository familyRepository, FatherService fatherService) {
        this.familyRepository = familyRepository;
        this.fatherService = fatherService;
    }
    
    public Family get(Serializable id) {
        return familyRepository.get(id);
    }

    public List<Family> getAll() {
        return familyRepository.getAll();
    }

    public Family create(Family entity) {
        return familyRepository.create(entity);
    }

    public boolean update(Family entity) {
        return familyRepository.update(entity);
    }

    public boolean delete(Family entity) {
        return familyRepository.delete(entity);
    }
    
}
