/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.daos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ru.ifmo.pe.oatmeal.model.Request;

/**
 *
 * @author D
 */
@Stateless
public class RequestDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Request> getRequestsList(){
        TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r", Request.class);
        return query.getResultList();
    }

    public void save(Request r) {
        em.persist(r);
    }
    
}
