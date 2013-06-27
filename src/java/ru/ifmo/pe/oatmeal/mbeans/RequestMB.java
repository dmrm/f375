/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import ru.ifmo.pe.oatmeal.business.Affair;
import ru.ifmo.pe.oatmeal.business.Person;
import ru.ifmo.pe.oatmeal.business.Request;

/**
 *
 * @author D
 */
@ManagedBean
public class RequestMB {
    
    @EJB
    private Request req;
    @EJB
    private Affair affair;
    @EJB
    private Person person;
    
    private int handler = 1;
    private String text;

    public int getHandler() {
        return handler;
    }

    public void setHandler(int handler) {
        this.handler = handler;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void send(){        
        if(handler == 2){        
            ru.ifmo.pe.oatmeal.model.Request r = new ru.ifmo.pe.oatmeal.model.Request();
            String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            r.setRequestBy(user);
            r.setText(text);
            req.saveRequest(r);
        } else {
            affair.createAffair(text, "moriarty");
        }
    }
    
    public List<ru.ifmo.pe.oatmeal.model.Request> getRequests(){
        return req.getRequests();
    }
    
}
