/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal;

import java.util.ArrayList;

/**
 *
 * @author Александр
 */
public class Affair {
    
    private int id;
    private boolean attached;
    private String imgpath;
    private ArrayList<Person> responsiblePersons;
    private ArrayList<Message> attachedMessages;
    private ArrayList<Evidence> attachedEvidences;
    
    public Affair(int id) {
        this.id = id;
        attached = false;
        responsiblePersons = new ArrayList<Person>();
        attachedEvidences = new ArrayList<Evidence>();
        attachedMessages = new ArrayList<Message>();
    }
    
    public int getId(){
        return id;
    }
    
    public void setAttached(String path){
        this.imgpath = path;
        attached = true;
    }
    
    public boolean isAttached(){
        return attached;
    }
    
    public String getImgPath(){
        return imgpath;
    }        
    
    public ArrayList<Person> getPersons(){
        return responsiblePersons;
    }
    
    public ArrayList<Message> getAttachedMessages(){
        return attachedMessages;
    }
    
    public ArrayList<Evidence> getAttachedEvidences(){
        return attachedEvidences;
    }
    
    public void addPersons(ArrayList<Person> persons){
        responsiblePersons.addAll(persons);
    }
    
    public void addPerson(Person person){
        responsiblePersons.add(person);
    }
    
    public void addEvidence(Evidence e){
        attachedEvidences.add(e);
    }
    
    public void addMessage(Message m){
        attachedMessages.add(m);
    }  
    
    public boolean isEmptyEvis(){
        return attachedEvidences.isEmpty();
    }
    
}
