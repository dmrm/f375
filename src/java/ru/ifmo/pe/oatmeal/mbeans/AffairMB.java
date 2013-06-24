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
import javax.servlet.http.HttpServletResponse;
import ru.ifmo.pe.oatmeal.business.Affair;

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
        System.out.println(id + " " + user);
        affair = affairBusiness.affairByIdAndUser(user, id);
        System.out.println(affair.getId() + " " + user);
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
    
}
