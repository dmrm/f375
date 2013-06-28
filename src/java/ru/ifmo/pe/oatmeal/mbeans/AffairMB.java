/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import ru.ifmo.pe.oatmeal.business.Affair;
import ru.ifmo.pe.oatmeal.model.Evidence;
import ru.ifmo.pe.oatmeal.model.User;

/**
 *
 * @author D
 */
@ManagedBean
@ViewScoped
public class AffairMB implements Serializable{
    
    @EJB
    private Affair affairBusiness;    
    
    private ru.ifmo.pe.oatmeal.model.Affair affair;
    private Evidence evi;
    private User user;
    
    private long id;
    private long eviId;
    private String userId;
    
    public void init() throws IOException{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String userName = ec.getUserPrincipal().getName();
        affair = affairBusiness.affairByIdAndUser(userName, id);
        evi = affairBusiness.evi(eviId);        
        if(userId != null){
            User usr = affairBusiness.getUser(userId);
            if(usr != null){
                user = checkUser(affairBusiness.getUsersList(id), usr);
            } else {
                user = null;
            }
        } else {
            user = null;
        }
    }
    
    private User checkUser(List<User> users, User user){
        for(User u : users){
            System.out.println(u.getLogin());
            if(u.getLogin().equals(user.getLogin())){
                return user;
            }
        }
        return null;
    }
    
    public long getId() {
        return id;
    }
        
    public void setId(long id) {
        this.id = id;
    }
    
    public ru.ifmo.pe.oatmeal.model.Affair getAffair(){
        return affair;
    }    
    
    public void createEvidence(){
        affairBusiness.createEvidence(id, null, null);
    }
    
    public List<Evidence> getEvidences(){
        return affairBusiness.getAffairEvidences(id);
    }
    
    public List<User> getUsers(){
        return affairBusiness.getUsersList(id);
    }
    
    public List<User> getResponsibleUsers(){
        return affairBusiness.getAllResponsibleUsers(id);
    }
    
    public String attachUser(String login){
        affairBusiness.addUser(id, login);
        return "affair.xhtml?id=" + id + "&user=" + login + "&faces-redirect=true";
    }

    public long getEviId() {
        return eviId;
    }

    public void setEviId(long eviId) {
        this.eviId = eviId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Evidence getEvi() {
        return evi;
    }

    public void setEvi(Evidence evi) {
        this.evi = evi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
