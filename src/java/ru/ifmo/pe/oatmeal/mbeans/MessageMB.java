/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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

    private void validateFile() throws ValidatorException {
        if (message.getText().isEmpty()||message.getText().length()>255) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Message is not valid");
            message.setSummary("Message is not valid");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void send() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String userStr = ec.getUserPrincipal().getName();
        User user = messageLogic.getUserByLogin(userStr);
        Affair affair = messageLogic.getAffairByID(id);
        //message.setText(str);
        try {
            validateFile();
        } catch (ValidatorException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
            return;
        }
        message.setUser(user);
        message.setAffair(affair);
        message.setDateM(new Date());
        System.out.println(new Date());
        messageLogic.save(message);
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
