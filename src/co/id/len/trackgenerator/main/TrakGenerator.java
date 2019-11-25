/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.main;

import co.id.len.tdl.variable.ModelVariabel;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Track Generator Class
 *
 * @author Yudha Panji Rahman
 * @author Faturrahman
 */
/**
 *
 * This is TrakGenerator class
 */
public class TrakGenerator {

    /**
     * Reference variables
     */
    public final int TYPE_TG_GPS = 1;
    public final int TYPE_TG_AIS = 2;
    public final int TYPE_TG_RADAR = 3;
    public final int TYPE_RX_TRACK = 4;
    public final int TYPE_TX_MSG = 5;
    public final int TYPE_RX_MSG = 6;
    public final int LENGTH_TOPIC = 8;
    public final int TOPIC_TG2CORE = 1;
    public final int TOPIC_CORE2TG = 2;
    public final int TOPIC_CCD2CORE = 3;
    public final int TOPIC_CORE2CCD = 4;
    public double longit = 180;
    public double latit = 90;
    public double spd = 50;
    public double crs = 360;
    public int hgt = 1000;
    public int attb = 1;
    private DatagramSocket socket = null;
    private DatagramPacket outPacket = null;
    private DatagramPacket outPacketToCcd = null;
    private DatagramPacket outPacketToChat = null;
    public int port;
    
    
    
    public int portToCcd;
    public InetAddress udpTxDestAddress;
    public InetAddress udpTxDestAddressToCcd;
    private InetAddress gpsTxDestAddressToCore;
    private InetAddress udpTxDestAddressToChat;
    private DatagramPacket outPacketGpsToCore = null;
    public double longit_tx = 180;
    public double latit_tx = 90;
    public double spd_tx = 50;
    public double crs_tx = 360;
    public int hgt_tx = 1000;
    public int attb_tx = 1;
    public String source;
    public int mmsi;
    public ModelVariabel mod_var;
    private DatagramSocket socketGps = null;
    private DatagramSocket socketToChat = null;
    private DatagramSocket socketToCcd = null;

    /**
     * @param model
     */
    public TrakGenerator(ModelVariabel model) {
        this.mod_var = model;
        try {
            this.socket = new DatagramSocket();
            this.socketToCcd = new DatagramSocket();
            this.socketGps = new DatagramSocket();
            this.socketToChat = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * method to get received data array
     */
    public void baca_array_rx() {
//        for (int i = 0; i < mod_var.getBuffer_trak_rx().length; i++) {
//        }
    }

    /**
     * method to get transmitted data array
     */
    public void baca_array_tx() {
        for (int i = 0; i < mod_var.getBuffer_trak_tx().size(); i++) {
        }
    }

    /**
     * method to update data received tracks
     *
     * @param data
     * @param index
     */
    public void insert_data(byte[] data, int index) {
//        mod_var.getBuffer_trak_rx()[index] = data;
    }
    /**
     * Reference variables
     */
    boolean dtdma_stop = false;
    byte[] fixed_trak_tx;
    int ntrak;
    byte[] one_trak;

    /**
     * method to get tracks TX
     *
     * @param traktx
     */
    public void read_trak_tx(byte[] traktx) {
        long longit1 = (traktx[0] & 0xff) + (traktx[1] & 0xff) * 0x100 + (traktx[2] & 0xff) * 0x10000 + (traktx[3] & 0xff) * 0x1000000;
        longit_tx = longit1 * 360.0 / 4294967295.0;
        if (longit_tx > 180) {
            longit_tx = longit_tx - 360;
        }
        long latit1 = (traktx[4] & 0xff) + (traktx[5] & 0xff) * 0x100 + (traktx[6] & 0xff) * 0x10000 + (traktx[7] & 0xff) * 0x1000000;
        latit_tx = latit1 * 180.0 / 4294967295.0;
        if (latit_tx > 90) {
            latit_tx = latit_tx - 180;
        }
        long spd1 = (traktx[8] & 0xff) + (traktx[9] & 0xff) * 0x100 + (traktx[10] & 0xff) * 0x10000;
        spd_tx = spd1 * 4000.0 / 0xffff;
        long crs1 = (traktx[11] & 0xff) + (traktx[12] & 0xff) * 0x100 + (traktx[13] & 0xff) * 0x10000;
        crs_tx = crs1 * 360.0 / 0xffff;
        hgt_tx = (traktx[14] & 0xff) + (traktx[15] & 0xff) * 0x100 + (traktx[16] & 0xff) * 0x10000;
        attb_tx = (traktx[17] & 0xff) + (traktx[18] & 0xff) * 0x100;
        switch (traktx[23]) {
            case (byte) 1:
                source = "GPS";
                break;
            case (byte) 2:
                source = "AIS";
                break;
            case (byte) 3:
                source = "RADAR";
                break;
        }
        mmsi = (traktx[24] & 0xff) + (traktx[25] & 0xff) * 0x100 + (traktx[26] & 0xff) * 0x10000 + (traktx[27] & 0xff) * 0x1000000;

    }

    /**
     * method to set UDP transmit Connection
     *
     * @param add
     * @param Port
     */
    public void setUDPTxConnection(String add, int Port) {
//        mod_var.setUdpPort(Port);
//        mod_var.setAddress(add);

        try {
            udpTxDestAddress = InetAddress.getByName(add);
            this.outPacket = new DatagramPacket("TEST".getBytes(), "TEST".getBytes().length, udpTxDestAddress, Port);

        } catch (UnknownHostException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * method to send data
     *
     * @param data
     */
    public void kirimData(byte[] data) {
        try {
            outPacket.setData(data);
            outPacket.setLength(data.length);
            this.socket.send(outPacket);
//            System.out.println("Data Terkirim");

        } catch (SocketException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * KIrim Data GPS To Core
     * @param data 
     */
    public void kirimGpsToCore(byte[] data) {
        try {
            outPacketGpsToCore.setData(data);
            outPacketGpsToCore.setLength(data.length);
            this.socketGps.send(outPacketGpsToCore);
//            System.out.println("Data Terkirim");

        } catch (SocketException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param add
     * @param Port
     */
    public void setUDPTxConnectionToCcd(String add, int Port) {
        mod_var.setPortUdpTxCcd(Port);
        mod_var.setAddressUdpTxCcd(add);

        try {
            udpTxDestAddressToCcd = InetAddress.getByName(add);
            this.outPacketToCcd = new DatagramPacket("TEST".getBytes(), "TEST".getBytes().length, udpTxDestAddressToCcd, Port);

        } catch (UnknownHostException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void setUDPTxConnectionToChat(String add, int Port) {

        try {
            udpTxDestAddressToChat = InetAddress.getByName(add);
            this.outPacketToChat = new DatagramPacket("TEST".getBytes(), "TEST".getBytes().length, udpTxDestAddressToChat, Port);

        } catch (UnknownHostException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setGpsTxConnectionToCore(String add, int Port) {

        try {
            gpsTxDestAddressToCore = InetAddress.getByName(add);
            this.outPacketGpsToCore = new DatagramPacket("TEST".getBytes(), "TEST".getBytes().length, gpsTxDestAddressToCore, Port);

        } catch (UnknownHostException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param data
     */
    public void kirimDataToCcd(byte[] data) {
        try {
            outPacketToCcd.setData(data);
            outPacketToCcd.setLength(data.length);
            this.socketToCcd.send(outPacketToCcd);

        } catch (SocketException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        /**
     *
     * @param data
     */
    public void kirimDataToChat(byte[] data) {
        try {
            outPacketToChat.setData(data);
            outPacketToChat.setLength(data.length);
            this.socketToChat.send(outPacketToChat);

        } catch (SocketException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TrakGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
