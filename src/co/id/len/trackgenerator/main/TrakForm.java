/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.main;

import co.id.len.domparser.DomParser;
import co.id.len.tdl.common.StruckModelCircle;
import co.id.len.tdl.common.StruckModelLine;
import co.id.len.tdl.common.StruckModelPolyline;
import co.id.len.tdl.common.StruckModelText;
import co.id.len.tdl.common.StruckModelTrack;
import co.id.len.tdl.common.StructAck;
import co.id.len.tdl.common.StructCircle;
import co.id.len.tdl.common.StructHeader;
import co.id.len.tdl.common.StructPolyline;
import co.id.len.tdl.common.StructText;
import co.id.len.tdl.common.StructTrack;
import co.id.len.tdl.variable.DataLink_Constanta;
import co.id.len.trackgenerator.connection.Readserial;
import co.id.len.trackgenerator.connection.ConnectionSettings;
import co.id.len.trackgenerator.connection.SerialConnection;
import co.id.len.trackgenerator.connection.UdpTx;
import co.id.len.trackgenerator.thread.ThreadSyncArray;
import co.id.len.trackgenerator.thread.ThreadSendData;
import co.id.len.trackgenerator.messaging.MessageForm;
import co.id.len.tdl.variable.ModelVariabel;
import co.id.len.trackgenerator.thread.ThreadReceiveData;
import co.id.len.trackgenerator.thread.ThreadReceiveDataChat;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import co.id.len.trackgenerator.about.About;
import co.id.len.trackgenerator.message.GpsNmeaRcvMessage;
import co.id.len.trackgenerator.message.GpsRcvMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdAckMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdCircleMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdFileMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdLineMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdPolylineMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdTextMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdTrackMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreAckMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreFileMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreTextMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreTrackMessage;
import co.id.len.trackgenerator.thread.ThreadReceiveDataChat;
import co.id.len.trackgenerator.thread.ThreadReceiveDataCore;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.len.ccad.baseinterface.MessageListener;
import org.len.ccad.messagecontroller.MessageController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author yudha
 * @author Faturrahman
 * @author Ian Agung
 */
/**
 *
 * This is TrakForm JFrame form class
 */
public class TrakForm extends javax.swing.JFrame {

    /**
     * Reference variables
     */
    private StructTrack structTrack;
    private StructHeader structHeader = new StructHeader();
    TrakForm trakForm;
    public ModelVariabel mod_var;
//    ThreadSyncArray thrd_sync_arr;
//    ThreadSendData thrd_send_data;
    ThreadReceiveData threadReceiveData;
    ThreadReceiveDataCore threadReceiveDataCore;
    ThreadReceiveDataChat threadReceiveDataChat;
//    ThreadReceiveData threadReceiveAck;
    //tidak pernah dipakai
    //UdpTx udpSend;
//    Readserial read_gps;
//    Readserial read_ais;
//    Readserial read_radar;
//    SerialConnection con_gps;
//    SerialConnection con_ais;
//    SerialConnection con_radar;
    TrakGenerator trak_gen;
//    ConnectionSettings connectionSettings;
    private final MessageController messageController;
    private final UdpRcvFromCcdTrackMessageListener udpRcvFromCcdTrackMessageListener = new UdpRcvFromCcdTrackMessageListener();
    private final UdpRcvFromCcdPolyMessageListener udpRcvFromCcdPolyMessageListener = new UdpRcvFromCcdPolyMessageListener();
    private final UdpRcvFromCcdLineMessageListener udpRcvFromCcdLineMessageListener = new UdpRcvFromCcdLineMessageListener();
    private final UdpRcvFromCcdCircleMessageListener udpRcvFromCcdCircleMessageListener = new UdpRcvFromCcdCircleMessageListener();
    private final UdpRcvFromCcdTextMessageListener udpRcvFromCcdTextMessageListener = new UdpRcvFromCcdTextMessageListener();
    private final UdpRcvFromCcdFileMessageListener udpRcvFromCcdFileMessageListener = new UdpRcvFromCcdFileMessageListener();
    private final UdpRcvFromCoreTrackMessageListener udpRcvFromCoreTrackMessageListener = new UdpRcvFromCoreTrackMessageListener();
    private final UdpRcvFromCoreTextMessageListener udpRcvFromCoreTextMessageListener = new UdpRcvFromCoreTextMessageListener();
    private final UdpRcvFromCoreFileMessageListener udpRcvFromCoreFileMessageListener = new UdpRcvFromCoreFileMessageListener();
    private final UdpRcvFromCoreAckMessageListener udpRcvFromCoreAckMessageListener = new UdpRcvFromCoreAckMessageListener();
    private final SerialRcvGpsTrackMessageListener serialRcvGpsTrackMessageListener = new SerialRcvGpsTrackMessageListener();
    private final SerialRcvGpsNmeaMessageListener serialRcvGpsNmeaMessageListener = new SerialRcvGpsNmeaMessageListener();
    public int ownpu;
    Settings settings;
    boolean show_setting;
    About about;
    private DomParser domParser = new DomParser();
    private SerialConnection serialGPS = new SerialConnection();
    private Readserial read_gps;
    private byte[] one_trak;
    private final DataLink_Constanta DEF = new DataLink_Constanta();
    private int countRow = 0;

    /**
     * Creates new form TrakForm
     */
    public TrakForm() {

        mod_var = new ModelVariabel();
        trak_gen = new TrakGenerator(mod_var);

        initComponents();
        messageController = new MessageController();
        messageController.registerListener(udpRcvFromCcdTrackMessageListener);
        messageController.registerListener(udpRcvFromCcdPolyMessageListener);
        messageController.registerListener(udpRcvFromCcdLineMessageListener);
        messageController.registerListener(udpRcvFromCcdCircleMessageListener);
        messageController.registerListener(udpRcvFromCcdTextMessageListener);
        messageController.registerListener(udpRcvFromCcdFileMessageListener);
        messageController.registerListener(udpRcvFromCoreTrackMessageListener);
        messageController.registerListener(udpRcvFromCoreTextMessageListener);
        messageController.registerListener(udpRcvFromCoreAckMessageListener);
        messageController.registerListener(udpRcvFromCoreFileMessageListener);
        messageController.registerListener(serialRcvGpsTrackMessageListener);
        messageController.registerListener(serialRcvGpsNmeaMessageListener);

//        thrd_sync_arr = new ThreadSyncArray(mod_var);
        //thrd_send_data = new ThreadSendData(trak_gen);
        //connectionSettings = new ConnectionSettings(trak_gen, messageController);
        //udpSend = new UdpTx();
//        read_gps = new Readserial(trak_gen, "gps");
//        read_ais = new Readserial(trak_gen, "ais");
//        read_radar = new Readserial(trak_gen, "radar");
//        show_setting = false;
        structTrack = new StructTrack();
        //this.setPreferredSize(new Dimension(755, 450));
        //this.setMaximumSize(new Dimension(755, 775));
        //this.setMinimumSize(new Dimension(755, 445));
        setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.doParsing();
        settings = new Settings(mod_var);
//        GUI_Update_Trak_Tx();
//        tscan.start();
//        thrd_sync_arr.start();

        startButton.doClick();
        startButton.setEnabled(false);
    }

    /**
     *
     * @param bln
     */
    private void SetParentVisible(boolean bln) {
        this.setVisible(bln);
    }

    @SuppressWarnings("unchecked")
    /**
     * Method to set scanning time of data track in tableTrack TX and send it to
     * UDP
     */
//    javax.swing.Timer tscan = new javax.swing.Timer(500, new ActionListener() {
//
//        private int ntrak;
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            if (mod_var.isMode_real()) {
//                int row = tableTrackTx.getRowCount();
//                int col = tableTrackTx.getColumnCount();
//                for (int i = 0; i < row; i++) {
//                    for (int j = 0; j < col; j++) {
//                        tableTrackTx.getModel().setValueAt(null, i, j);
//                    }
//                }
//
//                ntrak = mod_var.getBufferRxZize();
//                int k = 0;
//                for (int i = 0; i < ntrak; i++) {
//
//                    if (mod_var.getBuffer_trak_rx()[i][23] != 0) {
//                        trak_gen.read_trak_tx(mod_var.getBuffer_trak_rx()[i]);
////                        mod_var.getBuffer_trak_tx().add(mod_var.getBuffer_trak_rx()[i]);
//
//                        tableTrackTx.getModel().setValueAt((double) (i), k, 0);
//                        tableTrackTx.getModel().setValueAt(trak_gen.longit_tx, k, 1);
//                        tableTrackTx.getModel().setValueAt(trak_gen.latit_tx, k, 2);
//                        tableTrackTx.getModel().setValueAt(trak_gen.spd_tx, k, 3);
//                        tableTrackTx.getModel().setValueAt(trak_gen.crs_tx, k, 4);
//                        tableTrackTx.getModel().setValueAt((double) trak_gen.hgt_tx, k, 5);
//                        tableTrackTx.getModel().setValueAt((double) trak_gen.attb_tx, k, 6);
//                        tableTrackTx.getModel().setValueAt(trak_gen.source, k, 7);
//                        tableTrackTx.getModel().setValueAt(trak_gen.mmsi, k, 8);
//                        k++;
//                    }
//                }
//
//                mod_var.getBuffer_trak_tx().clear();
//                GUI_Update_Trak_Tx();
//            }
//        }
//    });
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        runPanel = new javax.swing.JPanel();
        messageButton = new javax.swing.JButton();
        settingButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        titleLable = new javax.swing.JLabel();
        versionLable = new javax.swing.JLabel();
        tgSeparator = new javax.swing.JSeparator();
        ownNpuTextField = new javax.swing.JTextField();
        ownNpuLabel = new javax.swing.JLabel();
        statusPanel4 = new javax.swing.JPanel();
        msgLabel = new javax.swing.JLabel();
        statusAppLabel = new javax.swing.JLabel();
        statusScrollPane = new javax.swing.JScrollPane();
        statusTextArea = new javax.swing.JTextArea();
        msgRecScrollPane = new javax.swing.JScrollPane();
        msgRecTextArea = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        DataTrackReceivedTabbedPane = new javax.swing.JTabbedPane();
        FromCcdPanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        tableTrackTx = new javax.swing.JTable();
        FromCorePanel = new javax.swing.JPanel();
        tableRxScrollPane = new javax.swing.JScrollPane();
        tableTrackRx = new javax.swing.JTable();
        FromGpsSerial = new javax.swing.JPanel();
        GpsNmeaScrollPane = new javax.swing.JScrollPane();
        GpsNmeaTextArea = new javax.swing.JTextArea();
        tgMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel10.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel10.setText("GPS");

        jLabel12.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel12.setText("AIS");

        jButton5.setText("Update Trakdata");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel15.setText("Addres");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        runPanel.setBackground(new java.awt.Color(153, 153, 153));
        runPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        messageButton.setText("Message");
        messageButton.setEnabled(false);
        messageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageButtonActionPerformed(evt);
            }
        });

        settingButton.setText("Show Connection Settings");
        settingButton.setEnabled(false);
        settingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingButtonActionPerformed(evt);
            }
        });

        startButton.setText("Run");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout runPanelLayout = new javax.swing.GroupLayout(runPanel);
        runPanel.setLayout(runPanelLayout);
        runPanelLayout.setHorizontalGroup(
            runPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(runPanelLayout.createSequentialGroup()
                .addComponent(startButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(messageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingButton)
                .addGap(22, 22, 22))
        );
        runPanelLayout.setVerticalGroup(
            runPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(runPanelLayout.createSequentialGroup()
                .addGroup(runPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(messageButton)
                    .addComponent(settingButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        titleLable.setFont(new java.awt.Font("AR BONNIE", 0, 48)); // NOI18N
        titleLable.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        titleLable.setText("Track Generator ");

        versionLable.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 12)); // NOI18N
        versionLable.setText("Part Of Datalink System");

        ownNpuTextField.setText("1");

        ownNpuLabel.setText("OWN NPU");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(versionLable)
                .addGap(284, 284, 284))
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addComponent(tgSeparator)
                        .addContainerGap())
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addComponent(ownNpuLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ownNpuTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleLable, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ownNpuTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ownNpuLabel))
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(titleLable, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(versionLable)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        msgLabel.setText("Message Record");

        statusAppLabel.setText("Status Aplikasi");

        statusTextArea.setColumns(20);
        statusTextArea.setRows(5);
        statusScrollPane.setViewportView(statusTextArea);

        msgRecTextArea.setColumns(20);
        msgRecTextArea.setRows(5);
        msgRecScrollPane.setViewportView(msgRecTextArea);

        javax.swing.GroupLayout statusPanel4Layout = new javax.swing.GroupLayout(statusPanel4);
        statusPanel4.setLayout(statusPanel4Layout);
        statusPanel4Layout.setHorizontalGroup(
            statusPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanel4Layout.createSequentialGroup()
                        .addComponent(statusAppLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(msgLabel)
                        .addGap(315, 315, 315))
                    .addGroup(statusPanel4Layout.createSequentialGroup()
                        .addComponent(statusScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msgRecScrollPane)))
                .addContainerGap())
        );
        statusPanel4Layout.setVerticalGroup(
            statusPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msgLabel)
                    .addComponent(statusAppLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msgRecScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(statusScrollPane)))
        );

        tableTrackTx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Trak no", "Longitude", "Latitude", "Speed", "Csr", "Height", "Attribute", "Source", "MMSI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableScrollPane.setViewportView(tableTrackTx);

        javax.swing.GroupLayout FromCcdPanelLayout = new javax.swing.GroupLayout(FromCcdPanel);
        FromCcdPanel.setLayout(FromCcdPanelLayout);
        FromCcdPanelLayout.setHorizontalGroup(
            FromCcdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FromCcdPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        FromCcdPanelLayout.setVerticalGroup(
            FromCcdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );

        DataTrackReceivedTabbedPane.addTab("From CCD", FromCcdPanel);

        tableTrackRx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Trak no", "Longitude", "Latitude", "Speed", "Csr", "Height", "Attribute", "Source", "MMSI"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRxScrollPane.setViewportView(tableTrackRx);

        javax.swing.GroupLayout FromCorePanelLayout = new javax.swing.GroupLayout(FromCorePanel);
        FromCorePanel.setLayout(FromCorePanelLayout);
        FromCorePanelLayout.setHorizontalGroup(
            FromCorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 843, Short.MAX_VALUE)
            .addGroup(FromCorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tableRxScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE))
        );
        FromCorePanelLayout.setVerticalGroup(
            FromCorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
            .addGroup(FromCorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tableRxScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
        );

        DataTrackReceivedTabbedPane.addTab("From Core", FromCorePanel);

        GpsNmeaTextArea.setColumns(20);
        GpsNmeaTextArea.setRows(5);
        GpsNmeaScrollPane.setViewportView(GpsNmeaTextArea);

        javax.swing.GroupLayout FromGpsSerialLayout = new javax.swing.GroupLayout(FromGpsSerial);
        FromGpsSerial.setLayout(FromGpsSerialLayout);
        FromGpsSerialLayout.setHorizontalGroup(
            FromGpsSerialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GpsNmeaScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
        );
        FromGpsSerialLayout.setVerticalGroup(
            FromGpsSerialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GpsNmeaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );

        DataTrackReceivedTabbedPane.addTab("From GPS", FromGpsSerial);

        fileMenu.setText("File");

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        tgMenuBar.add(fileMenu);

        jMenu2.setText("About");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem3.setText("About Me");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        tgMenuBar.add(jMenu2);

        setJMenuBar(tgMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DataTrackReceivedTabbedPane)
                            .addComponent(jSeparator1)
                            .addComponent(runPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataTrackReceivedTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(runPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        GUI_addPortList();


    }//GEN-LAST:event_formWindowOpened

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    }//GEN-LAST:event_jButton5ActionPerformed

    private void settingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingButtonActionPerformed

        //settings.setParameter(connectionSettings);
        settings.setVisible(true);
//        mod_var.setOwnNpu(Integer.valueOf(ownNpuTextField.getText()));
    }//GEN-LAST:event_settingButtonActionPerformed

    private void messageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageButtonActionPerformed
//        mod_var.setOwnNpu(Integer.valueOf(ownNpuTextField.getText()));
        new MessageForm(this, true, mod_var, trak_gen).setVisible(true);
    }//GEN-LAST:event_messageButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        about = new About(this, true);
        about.setResizable(false);
        about.setLocationRelativeTo(this);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    /**
     * This method used to start Send and Receive through UDP Network
     *
     * @param evt
     */
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        //
        // Set Own NPU
        this.ownNpuTextField.setText(String.valueOf(mod_var.getOwnNpu()));
        mod_var.setOwnNpu(Integer.valueOf(ownNpuTextField.getText()));
        statusTextArea.append("Own NPU" + mod_var.getOwnNpu() + "\n");

        if (this.mod_var.isStatUdpRx() && this.mod_var.isStatUdpTx()
                && this.mod_var.isStatUdpRxCore() && this.mod_var.isStatUdpTxCcd()) {
            //Set UDP TX and RX Address and Port
            System.out.println("Start UDP Thread Receive From Ccd");
            statusTextArea.append("Start UDP Thread Receive From Ccd \n");
            statusTextArea.append("UDP Tx Connected (Core) :IP = " + mod_var.getAddressUdpTx() + "\n");
            statusTextArea.append("Port = " + mod_var.getPortUdpTx() + "\n");
            trak_gen.setUDPTxConnection(mod_var.getAddressUdpTx(), mod_var.getPortUdpTx());
            //Set UDP TX and RX Address and Port
            System.out.println("Start UDP Thread Receive From Core");
            statusTextArea.append("Start UDP Thread Receive From Core \n");
            statusTextArea.append("UDP Tx Connected (CCD) :IP = " + mod_var.getAddressUdpTxCcd() + "\n");
            statusTextArea.append("Port = " + mod_var.getPortUdpTxCcd() + "\n");
            trak_gen.setUDPTxConnectionToCcd(mod_var.getAddressUdpTxCcd(), mod_var.getPortUdpTxCcd());
            //Set UDP TX and RX Address and Port for chat
            System.out.println("Start UDP Thread Receive From Chat App");
            statusTextArea.append("Start UDP Thread Receive From Chat App \n");
            statusTextArea.append("UDP Tx Connected (Chat) :IP = " + mod_var.getAddressUdpTxChat() + "\n");
            statusTextArea.append("Port = " + mod_var.getPortUdpTxChat() + "\n");
            trak_gen.setUDPTxConnectionToChat(mod_var.getAddressUdpTxChat(), mod_var.getPortUdpTxChat());
            //start receive data from Ccd
            threadReceiveData = new ThreadReceiveData(mod_var, trak_gen, messageController);
            threadReceiveData.start();
            //start receive data from Core
            threadReceiveDataCore = new ThreadReceiveDataCore(mod_var, trak_gen, messageController);
            threadReceiveDataCore.start();
            //start receive data from Chat
            threadReceiveDataChat = new ThreadReceiveDataChat(mod_var, trak_gen, messageController);
            threadReceiveDataChat.start();

        } else {
            JOptionPane.showMessageDialog(this.trakForm,
                    "Connection must be set first",
                    "Connection warning",
                    JOptionPane.WARNING_MESSAGE);
        }

        if (this.mod_var.isStatGpsRcv() && this.mod_var.isStatGpsTx()) {
            //Open Serial Connection
            serialGPS.OpenConnection(this.mod_var.getGpsBaud(), this.mod_var.getGpsPort());
            read_gps = new Readserial("gps", messageController, mod_var);
            read_gps.tis_mode = "GPS";
            read_gps.initialKoneksi(serialGPS);
            statusTextArea.append("Start Read Gps From Serial Port\n");
            statusTextArea.append("GPS Port : " + this.mod_var.getGpsPort()
                    + " Baudrate : " + this.mod_var.getGpsBaud() + "\n");
            //Initiate Port Address to Send Data Gps To Core
            statusTextArea.append("GPS Tx Connected (Core) :IP = " + mod_var.getAddressGpsTx() + "\n");
            statusTextArea.append("Port = " + mod_var.getPortGpsTx() + "\n");
            trak_gen.setGpsTxConnectionToCore(mod_var.getAddressGpsTx(), mod_var.getPortGpsTx());

        } else {
            JOptionPane.showMessageDialog(this.trakForm,
                    "Serial Connection must be set first",
                    "Connection warning",
                    JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_startButtonActionPerformed
    /**
     * Method to get data tracks in tableTrack TX to send via UDP and update
     * them
     */
    private void GUI_Update_Trak_Tx() {

        int row = tableTrackTx.getRowCount();
        byte owner = Byte.valueOf(ownNpuTextField.getText());
        int count_trak = 0;
        for (int i = 0; i < row; i++) {
            if (tableTrackTx.getModel().getValueAt(i, 0) != null) {
                count_trak++;
                structTrack.setOwner(owner);
                structTrack.setNumber((int) (double) tableTrackTx.getModel().getValueAt(i, 0));
                structTrack.setLongitude((double) tableTrackTx.getModel().getValueAt(i, 1));
                structTrack.setLatitude((double) tableTrackTx.getModel().getValueAt(i, 2));
                structTrack.setSpeed((double) tableTrackTx.getModel().getValueAt(i, 3));
                structTrack.setCourse((double) tableTrackTx.getModel().getValueAt(i, 4));
                structTrack.setHeight((int) (double) tableTrackTx.getModel().getValueAt(i, 5));
                structTrack.setAttribute((int) (double) tableTrackTx.getModel().getValueAt(i, 6));
                structTrack.setMmsi((int) tableTrackTx.getModel().getValueAt(i, 8));

//                mod_var.getBuffer_trak_tx().add(structTrack.getBytesTrack());
            }
        }
    }

    /**
     * Listener for GPS Track Received from Serial Port
     */
    private final class SerialRcvGpsTrackMessageListener implements MessageListener<GpsRcvMessage> {

        @Override
        public void notifyMessageListener(GpsRcvMessage message) {
            handleReceivedGpsData(message);
        }

        @Override
        public Class<GpsRcvMessage> getMessageType() {
            return GpsRcvMessage.class;
        }
    }

    /**
     * Handle Received Data GPS
     *
     * @param message
     */
    private void handleReceivedGpsData(GpsRcvMessage message) {

        // Data Gps dikirim kan via UDP ke IP Core Datalink
        // Data Gps dikirim kan via UDP ke IP CCD
        // Data GPS dalam bentuk Struck
        // ditampilkan ke dalam table
        byte[] messageData = message.getSelectedByte();

        //Masukkan data GPS ke dalam Track no.0
        structTrack = new StructTrack(messageData);
//        System.out.println("Data GPS , Lat =" + structTrack.getLatitude() + " ;Long = " + structTrack.getLongitude());

        //Terjemahkan isi Struck Track
        int number1 = 0;//TRack Nomer 0
        double longitude1 = structTrack.getLongitude();
        double latitude1 = structTrack.getLatitude();
        double speed1 = structTrack.getSpeed();
        double course1 = structTrack.getCourse();
        int height1 = structTrack.getHeight();
        int attribute1 = structTrack.getAttribute();
        int owner1 = structTrack.getOwner();
        int group1 = structTrack.getGroup();
        int mmsi1 = structTrack.getMmsi();

        StruckModelTrack struckModelTrack = new StruckModelTrack(number1, messageData);
        checkNoTrack(struckModelTrack);
        //Simpan track ke ModVar
        mod_var.getBuffer_trak_tx().add(0, struckModelTrack);
        mod_var.sortDataTrack();

        if (countRow == 20) {
            countRow = 0;
            GpsNmeaTextArea.setText("");
        }
        GpsNmeaTextArea.append("Size Elemen Array List ="
                + mod_var.getBuffer_trak_tx().size() + "\n");

        countRow++;
        //Tampilkan ke Tabel
        int row = tableTrackTx.getRowCount();
        int col = tableTrackTx.getColumnCount();

        //Delete semua isi Table dulu
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tableTrackTx.getModel().setValueAt(null, i, j);
            }
        }

        //Tampilkan ke Tabel --> Data GPS dalam bentuk Track no.0
        int ntrak = mod_var.getBufferTxSize();
        int k = 0;
        byte[] data;
        for (int i = 0; i < ntrak; i++) {

            data = mod_var.getBuffer_trak_tx().get(i).getData();

            structTrack = new StructTrack(data);
            longitude1 = structTrack.getLongitude();
            latitude1 = structTrack.getLatitude();
            speed1 = structTrack.getSpeed();
            course1 = structTrack.getCourse();
            height1 = structTrack.getHeight();
            attribute1 = structTrack.getAttribute();
            owner1 = structTrack.getOwner();
            group1 = structTrack.getGroup();
            number1 = structTrack.getNumber();
            mmsi1 = structTrack.getMmsi();

            tableTrackTx.setValueAt(number1, k, 0);
            tableTrackTx.setValueAt(longitude1, k, 1);
            tableTrackTx.setValueAt(latitude1, k, 2);
            tableTrackTx.setValueAt(speed1, k, 3);
            tableTrackTx.setValueAt(course1, k, 4);
            tableTrackTx.setValueAt(height1, k, 5);
            tableTrackTx.setValueAt(attribute1, k, 6);
            tableTrackTx.setValueAt(owner1, k, 7);
            tableTrackTx.setValueAt(mmsi1, k, 8);
            k++;

            GpsNmeaTextArea.append("GPS Lat = " + latitude1 + "\n");
            GpsNmeaTextArea.append("GPS Long = " + longitude1 + "\n");

        }

        //Kirim data GPS bentuk Struck Track ke CCD
        //Data GPS dari TG
        //Number = 0;
        //Source = 0;
        //Destination = 0;
        int source = mod_var.getOwnNpu();
        int destination = 0;
        int number = 0;

        byte[] udpPacket = new byte[16 + messageData.length];

        byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_TX_TRACK, DEF.TRACK_TYPE_GPS_OWN);
        // Data Dikirim via UDP ke CCD
        System.arraycopy(header, 0, udpPacket, 0, header.length);

        System.arraycopy(messageData, 0, udpPacket, 16, messageData.length);
        trak_gen.kirimDataToCcd(udpPacket);

        //Data dikirim juga ke Core sebagai Track-0
        //Hal ini pengecualian hanya utk Track-0
        //Yang merupakan Data GPS Own
        trak_gen.kirimData(setDataTrackReadySend());
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for GPS Track Received from Serial Port
     */
    private final class SerialRcvGpsNmeaMessageListener implements MessageListener<GpsNmeaRcvMessage> {

        @Override
        public void notifyMessageListener(GpsNmeaRcvMessage message) {
            handleReceivedGpsNmea(message);
        }

        @Override
        public Class<GpsNmeaRcvMessage> getMessageType() {
            return GpsNmeaRcvMessage.class;
        }
    }

    /**
     * Handle Received Data GPS NMEA Send Data To Core as NMEA GPS
     *
     * @param message
     */
    private void handleReceivedGpsNmea(GpsNmeaRcvMessage message) {

        //To change body of generated methods, choose Tools | Templates.
        //data GPS Nmea dikirimkan ke IP Core
        trak_gen.kirimGpsToCore(message.getSelectedByte());
        try {
            GpsNmeaTextArea.append("Data GPS : " + new String(message.getSelectedByte(), "UTF-8") + "\n");
            countRow++;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TrakForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Listener for Track Received from UDP CCD
     */
    private final class UdpRcvFromCcdTrackMessageListener implements MessageListener<UdpRcvFromCcdTrackMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCcdTrackMessage message) {
            handleReceivedUdpRcvFromCcdTrack(message);
        }

        @Override
        public Class<UdpRcvFromCcdTrackMessage> getMessageType() {
            return UdpRcvFromCcdTrackMessage.class;
        }
    }

    /**
     * UdpRcvFromCcdTrackMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCcdTrack(UdpRcvFromCcdTrackMessage message) {

        byte[] messageData = message.getSelectedByte();

        StructTrack structTrack = new StructTrack(messageData);
        double longitude = structTrack.getLongitude();
        double latitude = structTrack.getLatitude();
        double speed = structTrack.getSpeed();
        double course = structTrack.getCourse();
        int height = structTrack.getHeight();
        int attribute = structTrack.getAttribute();
        int owner = structTrack.getOwner();
        int group = structTrack.getGroup();
        int number = structTrack.getNumber();
        int mmsi = structTrack.getMmsi();

//        StruckModelTrack struckModelTrack = new StruckModelTrack(number, messageData);
//
//        //Simpan track ke ModVar
//        mod_var.getBuffer_trak_tx().add(struckModelTrack);
//        mod_var.sortDataTrack();
        //Tampilkan ke Tabel
        int row = tableTrackTx.getRowCount();
        int col = tableTrackTx.getColumnCount();
        //Delete semua isi Table dulu
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tableTrackTx.getModel().setValueAt(null, i, j);
            }
        }

        int ntrak = mod_var.getBufferTxSize();
        int k = 0;
        byte[] data;
        for (int i = 0; i < ntrak; i++) {

            data = mod_var.getBuffer_trak_tx().get(i).getData();

            structTrack = new StructTrack(data);
            longitude = structTrack.getLongitude();
            latitude = structTrack.getLatitude();
            speed = structTrack.getSpeed();
            course = structTrack.getCourse();
            height = structTrack.getHeight();
            attribute = structTrack.getAttribute();
            owner = structTrack.getOwner();
            group = structTrack.getGroup();
            number = structTrack.getNumber();
            mmsi = structTrack.getMmsi();

            tableTrackTx.setValueAt(number, k, 0);
            tableTrackTx.setValueAt(longitude, k, 1);
            tableTrackTx.setValueAt(latitude, k, 2);
            tableTrackTx.setValueAt(speed, k, 3);
            tableTrackTx.setValueAt(course, k, 4);
            tableTrackTx.setValueAt(height, k, 5);
            tableTrackTx.setValueAt(attribute, k, 6);
            tableTrackTx.setValueAt(owner, k, 7);
            tableTrackTx.setValueAt(mmsi, k, 8);
            k++;

        }

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for Text Received from UDP CCD
     */
    private final class UdpRcvFromCcdTextMessageListener implements MessageListener<UdpRcvFromCcdTextMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCcdTextMessage message) {
            handleReceivedUdpRcvFromCcdText(message);
        }

        @Override
        public Class<UdpRcvFromCcdTextMessage> getMessageType() {
            return UdpRcvFromCcdTextMessage.class;
        }
    }

    /**
     * UdpRcvFromCcdTextMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCcdText(UdpRcvFromCcdTextMessage message) {

        byte[] messageData = message.getSelectedByte();

        StructText structText = new StructText(messageData);
        String txtMsg = structText.getStext();
        int number = structText.getTextNumber();

        StruckModelText struckModelText = new StruckModelText(number, messageData);

        //Simpan text ke ModVar
        mod_var.getBuffer_msgtext_tx().add(struckModelText);
        mod_var.sortDataTextMsg();

        //Tampilkan ke Text Area
        msgRecTextArea.append("Received Text Message From CCD" + "\n");
        msgRecTextArea.append("NPU " + mod_var.getOwnNpu() + " :" + txtMsg + "\n");
        msgRecTextArea.append("Text Number " + number + "\n");
        msgRecTextArea.append("Sent to Core" + "\n");

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for File Received from UDP CCD
     */
    private final class UdpRcvFromCcdFileMessageListener implements MessageListener<UdpRcvFromCcdFileMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCcdFileMessage message) {
            handleReceivedUdpRcvFromCcdFile(message);
        }

        @Override
        public Class<UdpRcvFromCcdFileMessage> getMessageType() {
            return UdpRcvFromCcdFileMessage.class;
        }
    }

    /**
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCcdFile(UdpRcvFromCcdFileMessage message) {

        byte[] messageData = message.getSelectedByte();

        StructText structText = new StructText(messageData);
        String txtMsg = structText.getStext();
        int number = structText.getTextNumber();

        StruckModelText struckModelText = new StruckModelText(number, messageData);

        //Simpan text ke ModVar
        mod_var.getBuffer_msgtext_tx().add(struckModelText);
        mod_var.sortDataTextMsg();

        //Tampilkan ke Text Area
        msgRecTextArea.append("Received File From CCD" + "\n");
        msgRecTextArea.append("NPU " + mod_var.getOwnNpu() + " :File \n");
        msgRecTextArea.append("Text Number " + number + "\n");
        msgRecTextArea.append("Sent to Core" + "\n");

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for Poly Received from UDP CCD
     */
    private final class UdpRcvFromCcdPolyMessageListener implements MessageListener<UdpRcvFromCcdPolylineMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCcdPolylineMessage message) {
            handleReceivedUdpRcvFromCcdPoly(message);
        }

        @Override
        public Class<UdpRcvFromCcdPolylineMessage> getMessageType() {
            return UdpRcvFromCcdPolylineMessage.class;
        }
    }

    /**
     * UdpRcvFromCcdPolyMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCcdPoly(UdpRcvFromCcdPolylineMessage message) {

        byte[] messageData = message.getSelectedByte();

        StructPolyline structPolyline = new StructPolyline(messageData);

        int number = structPolyline.getNumber();

        StruckModelPolyline struckModelPolyline = new StruckModelPolyline(number, messageData);

        //Simpan track ke ModVar
        mod_var.getBuffer_poly_tx().add(struckModelPolyline);
        mod_var.sortDataPolyline();

        //Tampilkan ke Text Area
        msgRecTextArea.append("Received Polyline Message From CCD" + "\n");
        msgRecTextArea.append("NPU " + mod_var.getOwnNpu() + " :" + "\n");
        msgRecTextArea.append("Polyline Number " + number + "\n");
        msgRecTextArea.append("Sent to Core" + "\n");

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for Line Received from UDP CCD
     */
    private final class UdpRcvFromCcdLineMessageListener implements MessageListener<UdpRcvFromCcdLineMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCcdLineMessage message) {
            handleReceivedUdpRcvFromCcdLine(message);
        }

        @Override
        public Class<UdpRcvFromCcdLineMessage> getMessageType() {
            return UdpRcvFromCcdLineMessage.class;
        }
    }

    /**
     * UdpRcvFromCcdLineMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCcdLine(UdpRcvFromCcdLineMessage message) {

        byte[] messageData = message.getSelectedByte();

        StructPolyline structLine = new StructPolyline(messageData);

        int number = structLine.getNumber();

        StruckModelLine struckModelLine = new StruckModelLine(number, messageData);

        //Simpan track ke ModVar
        mod_var.getBuffer_line_tx().add(struckModelLine);
        mod_var.sortDataLine();

        //Tampilkan ke Text Area
        msgRecTextArea.append("Received Line Message From CCD" + "\n");
        msgRecTextArea.append("NPU " + mod_var.getOwnNpu() + "\n");
        msgRecTextArea.append("Line Number " + number + "\n");
        msgRecTextArea.append("Sent to Core" + "\n");

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for Circle Received from UDP CCD
     */
    private final class UdpRcvFromCcdCircleMessageListener implements MessageListener<UdpRcvFromCcdCircleMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCcdCircleMessage message) {
            handleReceivedUdpRcvFromCcdCircle(message);
        }

        @Override
        public Class<UdpRcvFromCcdCircleMessage> getMessageType() {
            return UdpRcvFromCcdCircleMessage.class;
        }
    }

    /**
     * UdpRcvFromCcdCircleMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCcdCircle(UdpRcvFromCcdCircleMessage message) {

        byte[] messageData = message.getSelectedByte();

        StructCircle structCircle = new StructCircle(messageData);

        int number = structCircle.getNumber();

        StruckModelCircle struckModelCircle = new StruckModelCircle(number, messageData);

        //Simpan track ke ModVar
        mod_var.getBuffer_circle_tx().add(struckModelCircle);
        mod_var.sortDataCircle();

        //Tampilkan ke Text Area
        msgRecTextArea.append("Received Text Message From CCD" + "\n");
        msgRecTextArea.append("NPU " + mod_var.getOwnNpu() + "\n");
        msgRecTextArea.append("Circle Number " + number + "\n");
        msgRecTextArea.append("Sent to Core" + "\n");

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for Track Received from UDP Core
     */
    private final class UdpRcvFromCoreTrackMessageListener implements MessageListener<UdpRcvFromCoreTrackMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCoreTrackMessage message) {
            handleReceivedUdpRcvFromCoreTrack(message);
        }

        @Override
        public Class<UdpRcvFromCoreTrackMessage> getMessageType() {
            return UdpRcvFromCoreTrackMessage.class;
        }
    }

    /**
     * UdpRcvFromCoreTrackMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCoreTrack(UdpRcvFromCoreTrackMessage message) {

        byte[] messageData = message.getSelectedByte();

        StructTrack structTrack;
        double longitude;
        double latitude;
        double speed;
        double course;
        int height;
        int attribute;
        int owner;
        int group;
        int number;
        int mmsi;

//        StruckModelTrack struckModelTrack = new StruckModelTrack(number, messageData);
//
//        //Simpan track ke ModVar
//        mod_var.getBuffer_trak_tx().add(struckModelTrack);
//        mod_var.sortDataTrack();
        //Tampilkan ke Tabel
        int row = tableTrackRx.getRowCount();
        int col = tableTrackRx.getColumnCount();
        //Delete semua isi Table dulu
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tableTrackRx.getModel().setValueAt(null, i, j);
            }
        }

        int ntrak = mod_var.getBufferRxSize();
        int k = 0;
        byte[] data;
        for (int i = 0; i < ntrak; i++) {

            data = mod_var.getBuffer_trax_rx().get(i).getData();

            structTrack = new StructTrack(data);
            longitude = structTrack.getLongitude();
            latitude = structTrack.getLatitude();
            speed = structTrack.getSpeed();
            course = structTrack.getCourse();
            height = structTrack.getHeight();
            attribute = structTrack.getAttribute();
            owner = structTrack.getOwner();
            group = structTrack.getGroup();
            number = structTrack.getNumber();
            mmsi = structTrack.getMmsi();

            tableTrackRx.setValueAt(number, i, 0);
            tableTrackRx.setValueAt(longitude, i, 1);
            tableTrackRx.setValueAt(latitude, i, 2);
            tableTrackRx.setValueAt(speed, i, 3);
            tableTrackRx.setValueAt(course, i, 4);
            tableTrackRx.setValueAt(height, i, 5);
            tableTrackRx.setValueAt(attribute, i, 6);
            tableTrackRx.setValueAt(owner, i, 7);
            tableTrackRx.setValueAt(mmsi, i, 8);
//            k++;

        }

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for Text Received from UDP Core
     */
    private final class UdpRcvFromCoreTextMessageListener implements MessageListener<UdpRcvFromCoreTextMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCoreTextMessage message) {
            handleReceivedUdpRcvFromCoreText(message);
        }

        @Override
        public Class<UdpRcvFromCoreTextMessage> getMessageType() {
            return UdpRcvFromCoreTextMessage.class;
        }
    }

    /**
     * UdpRcvFromCoreTextMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCoreText(UdpRcvFromCoreTextMessage message) {

        byte[] data = message.getSelectedByte();
        byte[] header = new byte[16];
//        byte[] header2 = new byte[8];
        System.arraycopy(data, 0, header, 0, 16);
        StructHeader structHeader = new StructHeader(header);
        //number as source
        int number = structHeader.getSource();

        byte[] messageData = new byte[data.length - 16];
        System.arraycopy(data, 16, messageData, 0, messageData.length);

        StructText structText = new StructText(messageData);
        String txtMsg = structText.getStext();
        int numberText = structText.getTextNumber();

        StruckModelText struckModelText = new StruckModelText(number, messageData);

        //Simpan text ke ModVar
        mod_var.getBuffer_msgtext_rx().add(struckModelText);
        mod_var.sortDataTextMsgFromCore();

        //Tampilkan ke Text Area
        msgRecTextArea.append("Received Text Message From Core" + "\n");
        msgRecTextArea.append("Source NPU " + number + " :" + txtMsg + "\n");
        msgRecTextArea.append("Text Number " + numberText + "\n");
        msgRecTextArea.append("Sent to CCD" + "\n");

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for File Received from UDP Core
     */
    private final class UdpRcvFromCoreFileMessageListener implements MessageListener<UdpRcvFromCoreFileMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCoreFileMessage message) {
            handleReceivedUdpRcvFromCoreFile(message);
        }

        @Override
        public Class<UdpRcvFromCoreFileMessage> getMessageType() {
            return UdpRcvFromCoreFileMessage.class;
        }
    }

    /**
     * UdpRcvFromCoreFileMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCoreFile(UdpRcvFromCoreFileMessage message) {

        byte[] data = message.getSelectedByte();
        byte[] header = new byte[16];
//        byte[] header2 = new byte[8];
        System.arraycopy(data, 0, header, 0, 16);
        StructHeader structHeader = new StructHeader(header);
        //number as source
        int number = structHeader.getSource();

        byte[] messageData = new byte[data.length - 16];
        System.arraycopy(data, 16, messageData, 0, messageData.length);

        StructText structText = new StructText(messageData);
        String txtMsg = structText.getStext();
        int numberText = structText.getTextNumber();

        StruckModelText struckModelText = new StruckModelText(number, messageData);

        //Simpan text ke ModVar
        mod_var.getBuffer_msgtext_rx().add(struckModelText);
        mod_var.sortDataTextMsgFromCore();

        //Tampilkan ke Text Area
        msgRecTextArea.append("Received Text Message From Core" + "\n");
        msgRecTextArea.append("Source NPU " + number + " : File \n");
        msgRecTextArea.append("Text Number " + numberText + "\n");
        msgRecTextArea.append("Sent to CCD" + "\n");

        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Listener for Ack Received from UDP Core
     */
    private final class UdpRcvFromCoreAckMessageListener implements MessageListener<UdpRcvFromCoreAckMessage> {

        @Override
        public void notifyMessageListener(UdpRcvFromCoreAckMessage message) {
            handleReceivedUdpRcvFromCoreAck(message);
        }

        @Override
        public Class<UdpRcvFromCoreAckMessage> getMessageType() {
            return UdpRcvFromCoreAckMessage.class;
        }
    }

    /**
     * UdpRcvFromCoreAckMessageListener Handling
     *
     * @param message
     */
    private void handleReceivedUdpRcvFromCoreAck(UdpRcvFromCoreAckMessage message) {

        byte[] data = message.getSelectedByte();
//        byte[] header = new byte[16];
//        System.arraycopy(data, 0, header, 0, 16);
//        StructHeader structHeader = new StructHeader(header);
        //number as source
//        int number = structHeader.getSource();

//        byte[] messageData = new byte[data.length - 16];
//        System.arraycopy(data, 0, messageData, 0, messageData.length);
        StructAck structAck = new StructAck(data);
        int msgType = structAck.getMsgType();
        int numberObj = structAck.getObjNumber();

        //ACK tidak dimasukkan ke Array List
//        StruckModelText struckModelText = new StruckModelText(numberObj, data);
        //Simpan text ke ModVar
//        mod_var.getBuffer_msgtext_rx().add(struckModelText);
//        mod_var.sortDataTextMsgFromCore();
        //Tampilkan ke Text Area
        msgRecTextArea.append("Received ACK Message From Core" + "\n");
        msgRecTextArea.append("Message Type  :" + msgType + "\n");
        msgRecTextArea.append("Object Number " + numberObj + "\n");
        msgRecTextArea.append("Sent to CCD" + "\n");

        //To change body of generated methods, choose Tools | Templates.
    }

    private void doParsing() {
        try {
            int baudrate = 9600;

            DOMParser parser = new DOMParser();
            parser.parse("Setting.xml");
            Document doc = parser.getDocument();

            // Get the document's root XML node
            NodeList root = doc.getChildNodes();

            // Navigate down the hierarchy to get to the software node
            Node soft = domParser.getNode("Software", root);
            Node exec = domParser.getNode("Parameter1", soft.getChildNodes());
            String execType = domParser.getNodeAttr("type", exec);

            // Load the parameter's data from the XML
            NodeList nodes = exec.getChildNodes();
            String port = domParser.getNodeValue("UdpPort", nodes);
            String address = domParser.getNodeValue("UdpAddress", nodes);

            System.out.println("Parameter Information:");
            System.out.println("Type: " + execType);
            System.out.println(address + " ; " + port);

            this.mod_var.setPortUdpRxCore(Integer.valueOf(port));
            this.mod_var.setStatUdpRxCore(true);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter2", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            port = domParser.getNodeValue("UdpPort", nodes);
            address = domParser.getNodeValue("UdpAddress", nodes);

            System.out.println("Type: " + execType);
            System.out.println(address + " ; " + port);

            this.mod_var.setPortUdpRx(Integer.valueOf(port));
            this.mod_var.setStatUdpRx(true);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter3", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            port = domParser.getNodeValue("UdpPort", nodes);
            address = domParser.getNodeValue("UdpAddress", nodes);

            System.out.println("Type: " + execType);
            System.out.println(address + " ; " + port);

            this.mod_var.setAddressUdpTx(address);
            this.mod_var.setPortUdpTx(Integer.valueOf(port));
            this.mod_var.setStatUdpTx(true);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter4", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            port = domParser.getNodeValue("UdpPort", nodes);
            address = domParser.getNodeValue("UdpAddress", nodes);

            System.out.println("Type: " + execType);
            System.out.println(address + " ; " + port);

            this.mod_var.setAddressUdpTxCcd(address);
            this.mod_var.setPortUdpTxCcd(Integer.valueOf(port));
            this.mod_var.setStatUdpTxCcd(true);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter5", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            port = domParser.getNodeValue("SerialPort", nodes);
            baudrate = Integer.valueOf(domParser.getNodeValue("BaudRate", nodes));

            System.out.println("Type: " + execType);
            System.out.println("Comm Port: " + port);

            this.mod_var.setGpsPort(port);
            this.mod_var.setGpsBaud(baudrate);
            this.mod_var.setStatGpsRcv(true);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter6", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            port = domParser.getNodeValue("UdpPort", nodes);
            address = domParser.getNodeValue("UdpAddress", nodes);

            System.out.println("Type: " + execType);
            System.out.println(address + " ; " + port);

            this.mod_var.setAddressGpsTx(address);
            this.mod_var.setPortGpsTx(Integer.valueOf(port));
            this.mod_var.setStatGpsTx(true);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter7", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            String id1, id2, id3, identitasKapal = " ";
            String[] grupKapal = new String[20];
            id1 = domParser.getNodeValue("Wasit", nodes);
            id2 = domParser.getNodeValue("Komandan", nodes);
            id3 = domParser.getNodeValue("Anggota", nodes);
            if (id1.equalsIgnoreCase("Y")
                    && id2.equalsIgnoreCase("N")
                    && id3.equalsIgnoreCase("N")) {
                identitasKapal = "Wasit";
            } else if (id1.equalsIgnoreCase("N")
                    && id2.equalsIgnoreCase("Y")
                    && id3.equalsIgnoreCase("N")) {
                identitasKapal = "Komandan";
            } else if (id1.equalsIgnoreCase("N")
                    && id2.equalsIgnoreCase("N")
                    && id3.equalsIgnoreCase("Y")) {
                identitasKapal = "Anggota";
            }
            this.mod_var.setIdentitasKapal(identitasKapal);
            grupKapal[0] = domParser.getNodeValue("ListGrupKapal0", nodes);
            grupKapal[1] = domParser.getNodeValue("ListGrupKapal1", nodes);
            grupKapal[2] = domParser.getNodeValue("ListGrupKapal2", nodes);
            grupKapal[3] = domParser.getNodeValue("ListGrupKapal3", nodes);
            grupKapal[4] = domParser.getNodeValue("ListGrupKapal4", nodes);
            grupKapal[5] = domParser.getNodeValue("ListGrupKapal5", nodes);
            grupKapal[6] = domParser.getNodeValue("ListGrupKapal6", nodes);
            grupKapal[7] = domParser.getNodeValue("ListGrupKapal7", nodes);
            grupKapal[8] = domParser.getNodeValue("ListGrupKapal8", nodes);
            grupKapal[9] = domParser.getNodeValue("ListGrupKapal9", nodes);

            this.mod_var.setGrupKapal(grupKapal);

            System.out.println("Type: " + execType);
            System.out.println("Identitas Kapal: " + identitasKapal);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter8", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            String no_npu = domParser.getNodeValue("NoNpu", nodes);

            System.out.println("Type: " + execType);
            System.out.println("No NPU: " + no_npu);

            this.mod_var.setOwnNpu(Integer.valueOf(no_npu));

            this.ownNpuTextField.setText(no_npu);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter9", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            port = domParser.getNodeValue("UdpPort", nodes);
            address = domParser.getNodeValue("UdpAddress", nodes);

            System.out.println("Type: " + execType);
            System.out.println(address + " ; " + port);

            this.mod_var.setPortUdpRxChat(Integer.valueOf(port));
            this.mod_var.setStatUdpRxChat(true);

            // Load the parameter's data from the XML
            exec = domParser.getNode("Parameter10", soft.getChildNodes());
            execType = domParser.getNodeAttr("type", exec);

            nodes = exec.getChildNodes();
            port = domParser.getNodeValue("UdpPort", nodes);
            address = domParser.getNodeValue("UdpAddress", nodes);
            String statusChat = domParser.getNodeValue("Status", nodes);
            System.out.println("Type: " + execType);
            System.out.println(address + " ; " + port);

            this.mod_var.setAddressUdpTxChat(address);
            this.mod_var.setPortUdpTxChat(Integer.valueOf(port));
            if (statusChat.equalsIgnoreCase("1")) {
                this.mod_var.setStatUdpTxChat(true);
            } else {
                this.mod_var.setStatUdpTxChat(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkNoTrack(StruckModelTrack struckModelTrack) {
        int ntrak = mod_var.getBuffer_trak_tx_size();

        int noTrack;
        for (int i = 0; i < ntrak; i++) {
            noTrack = mod_var.getBuffer_trak_tx().get(i).getTrackNumber();
            if (noTrack == struckModelTrack.getTrackNumber()) {
                mod_var.getBuffer_trak_tx().remove(i);
            }

        }
    }

    public byte[] setDataTrackReadySend() {

        byte[] dataReadyToSend;
        int ntrak = mod_var.getBuffer_trak_tx_size();
        byte[] fixed_trak_tx = new byte[ntrak * 32];
        for (int i = 0; i < ntrak; i++) {
            one_trak = (byte[]) mod_var.getBuffer_trak_tx().get(i).getData();
            System.arraycopy(one_trak, 0, fixed_trak_tx, i * 32, one_trak.length);
        }

        dataReadyToSend = new byte[fixed_trak_tx.length + 8];
        dataReadyToSend[0] = (byte) DEF.TOPIC_TG2CORE;
        dataReadyToSend[1] = (byte) DEF.TYPE_TX_TRACK;
        dataReadyToSend[2] = (byte) DEF.TRACK_TYPE_GPS_OWN;
        dataReadyToSend[3] = (byte) ntrak;
        System.arraycopy(fixed_trak_tx, 0, dataReadyToSend, 8, fixed_trak_tx.length);

        return dataReadyToSend;
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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrakForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrakForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrakForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrakForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new TrakForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane DataTrackReceivedTabbedPane;
    private javax.swing.JPanel FromCcdPanel;
    private javax.swing.JPanel FromCorePanel;
    private javax.swing.JPanel FromGpsSerial;
    private javax.swing.JScrollPane GpsNmeaScrollPane;
    private javax.swing.JTextArea GpsNmeaTextArea;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton messageButton;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JScrollPane msgRecScrollPane;
    private javax.swing.JTextArea msgRecTextArea;
    private javax.swing.JLabel ownNpuLabel;
    private javax.swing.JTextField ownNpuTextField;
    private javax.swing.JPanel runPanel;
    private javax.swing.JButton settingButton;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusAppLabel;
    private javax.swing.JPanel statusPanel4;
    private javax.swing.JScrollPane statusScrollPane;
    private javax.swing.JTextArea statusTextArea;
    private javax.swing.JScrollPane tableRxScrollPane;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTable tableTrackRx;
    private javax.swing.JTable tableTrackTx;
    private javax.swing.JMenuBar tgMenuBar;
    private javax.swing.JSeparator tgSeparator;
    private javax.swing.JLabel titleLable;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel versionLable;
    // End of variables declaration//GEN-END:variables
}
