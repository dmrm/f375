/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import ru.ifmo.sir.oatmeal.dbconnect.*;

/**
 *
 * @author D
 */
@ManagedBean
public class Login {

    private static final DBUtil dbUtil = DataAccessFactory.getDBUtils();

    public void testConnect() throws SQLException {
        
        String query = "select * from users";
                
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;     
        try {
            con = dbUtil.getConnection();
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
             while (result.next()){
                 System.out.println("");
                 System.out.print(result.getString(1) + " "
                         + result.getString(2)+ " " 
                         + result.getString(5)+ " "
                         + result.getString(6)+ " "
                         + result.getString(7)+ " ");
                 System.out.println("");
             }
        } catch (Exception e) {
            System.out.println("error Message ->>>" + e.getMessage());
            e.printStackTrace();
        } finally {
            result.close();
            stmt.close();
            con.close();
        }
    }

    public String login() {
        return "sir/video_feature.xhtml";
    }
}
