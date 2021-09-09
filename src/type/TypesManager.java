/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type;

import connect.BConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author WOLF
 */
public class TypesManager {

    private Connection connect;
    ArrayList<Types> lstType = new ArrayList<>();

    public TypesManager() {
        connect = BConnection.getConnection();
        if (connect != null) {
            System.out.println("Connect success");
        } else {
            System.out.println(">>> Connect fail, please check your connection");
        }
    }

    public DefaultComboBoxModel<Types> getDriverLicense() {
        lstType.removeAll(lstType);
        DefaultComboBoxModel<Types> dcbm = new DefaultComboBoxModel();

        try {
            String sql = "{CALL getAllType}";
            CallableStatement cs = connect.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Types t = new Types(rs.getInt("id"), rs.getString("driver_license"));
                lstType.add(t);
                dcbm.addElement(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypesManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dcbm;
        }
    }
}
