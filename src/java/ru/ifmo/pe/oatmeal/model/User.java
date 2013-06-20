/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author D
 */
@Entity
@Table(name = "USERSTABLE")
public class User implements Serializable{
    
    @Id
    @Column(unique=true, nullable=false)
    private String login;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private String password;
    
    @ElementCollection(targetClass=Group.class)
    @CollectionTable(name="USER_GROUPS", 
                     joinColumns= @JoinColumn(name="login", nullable=false),
                     uniqueConstraints= {@UniqueConstraint(columnNames={"login", "user_group"})})
    @Enumerated(EnumType.STRING)
    @Column(name="user_group", nullable=false)
    private List<Group> user_groups;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login= login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   

    public List<Group> getUser_groups() {
        return user_groups;
    }

    public void setUser_groups(List<Group> user_groups) {
        this.user_groups = user_groups;
    }
 
}