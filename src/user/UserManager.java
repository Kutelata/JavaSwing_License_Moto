/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import connect.BConnection;
import frame.ChangePass;
import frame.Result;
import frame.StInfor;
import java.awt.Image;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wolf
 */
public class UserManager {

    private final Connection connect;
    public ArrayList<User> lstUser = new ArrayList<>();
    public ArrayList<User> lstSearchUserByAccount = new ArrayList<>();
    public ArrayList<User> lstSearchUserByName = new ArrayList<>();
    public ArrayList<User> lstSearchUserByGender = new ArrayList<>();
    public ArrayList<User> lstSearchUserByDriverLicense = new ArrayList<>();
    StInfor st = new StInfor();
    public LocalDateTime time2;
    public String name, birthday, address, identificationCard, time, mark;
    public int sbd, gender, driverLicense, result;
    public float IntMark;

    public UserManager() {
        connect = BConnection.getConnection();
        if (connect != null) {
            System.out.println("Connect success");
        } else {
            System.out.println(">>> Connect fail, please check your connection");
        }
    }

    public List<String> getUser(String account, String pass) {
        List<String> user = new ArrayList<>();

        try {
            String sql = "{CALL getUser(?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, account);
            cs.setString(2, pass);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                user.add(rs.getString("account"));
                user.add(rs.getString("pass"));
                user.add(Integer.toString(rs.getInt("role_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int getInfor(String account) {

        try {
            String sql = "{CALL getUserByAccount(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, account);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                sbd = rs.getInt("id");
                String id = Integer.toString(sbd);
                name = rs.getNString("name");
                address = rs.getNString("address");
                birthday = rs.getString("birthday");
                identificationCard = rs.getString("identification_card");
                gender = rs.getInt("gender");
                result = rs.getInt("result");
                driverLicense = rs.getInt("driver_license_id");
                String img = rs.getNString("image");
                ImageIcon i = new ImageIcon(img);
                Image im = i.getImage();
                Image ima = im.getScaledInstance(StInfor.lbIm.getWidth(), StInfor.lbIm.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon userImg = new ImageIcon(ima);
                IntMark = rs.getFloat("mark");
                String mark = Float.toString(IntMark);
                st.mark = Float.toString(IntMark);
                st.IntMark = IntMark;
                time = rs.getString("test_time");
                StInfor.lbTitleMark.setText("Điểm thi:");
                StInfor.lbMark.setText(mark);
                StInfor.lbIm.setIcon(userImg);
                StInfor.lbA.setText(name);
                StInfor.lbBi.setText(birthday);
                StInfor.lbAd.setText(address);
                StInfor.lbId.setText(id);
                StInfor.lbCmt.setText(identificationCard);
                if (gender == 0) {
                    StInfor.lbGender.setText("Nữ");
                } else {
                    StInfor.lbGender.setText("Nam");
                }
                switch (driverLicense) {
                    case 1:
                        StInfor.lbGplx.setText("A1");
                        break;
                    case 2:
                        StInfor.lbGplx.setText("A2");
                        break;
                    case 3:
                        StInfor.lbGplx.setText("A3");
                        break;
                    case 4:
                        StInfor.lbGplx.setText("A4");
                        break;
                    case 5:
                        StInfor.lbGplx.setText("B1");
                        break;
                    case 6:
                        StInfor.lbGplx.setText("B2");
                        break;
                    case 7:
                        StInfor.lbGplx.setText("C");
                        break;
                    case 8:
                        StInfor.lbGplx.setText("D");
                        break;
                    default:
                        break;
                }
                if (result == 0) {
                    StInfor.lbTitleMark.setText("");
                    StInfor.lbMark.setText("");
                    StInfor.lbRe.setText("CHƯA THI");
                } else {
                    StInfor.lbTitleMark.setText("Điểm thi:");
                    StInfor.lbMark.setText(mark);
                    if (IntMark >= 3) {
                        StInfor.lbRe.setText("ĐẠT");
                    } else {
                        StInfor.lbRe.setText("KHÔNG ĐẠT");
                    }
                }
            }
            st.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return 0;
        }

    }

    public int getInforById(String user_id) {
        try {
            String sql = "{CALL getUserById(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, user_id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                sbd = rs.getInt("id");
                String id = Integer.toString(sbd);
                name = rs.getNString("name");
                address = rs.getNString("address");
                birthday = rs.getString("birthday");
                identificationCard = rs.getString("identification_card");
                gender = rs.getInt("gender");
                result = rs.getInt("result");
                driverLicense = rs.getInt("driver_license_id");
                String img = rs.getNString("image");
                ImageIcon i = new ImageIcon(img);
                Image im = i.getImage();
                Image ima = im.getScaledInstance(StInfor.lbIm.getWidth(), StInfor.lbIm.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon userImg = new ImageIcon(ima);
                IntMark = rs.getFloat("mark");
                String mark = Float.toString(IntMark);
                st.mark = Float.toString(IntMark);
                st.IntMark = IntMark;
                time = rs.getString("test_time");
                StInfor.lbTitleMark.setText("Điểm thi:");
                StInfor.lbMark.setText(mark);
                StInfor.lbIm.setIcon(userImg);
                StInfor.lbA.setText(name);
                StInfor.lbBi.setText(birthday);
                StInfor.lbAd.setText(address);
                StInfor.lbId.setText(id);
                StInfor.lbCmt.setText(identificationCard);
                if (gender == 0) {
                    StInfor.lbGender.setText("Nữ");
                } else {
                    StInfor.lbGender.setText("Nam");
                }
                if (driverLicense == 1) {
                    StInfor.lbGplx.setText("A1");
                } else if (driverLicense == 2) {
                    StInfor.lbGplx.setText("A2");
                } else if (driverLicense == 3) {
                    StInfor.lbGplx.setText("A3");
                } else if (driverLicense == 4) {
                    StInfor.lbGplx.setText("A4");
                } else if (driverLicense == 5) {
                    StInfor.lbGplx.setText("B1");
                } else if (driverLicense == 6) {
                    StInfor.lbGplx.setText("B2");
                } else if (driverLicense == 7) {
                    StInfor.lbGplx.setText("C");
                } else if (driverLicense == 8) {
                    StInfor.lbGplx.setText("D");
                }
                if (result == 0) {
                    StInfor.lbTitleMark.setText("");
                    StInfor.lbMark.setText("");
                    StInfor.lbRe.setText("CHƯA THI");
                } else {
                    StInfor.lbTitleMark.setText("Điểm thi:");
                    StInfor.lbMark.setText(mark);
                    if (IntMark >= 3) {
                        StInfor.lbRe.setText("ĐẠT");
                    } else {
                        StInfor.lbRe.setText("KHÔNG ĐẠT");
                    }
                }
            }
            st.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return 0;
        }

    }

    public int updateResult(int id) {
        try {
            String sql = "{CALL updateUserResult(?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, id);
            cs.setInt(2, 1);
            cs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return 0;
        }
    }

    public int showResult(int user_id) {
        try {
            Result r = new Result();
            String sql = "{CALL getUserById(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, user_id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                sbd = rs.getInt("id");
                String id = Integer.toString(sbd);
                name = rs.getNString("name");
                address = rs.getNString("address");
                birthday = rs.getString("birthday");
                identificationCard = rs.getString("identification_card");
                gender = rs.getInt("gender");
                driverLicense = rs.getInt("driver_license_id");
                String img = rs.getNString("image");
                ImageIcon i = new ImageIcon(img);
                Image im = i.getImage();
                Image ima = im.getScaledInstance(r.lbIm.getWidth(), r.lbIm.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon userImg = new ImageIcon(ima);
                r.lbIm.setIcon(userImg);
                r.jlbName.setText(name);
                r.jlbBirthday.setText(birthday);
                r.JlbAddress.setText(address);
                r.jlbId.setText(id);
                r.jlbCmt.setText(identificationCard);
                if (gender == 0) {
                    r.jlbGender.setText("Nữ");
                } else {
                    r.jlbGender.setText("Nam");
                }
                if (driverLicense == 1) {
                    r.lbGplx.setText("A1");
                } else if (driverLicense == 2) {
                    r.lbGplx.setText("A2");
                } else if (driverLicense == 3) {
                    r.lbGplx.setText("A3");
                } else if (driverLicense == 4) {
                    r.lbGplx.setText("A4");
                } else if (driverLicense == 5) {
                    r.lbGplx.setText("B1");
                } else if (driverLicense == 6) {
                    r.lbGplx.setText("B2");
                } else if (driverLicense == 7) {
                    r.lbGplx.setText("C");
                } else if (driverLicense == 8) {
                    r.lbGplx.setText("D");
                }
            }
            r.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return 0;
        }
    }

    public DefaultTableModel getAllUser() {
        lstUser.removeAll(lstUser);
        String columnNames[] = {
            "Id", "Account", "Password", "Mark", "Identification card", "Name", "Image", "Gender", "Birthday", "Address", "Driver license", "Role", "Test Time", "Result"
        };
        DefaultTableModel dtf = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "{CALL getAllUser}";
            CallableStatement cs = connect.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("account"),
                        rs.getString("pass"),
                        rs.getFloat("mark"),
                        rs.getString("identification_card"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("gender"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("driver_license"),
                        rs.getString("role"),
                        rs.getString("test_time"),
                        rs.getInt("result"),
                        rs.getInt("role_id"),
                        rs.getInt("driver_license_id")
                );
                lstUser.add(u);
                dtf.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("account"),
                    rs.getString("pass"),
                    rs.getFloat("mark"),
                    rs.getString("identification_card"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getInt("gender"),
                    rs.getString("birthday"),
                    rs.getString("address"),
                    rs.getString("driver_license"),
                    rs.getString("role"),
                    rs.getString("test_time"),
                    rs.getInt("result"),
                    rs.getInt("role_id"),
                    rs.getInt("driver_license_id")
                });
                if (lstUser.isEmpty()) {
                    System.out.println("Can't find data");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dtf;
        }
    }

    public int createUser(User u) {
        try {
            String sql = "{CALL createUser(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, u.getAccount());
            cs.setString(2, u.getPass());
            cs.setString(3, u.getIdentificationCard());
            cs.setString(4, u.getName());
            cs.setString(5, u.getImage());
            cs.setInt(6, u.getGender());
            cs.setString(7, u.getBirthday());
            cs.setString(8, u.getAddress());
            cs.setInt(9, u.getDriverLicenseId());
            cs.setInt(10, u.getRoleId());
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public int deleteUser(int id) {
        try {
            String sql = "{CALL deleteUser(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, id);
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public int updateUser(User u) {
        try {
            String sql = "{CALL updateUser(?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, u.getId());
            cs.setString(2, u.getAccount());
            cs.setString(3, u.getPass());
            cs.setString(4, u.getIdentificationCard());
            cs.setString(5, u.getName());
            cs.setString(6, u.getImage());
            cs.setInt(7, u.getGender());
            cs.setString(8, u.getBirthday());
            cs.setString(9, u.getAddress());
            cs.setInt(10, u.getDriverLicenseId());
            cs.setInt(11, u.getRoleId());
            return cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return 0;
        }
    }

    public int changePass(String id) {
        ChangePass cp = new ChangePass();
        cp.setVisible(true);
        try {
            String sql = "{CALL getUserById(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                String acc = rs.getString("account");
                String img = rs.getNString("image");
                ImageIcon i = new ImageIcon(img);
                Image im = i.getImage();
                Image ima = im.getScaledInstance(ChangePass.lbImg.getWidth(), ChangePass.lbImg.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon userImg = new ImageIcon(ima);
                ChangePass.lbImg.setIcon(userImg);
                ChangePass.txtUser.setText(acc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return 0;
        }
    }

    public int updateTime(String id, String time) {
        try {
            String sql = "{CALL updateTime(?,?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, id);
            cs.setString(2, time);
            cs.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return 0;
        }
    }

    public int getUserResult(String id) {
        try {
            String sql = "{CALL getUserById(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                result = rs.getInt("result");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return 0;
        }
    }

    public DefaultTableModel searchUserByAccount(String account) {
        lstSearchUserByAccount.removeAll(lstSearchUserByAccount);
        String columnNames[] = {
            "Id", "Account", "Password", "Mark", "Identification card", "Name", "Image", "Gender", "Birthday", "Address", "Driver license", "Role", "Test Time", "Result"
        };
        DefaultTableModel dtf = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "{CALL searchUserByAccount(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, account);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("account"),
                        rs.getString("pass"),
                        rs.getFloat("mark"),
                        rs.getString("identification_card"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("gender"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("driver_license"),
                        rs.getString("role"),
                        rs.getString("test_time"),
                        rs.getInt("result"),
                        rs.getInt("role_id"),
                        rs.getInt("driver_license_id")
                );
                lstSearchUserByAccount.add(u);
                dtf.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("account"),
                    rs.getString("pass"),
                    rs.getFloat("mark"),
                    rs.getString("identification_card"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getInt("gender"),
                    rs.getString("birthday"),
                    rs.getString("address"),
                    rs.getString("driver_license"),
                    rs.getString("role"),
                    rs.getString("test_time"),
                    rs.getInt("result"),
                    rs.getInt("role_id"),
                    rs.getInt("driver_license_id")
                });
                if (lstSearchUserByAccount.isEmpty()) {
                    System.out.println("Can't find data");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dtf;
        }
    }

    public DefaultTableModel searchUserByName(String name) {
        lstSearchUserByName.removeAll(lstSearchUserByName);
        String columnNames[] = {
            "Id", "Account", "Password", "Mark", "Identification card", "Name", "Image", "Gender", "Birthday", "Address", "Driver license", "Role", "Test Time", "Result"
        };
        DefaultTableModel dtf = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "{CALL searchUserByName(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("account"),
                        rs.getString("pass"),
                        rs.getFloat("mark"),
                        rs.getString("identification_card"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("gender"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("driver_license"),
                        rs.getString("role"),
                        rs.getString("test_time"),
                        rs.getInt("result"),
                        rs.getInt("role_id"),
                        rs.getInt("driver_license_id")
                );
                lstSearchUserByName.add(u);
                dtf.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("account"),
                    rs.getString("pass"),
                    rs.getFloat("mark"),
                    rs.getString("identification_card"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getInt("gender"),
                    rs.getString("birthday"),
                    rs.getString("address"),
                    rs.getString("driver_license"),
                    rs.getString("role"),
                    rs.getString("test_time"),
                    rs.getInt("result"),
                    rs.getInt("role_id"),
                    rs.getInt("driver_license_id")
                });
                if (lstSearchUserByName.isEmpty()) {
                    System.out.println("Can't find data");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dtf;
        }
    }

    public DefaultTableModel searchUserByGender(int gender) {
        lstSearchUserByGender.removeAll(lstSearchUserByGender);
        String columnNames[] = {
            "Id", "Account", "Password", "Mark", "Identification card", "Name", "Image", "Gender", "Birthday", "Address", "Driver license", "Role", "Test Time", "Result"
        };
        DefaultTableModel dtf = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "{CALL searchUserByGender(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, gender);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("account"),
                        rs.getString("pass"),
                        rs.getFloat("mark"),
                        rs.getString("identification_card"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("gender"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("driver_license"),
                        rs.getString("role"),
                        rs.getString("test_time"),
                        rs.getInt("result"),
                        rs.getInt("role_id"),
                        rs.getInt("driver_license_id")
                );
                lstSearchUserByGender.add(u);
                dtf.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("account"),
                    rs.getString("pass"),
                    rs.getFloat("mark"),
                    rs.getString("identification_card"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getInt("gender"),
                    rs.getString("birthday"),
                    rs.getString("address"),
                    rs.getString("driver_license"),
                    rs.getString("role"),
                    rs.getString("test_time"),
                    rs.getInt("result"),
                    rs.getInt("role_id"),
                    rs.getInt("driver_license_id")
                });
                if (lstSearchUserByGender.isEmpty()) {
                    System.out.println("Can't find data");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dtf;
        }
    }

    public DefaultTableModel searchUserByDriverLicense(int driverLicense) {
        lstSearchUserByDriverLicense.removeAll(lstSearchUserByDriverLicense);
        String columnNames[] = {
            "Id", "Account", "Password", "Mark", "Identification card", "Name", "Image", "Gender", "Birthday", "Address", "Driver license", "Role", "Test Time", "Result"
        };
        DefaultTableModel dtf = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "{CALL searchUserByDriverLicense(?)}";
            CallableStatement cs = connect.prepareCall(sql);
            cs.setInt(1, driverLicense);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("account"),
                        rs.getString("pass"),
                        rs.getFloat("mark"),
                        rs.getString("identification_card"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("gender"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("driver_license"),
                        rs.getString("role"),
                        rs.getString("test_time"),
                        rs.getInt("result"),
                        rs.getInt("role_id"),
                        rs.getInt("driver_license_id")
                );
                lstSearchUserByDriverLicense.add(u);
                dtf.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("account"),
                    rs.getString("pass"),
                    rs.getFloat("mark"),
                    rs.getString("identification_card"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getInt("gender"),
                    rs.getString("birthday"),
                    rs.getString("address"),
                    rs.getString("driver_license"),
                    rs.getString("role"),
                    rs.getString("test_time"),
                    rs.getInt("result"),
                    rs.getInt("role_id"),
                    rs.getInt("driver_license_id")
                });
                if (lstSearchUserByDriverLicense.isEmpty()) {
                    System.out.println("Can't find data");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return dtf;
        }
    }
}
