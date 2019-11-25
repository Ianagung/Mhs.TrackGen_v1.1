/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.connection;

/**
 *
 * @author riyanto
 */

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * 
 * This is SerialConnection class
 */
public class SerialConnection {

     /**
     * Reference variables
     */
    public SerialPort serialport;
    private String portName;
    private int baudrate;
    private boolean portOpen;
    final static int BAUDRATE = 2400;
    final static int FLOWCONTROL = SerialPort.FLOWCONTROL_NONE;
    final static int DATABITS = SerialPort.DATABITS_8;
    final static int STOPBITS = SerialPort.STOPBITS_1;
    final static int PARITY = SerialPort.PARITY_NONE;

    /**
     * @return the portOpen
     */
    public boolean isPortOpen() {
        return portOpen;
    }

    /**
     * @return the baud rate
     */
    public int getBaudrate() {
        return baudrate;
    }

    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * method to open connection
     * @param brate
     * @param portName
     */
    public void OpenConnection(int brate, String portName) {
        try {
            serialport = new SerialPort(portName);
            this.portName = portName;
            this.baudrate = brate;
            serialport.openPort();
            serialport.setParams(brate,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            portOpen = true;
        } catch (SerialPortException ex) {
            portOpen = false;
        }
    }

    /**
     * method to close connection
     */
    public void CloseConnection() {
        try {
            serialport.closePort();
            portOpen = false;
        } catch (SerialPortException ex) {
        }
    }
}