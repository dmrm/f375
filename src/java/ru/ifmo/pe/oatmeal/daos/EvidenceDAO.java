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
import ru.ifmo.pe.oatmeal.model.Evidence;

/**
 *
 * @author D
 */

@Stateless
public class EvidenceDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(Evidence evi){
        em.persist(evi);
    }
    
    public List<Evidence> getEvisByAffair(Affair affair){
        TypedQuery<Evidence> query = em.createQuery("SELECT e FROM Evidence e WHERE e.affair = :affair", Evidence.class);
        return query.setParameter("affair", affair).getResultList();
    }
    
}
