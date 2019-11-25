/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.jssctester;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author ian
 */

/**
 * 
 * This is Tester Jframe form class
 */
public class Tester extends javax.swing.JFrame {

     /**
     * Reference variables
     */
    private String[] listport;
    private String portName;
    private Openport openport;
    private Closeport closeport;
    private Writedata tulis;
    public Readdata readdata;
    private SerialPort portserial;

    /**
     *
     * @return listport
     */
    public String[] getListport() {
        return listport;
    }

    /**
     * method to set list port
     * @param listport
     */
    public void setListport(String[] listport) {
        this.listport = listport;
    }
    
    /**
     * Creates new form Tester
     */
    
    public Tester() {
        initComponents();
    }
    
    /**
     *
     * @param listport
     */
    public Tester(String[] listport){
    this.listport = listport;
    initComponents();
    initmethode();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTxtdatawrite = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBtnsend = new javax.swing.JButton();
        jTxtdataread = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jLabelportstatus = new javax.swing.JLabel();
        jBtnOpen = new javax.swing.JButton();
        jBtnClose = new javax.swing.JButton();
        jLblportname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Write");

        jLabel2.setText("Read");

        jBtnsend.setText("Send");
        jBtnsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnsendActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(getListport()));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabelportstatus.setText("Disconnect");

        jBtnOpen.setText("Open");
        jBtnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOpenActionPerformed(evt);
            }
        });

        jBtnClose.setText("Close");
        jBtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCloseActionPerformed(evt);
            }
        });

        jLblportname.setText("Port A");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtdatawrite)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnsend)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLblportname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelportstatus)
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                                .addComponent(jBtnOpen)
                                .addGap(35, 35, 35)
                                .addComponent(jBtnClose)
                                .addGap(131, 131, 131))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtdataread)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnOpen)
                    .addComponent(jBtnClose))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtdatawrite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnsend)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtdataread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelportstatus)
                    .addComponent(jLblportname))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        JComboBox cb = (JComboBox)evt.getSource();
        this.portName = (String)cb.getSelectedItem();
        updateLabel(portName);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jBtnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOpenActionPerformed
       this.portserial = openport.open(this.portName);
       if (openport.isStatuskoneksi()){
        updatelabel(Color.green);
        updateStatus("Connected");
        baca();
       }else{
       updatelabel(Color.red);
       updateStatus("Disconnected.False port");
       }
    }//GEN-LAST:event_jBtnOpenActionPerformed

    private void jBtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCloseActionPerformed
        closeport.close(this.portserial);
        if (closeport.isStatuskoneksi()){
            updatelabel(Color.red);
            updateStatus("Disconnected.");
        }else{
            updatelabel(Color.gray);
            updateStatus("failedclose");
        }
    }//GEN-LAST:event_jBtnCloseActionPerformed

    private void jBtnsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnsendActionPerformed
        tulis.write(this.portserial, jTxtdatawrite.getText());
    }//GEN-LAST:event_jBtnsendActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void updateLabel(String portname){
        jLblportname.setText(portname);
    }
    
    private void updatelabel(Color colour){
    jLabelportstatus.setForeground(colour);
    }
    
    private void updateStatus(String status){
    jLabelportstatus.setText(status);
    }
    private void initmethode(){
    openport = new Openport();
    closeport = new Closeport();
    tulis = new Writedata();
    }
    
    /**
     * method to read data
     */
    public void baca(){
        
        try {
            readdata = new Readdata(this.portserial);
            readdata.initform(jTxtdataread);
            int mask = this.portserial.MASK_RXCHAR + this.portserial.MASK_CTS + this.portserial.MASK_DSR;
            this.portserial.setEventsMask(mask);
            this.portserial.addEventListener(readdata);
        } catch (SerialPortException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
}
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
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tester().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnClose;
    private javax.swing.JButton jBtnOpen;
    private javax.swing.JButton jBtnsend;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelportstatus;
    private javax.swing.JLabel jLblportname;
    public javax.swing.JTextField jTxtdataread;
    private javax.swing.JTextField jTxtdatawrite;
    // End of variables declaration//GEN-END:variables
}