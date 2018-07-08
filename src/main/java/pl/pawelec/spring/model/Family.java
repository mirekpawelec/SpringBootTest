/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import pl.pawelec.spring.converter.json.FamilyDeserializer;

/**
 *
 * @author mirek
 */
@JsonDeserialize(using = FamilyDeserializer.class)
public class Family implements Serializable{
    private Long id;
    private Father father;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Father getFather() {return father;}
    public void setFather(Father father) {this.father = father;}

    public Family() {
        this.father = new Father();
    }
    public Family(Father father) {
        this();
        this.father = father;
    }
    public Family(Long id, Father father) {
        this();
        this.id = id;
        this.father = father;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash += this.id.intValue();
        hash += this.father.hashCode();
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Family) {
            final Family family = (Family) obj;
            if (this.id.compareTo(family.id)!=0) return false;
            if (!this.father.equals(family.father)) return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Family{" + 
                "id=" + id + 
                ", father=" + father +
                '}';
    }
     
}
