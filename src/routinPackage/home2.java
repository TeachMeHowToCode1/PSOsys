/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package routinPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author admin
 */
public class home2 extends javax.swing.JFrame {
  public String sender = Login.userid;
    java.sql.Connection con;
    PreparedStatement pst;
    ResultSet rs;
    home h = new home();
 ArrayList <String> actions = new ArrayList<>();
 public String of;

    /**
     * Creates new form home2
     */
    public home2() {
        initComponents();
//        getOffice();
        clock();
       getOfficename();
       getfullname();
     signed();
   act.setText(h.actions.toString());
act.setVisible(false);

userid.setVisible(false);
id.setEditable(false);
       
    }
    
//    
//    public void updateroute(){
//        try {
//            String status = "Received";
//
//         String routeid = id.getText().trim();
//            String from = name.getText().trim();
//           
//            String to_data = to.getSelectedItem().toString().trim();
//            String subject = subj.getText().trim();
////         String action = action_label.getText().toString();
//            String remarks = notes.getText().trim();
//            String date = time.getText().trim();
//            String userId = userid.getText().trim();
//            con = myConnection.getConnection();
//            pst = con.prepareStatement("insert  into all_route(sender_name,sendto_name,subject,action,remarks,user_id,status,date,route_id)values(?,?,?,?,?,?,?,?,?)");
//
//            pst.setString(1, from);
//          
//            pst.setString(2, to_data);
//            pst.setString(3, subject);
//            pst.setString(4, actions.toString());
//            pst.setString(5, remarks);
//            pst.setString(6, sender);
//            pst.setString(7, status);
//             pst.setString(8, date);
//             pst.setString(9, routeid);
//            pst.executeUpdate();
//            con.close();
//                JOptionPane.showMessageDialog(null, "Data Received Forwarded to Pending");
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        
//        
//    }
    
//     public void movetopending() {
//        String i = id.getText();
//        
//        try {
//            con = myConnection.getConnection();
//            pst = con.prepareStatement("delete from pending_tbl where route_id='" + i + "'");
//            pst.executeUpdate();
//            con.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
   
     public void clock() {
        DateFormat.getDateTimeInstance().format(new Date());
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentdate.setText(
                        
                        DateFormat.getDateTimeInstance().format(new Date())
                );
                                currentdate.setText(
                        
                        DateFormat.getDateTimeInstance().format(new Date())
                );
            }
            
        });
        t.setRepeats(true);
        t.setCoalesce(true);
        t.setInitialDelay(0);
        t.start();

    }
       public void movetoOutgoing() {
        String i = id.getText();
        
        try {
            con = myConnection.getConnection();
            pst = con.prepareStatement("delete from receive_tbl where route_id='" + i + "'");
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
       
        public void signed(){
              if(signature.isSelected()){
                     notes.setText("Signed and for Appropriate Action ");
              }
         }
            
//      public void getOffice() {
//   con = myConnection.getConnection();
//       
//        try {
//         
//   String query = "SELECT user_tbl.fullname, office_name  from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id order by office_lvl ASC";
//
//           
//            pst = con.prepareStatement(query);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//
//                to.addItem(rs.getString("fullname"));
//              
//            }
//            
//           con.close();
//        } catch (SQLException ex) {
//           ex.getStackTrace();
//        }
//
//    }
        public void getOfficename() {

        con = myConnection.getConnection();

        try {
            
            String query = "SELECT DISTINCT  office_tbl.office_name from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id order by office_lvl ASC";

            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {

                section.addItem(rs.getString(1));

            }
            of=section.getSelectedItem().toString();
           
            con.close();
        } catch (SQLException ex) {
                       JOptionPane.showMessageDialog(null, ex.getStackTrace());

        }

    }
          public void getfullname() {
       
        con = myConnection.getConnection();
       
        try {
             String query = "SELECT  user_tbl.fullname from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id WHERE office_tbl.office_name =?";
          
            pst = con.prepareStatement(query);
            
            pst.setString(1,of);
          
      
            
            rs = pst.executeQuery();
       
       
        to.removeAllItems();
       
            while (rs.next()) {
              
                to.addItem(rs.getString(1));
               
            
            
            }
         
        con.close();
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getStackTrace());
        }

    }
      
      
      public void logsforward(){
           try {
     
            String status = "FORWARDED";

            String routeid = id.getText().trim();
            String from = name.getText().trim();

            String to_data = to.getSelectedItem().toString().trim();
            String subject = subj.getText().trim();

            String remarks = notes.getText().trim();
            String date = currentdate.getText().trim();
            String userId = userid.getText().trim();
            con = myConnection.getConnection();

            pst = con.prepareStatement("insert into logs (user_id, route_id, sender_name,sendto_name,subject,action,remarks,status, date, receiveby)values(?,?,?,?,?,?,?,?,?,?)");
             pst.setString(1, sender);
            pst.setString(2, routeid);
            pst.setString(3, from);
            pst.setString(4, to_data);
            pst.setString(5, subject);
            pst.setString(6, act.getText());
            pst.setString(7, remarks);
            pst.setString(8, status);
            pst.setString(9, date);
          pst.setString(10, received_name.getText());


            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Data Forwarded");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          
      }
       public void donelogs(){
            try {
     
            String status = "DONE";

            String routeid = id.getText().trim();
            String from = name.getText().trim();

            String to_data = to.getSelectedItem().toString().trim();
            String subject = subj.getText().trim();

            String datereceive = currentdate.getText();
            String remarks = notes.getText().trim();
            String userId = userid.getText().trim();
            con = myConnection.getConnection();

            pst = con.prepareStatement("insert into logs (user_id, route_id, sender_name,sendto_name,subject,action,remarks,status, date, receiveby)values(?,?,?,?,?,?,?,?,?,?)");
             pst.setString(1, sender);
            pst.setString(2, routeid);
            pst.setString(3, from);
            pst.setString(4, received_name.getText());
            pst.setString(5, subject);
            pst.setString(6, act.getText());
            pst.setString(7, remarks);
            pst.setString(8, status);
            pst.setString(9, datereceive);
          pst.setString(10, received_name.getText());


            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Routing Successfully Done");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
       }

      
      public void addreciveby(){
          try {
     
            String status = "FORWARDED";

            String routeid = id.getText().trim();
            String from = name.getText().trim();

            String to_data = to.getSelectedItem().toString().trim();
            String subject = subj.getText().trim();

            String datereceive = currentdate.getText();
            String remarks = notes.getText().trim();
            String date = time.getText().trim();
            String userId = userid.getText().trim();
            con = myConnection.getConnection();

            pst = con.prepareStatement("insert into all_route ( route_id, sender_name,sendto_name,subject,action,remarks,status, date, receiveby)values(?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, routeid);
            pst.setString(2, from);
            pst.setString(3, to_data);
            pst.setString(4, subject);
            pst.setString(5, act.getText());
            pst.setString(6, remarks);
            pst.setString(7, status);
            pst.setString(8, date);
          pst.setString(9, received_name.getText());


            pst.executeUpdate();
            con.close();
           

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          
      }
      
      public void statusdone(){
            try {
     
            String status = "DONE";

            String routeid = id.getText().trim();
            String from = name.getText().trim();

                       String subject = subj.getText().trim();

            String datereceive = currentdate.getText();
            String remarks = notes.getText().trim();
            String date = currentdate.getText().trim();
            String userId = userid.getText().trim();
            con = myConnection.getConnection();

            pst = con.prepareStatement("insert into done_tbl ( route_id, sender_name,subject,action,remarks,status, date, receiveby)values(?,?,?,?,?,?,?,?)");
            
            pst.setString(1, routeid);
            pst.setString(2, from);
          
            pst.setString(3, subject);
            pst.setString(4, act.getText());
            pst.setString(5, remarks);
            pst.setString(6, status);
            pst.setString(7, date);
          pst.setString(8, received_name.getText());


            pst.executeUpdate();
            con.close();
           

        } catch (SQLException ex) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        notes = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        subj = new javax.swing.JTextArea();
        to = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userid = new javax.swing.JLabel();
        comments = new javax.swing.JCheckBox();
        consideration = new javax.swing.JCheckBox();
        drafting = new javax.swing.JCheckBox();
        information = new javax.swing.JCheckBox();
        others = new javax.swing.JCheckBox();
        appropraite = new javax.swing.JCheckBox();
        signature = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        act = new javax.swing.JTextField();
        forward = new javax.swing.JButton();
        currentdate = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        received_name = new javax.swing.JLabel();
        done = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        submitted = new javax.swing.JLabel();
        section = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel10.setText("FROM");

        name.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        name.setText("jLabel9");

        time.setText("1111111111");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("SUBJECT");

        notes.setColumns(20);
        notes.setRows(5);
        jScrollPane4.setViewportView(notes);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("ACTION NEEDED");

        subj.setEditable(false);
        subj.setColumns(20);
        subj.setRows(5);
        jScrollPane2.setViewportView(subj);

        to.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                toItemStateChanged(evt);
            }
        });
        to.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("TO");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("REMARKS/NOTES");

        userid.setText("jLabel8");

        comments.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        comments.setText("For Comments");
        comments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentsActionPerformed(evt);
            }
        });

        consideration.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        consideration.setText("For Consideration");
        consideration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                considerationActionPerformed(evt);
            }
        });

        drafting.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        drafting.setText("For Drafting of Reply");
        drafting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                draftingActionPerformed(evt);
            }
        });

        information.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        information.setText("For Information/File");
        information.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informationActionPerformed(evt);
            }
        });

        others.setText("Other");
        others.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                othersActionPerformed(evt);
            }
        });

        appropraite.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        appropraite.setText("For Appropriate Action");
        appropraite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appropraiteActionPerformed(evt);
            }
        });

        signature.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        signature.setText("For Initial/signature");
        signature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signatureActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        act.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actActionPerformed(evt);
            }
        });

        forward.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        forward.setText("FORWARD");
        forward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardActionPerformed(evt);
            }
        });

        currentdate.setText("jLabel9");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel11.setText("RECEIVED BY ");

        received_name.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        received_name.setText("jLabel9");

        done.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        done.setText("END OF THE PROCESS");
        done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneActionPerformed(evt);
            }
        });

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        submitted.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        submitted.setText("DATE/TIME SUBMITTED");

        section.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                sectionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel8.setText("OFFICE");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ROUTE ID CONTROL NUMBER");

        id.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(received_name)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(userid))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(name)
                                .addGap(295, 295, 295)
                                .addComponent(currentdate)))
                        .addGap(140, 140, 140)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comments)
                            .addComponent(appropraite)
                            .addComponent(consideration)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drafting)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(signature)
                        .addComponent(information))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(others)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(190, 190, 190))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(202, 202, 202)
                                .addComponent(submitted)
                                .addGap(18, 18, 18)
                                .addComponent(time))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(done, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(forward, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(554, 554, 554)
                        .addComponent(act, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitted)
                    .addComponent(time))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name)
                            .addComponent(jLabel10)
                            .addComponent(currentdate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(received_name)
                                .addComponent(userid))
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appropraite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comments)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consideration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(signature)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(information)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drafting)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(others))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(done, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forward, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(209, 209, 209)
                .addComponent(act, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(334, 334, 334))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void toItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_toItemStateChanged
//  try{
//         con = myConnection.getConnection();
//               String tmp = (String) to.getSelectedItem().toString();
//            String query = "select id from user_tbl where fullname = ?";
//           pst = con.prepareStatement(query);
//
//            
//            to.getSelectedItem();
//                pst.setString(1, tmp);
//            
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                String add = rs.getString("id");
//           
//                userid.setText(add);
//
//            }
//          
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (con != null) {
//                try {
//                 
//                    con.close();
//                } catch (SQLException ex) {
//                  ex.printStackTrace();
//                }
//            }
//
//        }        // TODO add your handling code here:
    }//GEN-LAST:event_toItemStateChanged

    private void toActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toActionPerformed
    
//        try{
//            
//             String query = "select id from user_tbl where fullname = ? order by position_id asc";
//
//             PreparedStatement pst = con.prepareStatement(query);
//             String tmp = (String) to.getSelectedItem().toString();
//              if (to.getSelectedItem() != null) {
//                    pst.setString(1, tmp);
//                }
//              ResultSet rs1 = pst.executeQuery();
//                while (rs1.next()) {
//                    String add = rs1.getString("id");
//                   
//                    
//                }
//        }catch(Exception e){
//
//        
//
//        }
//         
    }//GEN-LAST:event_toActionPerformed

    private void forwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardActionPerformed
           
        String csv = String.join("\t", actions);
       
      
        try {
     
            String status = "FORWARDED";

            String routeid = id.getText().trim();
            String from = name.getText().trim();

            String to_data = to.getSelectedItem().toString().trim();
            String subject = subj.getText().trim();

            String datereceive = currentdate.getText();
            String remarks = notes.getText().trim();
            String date = time.getText().trim();
            String userId = userid.getText().trim();
            con = myConnection.getConnection();

            pst = con.prepareStatement("insert into forward_tbl (route_id, sender_name,sendto_name,subject,action,remarks,status, date, receiveby)values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, routeid);
            pst.setString(2, from);

            pst.setString(3, to_data);
            pst.setString(4, subject);
            pst.setString(5, csv);
            pst.setString(6, remarks);
            pst.setString(7, status);
            pst.setString(8, date);
            pst.setString(9, received_name.getText());

            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Data Received Forwarded to Outgoing");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        movetoOutgoing();
        logsforward();
       addreciveby();
   
     
      
     
     
//       movetopending();
       
       
//        updateroute();

  this.dispose();
    }//GEN-LAST:event_forwardActionPerformed

    private void commentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentsActionPerformed
        if (comments.isSelected()) {
           actions.add("For Comments");
           act.setText("For Comments");
        } else if (!comments.isSelected()) {
           actions.remove("For Comments");

        }
    }//GEN-LAST:event_commentsActionPerformed

    private void considerationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_considerationActionPerformed
        if (consideration.isSelected()) {
            actions.add("For Consideration");
             act.setText("For Consideration");
        } else if (!consideration.isSelected()) {
           actions.remove("For Consideration");

        }
    }//GEN-LAST:event_considerationActionPerformed

    private void draftingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_draftingActionPerformed
        if (drafting.isSelected()) {
           actions.add("For Drafting of Reply");
                        act.setText("For Drafting of Reply");

        } else if (!drafting.isSelected()) {
            actions.remove("For Drafting of Reply");

        }

    }//GEN-LAST:event_draftingActionPerformed

    private void informationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informationActionPerformed
        if(information.isSelected()){
            actions.add("For Information/File");
           act.setText("For Information/File");

        }
        else if(!information.isSelected()) {
            actions.remove("For Information/File");

        }
    }//GEN-LAST:event_informationActionPerformed

    private void othersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_othersActionPerformed
        if (this.others.isSelected()) {
            jTextField2.setEditable(true);

           actions.add(jTextField2.getText());

        } else if (!others.isSelected()) {
            jTextField2.setText("");
            jTextField2.setEditable(false);
            actions.remove(jTextField2.getText());

        }

    }//GEN-LAST:event_othersActionPerformed

    private void appropraiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appropraiteActionPerformed
        if(this.appropraite.isSelected()){
            actions.add("For Appropriate Action");
                       act.setText("For Appropriate Action");

        }
       else if(!appropraite.isSelected()) {
           actions.remove("For Appropriate Action");
        
        }
        

    }//GEN-LAST:event_appropraiteActionPerformed

    private void signatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signatureActionPerformed

        if(signature.isSelected()){
            actions.add("For Initial/signature");
                       act.setText("For Initial/signature");
            notes.setText("Signed and for Appropriate Action ");
        }
        else if(!signature.isSelected()) {
            actions.remove("For Initial/signature");

        }
    }//GEN-LAST:event_signatureActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed

             int confirmed = JOptionPane.showConfirmDialog(null,
                "Complete Routing Process ?", "CONFIRMATION",
                JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {

            statusdone();
            donelogs();
            movetoOutgoing();
          
        }

        this.dispose();


    }//GEN-LAST:event_doneActionPerformed

    private void actActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_actActionPerformed

    private void sectionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_sectionPopupMenuWillBecomeInvisible
 of = section.getSelectedItem().toString();
        getfullname();        // TODO add your handling code here:
    }//GEN-LAST:event_sectionPopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(home2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField act;
    public javax.swing.JCheckBox appropraite;
    public javax.swing.JCheckBox comments;
    public javax.swing.JCheckBox consideration;
    private javax.swing.JLabel currentdate;
    private javax.swing.JButton done;
    public javax.swing.JCheckBox drafting;
    private javax.swing.JButton forward;
    public javax.swing.JTextField id;
    public javax.swing.JCheckBox information;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextField jTextField2;
    public javax.swing.JLabel name;
    public javax.swing.JTextArea notes;
    public javax.swing.JCheckBox others;
    public javax.swing.JLabel received_name;
    private javax.swing.JComboBox<String> section;
    public javax.swing.JCheckBox signature;
    public javax.swing.JTextArea subj;
    private javax.swing.JLabel submitted;
    public javax.swing.JLabel time;
    public javax.swing.JComboBox<String> to;
    public javax.swing.JLabel userid;
    // End of variables declaration//GEN-END:variables
}
