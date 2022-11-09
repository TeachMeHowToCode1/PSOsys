/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package routinPackage;

/**
 *
 * @author admin
 */
import java.sql.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.util.logging.Logger;

public class register extends javax.swing.JFrame {

    /**
     * Creates new form register
     */
    public register() {
        initComponents();

        getOffice();
        getPosition();
        

    }
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void getOffice() {
        try {
            con = myConnection.getConnection();
            pst = con.prepareStatement("SELECT office_name FROM office_tbl ");
            rs = pst.executeQuery();

            while (rs.next()) {
                String office = rs.getString("office_name");
                office_combo.addItem(office);

            }
            pst.close();
            rs.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void getPosition() {
        try {
            con = myConnection.getConnection();
            pst = con.prepareStatement("SELECT position_type FROM position_tbl ");
            rs = pst.executeQuery();

            while (rs.next()) {
                String position = rs.getString("position_type");
                position_combo.addItem(position);

            }
            pst.close();
            rs.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

//    public void reg() {
//        office_combo.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent ie) {
//                if (ie.getStateChange() == ItemEvent.SELECTED) {
//
//                    if (office_combo.getSelectedItem().toString().equalsIgnoreCase("CHIEF STATISTICAL SPECIALIST")) {
//                        position_combo.removeAllItems();
//
//                        position_combo.addItem("CSS");
//
//                    } else if (office_combo.getSelectedItem().toString().equalsIgnoreCase("STATISTICAL OPERATIONS & COORDINATION SECTION")) {
//
//                        position_combo.removeAllItems();
//
//                        position_combo.removeAllItems();
//                        position_combo.addItem("Supervising Statistical Specialist");
//                        position_combo.addItem("Statistical Specialist III");
//                        position_combo.addItem("Statistical Specialist II");
//                        position_combo.addItem("Statistical Specialist I");
//                        position_combo.addItem("Statical Analyst");
//                        position_combo.addItem("Assistant Statistician");
//
//                    } else if (office_combo.getSelectedItem().toString().equalsIgnoreCase("FINANCE")) {
//
//                        position_combo.removeAllItems();
//                        position_combo.setEnabled(true);
//
//                        position_combo.addItem("Accountant I");
//                        position_combo.addItem("Administrative Officer I");
//                        position_combo.addItem("Administrative Assistant II");
//                        position_combo.addItem("Administrative Assistant III");
//
//                    } else if (office_combo.getSelectedItem().toString().equalsIgnoreCase("ADMINISTRATIVE SUPPORT")) {
//
//                        position_combo.removeAllItems();
//                        position_combo.setEnabled(true);
//                        position_combo.addItem("Administrative Aide VI   ");
//
//                    } else if (office_combo.getSelectedItem().toString().equalsIgnoreCase("GENERAL SERVICES")) {
//
//                        position_combo.removeAllItems();
//                        position_combo.setEnabled(true);
//                        position_combo.addItem("Driver");
//                        position_combo.addItem("PhilSys Crowd Control");
//                        position_combo.addItem("Utility Worker");
//
//                    } else if (office_combo.getSelectedItem().toString().equalsIgnoreCase("CIVIL REGISTRATION")) {
//
//                        position_combo.removeAllItems();
//                        position_combo.setEnabled(true);
//                        position_combo.addItem("Registration Officer I");
//
//                    } else if (office_combo.getSelectedItem().toString().equalsIgnoreCase("PHILSYS REGISTRATION SECTION")) {
//
//                        position_combo.removeAllItems();
//                        position_combo.setEnabled(true);
//                        position_combo.addItem("Provincial Focal Person");
//                        position_combo.addItem("Information System Analyst ");
//                        position_combo.addItem("Clerk");
//
//                    }
//                }
//            }
//
//        });
//    }
//    public void getofficeCombo(){
//        
//        
//    }
    public void register() {

        String username = user_form.getText();
        String pass1 = pass_form.getText();
        String pass2 = pass2_form.getText();

        String first_name = fname_form.getText().toUpperCase();
        String last_name = lname_form.getText().toUpperCase();
        String mid = midname.getText().toUpperCase();
        
       String initial =  mid.substring(0, 1);
       String  fullname = first_name + " "+initial+". " + last_name;

     
        try {
                   

            con = myConnection.getConnection();
            pst = con.prepareStatement("SELECT * FROM user_tbl WHERE username='" + username + "' ");
            rs = pst.executeQuery();
            if (rs.absolute(1)) {
                JOptionPane.showMessageDialog(null, "Username Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (username.length() < 6) {
                JOptionPane.showMessageDialog(null, "Username is Too Short! Minimum of 6 Character", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (pass1.length() < 6) {
                JOptionPane.showMessageDialog(null, "Password is Too Short! Minimum of 6 Character", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                if (username.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Add username", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (pass1.equalsIgnoreCase("")) {
                } else if (!pass1.equalsIgnoreCase(pass2)) {
                    JOptionPane.showMessageDialog(null, "Password did not match!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (first_name.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Add Firstname", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (last_name.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Add Lastname", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    
                    
                    try {


            String sql1 = "SELECT  * FROM office_tbl WHERE office_name ='"+office_combo.getSelectedItem()+"'";
          
            ResultSet rs1;
            rs1 = pst.executeQuery(sql1);
            String officeid=null;
            while(rs1.next()){
                 officeid = rs1.getString("id");
            }
            
             
            rs1.close();
               String sql2 = "SELECT  * FROM position_tbl WHERE position_type ='"+position_combo.getSelectedItem()+"'";
          
            ResultSet rs2;
            rs2 = pst.executeQuery(sql2);
            String positionid=null;
            while(rs2.next()){
                 positionid = rs2.getString("id");
            }
            
             
            rs2.close();
           

            
            String sql = "INSERT INTO user_tbl(fname,middle_name,lname, fullname, username,password,office_id,position_id) VALUES ('"+ first_name + "','"+mid+"','" + last_name + "','"+fullname+"','" + username + "','" + pass1 +"','"+officeid +"', '"+positionid+"')";
             
            pst.addBatch(sql);
            pst.executeBatch();
            
            con.close();
            pst.close();
            JOptionPane.showMessageDialog(null, "Succesful saved");

        } catch (Exception e) {
            e.printStackTrace();

        }
                }
            }

        } catch (Exception ex) {
             ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lname_form = new javax.swing.JTextField();
        fname_form = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pass2_form = new javax.swing.JPasswordField();
        pass_form = new javax.swing.JPasswordField();
        user_form = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        office_combo = new javax.swing.JComboBox<>();
        position_combo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        midname = new javax.swing.JTextField();

        jLabel5.setText("PASSWORD");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("FIRST NAME");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("LAST NAME");

        lname_form.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        fname_form.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        fname_form.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fname_formActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("USERNAME");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("PASSWORD");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("CONFIRM PASSWORD");

        pass2_form.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        pass_form.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        user_form.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("OFFICE");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("POSITION ");

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("REGISTER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        office_combo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        office_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select  Office/Section " }));
        office_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                office_comboActionPerformed(evt);
            }
        });

        position_combo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        position_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Position" }));
        position_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                position_comboActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/register0user.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("MIDDLE NAME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(office_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, 289, Short.MAX_VALUE)
                    .addComponent(pass2_form, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass_form, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lname_form, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fname_form, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(midname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(user_form)
                    .addComponent(position_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(178, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(289, 289, 289))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel9)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(user_form, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(fname_form, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(midname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lname_form, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pass_form, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(pass2_form, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(office_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(position_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        register();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void office_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_office_comboActionPerformed

//        reg();
    }//GEN-LAST:event_office_comboActionPerformed

    private void position_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_position_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_position_comboActionPerformed

    private void fname_formActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fname_formActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fname_formActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new register().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fname_form;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lname_form;
    private javax.swing.JTextField midname;
    private javax.swing.JComboBox<String> office_combo;
    private javax.swing.JPasswordField pass2_form;
    private javax.swing.JPasswordField pass_form;
    private javax.swing.JComboBox<String> position_combo;
    private javax.swing.JTextField user_form;
    // End of variables declaration//GEN-END:variables
}
