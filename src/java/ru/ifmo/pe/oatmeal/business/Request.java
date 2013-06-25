/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.ifmo.pe.oatmeal.daos.RequestDAO;

/**
 *
 * @author D
 */
@Stateless
public class Request {
    
    @EJB
    private RequestDAO reqDAO;
    
    public void saveRequest(ru.ifmo.pe.oatmeal.model.Request r){
        reqDAO.save(r);
    }
    
    public List<ru.ifmo.pe.oatmeal.model.Request> getRequests(){
        return reqDAO.getRequestsList();
    }
    
}
