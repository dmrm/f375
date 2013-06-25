/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.daos;

import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ru.ifmo.pe.oatmeal.model.Group;
import ru.ifmo.pe.oatmeal.model.User;

/**
 *
 * @author D
 */
@Stateless
public class UserDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(User user){
        em.persist(user);
    }    
//    public void update(User user){
//        em.merge(user);
//    }
//    
//    public void remove(String login){
//        User user = find(login);
//        if(user != null){
//            em.remove(user);
//        }
//    }
//    
//    public void remove(User user){
//        if (user != null && user.getLogin()!=null && em.contains(user)) {
//            em.remove(user);
//        }
//    }
    
    public User find(String login){
        return em.find(User.class, login);
    }
    
    public List<User> getAllUsers(){
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }
        
    public List<String> getAllUsersInDetectiveRoles(long affairId, String login){
        Query query = em.createNativeQuery("SELECT DISTINCT(u.login) FROM \n" +
                                           "userstable u, user_groups g \n" +
                                           "WHERE g.login = u.login \n" +
                                           "AND (g.user_group = :role1 " +
                                           "OR g.user_group = :role2) " +
                                           "AND u.login != :login " +
                                           "AND u.login NOT IN (SELECT au.user_login FROM affairusers au " +
                                           "WHERE affair_id = :affairId)");
        return query.setParameter("role1", Group.PRIVATE_EYE.name())
                .setParameter("role2", Group.DETECTIVE.name())
                .setParameter("affairId", affairId)
                .setParameter("login", login)
                .getResultList();
    }

    public String lessBusyUser() {
        TypedQuery<String> query = em.createQuery("SELECT MIN u FROM Affair a JOIN a.owner u", String.class);
        return query.getSingleResult();
    }
       
}