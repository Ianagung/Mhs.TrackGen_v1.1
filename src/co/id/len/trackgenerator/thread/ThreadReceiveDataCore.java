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
import co.id.len.tdl.common.StructAck;
import co.id.len.tdl.common.StructHeader;
import co.id.len.tdl.common.StructText;
import co.id.len.tdl.common.StructTrack;
//import co.id.len.trackgenerator.dataparser.AISParser;
//import co.id.len.trackgenerator.dataparser.GPSParser;
//import co.id.len.trackgenerator.dataparser.RadarParser;
import co.id.len.tdl.variable.DataLink_Constanta;
import co.id.len.tdl.variable.ModelVariabel;
import co.id.len.trackgenerator.main.TrakForm;
//import java.net.UnknownHostException;
import co.id.len.trackgenerator.main.TrakGenerator;
//import co.id.len.trackgenerator.message.UdpRcvFromCcdTextMessage;
//import co.id.len.trackgenerator.message.UdpRcvFromCcdTrackMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreAckMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreCircleMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreFileMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCorePolylineMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreTextMessage;
import co.id.len.trackgenerator.message.UdpRcvFromCoreTrackMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
//import java.util.ArrayList;
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
public class ThreadReceiveDataCore implements Runnable {

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
    byte[] data = new byte[1024 * 64];
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
    private StructAck structAck = new StructAck();
    private boolean statChat = false;
    /**
     * Reference variables
     */
    byte[] databyte;
    byte[] messageData;
    byte[] headerMessage = new byte[8];
    byte[] Bytestrack;

    public ThreadReceiveDataCore(ModelVariabel modVar, TrakGenerator tG, MessageController messageController1) {
        threadName = "ReceivedFromCore";
        this.modelVariable = modVar;
        this.trackGenerator = tG;
        setSocket(modelVariable.getPortUdpRxCore());
        this.messageController = messageController1;
//        aisParser = new AISParser(trackGenerator);
//        gpsParser = new GPSParser(trackGenerator);
//        radarParser = new RadarParser(trackGenerator);
        this.statChat = modelVariable.isStatUdpTxChat();
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
                Logger.getLogger(ThreadReceiveDataCore.class.getName()).log(Level.SEVERE, null, ex);
            }
            realDataLength = inPacket.getLength();
            databyte = new byte[realDataLength];
//            databyte = inPacket.getData();
            System.arraycopy(data, 0, databyte, 0, realDataLength);
            messageData = new byte[databyte.length - 8];
            System.arraycopy(databyte, 8, messageData, 0, messageData.length);
            structHeader = new StructHeader();
            System.out.println("Data UDP berhasil diterima");
            System.out.println("Panjang paket = " + databyte.length);
//            System.out.println("Topic = " + structHeader.getTopic());
//            if (structHeader.getTopic() == DEF.TOPIC_CCD2TG) {
            if (databyte[0] == DEF.TOPIC_CORE2TG) {
                //Receive File Chat
                if ((databyte[1] == DEF.TYPE_RX_MSG) && (databyte[2] == DEF.MSG_TYPE_CHAT)) {

//                    byte[] pesan = new byte[messageData.length - 8];
                    byte[] pesan = new byte[messageData.length];
//                    byte[] header2 = new byte[8];
                    System.arraycopy(messageData, 0, pesan, 0, pesan.length);
//                    System.arraycopy(messageData, 0, header2, 0, header2.length);
                    System.out.println("Panjang messageData = " + messageData.length);
                    System.out.println("Panjang pesan = " + pesan.length);
                    structText = new StructText(pesan);
                    int source = databyte[3];
                    int destination = databyte[4];
                    byte[] udpPacket = new byte[16 + pesan.length];
                    byte[] header = new byte[16];

                    header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_CHAT);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
//                    System.arraycopy(header2, 0, udpPacket, 16, header2.length);
                    System.arraycopy(pesan, 0, udpPacket, 16, pesan.length);
                    // Data Dikirim via UDP ke CCD
                    System.out.println("message adalah " + structText.getStext());
//                    System.out.println("message adalah " + new String(pesan));
                    //rule message
                    //check bisa kirim atau tidak berdasarkan
                    //1. broadcast dari wasdal dan diterima semua
                    //2. broadcast dari komandan dan diterima grup sendiri
                    //3. broadcast dari anggota tidak bisa
                    //4. point to point dari anggota ke wasdal bisa
                    //5. point to point dari anggota ke komandan bisa asal 1 grup
                    //6. point to point dari anggota ke anggota bisa asal 1 grup
                    //7. point to point dari komandan ke komandan tidak bisa, syarat 1 grup
                    //8. point to point dari komandan ke wasdal bisa
                    //9. point to point dari komandan ke anggota bisa asal 1 grup
                    //10. point to point dari wasdal ke anggota bisa
                    //10. point to point dari wasdal ke komandan bisa
                    if ((destination == 0 && checkStatusGrupKapal())
                            || modelVariable.getIdentitasKapal().equalsIgnoreCase("wasit")
                            || (modelVariable.getIdentitasKapal().equalsIgnoreCase("komandan") && checkStatusGrupKapal())
                            || (destination == modelVariable.getOwnNpu() && checkStatusGrupKapal())) {

                        trackGenerator.kirimDataToChat(udpPacket);

                    }

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCoreTextMessage(udpPacket));
                }
                // Receive File Text
                if ((databyte[1] == DEF.TYPE_RX_MSG) && (databyte[2] == DEF.MSG_TYPE_TEXT)) {

//                    byte[] pesan = new byte[messageData.length - 8];
                    byte[] pesan = new byte[messageData.length];
//                    byte[] header2 = new byte[8];
                    System.arraycopy(messageData, 0, pesan, 0, pesan.length);
//                    System.arraycopy(messageData, 0, header2, 0, header2.length);
                    System.out.println("Panjang messageData = " + messageData.length);
                    System.out.println("Panjang pesan = " + pesan.length);
                    structText = new StructText(pesan);
                    int source = databyte[3];
                    int destination = databyte[4];
                    byte[] udpPacket = new byte[16 + pesan.length];
                    byte[] header = new byte[16];

                    header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_TEXT);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
//                    System.arraycopy(header2, 0, udpPacket, 16, header2.length);
                    System.arraycopy(pesan, 0, udpPacket, 16, pesan.length);
                    // Data Dikirim via UDP ke CCD
                    System.out.println("message adalah " + structText.getStext());
//                    System.out.println("message adalah " + new String(pesan));
                    //rule message
                    //check bisa kirim atau tidak berdasarkan
                    //1. broadcast dari wasdal dan diterima semua
                    //2. broadcast dari komandan dan diterima grup sendiri
                    //3. broadcast dari anggota tidak bisa
                    //4. point to point dari anggota ke wasdal bisa
                    //5. point to point dari anggota ke komandan bisa asal 1 grup
                    //6. point to point dari anggota ke anggota bisa asal 1 grup
                    //7. point to point dari komandan ke komandan tidak bisa, syarat 1 grup
                    //8. point to point dari komandan ke wasdal bisa
                    //9. point to point dari komandan ke anggota bisa asal 1 grup
                    //10. point to point dari wasdal ke anggota bisa
                    //10. point to point dari wasdal ke komandan bisa
                    if ((destination == 0 && checkStatusGrupKapal())
                            || modelVariable.getIdentitasKapal().equalsIgnoreCase("wasit")
                            || (modelVariable.getIdentitasKapal().equalsIgnoreCase("komandan") && checkStatusGrupKapal())
                            || (destination == modelVariable.getOwnNpu() && checkStatusGrupKapal())) {

                        trackGenerator.kirimDataToCcd(udpPacket);
                    }

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCoreTextMessage(udpPacket));
                }

                //Receive File
                if (databyte[1] == DEF.TYPE_RX_MSG && databyte[2] == DEF.MSG_TYPE_FTP) {

                    byte[] pesan = new byte[messageData.length];
//                    byte[] header2 = new byte[8];
                    System.arraycopy(messageData, 0, pesan, 0, pesan.length);
//                    System.arraycopy(messageData, 0, header2, 0, header2.length);
                    System.out.println("Panjang messageData = " + messageData.length);
                    System.out.println("Panjang pesan = " + pesan.length);
                    structText = new StructText(pesan);
                    int source = databyte[3];
                    int destination = databyte[4];
                    byte[] udpPacket = new byte[16 + pesan.length];

                    byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_FTP);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
//                    System.arraycopy(header2, 0, udpPacket, 16, header2.length);
                    System.arraycopy(pesan, 0, udpPacket, 16, pesan.length);
                    // Data Dikirim via UDP ke CCD
//                    System.out.println("message adalah " + structText.getStext());
//                    System.out.println("message adalah " + new String(pesan));
                    //rule message
                    //check bisa kirim atau tidak berdasarkan
                    //1. broadcast dari wasdal dan diterima semua
                    //2. broadcast dari komandan dan diterima grup sendiri
                    //3. broadcast dari anggota tidak bisa
                    //4. point to point dari anggota ke wasdal bisa
                    //5. point to point dari anggota ke komandan bisa asal 1 grup
                    //6. point to point dari anggota ke anggota bisa asal 1 grup
                    //7. point to point dari komandan ke komandan tidak bisa, syarat 1 grup
                    //8. point to point dari komandan ke wasdal bisa
                    //9. point to point dari komandan ke anggota bisa asal 1 grup
                    //10. point to point dari wasdal ke anggota bisa
                    //10. point to point dari wasdal ke komandan bisa
                    if ((destination == 0 && checkStatusGrupKapal())
                            || modelVariable.getIdentitasKapal().equalsIgnoreCase("wasit")
                            || (modelVariable.getIdentitasKapal().equalsIgnoreCase("komandan") && checkStatusGrupKapal())
                            || (destination == modelVariable.getOwnNpu() && checkStatusGrupKapal())) {
                        trackGenerator.kirimDataToCcd(udpPacket);
                    }

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCoreFileMessage(udpPacket));
                }

                if (databyte[1] == DEF.TYPE_RX_MSG && databyte[2] == DEF.MSG_TYPE_ACK) {

                    byte[] pesan = new byte[messageData.length];
//                    byte[] header2 = new byte[8];
                    System.arraycopy(messageData, 0, pesan, 0, pesan.length);
//                    System.arraycopy(messageData, 0, header2, 0, header2.length);
                    System.out.println("Panjang messageData = " + messageData.length);
                    System.out.println("Panjang pesan = " + pesan.length);
                    structAck = new StructAck(pesan);
                    int source = databyte[3];
                    int destination = databyte[4];
                    byte[] udpPacket = new byte[16 + messageData.length];

                    byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_ACK);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
//                    System.arraycopy(header2, 0, udpPacket, 16, header2.length);
                    System.arraycopy(pesan, 0, udpPacket, 16, pesan.length);
//                    System.arraycopy(header, 0, udpPacket, 0, header.length);
//                    System.arraycopy(messageData, 0, udpPacket, 16, messageData.length);
                    // Data Dikirim via UDP ke CCD
                    System.out.println("message adalah " + structAck.getMsgType()
                            + " ACK status = " + structAck.getAck()
                            + " ACK no " + structAck.getObjNumber());
                    if (structAck.getMsgType() == DEF.MSG_TYPE_CHAT) {
                        trackGenerator.kirimDataToChat(udpPacket);
                    } else {
                        trackGenerator.kirimDataToCcd(udpPacket);
                    }

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCoreAckMessage(pesan));
                }

                if (databyte[1] == DEF.TYPE_RX_MSG && databyte[2] == DEF.MSG_TYPE_NACK) {

                    structText = new StructText(messageData);
                    int source = databyte[3];
                    int destination = modelVariable.getOwnNpu();
                    byte[] udpPacket = new byte[16 + messageData.length];

                    byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_NACK);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
                    System.arraycopy(messageData, 0, udpPacket, 16, messageData.length);
                    // Data Dikirim via UDP ke CCD
                    System.out.println("message adalah " + structText.getStext());
                    trackGenerator.kirimDataToCcd(udpPacket);

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCoreTextMessage(messageData));
                }

                if (databyte[1] == DEF.TYPE_RX_MSG && databyte[2] == DEF.MSG_TYPE_DEL_CIRCLE) {

                    int source = databyte[3];
                    int destination = modelVariable.getOwnNpu();
                    byte[] udpPacket = new byte[16 + messageData.length];

                    byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_DEL_CIRCLE);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
                    System.arraycopy(messageData, 0, udpPacket, 16, messageData.length);

                    // Data Dikirim via UDP ke CCD
                    trackGenerator.kirimDataToCcd(udpPacket);

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCoreCircleMessage(messageData));
                }

                if (databyte[1] == DEF.TYPE_RX_MSG && databyte[2] == DEF.MSG_TYPE_DEL_POLYLINE) {

                    int source = databyte[3];
                    int destination = modelVariable.getOwnNpu();
                    byte[] udpPacket = new byte[16 + messageData.length];

                    byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_DEL_POLYLINE);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
                    System.arraycopy(messageData, 0, udpPacket, 16, messageData.length);

                    // Data Dikirim via UDP ke CCD
                    trackGenerator.kirimDataToCcd(udpPacket);

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCorePolylineMessage(messageData));
                }

                if (databyte[1] == DEF.TYPE_RX_MSG && databyte[2] == DEF.MSG_TYPE_DRAW_CIRCLE) {

                    int source = databyte[3];
                    int destination = modelVariable.getOwnNpu();
                    byte[] udpPacket = new byte[16 + messageData.length];

                    byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_DRAW_CIRCLE);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
                    System.arraycopy(messageData, 0, udpPacket, 16, messageData.length);

                    // Data Dikirim via UDP ke CCD
                    trackGenerator.kirimDataToCcd(udpPacket);

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCoreCircleMessage(messageData));
                }

                if (databyte[1] == DEF.TYPE_RX_MSG && databyte[2] == DEF.MSG_TYPE_DRAW_POLYLINE) {

                    int source = databyte[3];
                    int destination = modelVariable.getOwnNpu();
                    byte[] udpPacket = new byte[16 + messageData.length];

                    byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_MSG, DEF.MSG_TYPE_DRAW_POLYLINE);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);
                    System.arraycopy(messageData, 0, udpPacket, 16, messageData.length);

                    // Data Dikirim via UDP ke CCD
                    trackGenerator.kirimDataToCcd(udpPacket);

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCorePolylineMessage(messageData));
                }
                //Receive All Track
                if (databyte[1] == DEF.TYPE_RX_TRACK) {

                    byte[] pesan = new byte[32];
                    int source = databyte[3];

                    int destination = modelVariable.getOwnNpu();
                    byte[] udpPacket = new byte[16 + pesan.length];

                    byte[] header = structHeader.getBytesHeader(source, destination, DEF.TOPIC_TG2CCD, DEF.TYPE_RX_TRACK, 0);

                    System.arraycopy(header, 0, udpPacket, 0, header.length);

                    System.arraycopy(messageData, 0, pesan, 0, pesan.length);
                    System.out.println("Panjang messageData = " + messageData.length);
                    System.out.println("Panjang pesan = " + pesan.length);

                    System.arraycopy(pesan, 0, udpPacket, 16, pesan.length);

                    structTrack = new StructTrack(pesan);
                    System.out.println("Data Track Diterima , No track =" + structTrack.getNumber() + " ;MMSI = " + structTrack.getMmsi());
                    System.out.println("Data Track Diterima , Lat =" + structTrack.getLatitude() + " ;Long = " + structTrack.getLongitude());

                    StruckModelTrack struckModelTrackFromCore = new StruckModelTrack(structTrack.getNumber(), pesan, structTrack.getOwner());

                    checkNoTrack(struckModelTrackFromCore);
                    //Simpan track ke ModVar
//                    if (structTrack.getNumber() == 0) {
//                        modelVariable.getBuffer_trax_rx().add(0, struckModelTrackFromCore);
//                    } else {
                    modelVariable.getBuffer_trax_rx().add(struckModelTrackFromCore);
//                    }

                    modelVariable.sortDataTrackByNpuFromCore();

                    //rule track
                    //check bisa kirim atau tidak berdasarkan
                    //1. wasdal bisa menerima track dari semua
                    //2. komandan bisa menerima track dari semua asal 1 grup
                    //3. anggota tidak bisa menerima track
//                    if (modelVariable.getIdentitasKapal().equalsIgnoreCase("wasit")
//                            || (modelVariable.getIdentitasKapal().equalsIgnoreCase("komandan") && checkStatusGrupKapal())) {
//                        // Data Dikirim via UDP ke CCD
//                        trackGenerator.kirimDataToCcd(udpPacket);
//                    }
                    //sebagai wasdal
                    if (modelVariable.getIdentitasKapal().equalsIgnoreCase("wasit")) {
                        // Data Dikirim via UDP ke CCD
                        trackGenerator.kirimDataToCcd(udpPacket);
                        System.out.println("Data dikirim k CCD krn sbg wasit");
                    } //                    else if (modelVariable.getIdentitasKapal().equalsIgnoreCase("komandan") && checkStatusGrupKapal()) {
                    //sebagai komandan se-grup
                    else if (modelVariable.getIdentitasKapal().equalsIgnoreCase("komandan")
                            && checkStatusSourceGrupKapal(String.valueOf(structTrack.getOwner()))) {
                        // Data Dikirim via UDP ke CCD
                        trackGenerator.kirimDataToCcd(udpPacket);
                        System.out.println("Data dikirim k CCD krn sbg Komandan");
                    } //sebagai anggota bisa menerima track wasdal dan komandan
                    //tapi tidak ditampilkan krn butuh ID
                    else if (checkStatusSourceWasdal(String.valueOf(structTrack.getOwner()))) {
                        // Data Dikirim via UDP ke CCD
                        trackGenerator.kirimDataToCcd(udpPacket);
                        System.out.println("Data wasdal dikirim k CCD krn sbg Anggota");
                    } else if (checkStatusSourceKomandan(String.valueOf(structTrack.getOwner()))) {
                        // Data Dikirim via UDP ke CCD
                        trackGenerator.kirimDataToCcd(udpPacket);
                        System.out.println("Data Komandan dikirim k CCD krn sbg Anggota");
                    }

                    //Data ditampilkan ke text Area
                    messageController.produceMessage(new UdpRcvFromCoreTrackMessage(pesan));

                }
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
            Logger.getLogger(ThreadReceiveDataCore.class.getName()).log(Level.SEVERE, null, ex);
        }

        inPacket = new DatagramPacket(data, data.length);
    }

    /**
     * Check Track jika NPU dan
     *
     * @param struckModelTrack
     */
    private void checkNoTrack(StruckModelTrack struckModelTrack) {
        int ntrak = modelVariable.getBufferRxSize();

        int noTrack;
        int noNpu;
        for (int i = 0; i < ntrak; i++) {
            noTrack = modelVariable.getBuffer_trax_rx().get(i).getTrackNumber();
            noNpu = modelVariable.getBuffer_trax_rx().get(i).getNpuNumber();
            if (noTrack == struckModelTrack.getTrackNumber()
                    && noNpu == struckModelTrack.getNpuNumber()) {
                modelVariable.getBuffer_trax_rx().remove(i);
                break;
            }

        }
    }

    private boolean checkStatusGrupKapal() {
        boolean bolehKirim = false;
        String ownNpu = String.valueOf(modelVariable.getOwnNpu());
        for (String strTemp : modelVariable.getGrupKapal()) {
            if (ownNpu.equalsIgnoreCase(strTemp)) {
                bolehKirim = true;
            }
        }
        return bolehKirim;
    }

    private boolean checkStatusSourceGrupKapal(String source) {
        boolean bolehKirim = false;

        for (String strTemp : modelVariable.getGrupKapal()) {
            if (source.equalsIgnoreCase(strTemp)) {
                bolehKirim = true;
            }
        }
        return bolehKirim;
    }

    private boolean checkStatusSourceWasdal(String source) {
        boolean bolehKirim = false;

//        for (String strTemp : modelVariable.getGrupKapal()) {
        String strTemp = modelVariable.getWasdal();
        if (source.equalsIgnoreCase(strTemp)) {
            bolehKirim = true;
        }
//        }
        return bolehKirim;
    }

    private boolean checkStatusSourceKomandan(String source) {
        boolean bolehKirim = false;

        String strTemp = modelVariable.getKomandan();
        String strTemp2 = modelVariable.getKomandan2();
        if (source.equalsIgnoreCase(strTemp)
                || source.equalsIgnoreCase(strTemp2)) {
            bolehKirim = true;
        }
        return bolehKirim;
    }

}
