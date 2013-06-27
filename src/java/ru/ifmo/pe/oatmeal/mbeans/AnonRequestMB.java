/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import ru.ifmo.pe.oatmeal.business.Person;

/**
 *
 * @author D
 */
@ManagedBean
public class AnonRequestMB {
    
    @EJB
    private Person person;
    
    private String text;
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void sendAnonymously(){
        person.findLessBusyUser();
    }
    
}