/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author mirek
 */
public class Child implements Serializable{
    private Long id;
    private PersonData personData;
    private String sex;
    private Family family;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public PersonData getPersonData() {return personData;}
    public void setPersonData(PersonData personData) {this.personData = personData;}
    public String getSex() {return sex;}
    public void setSex(String sex) {this.sex = sex;}
    public Family getFamily() {return family;}
    public void setFamily(Family family) {this.family = family;}

    public Child() {
        this.personData = new PersonData();
        this.family = new Family();
    }
    public Child(PersonData personData, String sex, Family family) {
        this();
        this.personData = personData;
        this.sex = sex;
        this.family = family;
    }

    public Child(Long id, PersonData personData, String sex, Family family) {
        this();
        this.id = id;
        this.personData = personData;
        this.sex = sex;
        this.family = family;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash += this.id.intValue();
        hash += this.personData.hashCode();
        hash += this.sex.hashCode();
        hash += this.family.hashCode();
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj instanceof Child){
            Child child = (Child) obj;
            if (this.id.compareTo(child.id)!=0) return false;
            if (!this.personData.equals(child.personData)) return false;
            if (!this.sex.equals(child.sex)) return false;
            if (!this.family.equals(child.family)) return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Child{" + 
                "id=" + id + 
                ", personData=" + personData + 
                ", sex=" + sex + 
                ", familyId=" + family + 
                '}';
    }

}
