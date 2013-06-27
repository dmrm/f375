/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import ru.ifmo.pe.oatmeal.daos.AffairDAO;
import ru.ifmo.pe.oatmeal.daos.RequestDAO;
import ru.ifmo.pe.oatmeal.daos.UserDAO;

/**
 *
 * @author s149297
 */
@Stateless
public class Requests {
    
    @EJB
    private AffairDAO affiars;
    @EJB
    private RequestDAO req;
    @EJB
    private UserDAO userDAO;
    
    public void deleteRequest(long rId){
        req.remove(rId);
    }
    
    public void acceptRequest(long rId){
        ru.ifmo.pe.oatmeal.model.Affair aff = new ru.ifmo.pe.oatmeal.model.Affair();
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        ru.ifmo.pe.oatmeal.model.Request reqst = req.find(rId);
        aff.setDescription(reqst.getText());
        aff.setOwner(userDAO.find(user));
        affiars.save(aff);
        deleteRequest(rId);
    }
    
}
