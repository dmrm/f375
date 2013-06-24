/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import ru.ifmo.pe.oatmeal.daos.AffairDAO;
import ru.ifmo.pe.oatmeal.daos.UserDAO;

/**
 *
 * @author D
 */
@Stateless
public class Affair {
    
    @EJB
    private AffairDAO affairDAO;
    @EJB
    private UserDAO userDAO;
    
    public List<ru.ifmo.pe.oatmeal.model.Affair> getUserAffairs(){
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return affairDAO.findAllByUser(user);
    }

    public void createAffair() {        
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();        
        ru.ifmo.pe.oatmeal.model.Affair affair = new ru.ifmo.pe.oatmeal.model.Affair();     
        affair.setOwner(userDAO.find(user));
        affairDAO.save(affair);
    }
    
    public ru.ifmo.pe.oatmeal.model.Affair affairByIdAndUser(String login, long affairId){
        return affairDAO.affairsByIdAndUser(login, affairId);
    }
    
}
