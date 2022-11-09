/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package routinPackage;

import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ISA1-DABON
 */
public class adminhistory extends javax.swing.JFrame {
    
    
    java.sql.Connection con;
    PreparedStatement pst;
    ResultSet rs;
  

    /**
     * Creates new form adminhistory
     */
    public adminhistory() {
        initComponents();
        
        historydisplay();
        
        idx.setVisible(false);
    }
    
    
    
       public void historydisplay(){
              try {
                con = myConnection.getConnection();
                  String query = "SELECT\n"
                          + "    route_id,\n"
                          + "    subject,\n"    
                          + "    action,\n"
                          + "    remarks,\n"
                          + "    date,\n"
                          + "    sender_name,\n"
                             + "     receiveby,\n"
                            + "     sendto_name,\n"
                          + "  status\n"
                          + "FROM\n"
                          + "    logs m\n"
                          + "   group by m.route_id ORDER BY route_id desc;";
                  

                  pst = con.prepareStatement(query);
                  rs = pst.executeQuery();
                  DefaultTableModel model = (DefaultTableModel) admin_tbl.getModel();
                  model.setRowCount(0);
                  while (rs.next()) {
      
                      model.addRow(new Object[]{
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4),
                          rs.getString(5),
                        rs.getString(6),
                          rs.getString(7),
                          rs.getString(8),
                             rs.getString(9),
                   
                         
                      
                      });
            }
            admin_tbl.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        public void showhistoryadmin() {

        String id = idx.getText();

        try {
            con = myConnection.getConnection();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT route_id, subject, action, remarks, date, sender_name, sendto_name, receiveby, status FROM logs where route_id ='" + id + "'");

            DefaultTableModel model = (DefaultTableModel) admin_tbl.getModel();
            model.setRowCount(0);
                   
                  while (rs.next()) {
                  
                      model.addRow(new Object[]{
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4),
                          rs.getString(5),
                          rs.getString(6),
                          rs.getString(8),
                          rs.getString(7),
                          rs.getString(9),});
                  }
                  admin_tbl.setModel(model);

              } catch (Exception e) {
                  e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        admin_tbl = new javax.swing.JTable();
        idx = new javax.swing.JLabel();
        back_tbn = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        admin_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ROUTE ID CONTROL NUMBER", "Subject", "Action", "Remarks", "Date", "From", "Received ", "To", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        admin_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admin_tblMouseClicked(evt);
            }
        });
        admin_tbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                admin_tblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(admin_tbl);

        idx.setText("jLabel1");

        back_tbn.setText("back");
        back_tbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_tbnActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel1.setText("SEARCH");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        jButton1.setText("SIGN OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(82, 82, 82)
                .addComponent(idx)
                .addGap(182, 182, 182))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(back_tbn)
                                .addGap(944, 944, 944)))
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(idx))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(back_tbn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void admin_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_tblMouseClicked
      int row = admin_tbl.getSelectedRow();
                    if (row == -1) {

                    } else {
                       
                        idx.setText(admin_tbl.getValueAt(row, 0).toString());
                        
                        showhistoryadmin();

                    }
    }//GEN-LAST:event_admin_tblMouseClicked

    private void back_tbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_tbnActionPerformed
historydisplay();        // TODO add your handling code here:
    }//GEN-LAST:event_back_tbnActionPerformed

    private void admin_tblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_admin_tblKeyPressed
 if(evt.getKeyCode()==KeyEvent.VK_ESCAPE ||evt.getKeyCode()==KeyEvent.VK_BACK_SPACE ){
              historydisplay(); 
       
   }        // TODO add your handling code here:
    }//GEN-LAST:event_admin_tblKeyPressed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
     DefaultTableModel model = (DefaultTableModel) admin_tbl.getModel();

        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        admin_tbl.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(jTextField1.getText().trim()));

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Login h = new Login();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(adminhistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminhistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminhistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminhistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminhistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable admin_tbl;
    private javax.swing.JButton back_tbn;
    private javax.swing.JLabel idx;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}