/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ru.ifmo.pe.oatmeal.model.Group;

/**
 *
 * @author D
 */
@ManagedBean
public class RegisterMB {
    
    private String name;
    private String login;
    private String password;
    private String role;
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String createUser(){
        System.out.println(name + " " + login + " " + password);
        return "register.xhtml";
    }
    
}
