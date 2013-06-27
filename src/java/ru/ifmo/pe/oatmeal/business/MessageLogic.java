/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package ru.ifmo.pe.oatmeal.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ru.ifmo.pe.oatmeal.daos.AffairDAO;
import ru.ifmo.pe.oatmeal.daos.MessageDAO;
import ru.ifmo.pe.oatmeal.daos.UserDAO;
import ru.ifmo.pe.oatmeal.model.Affair;
import ru.ifmo.pe.oatmeal.model.Message;
import ru.ifmo.pe.oatmeal.model.User;

/**
*
* @author DSZ
*/
@Stateless
public class MessageLogic {

    @EJB
    private AffairDAO affairDAO;
    @EJB
    MessageDAO messageDAO;
    @EJB
    UserDAO userDAO;

    public List<Message> getMessagesAttachedAffair(long affairID) {
        Affair affair = affairDAO.find(affairID);
        
        return messageDAO.getMessagesAttachedAffair(affair);
    }

    public User getUserByLogin(String user) {
        return userDAO.find(user);
    }

    public void save(Message message) {
        messageDAO.save(message);
    }

    public Affair getAffairByID(long affairID) {
        return affairDAO.find(affairID);
    }
}