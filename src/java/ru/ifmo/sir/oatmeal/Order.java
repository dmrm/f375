/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal;

/**
 *
 * @author Александр
 */
public class Order {
    private int id;
    private String text;
    private String fromUsername;
    private String toUsername;
    private String toRole;
    private String comment;
    private String status;
    
    public Order () {
        
    }
    public Order(int id, String text, String fromUsername, String toUsername, String toRole, 
            String comment, String status) {
        this.id = id;
        this.text = text;
        this.fromUsername = fromUsername;
        this.toUsername = toUsername;
        this.toRole = toRole;
        this.comment = comment;
        this.status = status;
    }
}
