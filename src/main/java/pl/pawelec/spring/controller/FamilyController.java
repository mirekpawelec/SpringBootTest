/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.pawelec.spring.exception.FamilyNoCreatedException;
import pl.pawelec.spring.model.Family;
import pl.pawelec.spring.service.FamilyService;

/**
 *
 * @author mirek
 */
@RestController
@RequestMapping(path = {"/families"})
public class FamilyController {
    private FamilyService familyService;
    
    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }
    
    @RequestMapping(path = {"/{id}"})
    public Family getFamily(@PathVariable("id") Long id){
        return familyService.get(id);
    }
    
    @RequestMapping
    public List<Family> getFamilies(){
        return familyService.getAll();
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Family> createFamily(@RequestBody Family family){
        try{
            Family newFamily = familyService.create(family);
        }catch(Exception e){
            throw new FamilyNoCreatedException();
        }
        return new ResponseEntity<>(family, HttpStatus.CREATED);
    }
    
    
    
    
    
}
