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

import jssc.SerialPortList;

/**
 * 
 * This is PortList class
 */
public class PortList {
    
    /**
     * Reference variable
     */
    private String[] portNames;

    /**
     *
     * @return portNames
     */
    public String[] getPortNames() {
        return portNames;
    }

    /**
     * method to set Port Names
     * @param portNames
     */
    public void setPortNames(String[] portNames) {
        this.portNames = portNames;
    }

    /**
     * method to get port list
     */
    public PortList(){
        portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){    
    }
    }
}