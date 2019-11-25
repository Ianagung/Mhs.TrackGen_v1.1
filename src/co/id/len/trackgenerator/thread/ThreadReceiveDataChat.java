/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.thread;

import co.id.len.tdl.common.StruckModelTrack;
import co.id.len.tdl.common.StructHeader;
import co.id.len.tdl.common.StructText;
import co.id.len.tdl.common.StructTrack;
import co.id.len.trackgenerator.dataparser.AISParser;
import co.id.len.trackgenerator.dataparser.GPSParser;
import co.id.len.trackgenerator.dataparser.RadarParser;
import co.id.len.tdl.variable.DataLink_Constanta;
import co.id.len.tdl.variable.ModelVariabel;
import co.id.len.trackgenerator.main.TrakForm;
import java.net.UnknownHostException;
import co.id.len.trackgenerator.main.TrakGenerator;
import co.id.len.trackgenerator.message.UdpRcvFromCcdFileMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdTextMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCcdTrackMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.len.ccad.messagecontroller.MessageController;

/**
 * Thread Receive Data
 *
 * @author Yudha Panji Rahman
 * @author Ian Agung(2019)
 */
/**
 *
 * This is Thread Receive Data class
 */
public class ThreadReceiveDataChat implements Runnable {

    /**
     * Reference variables
     */
    private final ModelVariabel modelVariable;
    private final TrakGenerator trackGenerator;
//    private final AISParser aisParser;
//    private final GPSParser gpsParser;
//    private final RadarParser radarParser;
    private final String threadName;
    public Thread thrd;
    private final DataLink_Constanta DEF = new DataLink_Constanta();
    private final boolean send = true;
    int index;
    byte[] fixedTrack = new byte[32];
    int ntrak;
    byte[] one_trak;
    byte[] data = new byte[1024 * 60];
    byte[] dataToSend = new byte[256];
    private DatagramPacket inPacket;
    private DatagramSocket socketUdpRx;
    private int realDataLength;
    private InetAddress address;
    private final MessageController messageController;
    private boolean statDataAck = false;
    static TrakForm trakFormm;
    public int get_NPU;
    private StructText structText = new StructText();
    private StructHeader structHeader = new StructHeader();
    private StructTrack structTrack = new StructTrack();
    /**
     * Reference variables
     */
    byte[] databyte;
    byte[] messageData;
    byte[] headerMessage = new byte[8];
    byte[] Bytestrack;

    public ThreadReceiveDataChat(ModelVariabel modVar, TrakGenerator tG, MessageController messageController1) {
        threadName = "Received";
        this.modelVariable = modVar;
        this.trackGenerator = tG;
        setSocket(modelVariable.getPortUdpRxChat());
        this.messageController = messageController1;
//        aisParser = new AISParser(trackGenerator);
//        gpsParser = new GPSParser(trackGenerator);
//        radarParser = new RadarParser(trackGenerator);
    }

    /**
     * method to running thread process of thread receive data
     */
    @Override
    public void run() {

        while (send) {
            try {
                //                Thread.sleep(500);
                //Check Data Received or not
                socketUdpRx.receive(inPacket);
            } catch (IOException ex) {
                Logger.getLogger(ThreadReceiveDataChat.class.getName()).log(Level.SEVERE, null, ex);
            }
            realDataLength = inPacket.getLength();
            databyte = new byte[realDataLength];
//            databyte = inPacket.getData();
            System.arraycopy(data, 0, databyte, 0, realDataLength);
            messageData = new byte[realDataLength - 16];
            System.arraycopy(databyte, 16, messageData, 0, messageData.length);
            structHeader = new StructHeader(databyte);
            System.out.println("Topic = " + structHeader.getTopic());
            if (structHeader.getTopic() == DEF.TOPIC_CCD2TG) {
                if (structHeader.getType_msg() == DEF.TYPE_TX_MSG && structHeader.getType_sub_msg() == DEF.MSG_TYPE_CHAT) {
//
                    int source = structHeader.getSource();
                    int destination = structHeader.getDestination();

                    byte[] udpPacket = new byte[8 + messageData.length];

                    udpPacket[0] = (byte) DEF.TOPIC_TG2CORE;
                    udpPacket[1] = (byte) DEF.TYPE_TX_MSG;
                    udpPacket[2] = (byte) DEF.MSG_TYPE_CHAT;
                    udpPacket[3] = (byte) source;
                    udpPacket[4] = (byte) destination;

                    System.arraycopy(messageData, 0, udpPacket, 8, messageData.length);
                    structText = new StructText(messageData);

                    System.out.println("message adalah " + structText.getStext());
                    trackGenerator.kirimData(udpPacket);

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCcdTextMessage(messageData));
                }
                
//                if (structHeader.getType_msg() == DEF.TYPE_TX_MSG && structHeader.getType_sub_msg() == DEF.MSG_TYPE_FTP) {
////
//                    int source = structHeader.getSource();
//                    int destination = structHeader.getDestination();
//
//                    byte[] udpPacket = new byte[8 + messageData.length];
//
//                    udpPacket[0] = (byte) DEF.TOPIC_TG2CORE;
//                    udpPacket[1] = (byte) DEF.TYPE_TX_MSG;
//                    udpPacket[2] = (byte) DEF.MSG_TYPE_FTP;
//                    udpPacket[3] = (byte) source;
//                    udpPacket[4] = (byte) destination;
//
//                    System.arraycopy(messageData, 0, udpPacket, 8, messageData.length);
//                    structText = new StructText(messageData);
//
//                    System.out.println("message adalah " + structText.getStext());
//                    trackGenerator.kirimData(udpPacket);
//
//                    //Data ditampilkan ke text Area
//                    messageController.produceMessage(new UdpRcvFromCcdFileMessage(messageData));
//                }
            }
        }
    }

    public byte[] setDataTrackReadySend() {

        byte[] dataReadyToSend;
        int ntrak = modelVariable.getBuffer_trak_tx_size();
        byte[] fixed_trak_tx = new byte[ntrak * 32];
        for (int i = 0; i < ntrak; i++) {
            one_trak = (byte[]) modelVariable.getBuffer_trak_tx().get(i).getData();
            System.arraycopy(one_trak, 0, fixed_trak_tx, i * 32, one_trak.length);
        }

        dataReadyToSend = new byte[fixed_trak_tx.length + 8];
        dataReadyToSend[0] = (byte) DEF.TOPIC_TG2CORE;
        dataReadyToSend[1] = (byte) DEF.TYPE_TX_TRACK;
        dataReadyToSend[3] = (byte) ntrak;
        System.arraycopy(fixed_trak_tx, 0, dataReadyToSend, 8, fixed_trak_tx.length);

        return dataReadyToSend;
    }

    /**
     * method to start the thread
     */
    public void start() {
        if (thrd == null) {
            thrd = new Thread(this, threadName);
            thrd.start();
        }
    }

    /**
     * method to stop the thread
     */
    public void stop() {
        if (thrd != null) {
            thrd.stop();
        } else {

        }

    }

    /**
     * @return the statDataAck
     */
    public boolean isStatDataAck() {
        return statDataAck;
    }

    /**
     *
     * @param port Udp Rx
     */
    public void setSocket(int port) {
        try {

            socketUdpRx = new DatagramSocket(port);

        } catch (SocketException ex) {
            Logger.getLogger(ThreadReceiveDataChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        inPacket = null;
        inPacket = new DatagramPacket(data, data.length, this.address, port);
    }

    private void checkNoTrack(StruckModelTrack struckModelTrack) {
        int ntrak = modelVariable.getBuffer_trak_tx_size();

        int noTrack;
        for (int i = 0; i < ntrak; i++) {
            noTrack = modelVariable.getBuffer_trak_tx().get(i).getTrackNumber();
            if (noTrack == struckModelTrack.getTrackNumber()) {
                modelVariable.getBuffer_trak_tx().remove(i);
            }

        }
    }

}
