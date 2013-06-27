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
import ru.ifmo.pe.oatmeal.model.Message;

/**
*
* @author DSZ
*/
@Stateless
public class MessageDAO {

    @PersistenceContext
    private EntityManager em;

    public void save(Message m) {
        em.persist(m);
    }

    public List<Message> getMessagesAttachedAffair(Affair affair) {
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE m.affair = :affair ORDER BY m.dateM DESC", Message.class);
        return query.setParameter("affair", affair).getResultList();
    }
}