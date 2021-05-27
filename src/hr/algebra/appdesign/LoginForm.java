/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.appdesign;

import hr.algebra.DAL.sql.DataSourceSingleton;
import hr.algebra.utils.MessageUtils;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.awt.Toolkit;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



/**
 *
 * @author frank
 */
public class LoginForm extends javax.swing.JFrame {

    private static final String CREATE_USER = "{ CALL createUsers (?,?,?) }";
    private static final String FIND_USER = "{ CALL findUsers (?,?) }";
     
    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lnlBottomText = new javax.swing.JLabel();
        lblBottomText2 = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        txtPass = new javax.swing.JPasswordField();
        lblBackgImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" Cinestar Cinemas Login");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lnlBottomText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lnlBottomText.setForeground(new java.awt.Color(0, 0, 102));
        lnlBottomText.setText("VUA Algebra 2020");
        getContentPane().add(lnlBottomText, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 540, 110, 20));

        lblBottomText2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblBottomText2.setForeground(new java.awt.Color(0, 0, 102));
        lblBottomText2.setText("Franko Papić 3RP3");
        getContentPane().add(lblBottomText2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, 110, 20));

        lblLogin.setText("Login");
        getContentPane().add(lblLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 300, -1));

        lblPassword.setText("Password");
        getContentPane().add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, -1, -1));

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, -1, -1));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 300, -1));

        lblBackgImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logobackground.jpg"))); // NOI18N
        getContentPane().add(lblBackgImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (txtUsername.getText().isEmpty() || txtPass.getText().isEmpty()) {
            MessageUtils.showErrorMessage("Registration Error", "Please enter your information first");
        } else {
        try {
            DataSource dataSource = DataSourceSingleton.getInstance();
            Connection con = dataSource.getConnection();

            CallableStatement stmt = con.prepareCall(FIND_USER);
            stmt.setString(1, txtUsername.getText());
            stmt.setString(2, txtPass.getText());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MessageUtils.showInformationMessage("Login Successful", "Welcome back!");
                if (txtUsername.getText().equals("Admin")) {
                    MainApp as = new MainApp();
                    as.setVisible(true);
                    super.dispose();
                }
                else {
                    MainApp us = new MainApp();
                    us.setVisible(true);
                    super.dispose();
                }
            } else {
                MessageUtils.showInformationMessage("Login Error", "You have enter the wrond username/password combination");
                txtUsername.setText("");
                txtPass.setText("");
            }
        } catch (SQLException ex) {
            MessageUtils.showErrorMessage("Not connected to Database", "Connect to Database");
        } catch (Exception ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        if (txtUsername.getText().isEmpty() || txtPass.getText().isEmpty()) {
            MessageUtils.showErrorMessage("Registration Error", "Please enter your information first");
        } else {
        try {
            DataSource dataSource = DataSourceSingleton.getInstance();
            Connection con = dataSource.getConnection();

            //String sql = "insert into Users (Name,Password,AdminStatus) values (?,?,?)";
            CallableStatement stmt = con.prepareCall(CREATE_USER);
            stmt.setString(1, txtUsername.getText());
            stmt.setString(2, txtPass.getText());
            stmt.setInt(3, 0);
            stmt.executeUpdate();
            MessageUtils.showInformationMessage("Registration", "Succesfull registration, your account is created!");
        } catch (SQLException ex) {
            MessageUtils.showErrorMessage("DataBase Error", "Cant connect to database!");
        }
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    
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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel lblBackgImg;
    private javax.swing.JLabel lblBottomText2;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lnlBottomText;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}