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
       
}
