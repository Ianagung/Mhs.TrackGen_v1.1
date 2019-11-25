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
 * This is Writedata class
 */
public class Writedata {

     /**
     * Reference variable
     */
    public SerialPort serialport;
    
    /**
     *
     * @param serialport
     * @param data
     */
    public void write(SerialPort serialport,String data){
        
        this.serialport = serialport;
        try {
            this.serialport.writeBytes(data.getBytes());
        } catch (SerialPortException ex) {
            Logger.getLogger(Writedata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}