/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.mbeans;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import ru.ifmo.pe.oatmeal.business.Person;
import ru.ifmo.pe.oatmeal.model.Group;
import ru.ifmo.pe.oatmeal.model.User;

/**
 *
 * @author D
 */
@ManagedBean
public class RegisterMB {
    
    @EJB
    private Person person;
    
    private static final String photoPath = "files/users_p/";
    private static final String refPath = "/users_p/";
    
    private String name;
    private String login;
    private String password;
    private String role;
    private String photo;
    private UploadedFile file;

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
   
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
        
    public void registerUser() throws IOException{
        String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName(); 
        String fileName = file.getName();
        byte[] byteImg = file.getBytes();
        photo = saveFile(fileName, byteImg);
        List<Group> roleList = new ArrayList<Group>();
        roleList.add(defineRole(role));
        person.saveUser(new User(name, login, password, photo, roleList));
       // return "register.xhtml?register=success";
    }
    
    private String saveFile(String fileName, byte[] byteImg) throws IOException{
        photo = login + "_" + fileName;
        File f = new File(photoPath);
        f.mkdirs();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(photoPath + photo));
        bos.write(byteImg);
        bos.flush();
        bos.close();
        return refPath + photo;
    }
    
    private Group defineRole(String roleString){
        if(roleString.equals("DETECTIVE")){
            return Group.DETECTIVE;
        } else if(roleString.equals("PRIVATE_EYE")){
            return Group.PRIVATE_EYE;
        } else if(roleString.equals("REGISTRAR")){
            return Group.REGISTRAR;
        } else {
            return Group.OUSER;
        }
    }
    
}
