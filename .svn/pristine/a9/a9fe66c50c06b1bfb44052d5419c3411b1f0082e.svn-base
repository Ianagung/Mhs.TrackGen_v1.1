/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.jssctester;

/**
 *
 * @author ian
 */

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * 
 * This is Openport class
 */
public class Openport {
    
    /**
     * Reference variables
     */
    public SerialPort serialPort;
    public boolean statuskoneksi;

    /**
     * method to check connection status
     * @return statuskoneksi
     */
    public boolean isStatuskoneksi() {
        return statuskoneksi;
    }

    /**
     * method to set connection status
     * @param statuskoneksi
     */
    public void setStatuskoneksi(boolean statuskoneksi) {
        this.statuskoneksi = statuskoneksi;
    }
    
    /**
     * method to open connection port
     * @param port
     * @return getSerialPort()
     */
    public SerialPort open(String port){
    serialPort = new SerialPort(port);
    try {
        serialPort.openPort();
        serialPort.setParams(SerialPort.BAUDRATE_4800,
                             SerialPort.DATABITS_8,
                             SerialPort.STOPBITS_1,
                             SerialPort.PARITY_NONE);
        setStatuskoneksi(true);
    }
    catch (SerialPortException ex) {
        setStatuskoneksi(false);
    }
    return getSerialPort();
    }

    /**
     * method to get serial port
     * @return serialPort
     */
    public SerialPort getSerialPort() {
        return serialPort;
    }

    /**
     * method to set serial port
     * @param serialPort
     */
    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }
}