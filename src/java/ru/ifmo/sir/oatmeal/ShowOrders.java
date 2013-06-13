/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ru.ifmo.sir.oatmeal.dbconnect.DBUtil;
import ru.ifmo.sir.oatmeal.dbconnect.DataAccessFactory;

/**
 *
 * @author Александр
 */
public class ShowOrders {
    private static final DBUtil DB_UTIL = DataAccessFactory.getDBUtils();
    
    private static ArrayList<Order> orders;
    //0 - все заявки, 1 - открытые заявки, 2 - отклоненные
    private int filter;
    
    public static void main(String args[]) {
        try {
            loadAffairsFromDB();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private static void loadAffairsFromDB() throws SQLException {
        String query = "select * from ORDERS";
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;     
        try {
            con = DB_UTIL.getConnection();
            stmt = con.createStatement();
            result = stmt.executeQuery(query);
             while (result.next()){
                 int id = Integer.parseInt(result.getString("ID"));
                 String text = result.getString("TEXT");
                 int fromUserID = Integer.parseInt(result.getString("FROM_USR_ID"));
                 int toUserID = Integer.parseInt(result.getString("TO_USR_ID"));
                 int toRoleID = Integer.parseInt(result.getString("TO_RLS_ID"));
                 String comment = result.getString("COMMENTS");
                 int statusID = Integer.parseInt(result.getString("STATUS_ID"));
                 
                 ResultSet r = stmt.executeQuery("select FIRST_NAME from USERS where ID = " + fromUserID);
                 r.next();
                 String fromUsername = r.getString("FIRST_NAME");
                 r = stmt.executeQuery("select LAST_NAME from USERS where ID = " + fromUserID);
                 r.next();
                 fromUsername = fromUsername + " " + r.getString("LAST_NAME"); 
                 
                 r = stmt.executeQuery("select FIRST_NAME from USERS where ID = " + toUserID);
                 r.next();
                 String toUsername = r.getString("FIRST_NAME");
                 r = stmt.executeQuery("select LAST_NAME from USERS where ID = " + toUserID);
                 r.next();
                 toUsername = toUsername + " " + r.getString("LAST_NAME"); 
                 
                 r = stmt.executeQuery("select NAME from ROLES where ID = " + toRoleID);
                 r.next();
                 String toRole = r.getString("NAME");
                 
                 r = stmt.executeQuery("select DESCRIPTION from STATUS where ID = " + statusID);
                 r.next();
                 String status = r.getString("DESCRIPTION");
                 
                 Order order = new Order(id, text, fromUsername, toUsername, toRole, comment, status);
                 orders.add(order);
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
}
