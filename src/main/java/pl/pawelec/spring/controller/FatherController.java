/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pawelec.spring.service.FatherService;

/**
 *
 * @author mirek
 */
@RestController
@RequestMapping(path = {"/fathers"})
public class FatherController {
    private FatherService fatherService;
    
    @Autowired
    public FatherController(FatherService fatherService) {
        this.fatherService = fatherService;
    }
    
}
