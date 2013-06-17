/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import ru.ifmo.pe.oatmeal.daos.UserDAO;
import ru.ifmo.pe.oatmeal.model.Group;
import ru.ifmo.pe.oatmeal.model.User;

/**
 *
 * @author D
 */
@ManagedBean
@ViewScoped
public class Login {
    
    private String username;
    private String password;
    @EJB
    private UserDAO userDAO;
    
    private void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
        System.out.println("dfshgdshg");
//        User user = new User();
//        user.setLogin("holmes");
//        user.setName("Sherlock Holmes");
//        user.setPassword("holmes");
//        List<Group> glist = new ArrayList<Group>();
//        glist.add(Group.OUSER);
//        user.setUser_groups(glist);
//        userDAO.save(user);        
        try{
            userDAO.find(username);
            request.login(username, password);
            externalContext.redirect("sir/person.xhtml");
        } catch(ServletException e){
            
        }
    }
    
}
