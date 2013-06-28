/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package ru.ifmo.pe.oatmeal.daos;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ru.ifmo.pe.oatmeal.model.Affair;
import ru.ifmo.pe.oatmeal.model.Message;
import ru.ifmo.pe.oatmeal.model.User;

/**
*
* @author DSZ
*/
@Stateless
public class MessageDAO {

    @PersistenceContext
    private EntityManager em;
    
    public Message find(long id){
        return em.find(Message.class, id);
    }

    public void save(Message m) {
        em.persist(m);
    }

    public List<Message> getMessagesAttachedAffair(Affair affair) {
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE m.affair = :affair ORDER BY m.dateM DESC", Message.class);
        return query.setParameter("affair", affair).getResultList();
    }
    
    public List<Number> getUserRelatedMessagesIds(String userId) {
        Query query = em.createNativeQuery("select id from MESSAGE where AFFAIR_ID in " +
                    "(select a.ID from AFFAIR a where a.owner_login = :user )" +
                    " OR AFFAIR_ID IN " +
                    "(select au.AFFAIR_ID from AFFAIRUSERS au where au.USER_LOGIN = :user2 ) order by datem desc");
        return query.setParameter("user", userId).setParameter("user2", userId).getResultList();
    }
    public List<Message> getMessagesByIds(List<Number> Ids) {
        List<Message> messages = new ArrayList<Message>();
        for(Number id : Ids){
            messages.add(find(id.longValue()));
        }
        return messages;
    }
}