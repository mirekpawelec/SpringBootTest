/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author mirek
 */
public class PersonData implements Serializable{
    private Long id;
    private String firstName;
    private String secondName;
    private String pesel;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getSecondName() {return secondName;}
    public void setSecondName(String secondName) {this.secondName = secondName;}
    public String getPesel() {return pesel;}
    public void setPesel(String pesel) {this.pesel = pesel;}

    public PersonData() {}
    public PersonData(String firstName, String secondName, String pesel) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.pesel = pesel;
    }
    public PersonData(Long id, String firstName, String secondName, String pesel) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.pesel = pesel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash += this.id.intValue();
        hash += this.firstName.hashCode();
        hash += this.secondName.hashCode();
        hash += this.pesel.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof PersonData) {
            final PersonData personData = (PersonData) obj;
            if (this.id.compareTo(personData.id)!=0) return false;
            if (!this.firstName.equals(personData.firstName)) return false;
            if (!this.secondName.equals(personData.secondName)) return false;
            if (!this.pesel.equals(personData.pesel)) return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PersonData{" + 
                "id=" + id + 
                ", firstName=" + firstName + 
                ", secondName=" + secondName + 
                ", pesel=" + pesel + 
                '}';
    }
    
}
