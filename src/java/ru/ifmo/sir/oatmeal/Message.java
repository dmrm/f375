/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal;

/**
 *
 * @author Александр
 */

public class Message {
        private int idMess;
        private String nameUser;
        private String strMess;
        private String dateMess;
        private String fileLink;
        
        public int getIdMess() {
            return idMess;
        }
        
        public String getNameUser(){
            return nameUser;
        }
        
        public String getStrMess() {
            return strMess;
        }
        
        public String getDateMess() {
            return dateMess;
        }
        
        public String getFilename() {
            return fileLink;
        }
        
                
        public void setFileLink(String fileLink) {
            this.fileLink = fileLink;
        }
        
        public Message() {
            
        }
        
        public Message(int idMess, String nameUser, String strMess, String dateMess, String fileLink) {
            this.idMess = idMess;
            this.nameUser = nameUser;
            this.strMess = strMess;
            this.dateMess = dateMess;
            this.fileLink = fileLink;
        }
    
}
