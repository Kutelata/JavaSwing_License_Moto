/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package role;

import connect.BConnection;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
/**
 *
 * @author Wolf
 */
public class RoleManager {
    private Connection connect;
    ArrayList<Role> lstRole = new ArrayList<>();
    
    public RoleManager() {
        connect = BConnection.getConnection();
        if (connect != null) {
            System.out.println("Connect success");
        } else {
            System.out.println(">>> Connect fail, please check your connection");
        }
    }
    
    public DefaultComboBoxModel<Role> getRole() {
        lstRole.removeAll(lstRole);
        DefaultComboBoxModel<Role> dcbm = new DefaultComboBoxModel();
        
        try {
            String sql = "{CALL getAllRole}";
            CallableStatement cs = connect.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                Role r = new Role(rs.getInt("id"), rs.getString("role"));
                lstRole.add(r);
                dcbm.addElement(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dcbm;
        }
    }
    
    public DefaultComboBoxModel<Role> getRoleById(int id) {
        lstRole.removeAll(lstRole);
        DefaultComboBoxModel<Role> dcbm = new DefaultComboBoxModel();
        
        try {
            String sql = "{CALL getRoleById(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                Role r = new Role(rs.getInt("id"), rs.getString("role"));
                lstRole.add(r);
                dcbm.addElement(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dcbm;
        }
    }
}
