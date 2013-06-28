/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author D
 */
@ManagedBean
public class LoginMB implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private boolean err = false;
    private String login;
    private String password;

    public boolean isErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String submit(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(login, password);
            return "/p/person.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            return "login.xhtml?err=true&faces-redirect=true";
        }
    }
    
}
