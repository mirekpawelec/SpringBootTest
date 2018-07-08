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
import pl.pawelec.spring.model.Child;
import pl.pawelec.spring.repository.ChildRepository;
import pl.pawelec.spring.service.ChildService;

/**
 *
 * @author mirek
 */
@Service
public class ChildServiceImpl implements ChildService{
    private ChildRepository childRepository;

    @Autowired
    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Child get(Serializable id) {
        return childRepository.get(id);
    }

    public List<Child> getAll() {
        return childRepository.getAll();
    }

    public Child create(Child entity) {
        return childRepository.create(entity);
    }

    public boolean update(Child entity) {
        return childRepository.update(entity);
    }

    public boolean delete(Child entity) {
        return childRepository.delete(entity);
    }
    
}
