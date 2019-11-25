/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.messaging;

import co.id.len.tdl.variable.DataLink_Constanta;
import co.id.len.trackgenerator.main.TrakGenerator;
import co.id.len.tdl.common.StructCircle;
import co.id.len.tdl.common.StructPolyline;
import co.id.len.tdl.common.StructText;

/**
 *
 * @author Ud4
 */

/**
 * 
 * This is Message class
 */
public class Message {

     /**
     * Reference variables
     */
    private StructText structText = new StructText();
    private StructPolyline structPolyline = new StructPolyline();
    private StructCircle structCircle = new StructCircle();
    private byte[] msg_header = new byte[8];
    private byte[] property;
    private byte[] data_msg;
    private DataLink_Constanta DEF = new DataLink_Constanta();
    private String addrs;
    private int port;
    private TrakGenerator trakGenerator;

    /**
     *
     * @param mc_addr
     * @param mc_port
     */
    public Message(String mc_addr, int mc_port) {
        addrs = mc_addr;
        port = mc_port;
    }

    /**
     *
     * @param trakGenerator
     */
    public Message(TrakGenerator trakGenerator) {
        this.trakGenerator = trakGenerator;
    }

    /**
     * method to set connection
     * @param addressIP
     * @param Port
     * Unused Method
     */
    public void setConnection(String addressIP, int Port) {
        this.addrs = addressIP;
        this.port = Port;
    }

    /**
     * method to delete circle
     * @param circleNumber
     * @param source
     * @param destinationAddres
     */
    public void deletecircle(int circleNumber, int source, int destinationAddres) {

        byte[] deleteCircleMessage = new byte[DEF.HEADER_LENGTH + DEF.PACK_LENGTH];
        msg_header[0] = (byte) DEF.TOPIC_TG2CORE;
        msg_header[1] = (byte) DEF.TYPE_TX_MSG;
        msg_header[2] = (byte) DEF.MSG_TYPE_DEL_CIRCLE;
        msg_header[3] = (byte) source;
        msg_header[4] = (byte) destinationAddres;
        int msg_length = deleteCircleMessage.length - msg_header.length;
        msg_header[5] = (byte) ((msg_length >> 8) & 0xff);
        msg_header[6] = (byte) ((msg_length) & 0xff);
        System.arraycopy(msg_header, 0, deleteCircleMessage, 0, msg_header.length);
        System.arraycopy(structCircle.getBytesDeleteCircle(circleNumber), 0, deleteCircleMessage, DEF.HEADER_LENGTH, structCircle.getBytesDeleteCircle(circleNumber).length);
        trakGenerator.kirimData(deleteCircleMessage);
    }

    /**
     * method to delete polyline
     * @param PlineNumber
     * @param source
     * @param destinationAddres
     */
    public void deletepline(int PlineNumber, int source, int destinationAddres) {
        byte[] deletePlineMessage = new byte[DEF.HEADER_LENGTH + DEF.PACK_LENGTH];
        msg_header[0] = (byte) DEF.TOPIC_TG2CORE;
        msg_header[1] = (byte) DEF.TYPE_TX_MSG;
        msg_header[2] = (byte) DEF.MSG_TYPE_DEL_POLYLINE;
        msg_header[3] = (byte) source;
        msg_header[4] = (byte) destinationAddres;
        int msg_length = deletePlineMessage.length - msg_header.length;
        msg_header[5] = (byte) ((msg_length >> 8) & 0xff);
        msg_header[6] = (byte) ((msg_length) & 0xff);
        System.arraycopy(msg_header, 0, deletePlineMessage, 0, msg_header.length);
        System.arraycopy(structPolyline.getBytesDeletePolyline(PlineNumber), 0, deletePlineMessage, DEF.HEADER_LENGTH, structPolyline.getBytesDeletePolyline(PlineNumber).length);
        trakGenerator.kirimData(deletePlineMessage);
    }

    /**
     * method to send circle
     * @param crlData
     * @param crlNum
     * @param source
     * @param dest
     */
    public void send_circle(String crlData,int crlNum, int source, int dest) {
        byte[] circle_data;
        String[] crlSplit = crlData.split(",");
        if (crlSplit.length == 3) {
            circle_data = structCircle.getBytesCircle(crlData, crlNum);
            msg_header[0] = (byte) DEF.TOPIC_TG2CORE;
            msg_header[1] = (byte) DEF.TYPE_TX_MSG;
            msg_header[2] = (byte) DEF.MSG_TYPE_DRAW_CIRCLE;
            msg_header[3] = (byte) source;
            msg_header[4] = (byte) dest;
            msg_header[5] = (byte) ((circle_data.length >> 8) & 0xff);
            msg_header[6] = (byte) ((circle_data.length) & 0xff);
            data_msg = new byte[DEF.MSG_HDR_LENGTH + circle_data.length];
            System.arraycopy(msg_header, 0, data_msg, 0, DEF.MSG_HDR_LENGTH);
            System.arraycopy(circle_data, 0, data_msg, DEF.MSG_HDR_LENGTH, circle_data.length);
            getTrakGenerator().kirimData(data_msg);
        }
    }

    /**
     * method to send circle with properties
     * @param crlData
     * @param prop
     * @param notes
     * @param crlno
     * @param source
     * @param dest
     */
    public void sendCircleAndProperty(String crlData, byte[] prop, String notes, int crlno, int source, int dest) {
        byte[] circleData;
        String[] crlSplit = crlData.split(",");
        if (crlSplit.length == 3) {
            property = new byte[4 + notes.length()];
            property[3] = (byte) notes.length();
            System.arraycopy(prop, 0, property, 0, 3);
            System.arraycopy(notes.getBytes(), 0, property, 4, notes.length());
            circleData = structCircle.getBytesCircle(crlData, crlno, property);
            msg_header[0] = (byte) DEF.TOPIC_TG2CORE;
            msg_header[1] = (byte) DEF.TYPE_TX_MSG;
            msg_header[2] = (byte) DEF.MSG_TYPE_DRAW_CIRCLE;
            msg_header[3] = (byte) source;
            msg_header[4] = (byte) dest;
            int msg_length = circleData.length;
            msg_header[5] = (byte) ((msg_length >> 8) & 0xff);
            msg_header[6] = (byte) ((msg_length) & 0xff);
            data_msg = new byte[DEF.MSG_HDR_LENGTH + msg_length];
            System.arraycopy(msg_header, 0, data_msg, 0, DEF.MSG_HDR_LENGTH);
            System.arraycopy(circleData, 0, data_msg, DEF.MSG_HDR_LENGTH, msg_length);
            getTrakGenerator().kirimData(data_msg);
        }
    }

    /**
     * method to send text
     * @param text
     * @param source
     * @param dest
     */
    public void send_text(String text, int source, int dest) {
        byte[] text_ = structText.getBytesText(text, port);
        if (text_.length < DEF.MAX_TEXT_LENGTH) {
            msg_header[0] = (byte) DEF.TOPIC_TG2CORE;
            msg_header[1] = (byte) DEF.TYPE_TX_MSG;
            msg_header[2] = (byte) DEF.MSG_TYPE_TEXT;
            msg_header[3] = (byte) source;
            msg_header[4] = (byte) dest;
            msg_header[5] = (byte) ((text_.length >> 8) & 0xff);
            msg_header[6] = (byte) ((text_.length) & 0xff);
            data_msg = new byte[DEF.MSG_HDR_LENGTH + text_.length];
            System.arraycopy(msg_header, 0, data_msg, 0, DEF.MSG_HDR_LENGTH);
            System.arraycopy(text_, 0, data_msg, DEF.MSG_HDR_LENGTH, text_.length);
            getTrakGenerator().kirimData(data_msg);
        } else {
        }
    }

    /**
     * method to send file
     * @param file
     * @param source
     * @param destination
     */
    public void SendFile(byte[] file, int source, int destination) {
        msg_header[0] = (byte) DEF.TOPIC_TG2CORE;
        msg_header[1] = (byte) DEF.TYPE_TX_MSG;
        msg_header[2] = (byte) DEF.MSG_TYPE_FTP;
        msg_header[3] = (byte) source;
        msg_header[4] = (byte) destination;
        msg_header[5] = (byte) ((file.length >> 8) & 0xff);
        msg_header[6] = (byte) (file.length & 0xff);
        
        data_msg = new byte[DEF.MSG_HDR_LENGTH + file.length];
        System.arraycopy(msg_header, 0, data_msg, 0, DEF.MSG_HDR_LENGTH);
        System.arraycopy(file, 0, data_msg, DEF.MSG_HDR_LENGTH, file.length);
        trakGenerator.kirimData(data_msg);
    }

    /**
     * method to send polyline
     * @param plyData
     * @param plyNum
     * @param source
     * @param dest
     */
    public void sendPolyline(String plyData, int plyNum, int source, int dest) {
        byte[] poly_data;
        String[] plySplit = plyData.split(",");
        if (plySplit.length >= 4 && (plySplit.length % 2) == 0) {
            poly_data = structPolyline.getBytesPolyline(plyData, plyNum);
            msg_header[0] = (byte) DEF.TOPIC_TG2CORE;
            msg_header[1] = (byte) DEF.TYPE_TX_MSG;
            msg_header[2] = (byte) DEF.MSG_TYPE_DRAW_POLYLINE;
            msg_header[3] = (byte) source;
            msg_header[4] = (byte) dest;
            msg_header[5] = (byte) ((poly_data.length >> 8) & 0xff);
            msg_header[6] = (byte) (poly_data.length & 0xff);

            data_msg = new byte[DEF.MSG_HDR_LENGTH + poly_data.length];
            System.arraycopy(msg_header, 0, data_msg, 0, DEF.MSG_HDR_LENGTH);
            System.arraycopy(poly_data, 0, data_msg, DEF.MSG_HDR_LENGTH, poly_data.length);
            getTrakGenerator().kirimData(data_msg);
        }
    }

    /**
     * method to send polyline with properties
     * @param plyData
     * @param prop
     * @param notes
     * @param plyNum
     * @param source
     * @param dest
     */
    public void sendPolylineAndProperty(String plyData, byte[] prop, String notes, int plyNum, int source, int dest) {
        byte[] poly_data;
        String[] plySplit = plyData.split(",");
        if (plySplit.length >= 4 && (plySplit.length % 2) == 0) {

            property = new byte[6 + notes.length()];
            property[5] = (byte) notes.length();
            System.arraycopy(prop, 0, property, 0, prop.length);
            System.arraycopy(notes.getBytes(), 0, property, 6, notes.length());
            poly_data = structPolyline.getBytesPolyline(plyData, plyNum, property);
            msg_header[0] = (byte) DEF.TOPIC_TG2CORE;
            msg_header[1] = (byte) DEF.TYPE_TX_MSG;
            msg_header[2] = (byte) DEF.MSG_TYPE_DRAW_POLYLINE;
            msg_header[3] = (byte) source;
            msg_header[4] = (byte) dest;
            msg_header[5] = (byte) ((poly_data.length >> 8) & 0xff);
            msg_header[6] = (byte) (poly_data.length & 0xff);
            data_msg = new byte[DEF.MSG_HDR_LENGTH + poly_data.length];
            System.arraycopy(msg_header, 0, data_msg, 0, DEF.MSG_HDR_LENGTH);
            System.arraycopy(poly_data, 0, data_msg, DEF.MSG_HDR_LENGTH, poly_data.length);
            trakGenerator.kirimData(data_msg);
        }
    }

    /**
     * method to getTrakGenerator variables in TrakGenerator class
     * @return trakGenerator
     */
    public TrakGenerator getTrakGenerator(){
        return trakGenerator;
    }
    
    /**
     * method to setTrakGenerator variables in TrakGenerator class
     * @param trakGenerator
     */
    public void setTrakGenerator(TrakGenerator trakGenerator) {
        this.trakGenerator = trakGenerator;
    }

}