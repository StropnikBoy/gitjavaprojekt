/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprojekt;

/**
 *
 * @author theph
 */
public class AdminHomePage extends javax.swing.JFrame {

    /**
     * Creates new form AdminHomePage
     */
    public AdminHomePage() {
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

        jCheckoutsButton = new javax.swing.JButton();
        jAddItemsButton = new javax.swing.JButton();
        jEditItemsButton = new javax.swing.JButton();
        jDeleteItemsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCheckoutsButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jCheckoutsButton.setText("Chekouts");
        jCheckoutsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckoutsButtonActionPerformed(evt);
            }
        });

        jAddItemsButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jAddItemsButton.setText("Add Items");
        jAddItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddItemsButtonActionPerformed(evt);
            }
        });

        jEditItemsButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jEditItemsButton.setText("Edit Items");
        jEditItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditItemsButtonActionPerformed(evt);
            }
        });

        jDeleteItemsButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jDeleteItemsButton.setText("Delete Items");
        jDeleteItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteItemsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jCheckoutsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDeleteItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEditItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAddItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jAddItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jCheckoutsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jEditItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jDeleteItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckoutsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckoutsButtonActionPerformed
        if ( jNameTextBox.getText().trim().length() == 0
            || jSurnameTextBox.getText().trim().length() == 0
            || jEmailTextBox.getText().trim().length() == 0
            || jTelTextBox.getText().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(null,"Izpolni podatke pravilno.");
        }

        {
            Connection Conn;
            Database povezava = new Database();
            Conn = povezava.getConnection();

            String value = jKrajComboBox.getSelectedItem().toString();
            String x = value.substring(0,4);

            String name = jNameTextBox.getText();
            String surname = jSurnameTextBox.getText();
            String email = jEmailTextBox.getText();
            String tel = jTelTextBox.getText();
            String kraj_id = "SELECT id FROM residences WHERE post_number = " + Integer.parseInt(x);
            String date = jYearTextBox.getText() + "-" + jMonthTextBox.getText() + "-" + jDayTextBox.getText();
            String password = jPasswordTextBox.getText();
            String confirmation = jConfirmationTextBox.getText();
            Integer rank = 0;

            String passwordToHash = password;
            String generatedPassword = null;

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(passwordToHash.getBytes());
                byte[] bytes = md.digest();

                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                generatedPassword = sb.toString();
            }

            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }

            System.out.println(generatedPassword);

            ResultSet rezultati;
            String sql = "SELECT usersview ('" + name + "', '" + surname + "', '" + email + "', '" + kraj_id + "', '" + tel + "', '" + date + "', '" + password + "', '" + confirmation + "', '" + rank + "')";
            ResultSet rezultat;

            try
            {
                Statement stavek = Conn.createStatement();

                //JOptionPane.showMessageDialog(null, "Uspešna registracija!");
                String query = "SELECT register ('" + name + "', '" + surname + "', '" + email + "', (" + kraj_id + "), '" + tel + "', '" + date + "', '" + password + "', '" + confirmation + "', " + rank + ")";
                System.out.println(query);
                Statement statement = Conn.createStatement();
                rezultat = statement.executeQuery(query);
                while(rezultat.next()){
                    int er = rezultat.getInt("register");
                    System.out.println(er);
                }
                this.setVisible(false);
                Login Prijava = new Login();
                Prijava.setVisible(true);

                Conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jCheckoutsButtonActionPerformed

    private void jAddItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddItemsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAddItemsButtonActionPerformed

    private void jEditItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditItemsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEditItemsButtonActionPerformed

    private void jDeleteItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteItemsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDeleteItemsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddItemsButton;
    private javax.swing.JButton jCheckoutsButton;
    private javax.swing.JButton jDeleteItemsButton;
    private javax.swing.JButton jEditItemsButton;
    // End of variables declaration//GEN-END:variables
}