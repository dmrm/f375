/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ru.ifmo.pe.oatmeal.daos.UserDAO;

/**
 *
 * @author D
 */
@Stateless
public class Person {
      
    @EJB
    private UserDAO userDAO;
    
    public String getUserName(){
//        return userDAO.find(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()).getName();
        return "Some name";
    }    
}
