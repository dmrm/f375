/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal.dbconnect;

/**
 *
 * @author DSZ
 */
public class DataAccessFactory {
    private static final DataAccessFactory instance = new DataAccessFactory();
    private DBUtil dbUtil;
 
    private DataAccessFactory() {
    }
 
    public static DataAccessFactory getInstance() {
        return instance;
    }
 
    private DBUtil prepareDBUtils() {
        if (dbUtil == null) {
            dbUtil = new DBUtil();
            dbUtil.init("java:comp/env/jdbc/OatmealAppData");
        }
 
        return dbUtil;
    }
 
    public static synchronized DBUtil getDBUtils() {
        return getInstance().prepareDBUtils();
    }
}
