/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import ru.ifmo.pe.oatmeal.daos.UserDAO;
import ru.ifmo.pe.oatmeal.model.User;

/**
 *
 * @author D
 */
@Stateless
public class Person {
      
    @EJB
    private UserDAO userDAO;
    
    public String getUserName(){
        return userDAO.find(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()).getName();
    }
    
    public void saveUser(User user){
        userDAO.save(user);
    }

    public String getPhoto() {
        return userDAO.find(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()).getPhoto();
    }

    public List<User> getUsers() {
        return userDAO.getAllUsers();
    }
    
    public String findLessBusyUser() {
        return userDAO.lessBusyUser();
    }
    
}
