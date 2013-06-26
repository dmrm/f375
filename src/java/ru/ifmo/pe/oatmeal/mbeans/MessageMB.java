/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import ru.ifmo.pe.oatmeal.business.MessageLogic;
import ru.ifmo.pe.oatmeal.model.Affair;
import ru.ifmo.pe.oatmeal.model.Message;
import ru.ifmo.pe.oatmeal.model.User;

/**
 *
 * @author DSZ
 */
@ManagedBean
@RequestScoped
public class MessageMB implements Serializable {

    private Message message;
    private List<Message> listMessage;
    private long id;
    private String str;
    @EJB
    MessageLogic messageLogic;

    public void init() {
        listMessage = messageLogic.getMessagesAttachedAffair(id);
    }

    private void validateMessage() throws ValidatorException {
        if (message.getText().isEmpty()||message.getText().length()>255) {
            FacesMessage mess = new FacesMessage();
            mess.setDetail("Message is not valid");
            mess.setSummary("Message is not valid");
            mess.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(mess);
        }
    }

    public void send() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String userStr = ec.getUserPrincipal().getName();
        User user = messageLogic.getUserByLogin(userStr);
        Affair affair = messageLogic.getAffairByID(id);
        try {
            //Валидация сообщения(средства JSF exeption)
            validateMessage();
        } catch (ValidatorException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            return;
        }
        message.setUser(user);
        message.setAffair(affair);
        message.setDateM(new Date());
        //Добавить сообщение
        messageLogic.save(message);
        //обновить сообщения, прикреплённые к делу
        listMessage = messageLogic.getMessagesAttachedAffair(id);
    }

    public MessageMB() {
        message = new Message();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<Message> listMessage) {
        this.listMessage = listMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
