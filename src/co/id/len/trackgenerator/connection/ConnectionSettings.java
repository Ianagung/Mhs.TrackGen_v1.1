/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.connection;

import co.id.len.trackgenerator.main.TrakGenerator;
import co.id.len.trackgenerator.thread.ThreadReceiveData;
import co.id.len.trackgenerator.thread.ThreadSendData;
import org.len.ccad.messagecontroller.MessageController;

/**
 * Connection settings class
 *
 * @author Yudha Panji Rahman
 */

public class ConnectionSettings {

     /**
     * Reference variables
     */
    public SerialConnection serialGPS = new SerialConnection();
    public SerialConnection serialRADAR = new SerialConnection();
    public SerialConnection serialAIS = new SerialConnection();
    private final Readserial read_gps;
    private final Readserial read_ais;
    private final Readserial read_radar;
//    private final ThreadReceiveData threadReceiveData;
//    private final ThreadReceiveData threadReceiveDataFeedBack;
    private final UdpTx udpTxFeedback;
    private final ThreadSendData threadSendData;
    private boolean receiveDataState;
    private final MessageController messageController;
    private boolean udpTxState;
    private boolean udpRxState;
    private boolean udpTxFeedbackState;
    private boolean udpRxFeedbackState;

    /**
     * @return the udpTxState
     */
    
    public boolean isUdpTxState() {
        return udpTxState;
    }

    /**
     * @param udpTxState the udpTxState to set
     */
    public void setUdpTxState(boolean udpTxState) {
        this.udpTxState = udpTxState;
    }

    /**
     * @return the udpRxState
     */
    public boolean isUdpRxState() {
        return udpRxState;
    }

    /**
     * @param udpRxState the udpRxState to set
     */
    public void setUdpRxState(boolean udpRxState) {
        this.udpRxState = udpRxState;
    }

    /**
     * @return the udpTxFeedbackState
     */
    public boolean isUdpTxFeedbackState() {
        return udpTxFeedbackState;
    }

    /**
     * @param udpTxFeedbackState the udpTxFeedbackState to set
     */
    public void setUdpTxFeedbackState(boolean udpTxFeedbackState) {
        this.udpTxFeedbackState = udpTxFeedbackState;
    }

    /**
     * @return the udpRxFeedbackState
     */
    public boolean isUdpRxFeedbackState() {
        return udpRxFeedbackState;
    }

    /**
     * @param udpRxFeedbackState the udpRxFeedbackState to set
     */
    public void setUdpRxFeedbackState(boolean udpRxFeedbackState) {
        this.udpRxFeedbackState = udpRxFeedbackState;
    }

    /**
     * @return the receiveDataState
     */
    public boolean isReceiveDataState() {
        return receiveDataState;
    }

    /**
     * @param receiveDataState the receiveDataState to set
     */
    public void setReceiveDataState(boolean receiveDataState) {
        this.receiveDataState = receiveDataState;
    }

    /**
     * The Constructor
     *
     * @param trakGenerator Track Generator
     * @param messageController message Controller
     */
    public ConnectionSettings(TrakGenerator trakGenerator, MessageController messageController) {
//        threadReceiveData = new ThreadReceiveData(trakGenerator);
//        threadReceiveDataFeedBack = new ThreadReceiveData(trakGenerator, messageController);
        udpTxFeedback = new UdpTx();
        this.messageController = messageController;
        threadSendData = new ThreadSendData(trakGenerator);
        read_gps = new Readserial(trakGenerator, "gps");
        read_ais = new Readserial(trakGenerator, "ais");
        read_radar = new Readserial(trakGenerator, "radar");
        setReceiveDataState(false);
    }

    /**
     * Set UDP TX Connection
     *
     * @param port port
     * @param address address
     */
    public void SetUDPtxConnection(int port, String address) {
        threadSendData.trak_gen.setUDPTxConnection(address, port);
        threadSendData.start();
    }

    /**
     * Set UDP RX Connection
     *
     * @param port port
     * @param address address
     */
    public void SetUdpRxConnection(int port, String address) {
        
//        threadReceiveData.setSocket(port, address);
//        threadReceiveData.start();
//        setReceiveDataState(true);
    }

    /**
     * Set UDP RX FeedBack Connection
     *
     * @param port port
     * @param address address
     */
    public void SetUDPRxFeedBackConnection(int port, String address) {
//        threadReceiveDataFeedBack.setSocket(port, address);
//        threadReceiveDataFeedBack.start();
    }

    /**
     * Set UDP TX FeedBack Connection
     *
     * @param port port
     * @param address address
     */
    public void SetUDPTxFeedBackConnection(int port, String address) {
        udpTxFeedback.setSocket(port, address);
    }

    /**
     * open GPS Connection
     *
     * @param baudRate Baud rate
     * @param portName Port name
     */
    public void openGPSConnection(int baudRate, String portName) {
        serialGPS.OpenConnection(baudRate, portName);
        read_gps.tis_mode = "GPS";
        read_gps.initialKoneksi(serialGPS);
    }

    /**
     * open AIS Connection
     *
     * @param baudRate Baud rate
     * @param portName Port name
     */
    public void openAISConnection(int baudRate, String portName) {
        serialAIS.OpenConnection(baudRate, portName);
        read_ais.tis_mode = "AIS";
        read_ais.initialKoneksi(serialAIS);
    }

    /**
     * open RADAR Connection
     *
     * @param baudRate Baud rate
     * @param portName Port name
     */
    public void openRADARConnection(int baudRate, String portName) {
        serialRADAR.OpenConnection(baudRate, portName);
        read_radar.tis_mode = "RADAR";
        read_radar.initialKoneksi(serialRADAR);
    }
}