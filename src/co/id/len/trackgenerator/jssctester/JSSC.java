/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.jssctester;

import jssc.SerialPort;

/**
 *
 * @author ian
 */

/**
 * 
 * This is JSSC class
 */
public class JSSC {
     /**
     * Reference variables
     */
    public String port = "/dev/ttyUSB0";
    public SerialPort serialport;
    public Tester tampilan;
    private String[] portNames;

    /**
     *
     * @return portNames
     */
    public String[] getPortNames() {
        return portNames;
    }

    /**
     * method to set port names
     * @param portNames
     */
    public void setPortNames(String[] portNames) {
        this.portNames = portNames;
    }

    /**
     * method to create new portList serial
     */
    public JSSC(){
    PortList serial = new PortList();
    setPortNames(serial.getPortNames());
    }
    
    /**
     * method to show tester
     */
    public void view(){
        tampilan = new Tester(getPortNames());
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                tampilan.setVisible(true);
            }
        });
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        JSSC cubi = new JSSC();
        cubi.view();
    }
}