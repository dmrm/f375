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
    private int id;
    private String requestBy;
    private String text;
    private String handlerType;

    public int getId() {
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

    public String getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(String handler) {
        this.handlerType = handler;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }
    
        
    
}
