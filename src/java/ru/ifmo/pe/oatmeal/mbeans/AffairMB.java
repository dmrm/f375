/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import ru.ifmo.pe.oatmeal.business.Affair;
import ru.ifmo.pe.oatmeal.model.Evidence;

/**
 *
 * @author D
 */
@ManagedBean
public class AffairMB {
    
    @EJB
    private Affair affairBusiness;
    private ru.ifmo.pe.oatmeal.model.Affair affair;
    
    private long id;
    
    public void init() throws IOException{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String user = ec.getUserPrincipal().getName();
        affair = affairBusiness.affairByIdAndUser(user, id);
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public ru.ifmo.pe.oatmeal.model.Affair getAffair(){
        return affair;
    }    
    
    public void createEvidence(){
        affairBusiness.createEvidence(id, null, null);
    }
    
    public List<Evidence> getEvidences(){
        return affairBusiness.getAffairEvidences(id);
    }
    
    
    
}
