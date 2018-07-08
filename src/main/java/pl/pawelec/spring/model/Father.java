/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import pl.pawelec.spring.converter.json.FatherDeserializer;

/**
 *
 * @author mirek
 */
@JsonDeserialize(using = FatherDeserializer.class)
public class Father implements Serializable{
    private Long id;
    private PersonData personData;
    private Date birthDate;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public PersonData getPersonData() {return personData;}
    public void setPersonData(PersonData personData) {this.personData = personData;}
    public Date getBirthDate() {return birthDate;}
    public void setBirthDate(Date birthDate) {this.birthDate = birthDate;}

    public Father() {
        this.personData = new PersonData();
    }
    public Father(Date birthDate, PersonData personData) {
        this();
        this.birthDate = birthDate;
        this.personData = personData;
    }
//    @JsonCreator
//    public Father(@JsonProperty("id") Long id, @JsonProperty("personData") PersonData personData, @JsonProperty("birthDate") Date birthDate) {
    public Father(Long id, PersonData personData, Date birthDate) {
        this();
        this.id = id;
        this.personData = personData;
        this.birthDate = birthDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash += this.id.intValue();
        hash += this.personData.hashCode();
        hash += this.birthDate.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Father) {
            final Father father = (Father) obj;
            if (this.id.compareTo(father.id)!=0) return false;
            if (!this.personData.equals(father.personData)) return false;
            if (!this.birthDate.equals(father.birthDate)) return false;
            return true;          
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Father{" + 
                "id=" + id + 
                ", personData=" + personData + 
                ", birthDate=" + birthDate + 
                '}';
    }
}
