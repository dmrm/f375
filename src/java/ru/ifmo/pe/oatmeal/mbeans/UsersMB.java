/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import ru.ifmo.pe.oatmeal.business.Person;
import ru.ifmo.pe.oatmeal.model.User;

/**
 *
 * @author D
 */
@ManagedBean
public class UsersMB implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private transient Person person;
    
    public List<User> getUsers(){
        return person.getUsers();
    }
    
    public String currentUser(){
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }
    
}
