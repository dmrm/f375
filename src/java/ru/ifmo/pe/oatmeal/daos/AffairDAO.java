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
import ru.ifmo.pe.oatmeal.model.User;

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
    
    public List<Affair> findAllByUser(User login){
        TypedQuery<Affair> query = em.createQuery("SELECT a FROM Affair a WHERE a.owner = :owner", Affair.class);
        return query.setParameter("owner", login).getResultList();
    }
    
    public Affair affairByIdAndUser(User login, long affairId){
        TypedQuery<Affair> query = em.createQuery("SELECT a FROM Affair a WHERE a.id = :affairId AND a.owner = :owner", Affair.class);
        return query.setParameter("affairId", affairId).setParameter("owner", login).getSingleResult();
    }
    
}