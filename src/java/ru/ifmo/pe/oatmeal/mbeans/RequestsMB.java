/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import ru.ifmo.pe.oatmeal.business.Request;
import ru.ifmo.pe.oatmeal.business.Requests;

/**
 *
 * @author s149297
 */
@ManagedBean
public class RequestsMB {
    
    @EJB
    private Requests reqs;
    
    public void acceptReques(long reqId){
        reqs.acceptRequest(reqId);
    }
    
    public void declineRequest(long reqId){
        reqs.deleteRequest(reqId);
    }
    
}
