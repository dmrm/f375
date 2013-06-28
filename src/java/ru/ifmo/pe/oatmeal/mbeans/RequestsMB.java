/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import ru.ifmo.pe.oatmeal.business.Requests;

/**
 *
 * @author s149297
 */
@ManagedBean
public class RequestsMB implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @EJB
    private transient Requests reqs;
    
    public String acceptRequest(long reqId){
        return "requests.xhtml?id=" + reqs.acceptRequest(reqId) + "&faces-redirect=true";
    }
    
    public String declineRequest(long reqId){
        reqs.deleteRequest(reqId);
        return "requests.xhtml?ignored=true&faces-redirect=true";
    }
    
}
