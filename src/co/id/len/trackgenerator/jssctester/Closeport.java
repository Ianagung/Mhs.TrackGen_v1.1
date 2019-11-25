/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.jssctester;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author ian
 */

/**
 * 
 * This is Closeport class
 */
public class Closeport {

     /**
     * Reference variables
     */
    public SerialPort serialport;
    private boolean statuskoneksi;

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
     * method to close connection port
     * @param serialport
     */
    public void close(SerialPort serialport){
    this.serialport = serialport;
        try {
            this.serialport.closePort();
            setStatuskoneksi(true);
        } catch (SerialPortException ex) {
            setStatuskoneksi(false);
            Logger.getLogger(Closeport.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}