/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import ru.ifmo.pe.oatmeal.business.Person;
import ru.ifmo.pe.oatmeal.model.Affair;

/**
 *
 * @author D
 */
@ManagedBean
public class PersonMB implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private transient Person person;
    @EJB
    private transient ru.ifmo.pe.oatmeal.business.Affair affair;
    
    public String getPerson(){
        return person.getUserName();
    }
    
    public String getPhoto(){
        return person.getPhoto();
    }
       
    public void logout() throws IOException{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect("../login.xhtml");
    }
    
    public String createAffair(){
        affair.createAffair(null);
        return "person.xhtml?faces-redirect=true";
    }
    
    public List<Affair> getAffairs(){
        return affair.getUserAffairs();
    }
    
    public List<Affair> getGuestAffairs(){
        return affair.getUserGuestAffairs();
    }
    
}
