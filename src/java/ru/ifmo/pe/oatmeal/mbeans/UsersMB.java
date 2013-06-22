/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import ru.ifmo.pe.oatmeal.business.Person;
import ru.ifmo.pe.oatmeal.model.User;

/**
 *
 * @author D
 */
@ManagedBean
public class UsersMB {
    
    @EJB
    private Person person;
    
    public List<User> getUsers(){
        return person.getUsers();
    }
    
}
