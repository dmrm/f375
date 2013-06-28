/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package ru.ifmo.pe.oatmeal.business;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
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

    public void save(long affairId, String text) {
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        Message message = new Message();
        message.setAffair(affairDAO.find(affairId));
        message.setDateM(new Date());
        message.setText(text);
        message.setUser(userDAO.find(user));
        messageDAO.save(message);
    }
    
    public List<Message> getUserRelatedMessages(){
        String user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        List<Number> Ids = messageDAO.getUserRelatedMessagesIds(user);
        return messageDAO.getMessagesByIds(Ids);
    }

    public Affair getAffairByID(long affairID) {
        return affairDAO.find(affairID);
    }
}