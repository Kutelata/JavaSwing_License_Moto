/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import answer.Answer;
import answer.AnswerManager;
import answer.ManageAnswer;
import connect.BConnection;
import question.Question;
import question.QuestionManager;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import question.ManageQuestion;
import question.Questions;
import role.Role;
import role.RoleManager;
import type.Types;
import user.ManageUser;
import user.OptionsSearchUser;
import user.User;
import user.UserManager;

/**
 *
 * @author Wolf
 */
public class Admin extends javax.swing.JFrame implements ManageUser.CallbackUser, ManageQuestion.CallbackQuestion, ManageAnswer.CallbackAnswer {

    private final Connection connect;
    CardLayout cLayout;
    public static ArrayList<User> lstUser = new ArrayList<>();
    ArrayList<Question> lstQuestion = new ArrayList<>();
    ArrayList<Answer> lstAnswer = new ArrayList<>();
    public int AllUsers, PassUsers, FailureUsers, otherUsers;

    /**
     * Creates new form AdminManager
     */
    public Admin() {
        initComponents();
        setLocationRelativeTo(null);
        loadImage();
        loadCardLayout();
        connect = BConnection.getConnection();
        if (connect != null) {
            System.out.println("Connect success");
        } else {
            System.out.println(">>> Connect fail, please check your connection");
        }
    }

    public void loadImage() {
        ImageIcon b1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/manageUser.png")));
        Image iB1 = b1.getImage();
        Image img1 = iB1.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic1 = new ImageIcon(img1);
        jButton1.setIcon(ic1);
        lbTitleUser.setIcon(ic1);
        ImageIcon b2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/manageQues.png")));
        Image iB2 = b2.getImage();
        Image img2 = iB2.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic2 = new ImageIcon(img2);
        jButton2.setIcon(ic2);
        lbTitleQues.setIcon(ic2);
        ImageIcon b3 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/ans.png")));
        Image iB3 = b3.getImage();
        Image img3 = iB3.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic3 = new ImageIcon(img3);
        jButton3.setIcon(ic3);
        lbTitleAns.setIcon(ic3);
        ImageIcon b4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/exit.png")));
        Image iB4 = b4.getImage();
        Image img4 = iB4.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic4 = new ImageIcon(img4);
        buttonLogOut.setIcon(ic4);
        ImageIcon b5 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/add.png")));
        Image iB5 = b5.getImage();
        Image img5 = iB5.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic5 = new ImageIcon(img5);
        buttonAddUser.setIcon(ic5);
        ImageIcon b6 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/update.png")));
        Image iB6 = b6.getImage();
        Image img6 = iB6.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic6 = new ImageIcon(img6);
        buttonUpdateUser.setIcon(ic6);
        ImageIcon b7 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/del.png")));
        Image iB7 = b7.getImage();
        Image img7 = iB7.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic7 = new ImageIcon(img7);
        buttonDeleteUser.setIcon(ic7);
        buttonDeleteQuestion.setIcon(ic7);
        buttonDeleteAnswer.setIcon(ic7);
        ImageIcon b8 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/search.png")));
        Image iB8 = b8.getImage();
        Image img8 = iB8.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic8 = new ImageIcon(img8);
        buttonSearchUser.setIcon(ic8);
        ImageIcon b9 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/addques.png")));
        Image iB9 = b9.getImage();
        Image img9 = iB9.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic9 = new ImageIcon(img9);
        buttonAddQuestion.setIcon(ic9);
        buttonAddAnswer.setIcon(ic9);
        ImageIcon b10 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/updateques.png")));
        Image iB10 = b10.getImage();
        Image img10 = iB10.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic10 = new ImageIcon(img10);
        buttonUpdateQuestion.setIcon(ic10);
        buttonUpdateAnswer.setIcon(ic10);
        ImageIcon b11 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/admin.png")));
        Image iB11 = b11.getImage();
        Image img11 = iB11.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic11 = new ImageIcon(img11);
        lbAdImg.setIcon(ic11);
        ImageIcon b12 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/reload.png")));
        Image iB12 = b12.getImage();
        Image img12 = iB12.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon ic12 = new ImageIcon(img12);
        buttonReloadUser.setIcon(ic12);
    }

    public void loadUsers() {
        UserManager um = new UserManager();
        tblUser.setModel(um.getAllUser());
        lstUser = um.lstUser;
    }

    public void loadQuestion() {
        QuestionManager em = new QuestionManager();
        tblQuestion.setModel(em.getAllQuestion());
        lstQuestion = em.lstQuestion;
    }

    public void loadAnswer() {
        AnswerManager am = new AnswerManager();
        tblAnswer.setModel(am.getAllAnswer());
        lstAnswer = am.lstAnswer;
    }

    public void loadCardLayout() {
        jpCard.add(pnlManageUser, "ManageUser");
        loadUsers();
        jpCard.add(pnlManageQuestion, "ManageQuestion");
        jpCard.add(pnlManageAnswer, "ManageAnswer");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbAdmin = new javax.swing.JLabel();
        buttonLogOut = new javax.swing.JButton();
        jpCard = new javax.swing.JPanel();
        pnlManageAnswer = new javax.swing.JPanel();
        buttonAddAnswer = new javax.swing.JButton();
        buttonUpdateAnswer = new javax.swing.JButton();
        buttonDeleteAnswer = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAnswer = new javax.swing.JTable();
        lbTitleAns = new javax.swing.JLabel();
        pnlManageQuestion = new javax.swing.JPanel();
        buttonAddQuestion = new javax.swing.JButton();
        buttonUpdateQuestion = new javax.swing.JButton();
        buttonDeleteQuestion = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQuestion = new javax.swing.JTable();
        lbTitleQues = new javax.swing.JLabel();
        pnlManageUser = new javax.swing.JPanel();
        buttonAddUser = new javax.swing.JButton();
        buttonUpdateUser = new javax.swing.JButton();
        buttonDeleteUser = new javax.swing.JButton();
        buttonSearchUser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        lbTitleUser = new javax.swing.JLabel();
        buttonReloadUser = new javax.swing.JButton();
        lbAdImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Manage Users");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Manage Question");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Manage Answer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Hello");

        lbAdmin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        buttonLogOut.setText("Log Out");
        buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogOutActionPerformed(evt);
            }
        });

        jpCard.setLayout(new java.awt.CardLayout());

        buttonAddAnswer.setText("Add");
        buttonAddAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddAnswerActionPerformed(evt);
            }
        });

        buttonUpdateAnswer.setText("Update");
        buttonUpdateAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateAnswerActionPerformed(evt);
            }
        });

        buttonDeleteAnswer.setText("Delete");
        buttonDeleteAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteAnswerActionPerformed(evt);
            }
        });

        tblAnswer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblAnswer);

        lbTitleAns.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTitleAns.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitleAns.setText("Manage Answer");

        javax.swing.GroupLayout pnlManageAnswerLayout = new javax.swing.GroupLayout(pnlManageAnswer);
        pnlManageAnswer.setLayout(pnlManageAnswerLayout);
        pnlManageAnswerLayout.setHorizontalGroup(
            pnlManageAnswerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageAnswerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageAnswerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlManageAnswerLayout.createSequentialGroup()
                        .addComponent(buttonAddAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpdateAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDeleteAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 844, Short.MAX_VALUE))
                    .addComponent(lbTitleAns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlManageAnswerLayout.setVerticalGroup(
            pnlManageAnswerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageAnswerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitleAns, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManageAnswerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonUpdateAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
        );

        jpCard.add(pnlManageAnswer, "card3");

        buttonAddQuestion.setText("Add");
        buttonAddQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddQuestionActionPerformed(evt);
            }
        });

        buttonUpdateQuestion.setText("Update");
        buttonUpdateQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateQuestionActionPerformed(evt);
            }
        });

        buttonDeleteQuestion.setText("Delete");
        buttonDeleteQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteQuestionActionPerformed(evt);
            }
        });

        tblQuestion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblQuestion);

        lbTitleQues.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTitleQues.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitleQues.setText("Manage Question");

        javax.swing.GroupLayout pnlManageQuestionLayout = new javax.swing.GroupLayout(pnlManageQuestion);
        pnlManageQuestion.setLayout(pnlManageQuestionLayout);
        pnlManageQuestionLayout.setHorizontalGroup(
            pnlManageQuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageQuestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageQuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlManageQuestionLayout.createSequentialGroup()
                        .addComponent(buttonAddQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpdateQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDeleteQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 844, Short.MAX_VALUE))
                    .addComponent(lbTitleQues, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlManageQuestionLayout.setVerticalGroup(
            pnlManageQuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageQuestionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitleQues, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManageQuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonUpdateQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
        );

        jpCard.add(pnlManageQuestion, "card3");

        buttonAddUser.setText("Add");
        buttonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddUserActionPerformed(evt);
            }
        });

        buttonUpdateUser.setText("Update");
        buttonUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateUserActionPerformed(evt);
            }
        });

        buttonDeleteUser.setText("Delete");
        buttonDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteUserActionPerformed(evt);
            }
        });

        buttonSearchUser.setText("Search");
        buttonSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchUserActionPerformed(evt);
            }
        });

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblUser);

        lbTitleUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTitleUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitleUser.setText("Manage Users");

        buttonReloadUser.setText("Reload");
        buttonReloadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReloadUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlManageUserLayout = new javax.swing.GroupLayout(pnlManageUser);
        pnlManageUser.setLayout(pnlManageUserLayout);
        pnlManageUserLayout.setHorizontalGroup(
            pnlManageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlManageUserLayout.createSequentialGroup()
                        .addComponent(buttonAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpdateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonReloadUser, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 410, Short.MAX_VALUE))
                    .addComponent(lbTitleUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlManageUserLayout.setVerticalGroup(
            pnlManageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitleUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonUpdateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReloadUser, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpCard.add(pnlManageUser, "card2");

        lbAdImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(lbAdImg, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbAdImg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cLayout = (CardLayout) (jpCard.getLayout());
        cLayout.show(jpCard, "ManageUser");
        loadUsers();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cLayout = (CardLayout) (jpCard.getLayout());
        cLayout.show(jpCard, "ManageQuestion");
        loadQuestion();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cLayout = (CardLayout) (jpCard.getLayout());
        cLayout.show(jpCard, "ManageAnswer");
        loadAnswer();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void buttonUpdateQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateQuestionActionPerformed
        // TODO add your handling code here:
        ManageQuestion mq = new ManageQuestion(this, true, this);
        int pos = tblQuestion.getSelectedRow();
        if (pos < 0) {
            pos = 0;
        }
        if (lstQuestion.get(pos).getImage() == null) {
            mq.buttonCloseImage.setVisible(false);
        }
        mq.lblId.setText(String.valueOf(lstQuestion.get(pos).getId()));
        mq.txaQuestion.setText(lstQuestion.get(pos).getQuestion());
        mq.cbxLevel.getModel().setSelectedItem(new level.Level(lstQuestion.get(pos).getLevelId(), lstQuestion.get(pos).getLevel()));
        mq.lblPathImage.setText(lstQuestion.get(pos).getImage());
        String fileName = lstQuestion.get(pos).getImage();
        ImageIcon icon = new ImageIcon(fileName);
        mq.lblImage.setIcon(icon);
        mq.setLocationRelativeTo(null);
        mq.buttonAddQuestion.setVisible(false);
        mq.setVisible(true);
    }//GEN-LAST:event_buttonUpdateQuestionActionPerformed

    private void buttonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddUserActionPerformed
        ManageUser mu = new ManageUser(this, true, this);
        mu.buttonUpdateUser.setVisible(false);
        mu.lblFieldId.setVisible(false);
        mu.lblFieldMark.setVisible(false);
        mu.setLocationRelativeTo(null);
        mu.setVisible(true);
    }//GEN-LAST:event_buttonAddUserActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thoát?", "Close Window?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            Login l;
            try {
                l = new Login();
                l.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void buttonUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateUserActionPerformed
        // TODO add your handling code here:
        ManageUser mu = new ManageUser(this, true, this);
        RoleManager rm = new RoleManager();
        int pos = tblUser.getSelectedRow();
        if (pos < 0) {
            pos = 0;
        }
        if (!lstUser.get(pos).getAccount().equals("admin") && !lstUser.get(pos).getPass().equals("admin123")) {
            mu.buttonAddUser.setVisible(false);
            mu.lblId.setText(String.valueOf(lstUser.get(pos).getId()));
            mu.txtAccount.setText(lstUser.get(pos).getAccount());
            mu.txtPass.setText(lstUser.get(pos).getPass());
            mu.lblMark.setText(String.valueOf(lstUser.get(pos).getMark()));
            mu.txtName.setText(lstUser.get(pos).getName());
            mu.txtIdentificationCard.setText(lstUser.get(pos).getIdentificationCard());
            mu.jdpBirthday.getFormattedTextField().setText(lstUser.get(pos).getBirthday());
            mu.txaAddress.setText(lstUser.get(pos).getAddress());
            mu.lblPathImage.setText(lstUser.get(pos).getImage());
            mu.cbxRole.getModel().setSelectedItem(new Role(lstUser.get(pos).getRoleId(), lstUser.get(pos).getRole()));
            mu.cbxDriverLicense.getModel().setSelectedItem(new Types(lstUser.get(pos).getDriverLicenseId(), lstUser.get(pos).getDriverLicense()));
            if (lstUser.get(pos).getGender() == 1) {
                mu.rdbMale.setSelected(true);
            } else {
                mu.rdbFemale.setSelected(true);
            }
            String fileName = lstUser.get(pos).getImage();
            ImageIcon icon1 = new ImageIcon(fileName);
            Image image = icon1.getImage().getScaledInstance(mu.lblImage.getWidth(), mu.lblImage.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon2 = new ImageIcon(image);
            mu.lblImage.setIcon(icon2);
            mu.setLocationRelativeTo(null);
            mu.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "This row is default account, you can't update it");
        }
    }//GEN-LAST:event_buttonUpdateUserActionPerformed

    private void buttonDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteUserActionPerformed
        // TODO add your handling code here:
        int choose = JOptionPane.showConfirmDialog(rootPane, "Are you sure want to delete this user?", "Message", JOptionPane.OK_CANCEL_OPTION);
        if (choose == JOptionPane.OK_OPTION) {
            int pos = tblUser.getSelectedRow();
            if (pos < 0) {
                pos = 0;
            }
            if (!lstUser.get(pos).getAccount().equals("admin") && !lstUser.get(pos).getPass().equals("admin123")) {
                UserManager um = new UserManager();
                int id = Integer.parseInt(tblUser.getValueAt(tblUser.getSelectedRow(), 0).toString());
                int row = um.deleteUser(id);
                if (row == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Delete user success");
                    loadUsers();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Delete user fail, please try again!!!");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "This row is default account, you can't delete it");
            }
        }
    }//GEN-LAST:event_buttonDeleteUserActionPerformed

    private void buttonSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchUserActionPerformed
        // TODO add your handling code here:
        OptionsSearchUser osu = new OptionsSearchUser(this, true);
        osu.setLocationRelativeTo(null);
        osu.setVisible(true);
    }//GEN-LAST:event_buttonSearchUserActionPerformed

    private void buttonAddQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddQuestionActionPerformed
        // TODO add your handling code here:
        ManageQuestion mq = new ManageQuestion(this, true, this);
        mq.setLocationRelativeTo(null);
        mq.lblFieldId.setVisible(false);
        mq.buttonUpdateQuestion.setVisible(false);
        mq.buttonCloseImage.setVisible(false);
        mq.setVisible(true);
    }//GEN-LAST:event_buttonAddQuestionActionPerformed

    private void buttonDeleteQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteQuestionActionPerformed
        // TODO add your handling code here:
        int choose = JOptionPane.showConfirmDialog(rootPane, "Are you sure want to delete this question?", "Message", JOptionPane.OK_CANCEL_OPTION);
        if (choose == JOptionPane.OK_OPTION) {
            int pos = tblQuestion.getSelectedRow();
            if (pos < 0) {
                pos = 0;
            }
            QuestionManager qm = new QuestionManager();
            int id = Integer.parseInt(tblQuestion.getValueAt(tblQuestion.getSelectedRow(), 0).toString());
            int row = qm.deleteQuestion(id);
            if (row == 0) {
                JOptionPane.showMessageDialog(rootPane, "Delete question success");
                loadQuestion();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Delete question fail, please try again!!!");
            }
        }
    }//GEN-LAST:event_buttonDeleteQuestionActionPerformed

    private void buttonAddAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddAnswerActionPerformed
        // TODO add your handling code here:
        ManageAnswer ma = new ManageAnswer(this, true, this);
        ma.buttonUpdateAnswer.setVisible(false);
        ma.setLocationRelativeTo(null);
        ma.setVisible(true);
    }//GEN-LAST:event_buttonAddAnswerActionPerformed

    private void buttonUpdateAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateAnswerActionPerformed
        // TODO add your handling code here:
        ManageAnswer ma = new ManageAnswer(this, true, this);
        int pos = tblAnswer.getSelectedRow();
        if (pos < 0) {
            pos = 0;
        }
        ma.txaAnswer.setText(lstAnswer.get(pos).getAnswer());
        if (lstAnswer.get(pos).getResult() == 1) {
            ma.rdbTrue.setSelected(true);
        } else {
            ma.rdbFalse.setSelected(true);
        }
        ma.cbxQuestion.getModel().setSelectedItem(new Questions(lstAnswer.get(pos).getQuestionId(), lstAnswer.get(pos).getQuestion()));
        ma.lblId.setText(String.valueOf(lstAnswer.get(pos).getId()));
        ma.buttonAddAnswer.setVisible(false);
        ma.setLocationRelativeTo(null);
        ma.setVisible(true);
    }//GEN-LAST:event_buttonUpdateAnswerActionPerformed

    private void buttonDeleteAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteAnswerActionPerformed
        // TODO add your handling code here:
        int choose = JOptionPane.showConfirmDialog(rootPane, "Are you sure want to delete this answer?", "Message", JOptionPane.OK_CANCEL_OPTION);
        if (choose == JOptionPane.OK_OPTION) {
            int pos = tblAnswer.getSelectedRow();
            if (pos < 0) {
                pos = 0;
            }
            AnswerManager am = new AnswerManager();
            int id = Integer.parseInt(tblAnswer.getValueAt(tblAnswer.getSelectedRow(), 0).toString());
            int row = am.deleteAnswer(id);
            if (row == 0) {
                JOptionPane.showMessageDialog(rootPane, "Delete answer success");
                loadAnswer();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Delete answer fail, please try again!!!");
            }
        }
    }//GEN-LAST:event_buttonDeleteAnswerActionPerformed

    private void buttonReloadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReloadUserActionPerformed
        // TODO add your handling code here:
        loadUsers();
    }//GEN-LAST:event_buttonReloadUserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddAnswer;
    private javax.swing.JButton buttonAddQuestion;
    private javax.swing.JButton buttonAddUser;
    private javax.swing.JButton buttonDeleteAnswer;
    private javax.swing.JButton buttonDeleteQuestion;
    private javax.swing.JButton buttonDeleteUser;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonReloadUser;
    private javax.swing.JButton buttonSearchUser;
    private javax.swing.JButton buttonUpdateAnswer;
    private javax.swing.JButton buttonUpdateQuestion;
    private javax.swing.JButton buttonUpdateUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JPanel jpCard;
    private javax.swing.JLabel lbAdImg;
    public static javax.swing.JLabel lbAdmin;
    private javax.swing.JLabel lbTitleAns;
    private javax.swing.JLabel lbTitleQues;
    private javax.swing.JLabel lbTitleUser;
    private javax.swing.JPanel pnlManageAnswer;
    private javax.swing.JPanel pnlManageQuestion;
    private javax.swing.JPanel pnlManageUser;
    private javax.swing.JTable tblAnswer;
    private javax.swing.JTable tblQuestion;
    public static javax.swing.JTable tblUser;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionloadUser() {
        loadUsers();
    }

    public void actionloadQuestion() {
        loadQuestion();
    }

    public void actionloadAnswer() {
        loadAnswer();
    }
}
