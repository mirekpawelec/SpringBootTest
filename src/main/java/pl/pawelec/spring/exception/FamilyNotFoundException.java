/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author mirek
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FamilyNotFoundException extends RuntimeException{
    public FamilyNotFoundException() {
        super("No data found!");
    }
    public FamilyNotFoundException(String message) {
        super(message);
    }
    
}
