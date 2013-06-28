/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import ru.ifmo.pe.oatmeal.business.Person;

/**
 *
 * @author D
 */
@ManagedBean
public class AnonRequestMB implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private transient Person person;
    
    private String text;
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String sendAnonymously(){
        person.findLessBusyUser();
        return "anonymous_request.xhtml?success=true&faces-redirect=true";
    }
    
}
