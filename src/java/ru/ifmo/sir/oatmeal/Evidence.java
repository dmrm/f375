/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal;

/**
 *
 * @author Александр
 */
public class Evidence {
    private int id;
    private String link;
    private String filename;
    
    public int getId() {
        return id;
    }
    public String getLink() {
        return link;
    }
    public String getFilename() {
        return filename;
    }
    
    public Evidence() {
        
    }
    public Evidence(int id, String link, String filename) {
        this.id = id;
        this.link = link;
        this.filename = filename;
    }
    
}
