/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.exception;

/**
 *
 * @author mirek
 */
public class ChildNotFoundException extends RuntimeException{
    public ChildNotFoundException(String message) {
        super(message);
    }
    
}
