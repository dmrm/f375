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
import ru.ifmo.pe.oatmeal.model.AffairUsers;

/**
 *
 * @author D
 */
@Stateless
public class AffairUsersDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void addUsers(List<AffairUsers> users){
        for(AffairUsers user : users){
            em.persist(user);
        }
    }
    
    public void addUser(AffairUsers user){
        em.persist(user);
    }
    
    public List<String> getUsersList(long affairId){
        TypedQuery<String> query = em.createQuery("SELECT au.user.login FROM AffairUsers au WHERE au.affair.id = :affairId", String.class);
        return query.setParameter("affairId", affairId).getResultList();
    }
    
}
