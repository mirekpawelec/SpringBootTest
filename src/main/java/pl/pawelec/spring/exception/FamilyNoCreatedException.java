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
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FamilyNoCreatedException extends RuntimeException{
    public FamilyNoCreatedException() {
        super("The family has not been created!");
    }
    public FamilyNoCreatedException(String message) {
        super(message);
    }
    public FamilyNoCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
