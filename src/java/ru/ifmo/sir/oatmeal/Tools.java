/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal;

/**
 *
 * @author Александр
 */
public class Tools {
    public static String filename(String fileLink) {
        String filename = fileLink;
        int i = fileLink.length();
        while ((fileLink.charAt(i) != '\\') && (fileLink.charAt(i) != '/')) {
            i--;
        }
        if (i != fileLink.length()) {
            filename = fileLink.substring(i + 1, fileLink.length() - 1);
        }        
        return filename;
    }
}
