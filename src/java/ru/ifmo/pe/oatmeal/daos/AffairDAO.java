/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.daos;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    public long save(Affair affair){
        em.persist(affair);
        return affair.getId();
    }
    
    public void update(Affair affair){
        em.merge(affair);
    }
    
    public Affair find(long id){
        return em.find(Affair.class, id);
    }
    
    public List<Affair> findAllByUser(User login){
        TypedQuery<Affair> query = em.createQuery("SELECT a FROM Affair a WHERE a.owner = :owner", Affair.class);
        return query.setParameter("owner", login).getResultList();
    }
    
    public List<Affair> findUserGuestAffair(User login){
        TypedQuery<Affair> query = em.createQuery("SELECT a FROM AffairUsers au JOIN au.affair a WHERE au.user = :owner", Affair.class);
        return query.setParameter("owner", login).getResultList();
    }
    
    public Affair affairByIdAndUser(User login, long affairId){
        TypedQuery<Affair> query;
        Query q;
        try{
            query = em.createQuery("SELECT a FROM Affair a WHERE a.id = :affairId AND a.owner = :user", Affair.class);
            query.setParameter("affairId", affairId).setParameter("user", login);
            return query.getSingleResult();
        } catch (NoResultException nre){
            q = em.createNativeQuery("SELECT a.ID FROM AFFAIRUSERS au,AFFAIR a\n" +
                                   "WHERE au.AFFAIR_ID = a.ID \n" +
                                   "AND  au.AFFAIR_ID = :affairId \n" +
                                   "AND au.USER_LOGIN = :user");
            q.setParameter("affairId", affairId).setParameter("user", login.getLogin());
            return find(((BigDecimal)q.getSingleResult()).longValue());
        }        
    }
    
}