/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author D
 */
@Entity
public class Request implements Serializable{
    
    @Id @GeneratedValue    
    private long id;
    private String requestBy;
    private String text;
    private String handlerType;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHandler() {
        return handlerType;
    }

    public void setHandler(String handler) {
        this.handlerType = handler;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }
    
        
    
}
