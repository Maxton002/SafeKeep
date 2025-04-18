
import dao.ConnectionProvider;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Cris
 */
public class ScheduleOrder extends javax.swing.JFrame {

    /**
     * Creates new form ScheduleOrder
     */
    public ScheduleOrder() {
        initComponents();
        setTitle("ScheduleOrder");
        setSize(850, 600);
        setDefaultCloseOperation(Home.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void cancelDelivery(int scheduleId) {
        try {
            Connection con = ConnectionProvider.getCon();
            String query = "UPDATE scheduleDelivery SET status = 'Cancelled' WHERE schedule_pk = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, scheduleId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Delivery cancelled successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to cancel delivery.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String selectedValue = (String) comboBoxOrderNo.getSelectedItem();
        System.out.println("Selected Value: " + selectedValue); // Debug

        if (selectedValue != null && selectedValue.contains("-")) {
            try {
                String[] parts = selectedValue.split("-", 2); // Split only once
                int orderId = Integer.parseInt(parts[0].trim()); // Convert first part to int
                String orderName = parts[1].trim(); // Extract order name

                System.out.println("Selected Order ID: " + orderId);
                System.out.println("Selected Order Name: " + orderName);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error parsing order ID: " + ex.getMessage());
            }
        }
    }

    private void getAllCustomer() {
        try {
            Connection con = ConnectionProvider.getCon();
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from customer");
            comboBoxCustomerName.removeAllItems();

            while (rs.next()) {
                comboBoxCustomerName.addItem(rs.getString("customer_pk") + "-" + rs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void getAllOrder() {
        try {
            Connection con = ConnectionProvider.getCon();
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from orderDetails");
            comboBoxOrderNo.removeAllItems();

            while (rs.next()) {
                comboBoxOrderNo.addItem(rs.getString("order_pk") + "-" + rs.getString("orderNo"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableScheduleOrder = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtRemarks = new javax.swing.JTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnScheduleDelivery = new javax.swing.JButton();
        btnCancelDelivery = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        comboBoxCustomerName = new javax.swing.JComboBox<>();
        btnClose = new javax.swing.JButton();
        comboBoxOrderNo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(58, 96, 110));
        jLabel8.setText("Delivery Schedule");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Impact", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 96, 110));
        jLabel1.setText("Schedule Order");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        tableScheduleOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Customer Name", "Order ID", "Date", "Status"
            }
        ));
        tableScheduleOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableScheduleOrderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableScheduleOrder);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 810, 250));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(58, 96, 110));
        jLabel5.setText("Remarks:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, -1, -1));

        txtRemarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRemarksActionPerformed(evt);
            }
        });
        getContentPane().add(txtRemarks, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 490, 250, 30));
        getContentPane().add(jDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 240, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(58, 96, 110));
        jLabel2.setText("Choose Date:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, -1, -1));

        btnScheduleDelivery.setBackground(new java.awt.Color(60, 110, 126));
        btnScheduleDelivery.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnScheduleDelivery.setForeground(new java.awt.Color(255, 255, 204));
        btnScheduleDelivery.setText("Schedule Delivery");
        btnScheduleDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScheduleDeliveryActionPerformed(evt);
            }
        });
        getContentPane().add(btnScheduleDelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 380, 210, 50));

        btnCancelDelivery.setBackground(new java.awt.Color(60, 110, 126));
        btnCancelDelivery.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCancelDelivery.setForeground(new java.awt.Color(255, 255, 204));
        btnCancelDelivery.setText("Cancel Delivery");
        btnCancelDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelDeliveryActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelDelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, 210, 50));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(58, 96, 110));
        jLabel4.setText("Customer Name:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        comboBoxCustomerName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCustomerNameActionPerformed(evt);
            }
        });
        getContentPane().add(comboBoxCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 240, 40));

        btnClose.setBackground(new java.awt.Color(60, 110, 126));
        btnClose.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 204));
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 110, 50));

        comboBoxOrderNo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxOrderNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxOrderNoActionPerformed(evt);
            }
        });
        getContentPane().add(comboBoxOrderNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 240, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(58, 96, 110));
        jLabel7.setText("Order ID:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/All_page_Background.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRemarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRemarksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRemarksActionPerformed

    private void btnScheduleDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleDeliveryActionPerformed
        // TODO add your handling code here:
        try {
            Connection con = ConnectionProvider.getCon();

            String selectedCustomer = comboBoxCustomerName.getSelectedItem().toString();
            int customer_fk = Integer.parseInt(selectedCustomer.split("-")[0]);
            String selectedOrder = comboBoxOrderNo.getSelectedItem().toString();
            String[] orderParts = selectedOrder.split("-"); // Split "orderID - orderName"
            int orderNum = Integer.parseInt(orderParts[0]); // Extract order ID

            Date selectedDate = jDateChooser.getDate(); // Get selected date
            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime()); // Convert to SQL date

            String query = "INSERT INTO scheduleDelivery (orderNo, customer_fk, deliveryDate, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, Integer.toString(orderNum));  // orderId
            ps.setInt(2, customer_fk);  // customer_fk (Ensure it's stored correctly)
            ps.setDate(3, sqlDate);  // deliveryDate
            ps.setString(4, "Scheduled"); // Default status

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Delivery Scheduled Successfully!");
                new ScheduleOrder().setVisible(true); // 🔄 Refresh table
            }

            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

            String query = "UPDATE scheduleDelivery SET deliveryDate = ? WHERE orderNo = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, sqlDate);
            ps.setString(2, orderNo);
            int rowsUpdated = ps.executeUpdate();
            
            if(rowsUpdated>0){
                JOptionPane.showMessageDialog(this, "Delivery date updated successfully.");
            }else{
                JOptionPane.showMessageDialog(null, "Failed to schedule delivery.");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        setVisible(false);
        new ScheduleOrder().setVisible(true);
    }//GEN-LAST:event_btnScheduleDeliveryActionPerformed

    private void comboBoxCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCustomerNameActionPerformed
        // TODO add your handling code here:
        try {
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT customer_pk, name FROM customer";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            comboBoxCustomerName.removeAllItems();

            while (rs.next()) {
                int customerId = rs.getInt("customer_pk");
                String customerName = rs.getString("name");
                comboBoxCustomerName.addItem(String.valueOf(customerId) + "-" + customerName);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_comboBoxCustomerNameActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void tableScheduleOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableScheduleOrderMouseClicked
        // TODO add your handling code here: 
    }//GEN-LAST:event_tableScheduleOrderMouseClicked

    private void comboBoxOrderNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxOrderNoActionPerformed
        // TODO add your handling code here:
        try {
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT order_pk, orderNo FROM orderDetails";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            comboBoxOrderNo.removeAllItems(); // Clear previous entries

            while (rs.next()) {
                int orderId = rs.getInt("order_pk"); // Ensure order_pk is an integer
                String orderName = rs.getString("orderNo"); // Ensure orderNo is a string

                // Print retrieved values to debug
                System.out.println("Retrieved from DB -> Order ID: " + orderId + ", Order Name: " + orderName);

                comboBoxOrderNo.addItem(orderId + "-" + orderName);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print full error details in the console
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_comboBoxOrderNoActionPerformed

    private void btnCancelDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelDeliveryActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableScheduleOrder.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a delivery to cancel.");
            return;
        }

        int scheduleId = (int) tableScheduleOrder.getValueAt(selectedRow, 0); // Get schedule_pk (assumes it's in column 0)

        int confirmation = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to cancel this delivery?", "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            cancelDelivery(scheduleId);
            loadScheduleOrders(); // Refresh table after update
        }
    }//GEN-LAST:event_btnCancelDeliveryActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        getAllCustomer();
        getAllOrder();
        DefaultTableModel model = (DefaultTableModel) tableScheduleOrder.getModel();
        try {
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT schedule_pk, o.orderNo, c.name, s.deliveryDate, s.status "
                    + "FROM orderdetails o "
                    + "JOIN customer c ON o.customer_fk = c.customer_pk "
                    + "LEFT JOIN scheduleDelivery s ON o.orderNo = s.orderNo";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("schedule_pk");
                String orderNum = rs.getString("orderNo");
                String customerName = rs.getString("name");
                Date deliveryDate = rs.getDate("deliveryDate");
                String deliveryDateStr = (deliveryDate != null) ? deliveryDate.toString() : "Not Scheduled";

                // Handle null status
                String status = rs.getString("status");
                status = (status != null) ? status : "Pending";

                System.out.println("ID: " + id + ", Customer: " + customerName + ", OrderID: " + orderNum
                        + ", Date: " + deliveryDate + ", Status: " + status);

                model.addRow(new Object[]{id, orderNum, customerName, deliveryDateStr, status});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_formComponentShown

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
            java.util.logging.Logger.getLogger(ScheduleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScheduleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScheduleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScheduleOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ScheduleOrder().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelDelivery;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnScheduleDelivery;
    private javax.swing.JComboBox<String> comboBoxCustomerName;
    private javax.swing.JComboBox<String> comboBoxOrderNo;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableScheduleOrder;
    private javax.swing.JTextField txtRemarks;
    // End of variables declaration//GEN-END:variables

    private void loadScheduleOrders() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
