/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package routinPackage;

import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author admin
 */
public class home extends javax.swing.JFrame {

    java.sql.Connection con;
    PreparedStatement pst, pst2;
    ResultSet rs, rs2;
    public String sender = Login.userid;
    public static String trackingid = "";
    public String action;
    public String of;
    public String oth;
    ImageIcon imageIcon = new ImageIcon(new ImageIcon("home.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));


    static public String firstn;

    ArrayList<String> actions = new ArrayList<>();

    /**
     * Creates new form home
     */
    public home() {

        initComponents();

        userid.setVisible(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("download.png")));
        clock();
        autoId();
        numRowTrackingPagr();
        pendingnumberTrac();
        getOfficename();
         notifyNumber();
//    officebtn();

        jPanel4.removeAll();
        jPanel4.add(DASHBOARD);
        jPanel4.repaint();
        jPanel4.revalidate();
        
        from_label.setText(Login.fullname);
        userid.setText(Login.userid);
        office_label.setText(Login.officename.toUpperCase());
        id_label.setVisible(true);
        welcome.setText("Welcome " + Login.fullname);
        String officen = Login.officename;
        office.setText(officen.toUpperCase());

    } 
    
    public void h() {
        clock();
        
        autoId();
        getfullname();
        numRowTrackingPagr();
        pendingnumberTrac();
        notifyNumber();
        from_label.setText(Login.fullname);
        userid.setText(Login.userid);
        office_label.setText(Login.officename.toUpperCase());
        jButton4.setVisible(false);

        id_label.setVisible(false);
        String officen = Login.officename;

        office.setText(officen.toUpperCase());
        remark_text.setText("");
        subject_textArea.setText("");
        appropraite.setSelected(false);
        comments.setSelected(false);
        consideration.setSelected(false);
        signature.setSelected(false);
        information.setSelected(false);
        drafting.setSelected(false);
        others.setSelected(false);
        actions.removeAll(actions);
        others_txt.setEditable(false);

   }
   
   public void firstlog(){
       
        String csv = String.join("\t", actions);

        String status = "outgoing";

        String subj = subject_textArea.getText();

        String remarks = remark_text.getText();
        String from = from_label.getText().toUpperCase();
        String time = time_label.getText();
        String user = Login.userid;
        
     // int i = Integer.parseInt(id_label.getText());

      String routeid = route_id.getText();
      
     

        
        try {
            
            con = myConnection.getConnection();
            pst = con.prepareStatement("insert into logs(user_id,route_id,subject,action,remarks, date, sender_name, sendto_name, status )values(?,?,?,?,?,?,?,?,?)");
             pst.setString(1, user);
            pst.setString(2, routeid);
            pst.setString(3, subj);
            pst.setString(4, csv);
            pst.setString(5, remarks);
            pst.setString(6, time);
            pst.setString(7, from);
            pst.setString(8, to_selected.getSelectedItem().toString().toUpperCase());
            pst.setString(9, status);
             

                pst.executeUpdate();
         
           
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
        
           public void secondlog(){
       
        TableModel model = incoming_tbl.getModel();
         int row = incoming_tbl.getSelectedRow();
       if(row== -1){
           
        
       }
       else{
           
           try {
             
        
               String controlnumber = (model.getValueAt(row, 0).toString());
               String subject = (model.getValueAt(row, 1).toString());
               String action = model.getValueAt(row, 2).toString().trim();
               String remarks = model.getValueAt(row, 3).toString().trim();
               String date = model.getValueAt(row, 4).toString().trim();
               String status = "received";
               String from = model.getValueAt(row, 6).toString().trim();
                Object filObject = model.getValueAt(row, 7);
                   String receive =  (filObject == null) ? "" : filObject.toString();

               String to = model.getValueAt(row, 8).toString().trim();
              
                String user = Login.userid;
 
            
               con = myConnection.getConnection();
            pst = con.prepareStatement("insert into logs(user_id,route_id,subject,action,remarks, date, sender_name, sendto_name,receiveby, status )values(?,?,?,?,?,?,?,?,?,?)");

               pst.setString(1, user);
                pst.setString(2, controlnumber);
               pst.setString(3, subject);
               pst.setString(4, action);
               pst.setString(5, remarks);
                pst.setString(7, from);
               pst.setString(8, to);
               pst.setString(9, receive);
               pst.setString(6, time.getText());
               pst.setString(10, status);
            

            pst.executeUpdate();
            con.close();

      
       
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       }
       
   }

    
    public void receivedbtn(){
           
        TableModel model = incoming_tbl.getModel();
         int row = incoming_tbl.getSelectedRow();
       if(row== -1){
           
        
       }
       else{
         
           try {
               
        
               String controlnumber = (model.getValueAt(row, 0).toString());
               String subject = (model.getValueAt(row, 1).toString());
               String action = model.getValueAt(row, 2).toString().trim();
               String remarks = model.getValueAt(row, 3).toString().trim();
               String date = model.getValueAt(row, 4).toString().trim();
               String status = "received";
               String from = model.getValueAt(row, 6).toString().trim();
                 Object filObject = model.getValueAt(row, 7);
               String receive =  (filObject == null) ? "" : filObject.toString();
               String to = model.getValueAt(row, 8).toString().trim();
             

               con = myConnection.getConnection();

               pst = con.prepareStatement("insert into receive_tbl (route_id, sender_name,sendto_name,subject,action,remarks,status, date, date_receive, receiveby)values(?,?,?,?,?,?,?,?,?,?)");
               pst.setString(1, controlnumber);
               pst.setString(2, from);

               pst.setString(3, to);
               pst.setString(4, subject);
               pst.setString(5, action);
               pst.setString(6, remarks);
               pst.setString(7, status);

               pst.setString(8, date);

               pst.setString(9, time.getText());
               pst.setString(10, receive);
            

            pst.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "Routing  Received and Forwarded to Pending");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       }
             
    }
     
    public void clock() {
        DateFormat.getDateTimeInstance().format(new Date());
        Timer t = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time_label.setText(
                        
                        DateFormat.getDateTimeInstance().format(new Date())
                );
                                time.setText(
                                       
                        
                        DateFormat.getDateTimeInstance().format(new Date())
                );
            }
        });
        t.setRepeats(true);
        t.setCoalesce(true);
        t.setInitialDelay(0);
        t.start();

    }
      public void autoId() {
 
        Calendar calendar = new GregorianCalendar();

        try {
            con = myConnection.getConnection();
            Statement s = con.createStatement();

            rs = s.executeQuery("select Max(route_id) from documents");
            rs.next();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            rs.getString("Max(route_id)");

            if (rs.getString("Max(route_id)") == null) {
                route_id.setText(sdf.format(calendar.getTime()) + "-0001");

            } else {
                Long id = Long.parseLong(rs.getString("Max(route_id)").substring(10, rs.getString("Max(route_id)").length()));
                id++;
                route_id.setText(sdf.format(calendar.getTime()) + "-0" + String.format("%03d", id));
             
            }
       
      

        } catch (SQLException ex) {
            ex.printStackTrace();
//            Logger.getLogger(autoIn1.class.getName()).log(Level.SEVERE, null, ex);
        }
//            Logger.getLogger(autoIn1.class.getName()).log(Level.SEVERE, null, ex);

    }
//      public void t2(){
//        
//         try {
//
//            
//            con = myConnection.getConnection();
//String query = "select m.route_id, m.subject, m.action, m.remarks, m.posted_date, m.status, s.fullname as sender_name, r.fullname as receiver_name from route_tbl m join user_tbl s on m.sender_id = s.id join user_tbl r on m.sendto_id = r.id  where m.sender_id = '" + id_label.getText() +"' && m.status = 'outgoing'";
//  
//           pst = con.prepareStatement(query);
//
//          rs = pst.executeQuery();
//            DefaultTableModel model = (DefaultTableModel) outgoing_tbl.getModel();
//            model.setRowCount(0);
//            while (rs.next()) {
//
//                model.addRow(new Object[]{
//                    rs.getString(1),
//                    rs.getString(2),
//                    rs.getString(3),
//                    rs.getString(4),
//                    rs.getString(5),
//                    rs.getString(6),
//                    rs.getString(7),
//                    rs.getString(8),
//
//                    });
//            }
//            outgoing_tbl.setModel(model);
//     con.close();
//  
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
      
      public void pendingf(){
          try {

            
            con = myConnection.getConnection();
            String query = "SELECT m.route_id, m.subject, m.action, m.remarks, m.date, m.status, m.sender_name, m.sendto_name, m.receiveby FROM receive_tbl m WHERE m.sendto_name = '"+Login.fullname+"' ORDER BY route_id DESC;";
//String query = "SELECT m.route_id, m.subject, m.action,m.remarks,m.date,m.status, s.fullname AS sender_name,r.fullname AS receiver_name, m.receiveby FROM receive_tbl m JOIN user_tbl s ON m.sender_name = s.fullname JOIN user_tbl r ON m.sendto_name = r.fullname WHERE m.sendto_name = '"+Login.fullname+"'  order by route_id desc ";
        //      String query = "SELECT  user_tbl.id, fname, lname,office_name  from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id ";
//String query = "select m.*, s.fname as sender_name, r.fname as receiver_name from route_tbl m join user_tbl s on m.sender_id = s.id join user_tbl r on m.send_to = r.id";
         pst = con.prepareStatement(query);
           rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) pending_tbl.getModel();
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
                    rs.getString(9),
                    rs.getString(8),
                  

                    });
            }
            pending_tbl.setModel(model);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          
          
      }
        public void logsf(){
          try {

            
            con = myConnection.getConnection();
 String query = "SELECT\n"
                          + "    m.route_id,\n"
                          + "    m.subject,\n"
                          + "    m.action,\n"
                          + "    m.remarks,\n"
                          + "    m.date,\n"
                          + "    m.receiveby,\n"
                          
                          + "    s.fullname AS sender_name,\n"
                          + "    r.fullname AS receiver_name\n"
                          
                          + "FROM\n"
                          + "    logs m\n"
                          + "JOIN user_tbl s ON\n"
                          + "    m.sender_name = s.fullname\n"
                          + "JOIN user_tbl r ON\n"
                          + "    m.sendto_name = r.fullname\n"
                          + "WHERE\n"
                          + "    m.sendto_name = '" + Login.fullname + "' group by m.route_id  order by m.id desc ;";           
        //      String query = "SELECT  user_tbl.id, fname, lname,office_name  from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id ";
//String query = "select m.*, s.fname as sender_name, r.fname as receiver_name from route_tbl m join user_tbl s on m.sender_id = s.id join user_tbl r on m.send_to = r.id";
         pst = con.prepareStatement(query);
           rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) logs_tbl.getModel();
            model.setRowCount(0);
            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(7),
                     rs.getString(8),
                     rs.getString(6),
                     
                  

                    });
            }
            logs_tbl.setModel(model);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          
          
      }
         public void pendingnumberTrac() {
             
  
       
        try {
            con = myConnection.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(id) FROM receive_tbl WHERE sendto_name = '"+Login.fullname+"'");

            if (rs.next()) {
                String sum = rs.getString("COUNT(id)");
              
                pendingnumber.setText(sum);
            }
            con.close();

        } catch (Exception e) {
        }

    }
       
         public void numRowTrackingPagr() {
             
  
       
        try {
            con = myConnection.getConnection();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM all_route WHERE sendto_name = '"+Login.fullname+"'");

            if (rs.next()) {
                String sum = rs.getString("COUNT(*)");
              
                incoming.setText(sum);
            }
            con.close();

        } catch (Exception e) {
        }

    }
          public void notifyNumber() {
       
        if (Integer.parseInt(incoming.getText()) == 0) {
            incoming.setVisible(false);
          incomingcircle.setVisible(false);
          
        }
        if (Integer.parseInt(incoming.getText()) > 0) {
            incoming.setVisible(true);
              incomingcircle.setVisible(true);
          
        }
//        if (Integer.parseInt(incoming.getText()) > 9) {
//            String var = "9+";
//            incoming.setText(var.toString());
//            incoming.setVisible(true);
//              incomingcircle.setVisible(true);
//          
//        }////////2
       
        ////
        //pending
        if (Integer.parseInt(pendingnumber.getText()) == 0) {
            pendingcircle.setVisible(false);
            pendingnumber.setVisible(false);
           
        }
        if (Integer.parseInt(pendingnumber.getText()) > 0) {
            pendingnumber.setVisible(true);
              pendingcircle.setVisible(true);
          
        }
//        if (Integer.parseInt(pendingnumber.getText()) > 9) {
//            String var = "9+";
//            pendingnumber.setText(var);
//            pendingnumber.setVisible(true);
//                          pendingcircle.setVisible(true);
//
//          
//        }////////2
       
    }
        
        
     
      
      public void incoming(){
           try {
                con = myConnection.getConnection();
               String query = "SELECT route_id, subject, action, remarks, date, status, sender_name, sendto_name, receiveby FROM all_route WHERE sendto_name = '"+Login.fullname+"';";

//String query = "SELECT m.route_id, m.subject, m.action, m.remarks, m.date, m.status, s.fullname AS sender_name, r.fullname AS receiver_name, m.receiveby FROM all_route m JOIN user_tbl s ON m.sender_id = s.id JOIN user_tbl r ON m.sendto_id = r.id WHERE m.sendto_id = '"+from_label.getText()+"' ORDER BY m.id DESC;";



             pst = con.prepareStatement(query);
                 rs = pst.executeQuery();
               DefaultTableModel model = (DefaultTableModel) incoming_tbl.getModel();
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
                       rs.getString(9),
                        rs.getString(8),
                   });
               }
               incoming_tbl.setModel(model);


           
        } catch (Exception e) {
            e.printStackTrace();
        }
          
          
      }
      public void movetopending() {
      
        int row =incoming_tbl.getSelectedRow();
        String cell = incoming_tbl.getModel().getValueAt(row, 0).toString();
        
        try {
            con = myConnection.getConnection();
            pst = con.prepareStatement("delete from all_route where route_id='" + cell + "'");
            
            pst.executeUpdate();
           
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
         
         public void openpending(){
               home2 h2 = new home2();

             int row = pending_tbl.getSelectedRow();
             TableModel model = pending_tbl.getModel();

             if (row == -1) {

             } else {
                 h2.setVisible(true);
                 route_id.setText(model.getValueAt(row, 0).toString());
                 subject_textArea.setText(model.getValueAt(row, 1).toString());
                 action = model.getValueAt(row, 2).toString().trim();

                 
                 if (action.equals("For Comments	For Initial/signature	For Consideration") || action.equals("For Comments	For Consideration	For Initial/signature") || action.equals("For Consideration	For Comments	For Initial/signature")) {
                     consideration.setSelected(true);
                     comments.setSelected(true);
                     appropraite.setSelected(false);
                     signature.setSelected(true);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);

                 }
                 else if (action.equalsIgnoreCase("For Comments	For Appropriate Action	For Initial/signature") || action.equalsIgnoreCase("For Initial/signature	For Appropriate Action	For Comments")) {
                     consideration.setSelected(false);
                     comments.setSelected(true);
                   
                     appropraite.setSelected(true);
                     signature.setSelected(true);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);

                 }else if (action.equalsIgnoreCase("For Comments	For Initial/signature") || action.equalsIgnoreCase("For Initial/signature	For Comments")) {
                     consideration.setSelected(false);
                     comments.setSelected(true);
                   
                     appropraite.setSelected(false);
                     signature.setSelected(true);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);

                 } else if (action.equalsIgnoreCase("For Comments")) {
                     consideration.setSelected(false);
                     comments.setSelected(true);
                     appropraite.setSelected(false);
                     signature.setSelected(false);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);

                 } else if (action.equalsIgnoreCase("For Consideration	For Comments")) {
                     consideration.setSelected(true);
                     comments.setSelected(false);
                     appropraite.setSelected(false);
                     signature.setSelected(false);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);

                 } else if (action.equalsIgnoreCase("For Consideration")) {
                     consideration.setSelected(true);
                     comments.setSelected(false);
                     appropraite.setSelected(false);
                     signature.setSelected(false);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);

                 } else if (action.equals("For Consideration	For Initial/signature") || action.equals("For Initial/signature	For Consideration")) {
                     consideration.setSelected(true);
                     comments.setSelected(false);
                     appropraite.setSelected(false);
                     signature.setSelected(true);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);

                 } else if (action.equalsIgnoreCase("For Appropriate Action	For Drafting of Reply") || action.equalsIgnoreCase("For Drafting of Reply	For Appropriate Action")) {
                     appropraite.setSelected(true);
                     consideration.setSelected(false);
                     comments.setSelected(false);
                     signature.setSelected(false);
                     information.setSelected(false);
                     drafting.setSelected(true);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Appropriate Action")) {
                     appropraite.setSelected(true);
                     consideration.setSelected(false);
                     comments.setSelected(false);
                     signature.setSelected(false);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Appropriate Action	For Comments	For Initial/signature")) {
                     appropraite.setSelected(true);
                     consideration.setSelected(false);
                     comments.setSelected(true);
                     signature.setSelected(true);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Appropriate Action	For Consideration")) {
                     appropraite.setSelected(true);
                     consideration.setSelected(true);
                     comments.setSelected(false);
                     signature.setSelected(false);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Initial/signature	For Appropriate Action") || action.equals("For Appropriate Action 	For Initial/signature")) {
                     appropraite.setSelected(true);
                     consideration.setSelected(false);
                     comments.setSelected(false);
                     signature.setSelected(true);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Appropriate Action	For Comments")) {
                     appropraite.setSelected(true);
                     consideration.setSelected(false);
                     comments.setSelected(true);
                     signature.setSelected(false);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Appropriate Action	For Consideration	For Initial/signature")) {
                     appropraite.setSelected(true);
                     consideration.setSelected(true);
                     comments.setSelected(false);
                     signature.setSelected(true);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Initial/signature")) {
                     signature.setSelected(true);
                     appropraite.setSelected(false);
                     consideration.setSelected(false);
                     comments.setSelected(false);
                     information.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Information/File")) {
                     information.setSelected(true);
                     signature.setSelected(false);
                     appropraite.setSelected(false);
                     consideration.setSelected(false);
                     comments.setSelected(false);
                     drafting.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("For Drafting of Reply")) {
                     drafting.setSelected(true);
                     information.setSelected(false);
                     signature.setSelected(false);
                     appropraite.setSelected(false);
                     consideration.setSelected(false);
                     comments.setSelected(false);
                     others.setSelected(false);
                 } else if (action.equalsIgnoreCase("others")) {
                     others_txt.getText();
                     drafting.setSelected(false);
                     information.setSelected(false);
                     signature.setSelected(false);
                     appropraite.setSelected(false);
                     consideration.setSelected(false);
                     comments.setSelected(false);
                    others.setSelected(true);
                 }
//                  else {
//                      consideration.setSelected(false);
//                      signature.setSelected(false);
//                      comments.setSelected(false);
//                      appropraite.setSelected(false);
//                      information.setSelected(false);
//                      drafting.setSelected(false);
//                      others.setSelected(true);
//                      others_txt.getText();
//                  }
     
                  remark_text.setText(model.getValueAt(row, 3).toString());
                  time_label.setText(model.getValueAt(row, 4).toString());
                  from_label.setText(model.getValueAt(row, 6).toString());

                  to_selected.setSelectedItem(model.getValueAt(row, 7).toString());
              

                  
                  
                  
                  h2.subj.setText(subject_textArea.getText());
                  
                     if (action.equalsIgnoreCase("For Comments") ) {
                         h2.consideration.setSelected(false);
                         h2.comments.setSelected(true);
                         h2.consideration.setSelected(false);
                         h2.signature.setSelected(false);
                         h2.appropraite.setSelected(false);
                         h2.signature.setSelected(false);
                         h2.information.setSelected(false);
                         h2.drafting.setSelected(false);
                         h2.others.setSelected(false);

                     } else if (action.equals("For Comments	For Appropriate Action	For Initial/signature") || action.equals("For Initial/signature	For Appropriate Action	For Comments") || action.equals("Appropriate Action	For Comments	For Initial/signature")) {

                         h2.consideration.setSelected(true);
                         h2.comments.setSelected(true);
                         h2.notes.append("Signed and for Appropriate Action");

                         h2.appropraite.setSelected(false);
                         h2.signature.setSelected(true);
                         h2.information.setSelected(false);
                         h2.drafting.setSelected(false);
                         h2.others.setSelected(false);

                     }
                     else if (action.equals("For Comments	For Initial/signature	For Consideration") || action.equals("For Comments	For Consideration	For Initial/signature") || action.equals("For Consideration	For Comments	For Initial/signature")) {

                         h2.consideration.setSelected(true);
                         h2.comments.setSelected(true);
                         h2.notes.append("Signed and for Appropriate Action");

                         h2.appropraite.setSelected(false);
                         h2.signature.setSelected(true);
                         h2.information.setSelected(false);
                         h2.drafting.setSelected(false);
                         h2.others.setSelected(false);

                     } else if (action.equalsIgnoreCase("For Comments	For Initial/signature") || action.equalsIgnoreCase("For Initial/signature	For Comments")) {

                         h2.consideration.setSelected(false);
                         h2.comments.setSelected(true);
                         h2.appropraite.setSelected(false);
                         h2.signature.setSelected(true);
                         h2.notes.append("Signed and for Appropriate Action");

                         h2.information.setSelected(false);
                         h2.drafting.setSelected(false);
                         h2.others.setSelected(false);

                     } else if (action.equalsIgnoreCase("For Consideration	For Initial/signature") || action.equalsIgnoreCase("For Initial/signature	For Consideration")) {
                         h2.consideration.setSelected(true);
                         h2.comments.setSelected(false);
                         h2.appropraite.setSelected(false);
                         h2.signature.setSelected(true);

                         h2.notes.append("Signed and for Appropriate Action");
                         h2.information.setSelected(false);
                         h2.drafting.setSelected(false);
                         h2.others.setSelected(false);

                     } else if (action.equalsIgnoreCase("For Consideration")) {
                         h2.consideration.setSelected(true);
                         h2.comments.setSelected(false);
                         h2.appropraite.setSelected(false);
                         h2.signature.setSelected(false);
                      h2.information.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);

                  } 
                    
                else if (action.equalsIgnoreCase("For Consideration	For Comments")) {
                      h2.consideration.setSelected(true);
                      h2.comments.setSelected(true);
                      h2.appropraite.setSelected(false);
                      h2.signature.setSelected(false);
                      h2.information.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);

                  } 
                   else if (action.equalsIgnoreCase("For Appropriate Action	For Drafting of Reply") || action.equalsIgnoreCase("For Drafting of Reply	For Appropriate Action")) {
                       
                      h2.appropraite.setSelected(true);
                      h2.consideration.setSelected(false);
                      h2.comments.setSelected(false);
                      h2.signature.setSelected(false);
                      h2.information.setSelected(false);
                      h2.drafting.setSelected(true);
                      h2.others.setSelected(false);
                  }
                    else if (action.equalsIgnoreCase("For Appropriate Action	For Consideration")) {
                       
                      h2.appropraite.setSelected(true);
                      h2.consideration.setSelected(true);
                      h2.comments.setSelected(false);
                      h2.signature.setSelected(false);
                      h2.information.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);
                  }
                    
                    
                             else if (action.equalsIgnoreCase("For Appropriate Action	For Comments	For Initial/signature")) {
                      h2.appropraite.setSelected(true);
                      h2.consideration.setSelected(false);
                      h2.comments.setSelected(true);
                      h2.signature.setSelected(true);
                      h2.information.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);
                  }
                           
                                     
                    else if (action.equalsIgnoreCase("For Appropriate Action	For Consideration	For Initial/signature")) {
                      h2.appropraite.setSelected(true);
                      h2.consideration.setSelected(true);
                      h2.comments.setSelected(false);
                      h2.signature.setSelected(true);
                      h2.information.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);
                  }
                               
                   else if (action.equalsIgnoreCase("For Initial/signature	For Appropriate Action") || action.equals("For Appropriate Action	For Initial/signature")) {
                      h2.appropraite.setSelected(true);
                      h2.consideration.setSelected(false);
                      h2.comments.setSelected(false);
                         h2.signature.setSelected(true);

                         h2.notes.append("Signed and for Appropriate Action");

                         h2.information.setSelected(false);
                         h2.drafting.setSelected(false);
                         h2.others.setSelected(false);
                     } else if (action.equalsIgnoreCase("For Appropriate Action	For Comments")) {
                         h2.appropraite.setSelected(true);
                         h2.consideration.setSelected(false);
                         h2.comments.setSelected(true);
                         h2.signature.setSelected(false);
                      h2.information.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);
                  }
                     else if (action.equalsIgnoreCase("For Appropriate Action")) {
                      h2.appropraite.setSelected(true);
                      h2.consideration.setSelected(false);
                      h2.comments.setSelected(false);
                      h2.signature.setSelected(false);
                      h2.information.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);
                  } else if (action.equalsIgnoreCase("For Initial/signature")) {
                      h2.signature.setSelected(true);
                          h2.notes.append("Signed and for Appropriate Action");

                      h2.appropraite.setSelected(false);
                      h2.consideration.setSelected(false);
                      h2.comments.setSelected(false);
                     h2.information.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);
                  } else if (action.equalsIgnoreCase("For Information/File")) {
                      h2.information.setSelected(true);
                      h2.signature.setSelected(false);
                      h2.appropraite.setSelected(false);
                      h2.consideration.setSelected(false);
                      h2.comments.setSelected(false);
                      h2.drafting.setSelected(false);
                      h2.others.setSelected(false);
                  } else if (action.equalsIgnoreCase("For Drafting of Reply")) {
                      h2.drafting.setSelected(true);
                      h2.information.setSelected(false);
                      h2.signature.setSelected(false);
                      h2.appropraite.setSelected(false);
                     h2.consideration.setSelected(false);
                      h2.comments.setSelected(false);
                      h2.others.setSelected(false);
                  } 
                  else if (action.equalsIgnoreCase("others")) {
                      h2.jTextField2.getText();
                      h2.drafting.setSelected(false);
                      h2.information.setSelected(false);
                      h2.signature.setSelected(false);
                      h2.appropraite.setSelected(false);
                      h2.consideration.setSelected(false);
                      h2.comments.setSelected(false);
                      h2.others.setSelected(true);

                  } 
//                  else {
//                        h2.jTextField2.getText();
//                      h2.consideration.setSelected(false);
//                      h2.signature.setSelected(false);
//                      h2.comments.setSelected(false);
//                      h2.appropraite.setSelected(false);
//                      h2.information.setSelected(false);
//                      h2.drafting.setSelected(false);
//                      h2.others.setSelected(true);
//                  }

                

                  h2.notes.setText(remark_text.getText());
                  h2.time.setText(time_label.getText());
                  h2.received_name.setText(Login.fullname);
                  h2.name.setText(from_label.getText());
//                  h2.to.addItem(to_selected.getSelectedItem().toString());
//                  h2.to.getSelectedItem();

                  h2.id.setText(route_id.getText());
                  
                String csv = String.join("\t", actions);
                  h2.act.setText(action);

                 String var = route_id.getText();

                 try {
                     con = myConnection.getConnection();
                     Statement s = con.createStatement();

                     rs = s.executeQuery("SELECT sento_id FROM route_tbl where route_id ='" + var + "'");

                     if (rs.next()) {
                         int idd = rs.getInt(1);
                         String sd = String.valueOf(idd);
                         route_id.setText(sd);
                         h2.id.setText(route_id.getText());
                     }
                     con.close();

                 } catch (Exception e) {

                 }

             }

            
     
         }
         public void forwardpending(){
           
         try {
                con = myConnection.getConnection();
               String query = "SELECT m.route_id, m.subject, m.action, m.remarks, m.date, m.status, s.fullname AS sender_name, r.fullname AS receiver_name FROM all_route m JOIN user_tbl s ON m.sender_name = s.fullname JOIN user_tbl r ON m.sendto_name = r.fullname WHERE  m.sendto_name = '"+Login.fullname+"' && STATUS = 'forwarded'";
           


             pst = con.prepareStatement(query);
                 rs = pst.executeQuery();
               DefaultTableModel model = (DefaultTableModel) incoming_tbl.getModel();
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
                       rs.getString(8),});
               }
               incoming_tbl.setModel(model);


           
        } catch (Exception e) {
            e.printStackTrace();
        }
         
          
         }
        
         public void historydisplay(){
              try {
                con = myConnection.getConnection();
                  String query = "SELECT\n"
                          + "    m.route_id,\n"
                          + "    m.subject,\n"
                          + "    m.action,\n"
                          + "    m.remarks,\n"
                          + "    m.date,\n"
                          + "    m.receiveby,\n"
                          + "    m.status,\n"
                          + "    s.fullname AS sender_name,\n"
                          + "    r.fullname AS receiver_name,\n"
                          + "  m.status\n"
                          + "FROM\n"
                          + "    logs m\n"
                          + "JOIN user_tbl s ON\n"
                          + "    m.sender_name = s.fullname\n"
                          + "JOIN user_tbl r ON\n"
                          + "    m.sendto_name = r.fullname\n"
                          + "WHERE\n"
                          + "    m.sender_name = '" + Login.fullname + "' group by m.route_id desc;";

                  pst = con.prepareStatement(query);
                  rs = pst.executeQuery();
                  DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                  model.setRowCount(0);
                  while (rs.next()) {
      
                      model.addRow(new Object[]{
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4),
                          rs.getString(5),
                          rs.getString(8),
                          rs.getString(9),
                          rs.getString(6),
                          rs.getString(7),
                         
                      
                      });
            }
            jTable1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showhistory() {

        String id = route_id.getText();

        try {
            con = myConnection.getConnection();
            Statement s = con.createStatement();
            rs = s.executeQuery("SELECT route_id, subject, action, remarks, date, sender_name, sendto_name, receiveby, status FROM logs where route_id ='" + id + "'");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
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
                          rs.getString(9),});
                  }
                  jTable1.setModel(model);

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

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        home_btn = new javax.swing.JButton();
        history_btn = new javax.swing.JButton();
        pendingnumber = new javax.swing.JLabel();
        pendingcircle = new javax.swing.JLabel();
        pending_btn = new javax.swing.JButton();
        dashboard_btn = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        logs_btn = new javax.swing.JButton();
        incoming = new javax.swing.JLabel();
        incomingcircle = new javax.swing.JLabel();
        incoming_btn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        LOGS = new javax.swing.JPanel();
        adfa1 = new javax.swing.JScrollPane();
        logs_tbl = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PENDING = new javax.swing.JPanel();
        adfa = new javax.swing.JScrollPane();
        pending_tbl = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        INCOMING = new javax.swing.JPanel();
        asdasda = new javax.swing.JScrollPane();
        incoming_tbl = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        receivedbtn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        DASHBOARD = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        office = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        HOME = new javax.swing.JPanel();
        submit_btn = new javax.swing.JButton();
        to_selected = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        from_label = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        office_label = new javax.swing.JLabel();
        others_txt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        remark_text = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        time_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        userid = new javax.swing.JLabel();
        route_id = new javax.swing.JLabel();
        id_label = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comments = new javax.swing.JCheckBox();
        consideration = new javax.swing.JCheckBox();
        drafting = new javax.swing.JCheckBox();
        information = new javax.swing.JCheckBox();
        others = new javax.swing.JCheckBox();
        appropraite = new javax.swing.JCheckBox();
        signature = new javax.swing.JCheckBox();
        jLabel29 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        section = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        subject_textArea = new javax.swing.JTextArea();
        HISTORY = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        OUTGOING = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outgoing_tbl = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 204, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home_btn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        home_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/icons8-home-48.png"))); // NOI18N
        home_btn.setText("HOME");
        home_btn.setToolTipText("HOME");
        home_btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        home_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_btnActionPerformed(evt);
            }
        });
        jPanel3.add(home_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 92));

        history_btn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        history_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/icons8-logs-folder-48.png"))); // NOI18N
        history_btn.setText("HISTORY");
        history_btn.setToolTipText("LOGS");
        history_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                history_btnActionPerformed(evt);
            }
        });
        jPanel3.add(history_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 140, 92));

        pendingnumber.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        pendingnumber.setForeground(new java.awt.Color(255, 255, 255));
        pendingnumber.setText("0");
        jPanel3.add(pendingnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 30, 30));

        pendingcircle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/red Moon_32px.png"))); // NOI18N
        jPanel3.add(pendingcircle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 30, 30));

        pending_btn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        pending_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/icons8-data-pending-48.png"))); // NOI18N
        pending_btn.setText("PENDING");
        pending_btn.setToolTipText("INCOMING");
        pending_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pending_btnActionPerformed(evt);
            }
        });
        jPanel3.add(pending_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 140, 92));

        dashboard_btn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        dashboard_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/icons8-dashboard-layout-48.png"))); // NOI18N
        dashboard_btn.setText("DASHBOARD");
        dashboard_btn.setToolTipText("DASHBOARD");
        dashboard_btn.setSelected(true);
        dashboard_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboard_btnActionPerformed(evt);
            }
        });
        jPanel3.add(dashboard_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 2, -1, 90));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/icons8-logout-48.png"))); // NOI18N
        jButton5.setText("LOGOUT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 140, 92));

        logs_btn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        logs_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/icons8-list-50.png"))); // NOI18N
        logs_btn.setText("LOGS");
        logs_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logs_btnActionPerformed(evt);
            }
        });
        jPanel3.add(logs_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 140, 92));

        incoming.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        incoming.setForeground(new java.awt.Color(255, 255, 255));
        incoming.setText("0");
        jPanel3.add(incoming, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 20, 20));

        incomingcircle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/red Moon_32px.png"))); // NOI18N
        jPanel3.add(incomingcircle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 30, 20));

        incoming_btn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        incoming_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/icons8-incoming-data-48.png"))); // NOI18N
        incoming_btn.setText("INCOMING");
        incoming_btn.setToolTipText("INCOMING ");
        incoming_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incoming_btnActionPerformed(evt);
            }
        });
        jPanel3.add(incoming_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 140, 92));

        jSplitPane1.setLeftComponent(jPanel3);

        jPanel4.setLayout(new java.awt.CardLayout());

        LOGS.setBackground(new java.awt.Color(255, 255, 255));

        logs_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logs_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ROUTE ID CONTROL NUMBER", "SUBJECT", "ACTION", "REMARKS", "DATE", "FROM", "TO"
            }
        ));
        logs_tbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                logs_tblKeyPressed(evt);
            }
        });
        adfa1.setViewportView(logs_tbl);

        jButton8.setText("REFRESH");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setText("LOGS");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("SEARCH");

        javax.swing.GroupLayout LOGSLayout = new javax.swing.GroupLayout(LOGS);
        LOGS.setLayout(LOGSLayout);
        LOGSLayout.setHorizontalGroup(
            LOGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adfa1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(LOGSLayout.createSequentialGroup()
                .addGroup(LOGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LOGSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LOGSLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(265, 265, 265)
                        .addComponent(jLabel9)
                        .addGap(31, 31, 31)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LOGSLayout.createSequentialGroup()
                        .addGap(615, 615, 615)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        LOGSLayout.setVerticalGroup(
            LOGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LOGSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(LOGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(LOGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(4, 4, 4)
                .addComponent(adfa1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 206, Short.MAX_VALUE))
        );

        jPanel4.add(LOGS, "card3");

        PENDING.setBackground(new java.awt.Color(255, 255, 255));

        pending_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pending_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ROUTE ID CONTROL NUMBER", "SUBJECT", "ACTION", "REMARKS", "DATE", "STATUS", "FROM", "RECEIVED", "TO"
            }
        ));
        pending_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pending_tblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pending_tblMouseEntered(evt);
            }
        });
        adfa.setViewportView(pending_tbl);

        jButton7.setText("REFRESH");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setText("PENDING ROUTE TABLE LIST");

        javax.swing.GroupLayout PENDINGLayout = new javax.swing.GroupLayout(PENDING);
        PENDING.setLayout(PENDINGLayout);
        PENDINGLayout.setHorizontalGroup(
            PENDINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PENDINGLayout.createSequentialGroup()
                .addGroup(PENDINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PENDINGLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PENDINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(0, 548, Short.MAX_VALUE))
                    .addComponent(adfa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(PENDINGLayout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PENDINGLayout.setVerticalGroup(
            PENDINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PENDINGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(adfa, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        jPanel4.add(PENDING, "card3");

        INCOMING.setBackground(new java.awt.Color(255, 255, 255));

        asdasda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        incoming_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        incoming_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ROUTE ID CONTROL NUMBER", "SUBJECT", "ACTION NEED", "REMARKS / NOTED", "DATE", "STATUS", "FROM", "Receive by", "To"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        incoming_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                incoming_tblMouseClicked(evt);
            }
        });
        asdasda.setViewportView(incoming_tbl);

        jButton3.setText("REFRESH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        receivedbtn.setText("RECEIVED");
        receivedbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receivedbtnActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("INCOMING ROUTE TABLE LIST");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        javax.swing.GroupLayout INCOMINGLayout = new javax.swing.GroupLayout(INCOMING);
        INCOMING.setLayout(INCOMINGLayout);
        INCOMINGLayout.setHorizontalGroup(
            INCOMINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, INCOMINGLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(receivedbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addComponent(asdasda)
            .addGroup(INCOMINGLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(INCOMINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(219, 317, Short.MAX_VALUE))
        );
        INCOMINGLayout.setVerticalGroup(
            INCOMINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(INCOMINGLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel23)
                .addGap(6, 6, 6)
                .addComponent(jLabel16)
                .addGap(4, 4, 4)
                .addComponent(asdasda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(INCOMINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(receivedbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jPanel4.add(INCOMING, "card3");

        DASHBOARD.setBackground(new java.awt.Color(255, 255, 255));
        DASHBOARD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        welcome.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        welcome.setText("WELCOME ");

        time.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        time.setText("TIME ");

        office.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        office.setText("jLabel11");

        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 1, 40)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel22.setText("E - ROUTING SYSTEM  ");

        javax.swing.GroupLayout DASHBOARDLayout = new javax.swing.GroupLayout(DASHBOARD);
        DASHBOARD.setLayout(DASHBOARDLayout);
        DASHBOARDLayout.setHorizontalGroup(
            DASHBOARDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DASHBOARDLayout.createSequentialGroup()
                .addGroup(DASHBOARDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DASHBOARDLayout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(jLabel20))
                    .addGroup(DASHBOARDLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addGroup(DASHBOARDLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jLabel22))
                    .addGroup(DASHBOARDLayout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(welcome))
                    .addGroup(DASHBOARDLayout.createSequentialGroup()
                        .addGap(559, 559, 559)
                        .addGroup(DASHBOARDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(office)
                            .addComponent(time))))
                .addContainerGap(530, Short.MAX_VALUE))
        );
        DASHBOARDLayout.setVerticalGroup(
            DASHBOARDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DASHBOARDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(time)
                .addGap(31, 31, 31)
                .addComponent(welcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(office)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );

        jPanel4.add(DASHBOARD, "card6");

        HOME.setBackground(new java.awt.Color(255, 255, 255));
        HOME.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        HOME.setForeground(new java.awt.Color(0, 204, 204));

        submit_btn.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        submit_btn.setText("SUBMIT");
        submit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_btnActionPerformed(evt);
            }
        });

        to_selected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                to_selectedActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("POSITION  :");

        from_label.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        from_label.setText("jLabel9");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel10.setText("FROM        :");

        office_label.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        office_label.setText("jLabel11");

        others_txt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        others_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                others_txtActionPerformed(evt);
            }
        });

        remark_text.setColumns(20);
        remark_text.setRows(5);
        jScrollPane2.setViewportView(remark_text);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setText("REMARKS/NOTES :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("SUBJECT :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("ACTION NEEDED  :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setText("TO :");

        time_label.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        time_label.setText("1111111111");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        route_id.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        route_id.setText("jLabel12");

        id_label.setText("id");

        jLabel8.setText("ROUTE ID CONTROL NUMBER");

        comments.setText("For Comments");
        comments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentsActionPerformed(evt);
            }
        });

        consideration.setText("For Consideration");
        consideration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                considerationActionPerformed(evt);
            }
        });

        drafting.setText("For Drafting of Reply");
        drafting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                draftingActionPerformed(evt);
            }
        });

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

        appropraite.setText("For Appropriate Action");
        appropraite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appropraiteActionPerformed(evt);
            }
        });

        signature.setText("For Initial/signature");
        signature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signatureActionPerformed(evt);
            }
        });

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        section.setToolTipText("");
        section.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sectionItemStateChanged(evt);
            }
        });
        section.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                sectionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        section.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectionActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel13.setText("OFFICE :");

        jButton4.setText("confirm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        subject_textArea.setColumns(20);
        subject_textArea.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        subject_textArea.setRows(5);
        jScrollPane3.setViewportView(subject_textArea);

        javax.swing.GroupLayout HOMELayout = new javax.swing.GroupLayout(HOME);
        HOME.setLayout(HOMELayout);
        HOMELayout.setHorizontalGroup(
            HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HOMELayout.createSequentialGroup()
                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(route_id))
                        .addGap(393, 393, 393)
                        .addComponent(time_label))
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(852, 852, 852)
                        .addComponent(userid))
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(from_label)
                            .addComponent(office_label)
                            .addGroup(HOMELayout.createSequentialGroup()
                                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(appropraite)
                                    .addComponent(comments)
                                    .addComponent(consideration))
                                .addGap(281, 281, 281)
                                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(drafting)
                                    .addComponent(information)
                                    .addComponent(signature)
                                    .addGroup(HOMELayout.createSequentialGroup()
                                        .addComponent(others)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(others_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4))))))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(HOMELayout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(jLabel7))
                            .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(HOMELayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(jLabel6))
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HOMELayout.createSequentialGroup()
                                .addComponent(to_selected, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(submit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        HOMELayout.setVerticalGroup(
            HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HOMELayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(route_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(from_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(office_label)
                            .addComponent(jLabel3)))
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(time_label)))
                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4))
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userid)
                    .addGroup(HOMELayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(appropraite)
                            .addComponent(signature))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comments)
                            .addComponent(information))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(consideration)
                            .addComponent(drafting))
                        .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HOMELayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(HOMELayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(others, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(others_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4)))
                            .addGroup(HOMELayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(19, 19, 19)
                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(section, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(4, 4, 4)
                .addGroup(HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_label, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to_selected, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(0, 219, Short.MAX_VALUE))
        );

        jPanel4.add(HOME, "card2");

        HISTORY.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("HISTORY TABLE LIST");

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ROUTE ID CONTROL NUMBER", "SUBJECT", "ACTION", "REMARKS", "DATE", "FROM", "TO", "RECEIVE BY", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable1);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("SEARCH");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout HISTORYLayout = new javax.swing.GroupLayout(HISTORY);
        HISTORY.setLayout(HISTORYLayout);
        HISTORYLayout.setHorizontalGroup(
            HISTORYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HISTORYLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(HISTORYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HISTORYLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(375, Short.MAX_VALUE))
                    .addGroup(HISTORYLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(HISTORYLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5))
        );
        HISTORYLayout.setVerticalGroup(
            HISTORYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HISTORYLayout.createSequentialGroup()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HISTORYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap(253, Short.MAX_VALUE))
        );

        jPanel4.add(HISTORY, "card5");

        OUTGOING.setBackground(new java.awt.Color(255, 255, 255));

        outgoing_tbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        outgoing_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Control ID", "SUBJECT", "ACTION NEEDED", "REMARKS", "DATE", "STATUS", "FROM", "TO"
            }
        ));
        jScrollPane1.setViewportView(outgoing_tbl);

        jButton1.setText("REFRESH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("RECEIVED");

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/routinPackage/305187041_2279666412200643_7195722002026157565_n.png"))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setText("OUTGOING ROUTE TABLE LIST");

        javax.swing.GroupLayout OUTGOINGLayout = new javax.swing.GroupLayout(OUTGOING);
        OUTGOING.setLayout(OUTGOINGLayout);
        OUTGOINGLayout.setHorizontalGroup(
            OUTGOINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OUTGOINGLayout.createSequentialGroup()
                .addGroup(OUTGOINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addGroup(OUTGOINGLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OUTGOINGLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(OUTGOINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, OUTGOINGLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OUTGOINGLayout.setVerticalGroup(
            OUTGOINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OUTGOINGLayout.createSequentialGroup()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(OUTGOINGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jPanel4.add(OUTGOING, "card7");

        jSplitPane1.setRightComponent(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1459, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void incoming_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incoming_btnActionPerformed
     jPanel4.removeAll();
      jPanel4.add(INCOMING);
      jPanel4.repaint();
      jPanel4.revalidate();  
     incoming();
     numRowTrackingPagr();
           pendingnumberTrac();

     notifyNumber();
//     forwardpending();
     
    }//GEN-LAST:event_incoming_btnActionPerformed

    private void pending_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pending_btnActionPerformed
//        pending in = new pending();
//        in.setVisible(true);
jPanel4.removeAll();
      jPanel4.add(PENDING);
      jPanel4.repaint();
      jPanel4.revalidate();
      pendingf();
      pendingnumberTrac();
      numRowTrackingPagr();
       notifyNumber();

                         
    }//GEN-LAST:event_pending_btnActionPerformed

    private void home_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnActionPerformed
      jPanel4.removeAll();
      jPanel4.add(HOME);
      jPanel4.repaint();
      jPanel4.revalidate();
      pendingnumberTrac();
       numRowTrackingPagr();
       notifyNumber();
      h();
    }//GEN-LAST:event_home_btnActionPerformed

    private void history_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_history_btnActionPerformed
      jPanel4.removeAll();
      jPanel4.add(HISTORY);
      jPanel4.repaint();
      jPanel4.revalidate();
      historydisplay();
    }//GEN-LAST:event_history_btnActionPerformed

    private void dashboard_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboard_btnActionPerformed
        jPanel4.removeAll();
        jPanel4.add(DASHBOARD);
        jPanel4.repaint();
        jPanel4.revalidate();
        numRowTrackingPagr();
        pendingnumberTrac();
        notifyNumber();
    }//GEN-LAST:event_dashboard_btnActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      pendingf();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//t2();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void receivedbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receivedbtnActionPerformed
receivedbtn();   
//movetopending();

    }//GEN-LAST:event_receivedbtnActionPerformed

    private void incoming_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incoming_tblMouseClicked

        
             if (incoming_tbl.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Select data in the table");

        } else {

            int confirmed = JOptionPane.showConfirmDialog(null,
                    "RECEIVE DATA AND FORWARD TO PENDING ?", "CONFIRMATION",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {

                receivedbtn();
                 secondlog();
                movetopending(); // delete after ma accept
                numRowTrackingPagr();
                 pendingnumberTrac();
notifyNumber();
               

            }

            incoming();
        }
        
         
    }//GEN-LAST:event_incoming_tblMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 incoming();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       int confirmed = JOptionPane.showConfirmDialog(null, 
        "Are you sure you want to Log out this Account?", "Logging out Program Message Box",
        JOptionPane.YES_NO_OPTION);
       if (confirmed == JOptionPane.YES_OPTION) {
             Login n = new Login();
      dispose();
    
      n.setVisible(true);
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void pending_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pending_tblMouseClicked
    
        openpending();
         pendingnumberTrac();
         numRowTrackingPagr();
         notifyNumber();
        
         

    }//GEN-LAST:event_pending_tblMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
     int row = jTable1.getSelectedRow();
                    if (row == -1) {

                    } else {
                       
                        route_id.setText(jTable1.getValueAt(row, 0).toString());
                        
                        showhistory();

                    }
            
    }//GEN-LAST:event_jTable1MouseClicked

    private void logs_tblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_logs_tblKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_logs_tblKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void logs_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logs_btnActionPerformed
      jPanel4.removeAll();
        jPanel4.add(LOGS);
      jPanel4.repaint();
      jPanel4.revalidate();
      logsf();
    }//GEN-LAST:event_logs_btnActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        
                 DefaultTableModel model = (DefaultTableModel) logs_tbl.getModel();
   
           TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
           logs_tbl.setRowSorter(tr);
           tr.setRowFilter(RowFilter.regexFilter(jTextField1.getText().trim()));
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(jTextField2.getText().trim()));

    }//GEN-LAST:event_jTextField2KeyTyped

    private void pending_tblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pending_tblMouseEntered
        pendingf();
        pendingnumberTrac();
        numRowTrackingPagr();
        notifyNumber();
  // TODO add your handling code here:
    }//GEN-LAST:event_pending_tblMouseEntered

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void sectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectionActionPerformed

        
  
        
        //        try {
            //            con = myConnection.getConnection();
            //
            //            String query = "select office_lvl from office_tbl where office_name = ?";
            //
            //            PreparedStatement pst = con.prepareStatement(query);
            //            String tmp = (String) section.getSelectedItem().toString();
            //            if (section.getSelectedItem() != null) {
                //                pst.setString(1, tmp);
                //            }
            //            ResultSet rs1 = pst.executeQuery();
            //            while (rs1.next()) {
                //                String add = rs1.getString("office_lvl");
                //                officename.setText(add);
                //            }
            //
            //            con.close();
            //
            //        } catch (Exception e) {
            //
            //            JOptionPane.showMessageDialog(null, e);
            //
            //        }

    }//GEN-LAST:event_sectionActionPerformed

    private void signatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signatureActionPerformed

        if(signature.isSelected()){
            actions.add("For Initial/signature");
        }
        else if(!signature.isSelected()) {
            actions.remove("For Initial/signature");

        }
    }//GEN-LAST:event_signatureActionPerformed

    private void appropraiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appropraiteActionPerformed
        if(this.appropraite.isSelected()){
            actions.add("For Appropriate Action");
        }
        else if(!appropraite.isSelected()) {
            actions.remove("For Appropriate Action");

        }
    }//GEN-LAST:event_appropraiteActionPerformed

    private void othersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_othersActionPerformed
        if (this.others.isSelected()) {
            jButton4.setVisible(true);
others_txt.setEditable(true);
     
            //  other();
        } else if (!others.isSelected()) {
            others_txt.setText("");
            others_txt.setEditable(false);
            actions.remove(others_txt.getText());

        }
    }//GEN-LAST:event_othersActionPerformed

    private void informationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informationActionPerformed
        if (information.isSelected()) {
            actions.add("For Information/File");
        } else if (!information.isSelected()) {
            actions.remove("For Information/File");

        }
    }//GEN-LAST:event_informationActionPerformed

    private void draftingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_draftingActionPerformed
        if (drafting.isSelected()) {
            actions.add("For Drafting of Reply");
        } else if (!drafting.isSelected()) {
            actions.remove("For Drafting of Reply");

        }
    }//GEN-LAST:event_draftingActionPerformed

    private void considerationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_considerationActionPerformed
        if (consideration.isSelected()) {
            actions.add("For Consideration");
        } else if (!consideration.isSelected()) {
            actions.remove("For Consideration");

        }
    }//GEN-LAST:event_considerationActionPerformed

    private void commentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentsActionPerformed
        if (comments.isSelected()) {
            actions.add("For Comments");

        }else if (!comments.isSelected()){
            actions.remove("For Comments");

        }
    }//GEN-LAST:event_commentsActionPerformed

    private void others_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_others_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_others_txtActionPerformed

    private void to_selectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_selectedActionPerformed

        try {
            con = myConnection.getConnection();

            String query = "select id from user_tbl where fullname = ?";
            PreparedStatement pst = con.prepareStatement(query);
            String tmp = (String) to_selected.getSelectedItem().toString();
            if (to_selected.getSelectedItem() != null) {
                pst.setString(1, tmp);
            }
            ResultSet rs1 = pst.executeQuery();
            while (rs1.next()) {
                String add = rs1.getString("id");
                id_label.setText(add);
            }
            con.close();
        } catch (Exception e) {

           

        }
    }//GEN-LAST:event_to_selectedActionPerformed

    private void submit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_btnActionPerformed

        String sub = subject_textArea.getText().trim();
        String re = remark_text.getText().trim();

        if (actions.isEmpty()  ||  sub.equals("")  ) {

            JOptionPane optionPane = new JOptionPane("INPUT ALL INFORMATION NEED!!", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("ERROR");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);

        } else {
           
            routeOut();
            firstlog();
            submit();
            savedocuments();
             autoId();
            other();
            clear(); 
            pendingnumberTrac();
        getOfficename();
         notifyNumber();

        }
    }//GEN-LAST:event_submit_btnActionPerformed

    private void sectionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_sectionPopupMenuWillBecomeInvisible

        of = section.getSelectedItem().toString();
        getfullname();        // TODO add your handling code here:
    }//GEN-LAST:event_sectionPopupMenuWillBecomeInvisible

    private void sectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sectionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_sectionItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
if(others_txt.getText().equals("")){
                    JOptionPane.showMessageDialog(null,     "Enter others action needed ","Missing Text!", JOptionPane.ERROR_MESSAGE);
                 
                  
      
}else{
        
        JOptionPane.showMessageDialog(null, others_txt.getText() +" added to Action Needed");
 
        
        actions.add(others_txt.getText());
        others_txt.setEditable(false);
}
      
    }//GEN-LAST:event_jButton4ActionPerformed
  
    public void other(){
       oth = others_txt.getText();
        actions.add(oth);
    }
     public void getOfficename() {

        con = myConnection.getConnection();

        try {
            
            String query = "SELECT DISTINCT  office_tbl.office_name from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id order by office_lvl ASC";

            pst2 = con.prepareStatement(query);
            rs2 = pst2.executeQuery();
            while (rs2.next()) {

                section.addItem(rs2.getString(1));

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
             String query = "SELECT  user_tbl.fullname from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id WHERE office_tbl.office_name =? ";
          
            pst = con.prepareStatement(query);
            
            pst.setString(1,of);
          
      
            
            rs = pst.executeQuery();
       
       
        to_selected.removeAllItems();
       
            while (rs.next()) {
              
                to_selected.addItem(rs.getString(1));
               
            
            
            }
         
        con.close();
        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getStackTrace());
        }

    }
    
   
   
public void routeOut(){
autoId();
    
    
       String csv = String.join("\t", actions);
     

   
        String status = "outgoing";

        String subj = subject_textArea.getText();

        String remarks = remark_text.getText();
        String from = Login.fullname;
        String to = to_selected.getSelectedItem().toString();
        String time = time_label.getText();
        
      int i = Integer.parseInt(id_label.getText());

      String routeid = route_id.getText();
      
     

        
        try {
            
            con = myConnection.getConnection();
            pst = con.prepareStatement("insert into all_route(route_id,subject,action,remarks, date, sender_name, sendto_name, status )values(?,?,?,?,?,?,?,?)");
            pst.setString(1, routeid);
            pst.setString(2, subj);
            pst.setString(3, csv);
            pst.setString(4, remarks);
            pst.setString(5, time);
            pst.setString(6, from);
            pst.setString(7, to);
            pst.setString(8, status);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "SUBMITTED SUCCESSFUL");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

    
    
}



    public void submit() {
        
       String csv = String.join("\t", actions);


        

        String status = "ongoing";

        String subj = subject_textArea.getText();

        String remarks = remark_text.getText();
        String from = from_label.getText();
        String time = time_label.getText();
        
      int i = Integer.parseInt(id_label.getText());
      
     

        
        try {
         
             int number = Integer.parseInt(sender);
         
            con = myConnection.getConnection();//         pst = con.prepareStatement("SELECT user_tbl.id,  fname, lname,office_name  from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id ");

    pst = con.prepareStatement("insert into route_tbl(subject,action,remarks, posted_date, status, sender_id, sendto_id  )values(?,?,?,?,?,?,?)");
            pst.setString(1, subj);
            pst.setString(2, csv);
            pst.setString(3, remarks);
            pst.setString(4, time);
            pst.setString(5, status);
            pst.setInt(6, number);
            pst.setInt(7, i);

            pst.executeUpdate();
           

//            JOptionPane.showMessageDialog(null, "Succesful saved");
           
        } catch (Exception e) {
            e.printStackTrace();

        }

        

    }
    
    public void savedocuments() {
        
       String csv = String.join("\t", actions);

        String status = "ongoing";

        String subj = subject_textArea.getText();

        String remarks = remark_text.getText();
        String from = from_label.getText().toUpperCase();
        String time = time_label.getText();
        String id = Login.userid;
                
        
      int i = Integer.parseInt(id_label.getText());
      
     

        
        try {
         
             int number = Integer.parseInt(sender);
         
            con = myConnection.getConnection();//         pst = con.prepareStatement("SELECT user_tbl.id,  fname, lname,office_name  from user_tbl LEFT JOIN office_tbl ON user_tbl.office_id = office_tbl.id ");

    pst = con.prepareStatement("insert into documents(user_id,subject,action,remarks, date, status, sender_name, sendto_name, route_id  )values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, id);
             pst.setString(2, subj);
            pst.setString(3, csv);
            pst.setString(4, remarks);
            pst.setString(5, time);
            pst.setString(6, status);
            pst.setInt(7, number);
            pst.setInt(8, i);
            pst.setString(9, route_id.getText());
            

            pst.executeUpdate();
           

//            JOptionPane.showMessageDialog(null, "Succesful saved");
           
        } catch (Exception e) {
            e.printStackTrace();

        }

        

    }
    
    

        
    
    
    void clear(){
        
        subject_textArea.setText("");
      
        remark_text.setText("");
         appropraite.setSelected(false);
          comments.setSelected(false);
           consideration.setSelected(false);
            signature.setSelected(false);
             information.setSelected(false);
              drafting.setSelected(false);
              others.setSelected(false);
              others_txt.setText("");
              actions.removeAll(actions);
        
        
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DASHBOARD;
    private javax.swing.JPanel HISTORY;
    private javax.swing.JPanel HOME;
    private javax.swing.JPanel INCOMING;
    private javax.swing.JPanel LOGS;
    private javax.swing.JPanel OUTGOING;
    private javax.swing.JPanel PENDING;
    private javax.swing.JScrollPane adfa;
    private javax.swing.JScrollPane adfa1;
    private javax.swing.JCheckBox appropraite;
    private javax.swing.JScrollPane asdasda;
    private javax.swing.JCheckBox comments;
    private javax.swing.JCheckBox consideration;
    private javax.swing.JButton dashboard_btn;
    private javax.swing.JCheckBox drafting;
    private javax.swing.JLabel from_label;
    private javax.swing.JButton history_btn;
    private javax.swing.JButton home_btn;
    private javax.swing.JLabel id_label;
    public javax.swing.JLabel incoming;
    private javax.swing.JButton incoming_btn;
    private javax.swing.JTable incoming_tbl;
    public javax.swing.JLabel incomingcircle;
    private javax.swing.JCheckBox information;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton logs_btn;
    private javax.swing.JTable logs_tbl;
    private javax.swing.JLabel office;
    private javax.swing.JLabel office_label;
    private javax.swing.JCheckBox others;
    private javax.swing.JTextField others_txt;
    private javax.swing.JTable outgoing_tbl;
    private javax.swing.JButton pending_btn;
    private javax.swing.JTable pending_tbl;
    public javax.swing.JLabel pendingcircle;
    public javax.swing.JLabel pendingnumber;
    private javax.swing.JButton receivedbtn;
    private javax.swing.JTextArea remark_text;
    private javax.swing.JLabel route_id;
    private javax.swing.JComboBox<String> section;
    private javax.swing.JCheckBox signature;
    private javax.swing.JTextArea subject_textArea;
    private javax.swing.JButton submit_btn;
    private javax.swing.JLabel time;
    private javax.swing.JLabel time_label;
    public javax.swing.JComboBox<String> to_selected;
    private javax.swing.JLabel userid;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
