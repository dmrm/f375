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
import ru.ifmo.pe.oatmeal.model.Affair;

/**
 *
 * @author D
 */
@Stateless
public class AffairDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(Affair affair){
        em.persist(affair);
    }
    
    public Affair find(long id){
        return em.find(Affair.class, id);
    }
    
    public List<Affair> findAllByUser(String login){
        TypedQuery<Affair> query = em.createQuery("SELECT a FROM Affair a WHERE a.owner = '" + login + "'", Affair.class);
        return query.getResultList();
    }
    
    public Affair affairsByIdAndUser(String login, long affairId){
        return em.createQuery("SELECT a FROM Affair a WHERE a.id = " + affairId + " AND " + "a.owner = '" + login + "'", Affair.class).getSingleResult();
    }
    
}
