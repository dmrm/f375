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
import ru.ifmo.pe.oatmeal.daos.EvidenceDAO;
import ru.ifmo.pe.oatmeal.daos.UserDAO;
import ru.ifmo.pe.oatmeal.model.Evidence;

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
    @EJB
    private EvidenceDAO eviDAO;
    
    public List<ru.ifmo.pe.oatmeal.model.Affair> getUserAffairs(){
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return affairDAO.findAllByUser(userDAO.find(user));
    }

    public void createAffair(String description) {        
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();        
        ru.ifmo.pe.oatmeal.model.Affair affair = new ru.ifmo.pe.oatmeal.model.Affair();     
        affair.setOwner(userDAO.find(user));
        affair.setDescription(description);
        affairDAO.save(affair);
    }
    
    public ru.ifmo.pe.oatmeal.model.Affair affairByIdAndUser(String login, long affairId){
        return affairDAO.affairByIdAndUser(userDAO.find(login), affairId);
    }
    
    public List<Evidence> getAffairEvidences(long id){
        return eviDAO.getEvisByAffair(affairDAO.find(id));
    }
    
    public void createEvidence(long id, String description, String path){
        Evidence evi = new Evidence();
        evi.setAffair(affairDAO.find(id));
        evi.setDescription(description);
        evi.setEviPath(path);
        eviDAO.save(evi);
    }
    
}
