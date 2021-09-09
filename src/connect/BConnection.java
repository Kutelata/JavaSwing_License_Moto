/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Wolf
 */
public class BConnection {

    private final static String HOST = "jdbc:sqlserver://localhost:1433;databaseName=ProjectJavaSwing";
    private final static String USER = "sa";
    private final static String PASS = "1234$";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(HOST, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
