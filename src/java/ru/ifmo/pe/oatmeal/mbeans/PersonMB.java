/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ru.ifmo.pe.oatmeal.business.Person;

/**
 *
 * @author D
 */
@ManagedBean
public class PersonMB {
    
    @EJB
    private Person person;
    
    public String getPerson(){
        return person.getUserName();
    }
       
    public void logout() throws IOException{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect("../login.xhtml");
    }
    
}
