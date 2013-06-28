/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.daos;

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
    
    public User find(String login){
        return em.find(User.class, login);
    }
    
    public List<User> getAllUsers(){
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }
        
    public List<String> getAllUsersInDetectiveRoles(long affairId, String login){
        Query query = em.createNativeQuery("SELECT DISTINCT(u.login) FROM " +
                                           "userstable u, user_groups g " +
                                           "WHERE g.login = u.login " +
                                           "AND (g.user_group = :role1 " +
                                           "OR g.user_group = :role2) " +
                                           "AND u.login != :login " +
                                           "AND u.login NOT IN (SELECT au.user_login FROM affairusers au " +
                                           "WHERE au.affair_id = :affairId) " +
                                           "AND u.login NOT IN (SELECT a.owner_login FROM affair a " +
                                           "WHERE a.id = :affairId)");
        return query.setParameter("role1", Group.PRIVATE_EYE.name())
                .setParameter("role2", Group.DETECTIVE.name())
                .setParameter("affairId", affairId)
                .setParameter("login", login)
                .getResultList();
    }

    public String lessBusyUser() {
        Query query = em.createNativeQuery("select login from" +
                "(select login, count(id) from " +
                    "USERSTABLE left outer join OATMEAL.AFFAIR on login = owner_login " +
                    "group by login " +
                    "having count(id) = " +
                        "(select min(count(id)) from " +
                        "USER_GROUPS g inner join USERSTABLE u on u.LOGIN = g.LOGIN " +
                        "left outer join OATMEAL.AFFAIR on u.login = owner_login " +
                        "where g.USER_GROUP in ('DETECTIVE', 'PRIVATE_EYE') " +
                        "group by u.login)) " +
                "where rownum = 1");
        return (String)query.getSingleResult();
    }
       
}
