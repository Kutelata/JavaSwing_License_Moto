/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import connect.BConnection;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wolf
 */
public class QuestionManager {

    private final Connection connect;
    public ArrayList<Question> lstQuestion = new ArrayList<>();

    public QuestionManager() {
        connect = BConnection.getConnection();
        if (connect != null) {
            System.out.println("Connect success");
        } else {
            System.out.println(">>> Connect fail");
        }
    }

    public DefaultComboBoxModel<Questions> getQuestion() {
        ArrayList<Questions> listQuestion = new ArrayList<>();
        listQuestion.removeAll(listQuestion);
        DefaultComboBoxModel<Questions> dcbm = new DefaultComboBoxModel<>();

        try {
            String sql = "{CALL getQuestion}";
            CallableStatement cs = connect.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Questions q = new Questions(rs.getInt("id"), rs.getNString("question"));
                listQuestion.add(q);
                dcbm.addElement(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dcbm;
        }
    }

    public DefaultTableModel getAllQuestion() {
        lstQuestion.removeAll(lstQuestion);
        String columnNames[] = {
            "Id", "Question", "Image", "Level"
        };
        DefaultTableModel dtf = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "{CALL getAllQuestion}";
            CallableStatement cs = connect.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Question a = new Question(
                        rs.getInt("id"),
                        rs.getString("question"),
                        rs.getString("image"),
                        rs.getString("level"),
                        rs.getInt("level_id")
                );
                lstQuestion.add(a);
                dtf.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("question"),
                    rs.getString("image"),
                    rs.getString("level"),
                    rs.getInt("level_id")
                });
                if (lstQuestion.size() == 0) {
                    System.out.println("Can't find data");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dtf;
        }
    }

    public int createQuestion(Question a) {
        try {
            String sql = "{CALL createQuestion(?,?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, a.getQuestion());
            cs.setInt(2, a.getLevelId());
            cs.setString(3, a.getImage());
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public int deleteQuestion(int id) {
        try {
            String sql = "{CALL deleteQuestion(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, id);
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public int updateQuestion(Question a) {
        try {
            String sql = "{CALL updateQuestion(?,?,?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, a.getId());
            cs.setString(2, a.getQuestion());
            cs.setInt(3, a.getLevelId());
            cs.setString(4, a.getImage());
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public List<Question> CauHoiDe = new ArrayList<>();
    public List<Question> CauHoiThuong = new ArrayList<>();
    public List<Question> CauHoiKho = new ArrayList<>();

    public int getQuestionExam(String level, String driver_license) {
        try {
            String sql = "{CALL getQuestionExam(?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setNString(1, level);
            cs.setNString(2, driver_license);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Question q = new Question(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getString("image"),
                        rs.getInt("level_id"),
                        rs.getInt("driver_license_id")
                );
                switch (level) {
                    case "Dễ":
                        CauHoiDe.add(q);
                        break;
                    case "Thường":
                        CauHoiThuong.add(q);
                        break;
                    case "Khó":
                        CauHoiKho.add(q);
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
