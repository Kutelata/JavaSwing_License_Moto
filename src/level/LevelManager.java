/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import connect.BConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Wolf
 */
public class LevelManager {
    private Connection connect;
    public ArrayList<Level> lstLevel = new ArrayList<>();
    
    public LevelManager() {
        connect = BConnection.getConnection();
        if (connect != null) {
            System.out.println("Connect success");
        } else {
            System.out.println(">>> Connect fail");
        }
    }
    
    public DefaultComboBoxModel<Level> getAllLevel() {
        lstLevel.removeAll(lstLevel);
        DefaultComboBoxModel<Level> dcbm = new DefaultComboBoxModel<>();
        
        try {
            String sql = "{CALL getAllLevel}";
            CallableStatement cs = connect.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                Level l = new Level(rs.getInt("id"), rs.getString("level"));
                lstLevel.add(l);
                dcbm.addElement(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LevelManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            return dcbm;
        }
    }
    
}
