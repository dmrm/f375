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
public class Person {
    private int id;
    private ArrayList<Affair> affairs;
    
    public int getId() {
        return id;        
    }
    public ArrayList<Affair> getAffairs() {
        return (ArrayList<Affair>)affairs.clone();
    }
    
    public Person () {
        
    }
    public Person (int id, ArrayList<Affair> affairs) {
        this.id = id;
        this.affairs = affairs;
    }
}
