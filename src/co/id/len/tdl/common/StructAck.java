/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.tdl.common;

import co.id.len.tdl.variable.DataLink_Constanta;
import co.id.len.tdl.tools.crc16_ccitt;

/**
 *
 * @author datalink1
 */

/**
 * 
 * This is StructAck class
 */
public class StructAck {
    
     /**
     * Reference Variables
     */
    private boolean valid;
    private int msgType = 0;
    private int objNumber = 0;
    private int ack;
    private final DataLink_Constanta DEF = new DataLink_Constanta();
    private final crc16_ccitt crc16 = new crc16_ccitt();
    
    /**
     * Method to call StructAck class
     */
    public StructAck() {}
    
    /**
     * Method to checking crc acknowledge of data
     * @param data
     */
    public StructAck(byte[] data) {
        
        int data_length = data.length;
        byte[] crc = crc16.compute(data, 0, data_length - 2);
        
        if ( (crc[0] == data[data_length-2]) && (crc[1] == data[data_length-1])) {
        
            
            msgType = (data[0]& 0xFF) * 256 + (data[1]& 0xFF);
            objNumber = (data[2]& 0xFF) * 256 + (data[3]& 0xFF);
            ack = (int) data[4];
            valid = true;
        }
        else
        {
            valid = false;
        }
    }
    
    /**
     * Method to get bytes of Acknowledge data
     * @param ack
     * @param message_type
     * @param object_number
     * @return dataAck
     */
    public byte[] getBytesAck(int ack, int message_type, int object_number) {        
        
        int pack_length = DEF.PACK_LENGTH;
        byte[] dataAck = new byte[pack_length];
        
        dataAck[0] = (byte) ( (message_type >> 8) & 0xFF);
        dataAck[1] = (byte) ( message_type & 0xFF);
        dataAck[2] = (byte) ( (object_number >> 8) & 0xFF);
        dataAck[3] = (byte) ( object_number & 0xFF);
        dataAck[4] = (byte) ack;
        byte[] crc = crc16.compute(dataAck, 0, pack_length - 2);
        dataAck[pack_length - 2] = crc[0];
        dataAck[pack_length - 1] = crc[1];
        
        return dataAck;
    }

    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @return the msgType
     */
    public int getMsgType() {
        return msgType;
    }

    /**
     * @return the objNumber
     */
    public int getObjNumber() {
        return objNumber;
    }

    /**
     * @return the ack
     */
    public int getAck() {
        return ack;
    }
}