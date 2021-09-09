/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answer;

import connect.BConnection;
import frame.Result;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolf
 */
public class AnswerManager {

    private Connection connect;
    public String nullAns, TrueAns, FalseAns;
    public int flag1;
    public int flag2;
    public ArrayList<Answer> lstAnswer = new ArrayList<>();

    public AnswerManager() {
        connect = BConnection.getConnection();
        if (connect != null) {
            System.out.println("Connect success");
        } else {
            System.out.println(">>> Connect fail");
        }
    }

    public DefaultTableModel getAllAnswer() {
        lstAnswer.removeAll(lstAnswer);
        String columnNames[] = {
            "Id", "Answer", "Question", "Result"
        };
        DefaultTableModel dtf = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "{CALL getAllAnswer}";
            CallableStatement cs = connect.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Answer a = new Answer(
                        rs.getInt("id"),
                        rs.getNString("answer"),
                        rs.getNString("question"),
                        rs.getInt("result"),
                        rs.getInt("question_id")
                );
                lstAnswer.add(a);
                dtf.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getNString("answer"),
                    rs.getNString("question"),
                    rs.getInt("result"),
                    rs.getInt("question_id")
                });
                if (lstAnswer.size() == 0) {
                    System.out.println("Can't find data");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dtf;
        }
    }

    public int createAnswer(Answer a) {
        try {
            String sql = "{CALL createAnswer(?,?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, a.getAnswer());
            cs.setInt(2, a.getResult());
            cs.setInt(3, a.getQuestionId());
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public int deleteAnswer(int id) {
        try {
            String sql = "{CALL deleteAnswer(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, id);
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public int updateAnswer(Answer a) {
        try {
            String sql = "{CALL updateAnswer(?,?,?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, a.getId());
            cs.setString(2, a.getAnswer());
            cs.setInt(3, a.getResult());
            cs.setInt(4, a.getQuestionId());
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public ArrayList<Answer> lstCauTraLoi = new ArrayList<>();

    public void CauTraLoi(int id) {
        lstCauTraLoi.removeAll(lstCauTraLoi);
        try {
            String sql = "{CALL getAnswerExam(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Answer a = new Answer(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getInt("result"),
                        rs.getInt("question_id"));
                lstCauTraLoi.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getResult(int user_id, int question_id, int result) {
        try {
            String sql = "{CALL getResult(?,?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, user_id);
            cs.setInt(2, question_id);
            cs.setInt(3, result);
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public int countAns(int id) {
        try {
            String sql = "{CALL countAns(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                int repliedAns = rs.getInt("repliedAns");
                int flag = 20 - repliedAns;
                nullAns = Integer.toString(flag);
                Result.lbNullAns.setText(nullAns);
                int nullAnswer = Integer.parseInt(nullAns);
                String sql2 = "{CALL countTrueAns(?)}";
                CallableStatement cs2 = connect.prepareCall(sql2);
                cs2.setInt(1, id);
                ResultSet rs2 = cs2.executeQuery();
                if (rs2.next()) {
                    flag1 = rs2.getInt("TrueAnswers");
                    flag2 = (20 - nullAnswer) - flag1;
                    TrueAns = Integer.toString(flag1);
                    FalseAns = Integer.toString(flag2);
                    Result.lbTrueAns.setText(TrueAns);
                    Result.lbFalseAns.setText(FalseAns);
                    if (flag1 >= 3) {
                        Result.lbRe.setText("ĐẠT");
                    } else {
                        Result.lbRe.setText("KHÔNG ĐẠT");
                    }
                }
                String sql3 = "{CALL updateMark(?,?)}";
                CallableStatement cs3 = connect.prepareCall(sql3);
                cs3.setFloat(1, flag1);
                cs3.setInt(2, id);
                cs3.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return 0;
        }
    }
}
