/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.messaging;

import co.id.len.tdl.variable.ModelVariabel;
import co.id.len.trackgenerator.main.TrakGenerator;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import co.id.len.tdl.common.StructCircle;
import co.id.len.tdl.common.StructFile;
import co.id.len.tdl.common.StructPolyline;
import co.id.len.tdl.common.StructText;

/**
 *
 * @author datalink
 */

/**
 * 
 * This is MessageForm Jframe form class
 */
public final class MessageForm extends javax.swing.JDialog {

     /**
     * Reference variables
     */
    private TrakGenerator trakGenerator;
    private Message msg;
    private byte[] property;
    private ModelVariabel mod_var;
    private String addressIP;
    private int port;
    private StructText structText;
    private StructPolyline structPolyline;
    private StructCircle structCircle;
    private JFileChooser fileChooser = new JFileChooser();
    private byte[] msg_file;

    /**
     * Creates new form Message_Test
     *
     * @param parent
     * @param modal
     * @param mod
     * @param addressIP
     * @param port
     * @param trakGenerator
     */
    
    public MessageForm(java.awt.Frame parent, boolean modal, ModelVariabel mod, String addressIP, int port, TrakGenerator trakGenerator) {
        initComponents();
        this.trakGenerator = trakGenerator;
        this.setLocationRelativeTo(null);
        msg = new Message(mod.getAddress(), mod.getPort());
        msg.setTrakGenerator(this.trakGenerator);
        structCircle = new StructCircle();
        structPolyline = new StructPolyline();
        structText = new StructText();
        mod_var = mod;
        this.addressIP = addressIP;
        this.port = port;
        setPropEnabled(false);
        this.setModal(modal);
    }

    /**
     *
     * @param parent
     * @param modal
     * @param mod
     * @param trakGenerator
     */
    public MessageForm(java.awt.Frame parent, boolean modal, ModelVariabel mod, TrakGenerator trakGenerator) {
        this.trakGenerator = trakGenerator;
        msg = new Message(this.trakGenerator);
        initComponents();
        this.setLocationRelativeTo(null);
        structCircle = new StructCircle();
        structPolyline = new StructPolyline();
        structText = new StructText();
        mod_var = mod;
        setPropEnabled(false);
        this.setModal(modal);
    }

    private MessageForm(JFrame jFrame, boolean b, Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /* SEND */

    /**
     * method to send text
     */
    
    public void sendText() {
        msg.send_text(txtMessage.getText(), mod_var.getOwnNpu(), Byte.valueOf(txtDestination.getText()));
    }

    /**
     * method to send circle
     */
    public void sendCircle() {
        property = new byte[3];
        property[0] = (byte) cmb_area_color.getSelectedIndex();
        property[1] = (byte) cmb_line_color.getSelectedIndex();
        property[2] = (byte) cmb_line_width.getSelectedIndex();

        if (chb_enable_properties.isSelected()) {
            msg.sendCircleAndProperty(txtMessage.getText(), property, txt_notes.getText(), Integer.parseInt(txt_obj_num.getText()), mod_var.getOwnNpu(),
            Byte.valueOf(txtDestination.getText()));
        } else {
            msg.send_circle(txtMessage.getText(), Integer.parseInt(txt_obj_num.getText()), mod_var.getOwnNpu(),
            Byte.valueOf(txtDestination.getText()));
        }
    }

    /**
     * method to send polyline
     */
    public void sendPolyline() {
        property = new byte[5];
        property[0] = (byte) cmb_area_color.getSelectedIndex();
        property[1] = (byte) cmb_line_color.getSelectedIndex();
        property[2] = (byte) cmb_line_width.getSelectedIndex();
        property[3] = (byte) cmb_line_type.getSelectedIndex();
        property[4] = (byte) cmb_arrow_type.getSelectedIndex();
        if (chb_enable_properties.isSelected()) {
            msg.sendPolylineAndProperty(txtMessage.getText(),property, txt_notes.getText(),
                    Integer.valueOf(txt_obj_num.getText()), mod_var.getOwnNpu(), Byte.valueOf(txtDestination.getText()));
        } else {
            msg.sendPolyline(txtMessage.getText(), Integer.valueOf(txt_obj_num.getText()), mod_var.getOwnNpu(), Byte.valueOf(txtDestination.getText()));
        }
    }

     /**
     * method to send file
     */
    private void sendFile() {
        String sfilename = txtMessage.getText();
        StructFile fileTx = new StructFile();
        byte[] data_file = fileTx.getBytesFile(sfilename, msg_file);
        msg.SendFile(data_file, mod_var.getOwnNpu(), 1);
    }

    /**
     * method to set properties enable
     * @param status
     */
    public void setPropEnabled(boolean status) {
        txt_notes.setEnabled(status);
        cmb_area_color.setEnabled(status);
        cmb_arrow_type.setEnabled(status);
        cmb_line_color.setEnabled(status);
        cmb_line_type.setEnabled(status);
        cmb_line_width.setEnabled(status);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formatLabel = new javax.swing.JLabel();
        objectNumberLabel = new javax.swing.JLabel();
        sendplbtn = new javax.swing.JButton();
        chb_enable_properties = new javax.swing.JCheckBox();
        messageTypeLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        npuDestinationLabel = new javax.swing.JLabel();
        textMessageLabel = new javax.swing.JLabel();
        delsplbtn = new javax.swing.JButton();
        cmb_msg_type = new javax.swing.JComboBox();
        txtDestination = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        cmb_line_type = new javax.swing.JComboBox();
        cmb_arrow_type = new javax.swing.JComboBox();
        jLabel42 = new javax.swing.JLabel();
        cmb_area_color = new javax.swing.JComboBox();
        cmb_line_color = new javax.swing.JComboBox();
        cmb_line_width = new javax.swing.JComboBox();
        txt_notes = new javax.swing.JTextField();
        txt_obj_num = new javax.swing.JTextField();
        txtMessage = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        titleLable = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        exploreButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        formatLabel.setText("Format : ");

        objectNumberLabel.setText("Object Number ");

        sendplbtn.setText("Send");
        sendplbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendplbtnActionPerformed(evt);
            }
        });

        chb_enable_properties.setText("Enable properties");
        chb_enable_properties.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chb_enable_propertiesItemStateChanged(evt);
            }
        });
        chb_enable_properties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb_enable_propertiesActionPerformed(evt);
            }
        });
        chb_enable_properties.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chb_enable_propertiesPropertyChange(evt);
            }
        });

        messageTypeLabel.setText("Message Type");

        npuDestinationLabel.setText(" PU Destination");

        textMessageLabel.setText("Text Message");

        delsplbtn.setText("Delete");
        delsplbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delsplbtnActionPerformed(evt);
            }
        });

        cmb_msg_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_msg_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_msg_typeItemStateChanged(evt);
            }
        });
        cmb_msg_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_msg_typeActionPerformed(evt);
            }
        });

        txtDestination.setText("0");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel1PropertyChange(evt);
            }
        });

        jLabel27.setText("Area Color");

        jLabel31.setText("Line Color");

        jLabel28.setText("Line Width");

        jLabel29.setText("Notes");

        jLabel41.setText("Line Type");

        cmb_line_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Solid", "Dash", "Dot", "Dash Dot" }));

        cmb_arrow_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Arrow", "Begin Arrow", "End Arrow", "Begin and End" }));

        jLabel42.setText("Arrow Type");

        cmb_area_color.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No Color", "Red", "Green", "Blue", "Yellow", "Maroon", "Lime", "White" }));

        cmb_line_color.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Black", "Red", "Green", "Blue", "Yellow", "Maroon", "Lime", "White" }));

        cmb_line_width.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0" }));

        txt_notes.setText("Hati-hati daerah musuh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(23, 23, 23))
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmb_arrow_type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_area_color, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_line_color, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_line_width, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_line_type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_notes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txt_notes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cmb_area_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_line_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_line_width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(cmb_line_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cmb_arrow_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        txt_obj_num.setText("0");

        jPanel2.setPreferredSize(new java.awt.Dimension(531, 68));

        titleLable.setFont(new java.awt.Font("AR BONNIE", 0, 36)); // NOI18N
        titleLable.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        titleLable.setText("Messaging");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLable, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(titleLable, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        exploreButton.setText("...");
        exploreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exploreButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(delsplbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sendplbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(messageTypeLabel)
                                            .addComponent(objectNumberLabel))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(formatLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDestination, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_msg_type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_obj_num, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(textMessageLabel)
                                    .addComponent(npuDestinationLabel))
                                .addGap(10, 10, 10)
                                .addComponent(exploreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(chb_enable_properties)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageTypeLabel)
                    .addComponent(cmb_msg_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(objectNumberLabel)
                    .addComponent(txt_obj_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(npuDestinationLabel)
                    .addComponent(txtDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textMessageLabel)
                    .addComponent(exploreButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chb_enable_properties)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delsplbtn)
                    .addComponent(sendplbtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * method to choose type of send messages
     */
    private void sendMessage() {

        switch (cmb_msg_type.getSelectedIndex()) {
            case 1:
                sendText();
                break;
            case 2:
                sendCircle();
                break;
            case 3:
                sendPolyline();
                break;
            case 4:
                sendFile();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Pilih tipe pesan");
                break;
        }

    }

    private String SelectedMessage;
    
    private void sendplbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendplbtnActionPerformed
        switch (cmb_msg_type.getSelectedIndex()) {
            case 1:
                SelectedMessage = "Text";
                break;
            case 2:
                SelectedMessage = "Circle";
                break;
            case 3:
                SelectedMessage = "Polyline";
                break;
            case 4:
                SelectedMessage = "File";
                break;
        }
        int send = JOptionPane.showConfirmDialog(null, " Send " + SelectedMessage
                + "\n\n Object Number : " + txt_obj_num.getText() + "\n Destination Addres : " + txtDestination.getText()
                + "\n Data :  " + txtMessage.getText() + "\n\n Continue Send Message ?",
                "Send Message", JOptionPane.YES_NO_OPTION);
        if (send == JOptionPane.YES_OPTION) {
            sendMessage();
        } else if (send == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_sendplbtnActionPerformed

    private void chb_enable_propertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb_enable_propertiesActionPerformed
    }//GEN-LAST:event_chb_enable_propertiesActionPerformed
    /**
     * method to choose type of delete messages
     */
    private void deleteMessage() {

        switch (cmb_msg_type.getSelectedIndex()) {

            case 2:
                msg.deletecircle(Integer.parseInt(txt_obj_num.getText()), mod_var.getOwnNpu(), Byte.valueOf(txtDestination.getText()));
                break;
            case 3:
                msg.deletepline(Integer.parseInt(txt_obj_num.getText()), mod_var.getOwnNpu(), Byte.valueOf(txtDestination.getText()));

                break;
            default:
                JOptionPane.showMessageDialog(null, "Pilih tipe pesan");
                break;
        }

    }
    private void delsplbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delsplbtnActionPerformed
        switch (cmb_msg_type.getSelectedIndex()) {
            case 1:
                SelectedMessage = "Text";
                break;
            case 2:
                SelectedMessage = "Circle";
                break;
            case 3:
                SelectedMessage = "Polyline";
                break;
        }
        int delete = JOptionPane.showConfirmDialog(null, " Delete " + SelectedMessage
                + "\n\n Object Number : " + txt_obj_num.getText() + "\n Destination Addres : " + txtDestination.getText()
                + "\n \n Continue Delete Message ?",
                "Delete Message", JOptionPane.YES_NO_OPTION);
        if (delete == JOptionPane.YES_OPTION) {
            deleteMessage();
        } else if (delete == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_delsplbtnActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        cmb_msg_type.removeAllItems();
        cmb_msg_type.addItem("Choose Message Type");
        cmb_msg_type.addItem("Text");
        cmb_msg_type.addItem("Circle");
        cmb_msg_type.addItem("Polyline");
        cmb_msg_type.addItem("File");
    }//GEN-LAST:event_formComponentShown

    private void cmb_msg_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_msg_typeItemStateChanged
        chb_enable_properties.setSelected(false);
        switch (evt.getItem().toString()) {
            case "Text":
                formatLabel.setText("");
                txtMessage.setText("abc1234567890");
                txt_obj_num.setEnabled(false);
                chb_enable_properties.setSelected(false);
                chb_enable_properties.setEnabled(false);
                textMessageLabel.setText("Text Message");
                exploreButton.setVisible(false);
                chb_enable_properties.setEnabled(false);
                break;
            case "Circle":
                formatLabel.setText("Longitude,Latitude,Range");
                txtMessage.setText("104.665,90.123,36689");
                txt_obj_num.setEnabled(true);
                chb_enable_properties.setEnabled(true);
                textMessageLabel.setText("Text Message");
                exploreButton.setVisible(false);
                chb_enable_properties.setEnabled(true);
                break;
            case "Polyline":
                formatLabel.setText("Longitude,Latitude,Longitude,Latitude,....");
                txtMessage.setText("104.665,90.12,103.21,91.3,105.33,89.88");
                txt_obj_num.setEnabled(true);
                chb_enable_properties.setEnabled(true);
                textMessageLabel.setText("Text Message");
                exploreButton.setVisible(false);
                chb_enable_properties.setEnabled(true);
                break;
            case "File":
                formatLabel.setText("");
                textMessageLabel.setText("File Name");
                exploreButton.setVisible(true);
                chb_enable_properties.setEnabled(false);
                break;

            default:
                formatLabel.setText("");
                txtMessage.setText("");
                txt_obj_num.setEnabled(false);
                chb_enable_properties.setEnabled(false);
                textMessageLabel.setText("Text Message");
                exploreButton.setVisible(false);
                chb_enable_properties.setEnabled(false);
                break;
        }
    }//GEN-LAST:event_cmb_msg_typeItemStateChanged

    private void jPanel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel1PropertyChange
    }//GEN-LAST:event_jPanel1PropertyChange

    private void chb_enable_propertiesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chb_enable_propertiesPropertyChange
    }//GEN-LAST:event_chb_enable_propertiesPropertyChange

    private void chb_enable_propertiesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chb_enable_propertiesItemStateChanged
        setPropEnabled(chb_enable_properties.isSelected());
        if (chb_enable_properties.isSelected()) {
            if (cmb_msg_type.getSelectedIndex() == 2) {
                cmb_arrow_type.setEnabled(false);
                cmb_line_type.setEnabled(false);
            } else {
                cmb_arrow_type.setEnabled(true);
                cmb_line_type.setEnabled(true);
            }
        }
    }//GEN-LAST:event_chb_enable_propertiesItemStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void cmb_msg_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_msg_typeActionPerformed
    }//GEN-LAST:event_cmb_msg_typeActionPerformed

    private void exploreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exploreButtonActionPerformed
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            int msg_file_length = (int) selectedFile.length();
            txtMessage.setText(selectedFile.getName());
            formatLabel.setText("File size = " + msg_file_length + " bytes");
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(selectedFile));
                msg_file = new byte[msg_file_length];
                bis.read(msg_file, 0, msg_file_length);
            } catch (IOException e) {
            }
        }
    }//GEN-LAST:event_exploreButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MessageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MessageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MessageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MessageForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MessageForm dialog = new MessageForm(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chb_enable_properties;
    private javax.swing.JComboBox cmb_area_color;
    private javax.swing.JComboBox cmb_arrow_type;
    private javax.swing.JComboBox cmb_line_color;
    private javax.swing.JComboBox cmb_line_type;
    private javax.swing.JComboBox cmb_line_width;
    private javax.swing.JComboBox cmb_msg_type;
    private javax.swing.JButton delsplbtn;
    private javax.swing.JButton exploreButton;
    private javax.swing.JLabel formatLabel;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel messageTypeLabel;
    private javax.swing.JLabel npuDestinationLabel;
    private javax.swing.JLabel objectNumberLabel;
    private javax.swing.JButton sendplbtn;
    private javax.swing.JLabel textMessageLabel;
    private javax.swing.JLabel titleLable;
    private javax.swing.JTextField txtDestination;
    private javax.swing.JTextField txtMessage;
    private javax.swing.JTextField txt_notes;
    private javax.swing.JTextField txt_obj_num;
    // End of variables declaration//GEN-END:variables
}