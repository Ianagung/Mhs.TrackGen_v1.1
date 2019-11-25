/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.tdl.variable;

import co.id.len.tdl.common.StruckModelCircle;
import co.id.len.tdl.common.StruckModelLine;
import co.id.len.tdl.common.StruckModelPolyline;
import co.id.len.tdl.common.StruckModelText;
import co.id.len.tdl.common.StruckModelTrack;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Yudha Panji Rahman
 * @author Ian Agung P.
 */
/**
 *
 * This is ModelVariabel class
 */
public class ModelVariabel {

    /**
     * Reference variables
     */
    private boolean mode_real;
    private ArrayList<StruckModelTrack> buffer_trak_tx = new ArrayList();
    private ArrayList<StruckModelPolyline> buffer_poly_tx = new ArrayList();
    private ArrayList<StruckModelLine> buffer_line_tx = new ArrayList();
    private ArrayList<StruckModelCircle> buffer_circle_tx = new ArrayList();
    private ArrayList<StruckModelText> buffer_msgtext_tx = new ArrayList();
    private ArrayList<StruckModelTrack> buffer_trax_rx = new ArrayList();
    private ArrayList<byte[]> bufer_trax_rx = new ArrayList();
    private ArrayList<StruckModelText> buffer_msgtext_rx = new ArrayList();
//    private byte[][] buffer_trak_rx = new byte[201][32];
    private int buffer_trak_tx_size;
    private int multicastPort = 0;
    private String multicastAddress = "";
    private int udpPort = 0;
    private String udpAddress = "";
    private boolean thrd_sendata;
    private boolean threadReceiveData;
    private int own_npu;

    //Connection Setting
    //UDP TX to Core Datalink
    private int portUdpTx;
    //UDP RX from Core Datalink
    private int portUdpRxCore;
    //UDP TX to Core Datalink - DataGpsNmea
    private int portGpsTx;    
    //UDP RX from CCD
    private int portUdpRx;
    //UDP TX to CCD Datalink
    private int portUdpTxCcd;
    //UDP RX from Chat
    private int portUdpRxChat;
    //UDP TX to Chat App
    private int portUdpTxChat;



    private String addressGpsTx;
    private String addressUdpTx;
    private String addressUdpTxCcd;
    private String addressUdpTxChat;
    //stat connection
    private boolean statUdpTx = false;
    private boolean statUdpRx = false;
    private boolean statUdpTxCcd = false;
    private boolean statUdpRxCore = false;
    private boolean statGpsTx = false;
    private boolean statUdpTxChat = false;
    private boolean statUdpRxChat = false;

    //Serial Connection Setting
    private String gpsPort;
    private int gpsBaud;
    private boolean statGpsRcv;

    //Keterangan Grup Kapal
    private String identitasKapal;
    private String[] grupKapal;

    /**
     * modelVariabel state
     */
    public ModelVariabel() {
        mode_real = false;
    }

    /**
     *
     * @param modelTrack
     * @return
     */
    public ArrayList<StruckModelTrack> sortDataTrack(ArrayList<StruckModelTrack> modelTrack) {

        Collections.sort(modelTrack, StruckModelTrack.trackNumComparator);

        return modelTrack;

    }

    /**
     * method to sort Data Track internally
     */
    public void sortDataTrack() {

        Collections.sort(getBuffer_trak_tx(), StruckModelTrack.trackNumComparator);

    }

    /**
     * method to sort Data Track internally
     */
    public void sortDataTrackFromCore() {

        Collections.sort(getBuffer_trax_rx(), StruckModelTrack.trackNumComparator);

    }

    public void sortDataTrackByNpuFromCore() {

        Collections.sort(getBuffer_trax_rx(), StruckModelTrack.npuNumComparator);

    }

    /**
     * method to sort Data Text Message internally
     */
    public void sortDataTextMsg() {

        Collections.sort(getBuffer_msgtext_tx(), StruckModelText.textNumComparator);

    }

    /**
     * method to sort Data Text Message internally
     */
    public void sortDataTextMsgFromCore() {

//        Collections.sort(getBuffer_msgtext_rx(), StruckModelText.textNumComparator);
        Collections.sort(getBuffer_msgtext_rx(), StruckModelText.textSourceComparator);

    }

    /**
     * method to sort Data Poly Message internally
     */
    public void sortDataPolyline() {

        Collections.sort(getBuffer_poly_tx(), StruckModelPolyline.polyNumComparator);

    }

    /**
     * method to sort Data Line Message internally
     */
    public void sortDataLine() {

        Collections.sort(getBuffer_line_tx(), StruckModelLine.lineNumComparator);

    }

    /**
     * method to sort Data Circle Message internally
     */
    public void sortDataCircle() {

        Collections.sort(getBuffer_circle_tx(), StruckModelCircle.circleNumComparator);

    }

    /**
     * method to set own NPU
     *
     * @param npu
     */
    public void setOwnNpu(int npu) {
        own_npu = npu;
    }

    /**
     * method to get own NPU
     *
     * @return own_npu
     */
    public int getOwnNpu() {
        return own_npu;
    }

//    /**
//     * method to reset buffer RX
//     */
//    public void resetBuffeRx() {
//        for (int i = 0; i < 201; i++) {
//            getBuffer_trak_rx()[i][29] = 0;
//        }
//    }
    /**
     * method to get buffer RX size
     *
     * @return 121
     */
    public int getBufferRxSize() {
        return this.buffer_trax_rx.size();
    }

    public int getBufferTxSize() {

        return this.buffer_trak_tx.size();
    }

    /**
     * method to reset buffer TX
     */
    public void resetBufferTx() {
        buffer_trak_tx.removeAll(buffer_trak_tx);
    }

    /**
     * @return the mode_real
     */
    public boolean isMode_real() {
        return mode_real;
    }

    /**
     * @param mode_real the mode_real to set
     */
    public void setMode_real(boolean mode_real) {
        this.mode_real = mode_real;
    }

    /**
     * @return the buffer_trak_tx
     */
    public ArrayList<StruckModelTrack> getBuffer_trak_tx() {
        return this.buffer_trak_tx;
    }

    /**
     * @param buffer_trak_tx the buffer_trak_tx to set
     */
    public void setBuffer_trak_tx(ArrayList<StruckModelTrack> buffer_trak_tx) {
        this.buffer_trak_tx = buffer_trak_tx;
    }

//    /**
//     * @return the buffer_trak_rx
//     */
//    public byte[][] getBuffer_trak_rx() {
//        return buffer_trak_rx;
//    }
//    /**
//     * @param buffer_trak_rx the buffer_trak_rx to set
//     */
//    public void setBuffer_trak_rx(byte[][] buffer_trak_rx) {
//        this.buffer_trak_rx = buffer_trak_rx;
//    }
    /**
     * @return the buffer_trak_tx_size
     */
    public int getBuffer_trak_tx_size() {
        buffer_trak_tx_size = buffer_trak_tx.size();
        return buffer_trak_tx_size;
    }

    /**
     * @param buffer_trak_tx_size the buffer_trak_tx_size to set
     */
    public void setBuffer_trak_tx_size(int buffer_trak_tx_size) {
        this.buffer_trak_tx_size = buffer_trak_tx_size;
    }

    /**
     * @return the multicastPort
     */
    public int getPort() {
        return multicastPort;
    }

    /**
     * @param port the multicastPort to set
     */
    public void setPort(int port) {
        this.multicastPort = port;
    }

    /**
     * @return the multicastAddress
     */
    public String getAddress() {
        return multicastAddress;
    }

    /**
     * @param address the multicastAddress to set
     */
    public void setAddress(String address) {
        this.multicastAddress = address;
    }

    /**
     * @return the thrd_sendata
     */
    public boolean isThrd_sendata() {
        return thrd_sendata;
    }

    /**
     * @param thrd_sendata the thrd_sendata to set
     */
    public void setThrd_sendata(boolean thrd_sendata) {
        this.thrd_sendata = thrd_sendata;
    }

    /**
     * @return the udpPort
     */
    public int getUdpPort() {
        return udpPort;
    }

    /**
     * @param udpPort the udpPort to set
     */
    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    /**
     * @return the udpAddress
     */
    public String getUdpAddress() {
        return udpAddress;
    }

    /**
     * @param udpAddress the udpAddress to set
     */
    public void setUdpAddress(String udpAddress) {
        this.udpAddress = udpAddress;
    }

    /**
     * @return the threadReceiveData
     */
    public boolean isThreadReceiveData() {
        return threadReceiveData;
    }

    /**
     * @param threadReceiveData the threadReceiveData to set
     */
    public void setThreadReceiveData(boolean threadReceiveData) {
        this.threadReceiveData = threadReceiveData;
    }

    /**
     * @return the portUdpTx
     */
    public int getPortUdpTx() {
        return portUdpTx;
    }

    /**
     * @param portUdpTx the portUdpTx to set
     */
    public void setPortUdpTx(int portUdpTx) {
        this.portUdpTx = portUdpTx;
    }

    /**
     * @return the addressUdpTx
     */
    public String getAddressUdpTx() {
        return addressUdpTx;
    }

    /**
     * @param addressUdpTx the addressUdpTx to set
     */
    public void setAddressUdpTx(String addressUdpTx) {
        this.addressUdpTx = addressUdpTx;
    }

    /**
     * @return the portUdpRx
     */
    public int getPortUdpRx() {
        return portUdpRx;
    }

    /**
     * @param portUdpRx the portUdpRx to set
     */
    public void setPortUdpRx(int portUdpRx) {
        this.portUdpRx = portUdpRx;
    }

    /**
     * @return the statUdpTx
     */
    public boolean isStatUdpTx() {
        return statUdpTx;
    }

    /**
     * @param statUdpTx the statUdpTx to set
     */
    public void setStatUdpTx(boolean statUdpTx) {
        this.statUdpTx = statUdpTx;
    }

    /**
     * @return the statUdpRx
     */
    public boolean isStatUdpRx() {
        return statUdpRx;
    }

    /**
     * @param statUdpRx the statUdpRx to set
     */
    public void setStatUdpRx(boolean statUdpRx) {
        this.statUdpRx = statUdpRx;
    }

    /**
     * @return the bufer_trax_rx
     */
    public ArrayList<byte[]> getBufer_trax_rx() {
        return bufer_trax_rx;
    }

    /**
     * @param bufer_trax_rx the bufer_trax_rx to set
     */
    public void setBufer_trax_rx(ArrayList<byte[]> bufer_trax_rx) {
        this.bufer_trax_rx = bufer_trax_rx;
    }

    /**
     * @return the buffer_msgtext_tx
     */
    public ArrayList<StruckModelText> getBuffer_msgtext_tx() {
        return buffer_msgtext_tx;
    }

    /**
     * @param buffer_msgtext_tx the buffer_msgtext_tx to set
     */
    public void setBuffer_msgtext_tx(ArrayList<StruckModelText> buffer_msgtext_tx) {
        this.buffer_msgtext_tx = buffer_msgtext_tx;
    }

    /**
     * @return the buffer_msgtext_rx
     */
    public ArrayList<StruckModelText> getBuffer_msgtext_rx() {
        return buffer_msgtext_rx;
    }

    /**
     * @param buffer_msgtext_rx the buffer_msgtext_rx to set
     */
    public void setBuffer_msgtext_rx(ArrayList<StruckModelText> buffer_msgtext_rx) {
        this.buffer_msgtext_rx = buffer_msgtext_rx;
    }

    /**
     * @return the buffer_poly_tx
     */
    public ArrayList<StruckModelPolyline> getBuffer_poly_tx() {
        return buffer_poly_tx;
    }

    /**
     * @param buffer_poly_tx the buffer_poly_tx to set
     */
    public void setBuffer_poly_tx(ArrayList<StruckModelPolyline> buffer_poly_tx) {
        this.buffer_poly_tx = buffer_poly_tx;
    }

    /**
     * @return the buffer_line_tx
     */
    public ArrayList<StruckModelLine> getBuffer_line_tx() {
        return buffer_line_tx;
    }

    /**
     * @param buffer_line_tx the buffer_line_tx to set
     */
    public void setBuffer_line_tx(ArrayList<StruckModelLine> buffer_line_tx) {
        this.buffer_line_tx = buffer_line_tx;
    }

    /**
     * @return the buffer_circle_tx
     */
    public ArrayList<StruckModelCircle> getBuffer_circle_tx() {
        return buffer_circle_tx;
    }

    /**
     * @param buffer_circle_tx the buffer_circle_tx to set
     */
    public void setBuffer_circle_tx(ArrayList<StruckModelCircle> buffer_circle_tx) {
        this.buffer_circle_tx = buffer_circle_tx;
    }

    /**
     * @return the portUdpTxCcd
     */
    public int getPortUdpTxCcd() {
        return portUdpTxCcd;
    }

    /**
     * @param portUdpTxCcd the portUdpTxCcd to set
     */
    public void setPortUdpTxCcd(int portUdpTxCcd) {
        this.portUdpTxCcd = portUdpTxCcd;
    }

    /**
     * @return the addressUdpTxCcd
     */
    public String getAddressUdpTxCcd() {
        return addressUdpTxCcd;
    }

    /**
     * @param addressUdpTxCcd the addressUdpTxCcd to set
     */
    public void setAddressUdpTxCcd(String addressUdpTxCcd) {
        this.addressUdpTxCcd = addressUdpTxCcd;
    }

    /**
     * @return the portUdpRxCore
     */
    public int getPortUdpRxCore() {
        return portUdpRxCore;
    }

    /**
     * @param portUdpRxCore the portUdpRxCore to set
     */
    public void setPortUdpRxCore(int portUdpRxCore) {
        this.portUdpRxCore = portUdpRxCore;
    }

    /**
     * @return the statUdpTxCcd
     */
    public boolean isStatUdpTxCcd() {
        return statUdpTxCcd;
    }

    /**
     * @param statUdpTxCcd the statUdpTxCcd to set
     */
    public void setStatUdpTxCcd(boolean statUdpTxCcd) {
        this.statUdpTxCcd = statUdpTxCcd;
    }

    /**
     * @return the statUdpRxCore
     */
    public boolean isStatUdpRxCore() {
        return statUdpRxCore;
    }

    /**
     * @param statUdpRxCore the statUdpRxCore to set
     */
    public void setStatUdpRxCore(boolean statUdpRxCore) {
        this.statUdpRxCore = statUdpRxCore;
    }

    /**
     * @return the buffer_trax_rx
     */
    public ArrayList<StruckModelTrack> getBuffer_trax_rx() {
        return buffer_trax_rx;
    }

    /**
     * @param buffer_trax_rx the buffer_trax_rx to set
     */
    public void setBuffer_trax_rx(ArrayList<StruckModelTrack> buffer_trax_rx) {
        this.buffer_trax_rx = buffer_trax_rx;
    }

    /**
     * @return the GpsPort
     */
    public String getGpsPort() {
        return gpsPort;
    }

    /**
     * @param GpsPort the GpsPort to set
     */
    public void setGpsPort(String GpsPort) {
        this.gpsPort = GpsPort;
    }

    /**
     * @return the gpsBaud
     */
    public int getGpsBaud() {
        return gpsBaud;
    }

    /**
     * @param gpsBaud the gpsBaud to set
     */
    public void setGpsBaud(int gpsBaud) {
        this.gpsBaud = gpsBaud;
    }

    /**
     * @return the statGpsRcv
     */
    public boolean isStatGpsRcv() {
        return statGpsRcv;
    }

    /**
     * @param statGpsRcv the statGpsRcv to set
     */
    public void setStatGpsRcv(boolean statGpsRcv) {
        this.statGpsRcv = statGpsRcv;
    }

    /**
     * @return the portGpsTx
     */
    public int getPortGpsTx() {
        return portGpsTx;
    }

    /**
     * @param portGpsTx the portGpsTx to set
     */
    public void setPortGpsTx(int portGpsTx) {
        this.portGpsTx = portGpsTx;
    }

    /**
     * @return the addressGpsTx
     */
    public String getAddressGpsTx() {
        return addressGpsTx;
    }

    /**
     * @param addressGpsTx the addressGpsTx to set
     */
    public void setAddressGpsTx(String addressGpsTx) {
        this.addressGpsTx = addressGpsTx;
    }

    /**
     * @return the statGpsTx
     */
    public boolean isStatGpsTx() {
        return statGpsTx;
    }

    /**
     * @param statGpsTx the statGpsTx to set
     */
    public void setStatGpsTx(boolean statGpsTx) {
        this.statGpsTx = statGpsTx;
    }

    /**
     * @return the identitasKapal
     */
    public String getIdentitasKapal() {
        return identitasKapal;
    }

    /**
     * @param identitasKapal the identitasKapal to set
     */
    public void setIdentitasKapal(String identitasKapal) {
        this.identitasKapal = identitasKapal;
    }

    /**
     * @return the grupKapal
     */
    public String[] getGrupKapal() {
        return grupKapal;
    }

    /**
     * @param grupKapal the grupKapal to set
     */
    public void setGrupKapal(String[] grupKapal) {
        this.grupKapal = grupKapal;
    }

    public String getWasdal() {
        return grupKapal[0];
    }

    public String getKomandan() {
        return grupKapal[1];
    }

    public String getKomandan2() {
        return grupKapal[2];
    }

    /**
     * @return the portUdpRxChat
     */
    public int getPortUdpRxChat() {
        return portUdpRxChat;
    }

    /**
     * @param portUdpRxChat the portUdpRxChat to set
     */
    public void setPortUdpRxChat(int portUdpRxChat) {
        this.portUdpRxChat = portUdpRxChat;
    }

    /**
     * @return the portUdpTxChat
     */
    public int getPortUdpTxChat() {
        return portUdpTxChat;
    }

    /**
     * @param portUdpTxChat the portUdpTxChat to set
     */
    public void setPortUdpTxChat(int portUdpTxChat) {
        this.portUdpTxChat = portUdpTxChat;
    }

    /**
     * @return the addressUdpTxChat
     */
    public String getAddressUdpTxChat() {
        return addressUdpTxChat;
    }

    /**
     * @param addressUdpTxChat the addressUdpTxChat to set
     */
    public void setAddressUdpTxChat(String addressUdpTxChat) {
        this.addressUdpTxChat = addressUdpTxChat;
    }

    /**
     * @return the statUdpTxChat
     */
    public boolean isStatUdpTxChat() {
        return statUdpTxChat;
    }

    /**
     * @param statUdpTxChat the statUdpTxChat to set
     */
    public void setStatUdpTxChat(boolean statUdpTxChat) {
        this.statUdpTxChat = statUdpTxChat;
    }

    /**
     * @return the statUdpRxChat
     */
    public boolean isStatUdpRxChat() {
        return statUdpRxChat;
    }

    /**
     * @param statUdpRxChat the statUdpRxChat to set
     */
    public void setStatUdpRxChat(boolean statUdpRxChat) {
        this.statUdpRxChat = statUdpRxChat;
    }
}
