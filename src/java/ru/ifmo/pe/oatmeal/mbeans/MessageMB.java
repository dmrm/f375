/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import ru.ifmo.pe.oatmeal.business.MessageLogic;
import ru.ifmo.pe.oatmeal.model.Message;

/**
*
* @author DSZ
*/
@ManagedBean
public class MessageMB implements Serializable {

    private Message message;
    private List<Message> listMessage;
    private long id;
    private String text;
    @EJB
    MessageLogic messageLogic;

    public List<Message> getMessages(long affairId){
        return messageLogic.getMessagesAttachedAffair(affairId);
    }
    
    public void newMessage(long affairId){
        messageLogic.save(affairId, text);
    }
    
    public List<Message> getUserRelatedMessages(){
        return messageLogic.getUserRelatedMessages();
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

    public String getText() {
        return text;
    }

    public void setText(String str) {
        this.text = str;
    }
}