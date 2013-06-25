/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author D
 */
@Entity
public class Evidence implements Serializable{
    
    @Id @GeneratedValue
    private long id;
    @ManyToOne
    private Affair affair;
    private String description;
    private String eviPath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Affair getAffair() {
        return affair;
    }

    public void setAffair(Affair affair) {
        this.affair = affair;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEviPath() {
        return eviPath;
    }

    public void setEviPath(String eviPath) {
        this.eviPath = eviPath;
    }
    
}
