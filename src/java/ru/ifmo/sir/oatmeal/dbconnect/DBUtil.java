/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.sir.oatmeal.dbconnect;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DSZ
 */
public class DBUtil {
    //private static final Logger log = Logger.getLogger(DBUtil.class.getName());

    private DataSource dataSource;

    public DBUtil() {
    }

    public void init(String dataSourceName) {
        try {
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(dataSourceName);
        } catch (NamingException e) {
            //log.error("JNDIException: " + e.getMessage());
            System.out.println("error Message ->>>" + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource is null.");
        }
        return dataSource.getConnection();
    }
}
