/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author datalink2
 */

/**
 * 
 * This is UdpTx class
 */
public class UdpTx {
    
     /**
     * Reference variables
     */
    private DatagramSocket socketUdp;
    private DatagramPacket outPacket;
    private byte[] txBuf = new byte[2048];
    private InetAddress address;
    
    /**
     * The Constructor
     */
    public UdpTx()
    {
        try {
            socketUdp = new DatagramSocket();
        } catch (IOException ex) {
        }          
    }
   
     /**
     * Method to set socket IP Address and Port
     * @param port
     * @param add
     */
    public void setSocket(int port, String add) {
        try {
            address = InetAddress.getByName(add);
            outPacket = new DatagramPacket(txBuf, txBuf.length, address, port);
        } catch (IOException ex) {
        }
    }
        
    /**
     * Method to send packet data via UDP
     * @param data
     */
    public void sendUdp(byte[] data)
    {
        try {
            outPacket.setData(data);
            socketUdp.send(outPacket);
        } catch (IOException ioe) {
        }
    } 
}