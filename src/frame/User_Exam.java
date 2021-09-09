package frame;

import answer.AnswerManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import question.Question;
import question.QuestionManager;
import user.UserManager;

public class User_Exam extends javax.swing.JFrame implements TimeOut.CallbackTimeOut {
    
    public int user_id;
    public String user_name;
    
    public User_Exam() {
        initComponents();
        Clock();
        ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../image/logo-tongcuc-db.png")));
        Image i1 = img.getImage();
        Image i2 = i1.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(i2);
        lblImg.setIcon(i);
        this.setExtendedState(MAXIMIZED_BOTH);
    }
    
    int sec;
    int min;
    Timer timer;
    TimeOut to = new TimeOut(this, true, this);
    
    private void Clock() {
        min = Integer.parseInt(txtMinute.getText());
        sec = Integer.parseInt(txtSeconds.getText());
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sec == 0) {
                    sec = 60;
                    min--;
                }
                if (min < 0) {
                    min = 0;
                    sec = 0;
                    timer.stop();
                    to.setVisible(true);
                } else {
                    sec--;
                    if (min < 10) {
                        txtMinute.setText("0" + min);
                    } else {
                        txtMinute.setText("" + min);
                    }
                    if (sec < 10) {
                        txtSeconds.setText("0" + sec);
                    } else {
                        txtSeconds.setText("" + sec);
                    }
                }
            }
        });
        timer.start();
    }
    
    QuestionManager qm = new QuestionManager();
    AnswerManager am = new AnswerManager();
    List<Question> Exam = new ArrayList<>();
    CardLayout cLayout;
    
    public void Create_Exam(String user_driver_license) {
        qm.getQuestionExam("Dễ", user_driver_license);
        Collections.shuffle(qm.CauHoiDe);
        for (int i = 0; i < 5; i++) {
            Exam.add(qm.CauHoiDe.get(i));
        }
        qm.getQuestionExam("Thường", user_driver_license);
        Collections.shuffle(qm.CauHoiThuong);
        for (int i = 0; i < 10; i++) {
            Exam.add(qm.CauHoiThuong.get(i));
        }
        qm.getQuestionExam("Khó", user_driver_license);
        Collections.shuffle(qm.CauHoiKho);
        for (int i = 0; i < 5; i++) {
            Exam.add(qm.CauHoiKho.get(i));
        }
    }
    
    public void Create_jPanelCardLayout() {
        for (int i = 0; i < Exam.size(); i++) {
            am.CauTraLoi(Exam.get(i).getId());
            JPanel jp = new JPanel();
            jp.setBorder(new EmptyBorder(10, 10, 10, 10));
            jp.setLayout(new BorderLayout());
            
            JTextArea jQuestion = new JTextArea("Câu hỏi " + (i + 1) + " : " + Exam.get(i).getContent());
            jQuestion.setLineWrap(true);
            jQuestion.setEditable(false);
            jQuestion.setFocusable(false);
            jQuestion.setWrapStyleWord(true);
            jQuestion.setFont(new Font("Tahoma", Font.PLAIN, 20));
            jQuestion.setPreferredSize(new Dimension(250, 100));
            jp.add(jQuestion, BorderLayout.NORTH);
            
            JLabel jQuestion_Image = new JLabel();
            jQuestion_Image.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            if (Exam.get(i).getImage() != null) {
                ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Exam.get(i).getImage())));
                Image img1 = img.getImage();
                Image img2 = img1.getScaledInstance(600, 200, Image.SCALE_SMOOTH);
                ImageIcon img3 = new ImageIcon(img2);
                jQuestion_Image.setIcon(img3);
            }
            jp.add(jQuestion_Image, BorderLayout.CENTER);
            
            JPanel jpBottom = new JPanel();
            jpBottom.setLayout(new BorderLayout());
            JPanel jpBottomCenter = new JPanel();
            jpBottom.add(jpBottomCenter, BorderLayout.CENTER);
            JPanel jpBottomSouth = new JPanel();
            jpBottom.add(jpBottomSouth, BorderLayout.SOUTH);
            jpBottomCenter.setLayout(new GridLayout(0, 1));
            jpBottomCenter.setPreferredSize(new Dimension(250, 200));
            JButton btnNext = new JButton("Câu trước");
            btnNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cLayout = (CardLayout) (jPanel_Middle.getLayout());
                    cLayout.previous(jPanel_Middle);
                }
            });
            jpBottomSouth.add(btnNext);
            JButton btnPrevious = new JButton("Câu tiếp theo");
            btnPrevious.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cLayout = (CardLayout) (jPanel_Middle.getLayout());
                    cLayout.next(jPanel_Middle);
                }
            });
            jpBottomSouth.add(btnPrevious);
            jp.add(jpBottom, BorderLayout.SOUTH);
            int question_id = Exam.get(i).getId();
            ButtonGroup bg = new ButtonGroup();
            am.lstCauTraLoi.forEach((a) -> {
                JRadioButton jAnswer = new JRadioButton("<html>" + a.getAnswer() + "</html>");
                jAnswer.setFont(new Font("Tahoma", Font.PLAIN, 16));
                jAnswer.setActionCommand("" + a.getResult());
                jAnswer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int result = Integer.parseInt(bg.getSelection().getActionCommand());
                        am.getResult(user_id, question_id, result);
                    }
                });
                bg.add(jAnswer);
                jpBottomCenter.add(jAnswer);
            });
            jPanel_Middle.add(jp, "" + (i + 1));
        }
    }
    
    public void Create_btnQuestion() {
        jPanel_btnQuestion.setLayout(new GridLayout((Exam.size() / 2), 2));
        int count = 0;
        for (int i = 0; i < (Exam.size() / 2); i++) {
            for (int j = 0; j < 2; j++) {
                count++;
                int switchPanel = count;
                JButton b = new JButton("Câu hỏi " + count);
                if (count == 1) {
                    b.setBackground(Color.YELLOW);
                } else {
                    b.setBackground(Color.LIGHT_GRAY);
                }
                b.setFocusTraversalKeysEnabled(false);
                b.setPreferredSize(new Dimension(90, 45));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cLayout = (CardLayout) (jPanel_Middle.getLayout());
                        cLayout.show(jPanel_Middle, "" + switchPanel);
                        setBackgroundButton();
                        b.setBackground(Color.YELLOW);
                        
                    }
                });
                b.setVisible(true);
                jPanel_btnQuestion.add(b);
            }
        }
        jPanel_btnQuestion.validate();
        jPanel_btnQuestion.repaint();
    }
    
    public void setBackgroundButton() {
        Component[] components = jPanel_btnQuestion.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                ((JButton) component).setBackground(Color.LIGHT_GRAY);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Bottom = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btnKetThuc = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        lblImg = new javax.swing.JLabel();
        jPanel_Right = new javax.swing.JPanel();
        jPanel_Right_Content = new javax.swing.JPanel();
        jPanel_Time_Exam = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        txtMinute = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSeconds = new javax.swing.JLabel();
        jPanel_User = new javax.swing.JPanel();
        lblUser_Name = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel_btnQuestion = new javax.swing.JPanel();
        jPanel_Middle = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Your Exam");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        jPanel_Bottom.setBackground(new java.awt.Color(0, 153, 255));

        btnKetThuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });

        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Phần thi kết thúc sau khi hết thời gian hoặc thí sinh nhấn \"Kết thúc\".");

        javax.swing.GroupLayout jPanel_BottomLayout = new javax.swing.GroupLayout(jPanel_Bottom);
        jPanel_Bottom.setLayout(jPanel_BottomLayout);
        jPanel_BottomLayout.setHorizontalGroup(
            jPanel_BottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel_BottomLayout.createSequentialGroup()
                .addGroup(jPanel_BottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_BottomLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel73)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_BottomLayout.createSequentialGroup()
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel_BottomLayout.setVerticalGroup(
            jPanel_BottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_BottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_BottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_BottomLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addContainerGap())
        );

        getContentPane().add(jPanel_Bottom, java.awt.BorderLayout.PAGE_END);

        jPanel_Right_Content.setBackground(new java.awt.Color(0, 153, 255));

        jPanel_Time_Exam.setBackground(new java.awt.Color(0, 0, 0));

        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("THỜI GIAN CÒN LẠI :");

        txtMinute.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtMinute.setForeground(new java.awt.Color(255, 255, 255));
        txtMinute.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMinute.setText("15");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(":");

        txtSeconds.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSeconds.setForeground(new java.awt.Color(255, 255, 255));
        txtSeconds.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSeconds.setText("00");

        javax.swing.GroupLayout jPanel_Time_ExamLayout = new javax.swing.GroupLayout(jPanel_Time_Exam);
        jPanel_Time_Exam.setLayout(jPanel_Time_ExamLayout);
        jPanel_Time_ExamLayout.setHorizontalGroup(
            jPanel_Time_ExamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Time_ExamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74)
                .addGap(34, 34, 34)
                .addComponent(txtMinute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSeconds)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Time_ExamLayout.setVerticalGroup(
            jPanel_Time_ExamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Time_ExamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(txtMinute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSeconds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_User.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_User.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel_User.setPreferredSize(new java.awt.Dimension(221, 36));

        lblUser_Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUser_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Thí sinh:");

        javax.swing.GroupLayout jPanel_UserLayout = new javax.swing.GroupLayout(jPanel_User);
        jPanel_User.setLayout(jPanel_UserLayout);
        jPanel_UserLayout.setHorizontalGroup(
            jPanel_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_UserLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUser_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_UserLayout.setVerticalGroup(
            jPanel_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUser_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_btnQuestion.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout jPanel_btnQuestionLayout = new javax.swing.GroupLayout(jPanel_btnQuestion);
        jPanel_btnQuestion.setLayout(jPanel_btnQuestionLayout);
        jPanel_btnQuestionLayout.setHorizontalGroup(
            jPanel_btnQuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_btnQuestionLayout.setVerticalGroup(
            jPanel_btnQuestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_Right_ContentLayout = new javax.swing.GroupLayout(jPanel_Right_Content);
        jPanel_Right_Content.setLayout(jPanel_Right_ContentLayout);
        jPanel_Right_ContentLayout.setHorizontalGroup(
            jPanel_Right_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_btnQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_Right_ContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Right_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Time_Exam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_User, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_Right_ContentLayout.setVerticalGroup(
            jPanel_Right_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Right_ContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Time_Exam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_User, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_btnQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_RightLayout = new javax.swing.GroupLayout(jPanel_Right);
        jPanel_Right.setLayout(jPanel_RightLayout);
        jPanel_RightLayout.setHorizontalGroup(
            jPanel_RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_RightLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_Right_Content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_RightLayout.setVerticalGroup(
            jPanel_RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Right_Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_Right, java.awt.BorderLayout.LINE_END);

        jPanel_Middle.setLayout(new java.awt.CardLayout());
        getContentPane().add(jPanel_Middle, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        timer.stop();
        this.dispose();
        UserManager u = new UserManager();
        u.updateResult(user_id);
        u.showResult(user_id);
        AnswerManager a = new AnswerManager();
        a.countAns(user_id);
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        this.setState(User_Exam.NORMAL);
    }//GEN-LAST:event_formWindowIconified

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
            java.util.logging.Logger.getLogger(User_Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new User_Exam().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel_Bottom;
    private javax.swing.JPanel jPanel_Middle;
    private javax.swing.JPanel jPanel_Right;
    private javax.swing.JPanel jPanel_Right_Content;
    private javax.swing.JPanel jPanel_Time_Exam;
    private javax.swing.JPanel jPanel_User;
    private javax.swing.JPanel jPanel_btnQuestion;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblImg;
    public static javax.swing.JLabel lblUser_Name;
    private javax.swing.JLabel txtMinute;
    private javax.swing.JLabel txtSeconds;
    // End of variables declaration//GEN-END:variables

    @Override
    public void finishExam() {
        this.dispose();
        UserManager u = new UserManager();
        u.updateResult(user_id);
        u.showResult(user_id);
        AnswerManager a = new AnswerManager();
        a.countAns(user_id);
    }
}
