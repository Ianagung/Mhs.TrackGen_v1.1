/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.jssctester;

import javax.swing.JTextField;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author ian
 */

/**
 * 
 * This is Readdata class
 */
public final class Readdata implements SerialPortEventListener {

     /**
     * Reference variables
     */
    public SerialPort serialport;
    public boolean statdataavail;
    public String data;
    public byte buffer[];
    private GPSbuilder gpsbytebuilder;
    private JTextField form;
    private boolean stat_multicast; 
    
    /**
     *
     * @param serialport
     */
    public Readdata(SerialPort serialport){
        this.serialport = serialport;
        gpsbytebuilder = new GPSbuilder();
        setStat_multicast(false);
    }

    public Readdata() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     *
     * @param form
     */
    public void initform(JTextField form){
    this.form = form;
    }
    
    /**
     * 
     * @param event
     */
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                            if (this.serialport.getInputBufferBytesCount() > 0) {
                                byte[] data_serial_gps = this.serialport.readBytes();
                                for (int i = 0; i < data_serial_gps.length; i++) {
                                    byte byte_gps = data_serial_gps[i];
                                    rcv_gps(byte_gps);
                                }
                            }
                        } catch (SerialPortException ex) {
                        }
        }
        else if(event.isCTS()){
            if(event.getEventValue() == 1){
            }
            else {
            }
        }
        else if(event.isDSR()){
            if(event.getEventValue() == 1){
            }
            else {
            }
        }
    }

    /**
     * @return the stat_multicast
     */
    
    public boolean isStat_multicast() {
        return stat_multicast;
    }
     
    /**
     * Reference variables
     */
    private int ptr_serial = 0;
    byte[] buffer_serial = new byte[258];
    byte[] byte_rx = new byte[3];
    double longit;
    double latit;
    double spd;
    double crs;   
    int hgt;
    
    /**
     * method to receive data GPS 
     * @param byte_rcv 
     */
 private void rcv_gps(byte byte_rcv) {

        byte_rx[0] = byte_rx[1];
        byte_rx[1] = byte_rx[2];
        byte_rx[2] = byte_rcv;

        byte byte_tmp = byte_rx[0];
        
            if (byte_tmp == '$') {
                ptr_serial = 0;
            }

            if (byte_tmp == '*') {
                if( ((buffer_serial[1] == 'G')
                        && (buffer_serial[2] == 'P')
                        && (buffer_serial[3] == 'R')
                        && (buffer_serial[4] == 'M')
                        && (buffer_serial[5] == 'C')) ||  
                        ((buffer_serial[1] == 'G')
                        && (buffer_serial[2] == 'P')
                        && (buffer_serial[3] == 'G')
                        && (buffer_serial[4] == 'G')
                        && (buffer_serial[5] == 'A')) ){

                    buffer_serial[0] = '$';

                    if (ptr_serial > 16) {                                       
                        byte[] gps2 = new byte[ptr_serial];
                        System.arraycopy(buffer_serial, 0, gps2, 0, gps2.length);

                        byte xor = gps2[1];
                        for (int i = 2; i < gps2.length; i++) {
                            xor ^= gps2[i];
                        }

                        String s6 = new String(gps2);
                        
                            String gps_string[] = s6.split(",");

                            byte[] header_gps = gps_string[0].getBytes();

                            if ((header_gps[3] == 'R') && (header_gps[4] == 'M') && (header_gps[5] == 'C')) {

                                String slatitude = gps_string[3];
                                String slongitude = gps_string[5];
                                String sspeed = gps_string[7];
                                String scourse = gps_string[8];

                                double latitude = Double.valueOf(slatitude);
                                double degree = Math.floor(latitude / 100);
                                double minute = Math.floor(latitude) - (100 * degree);
                                double second = latitude - Math.floor(latitude);
                                double decimal_lat = degree + (minute / 60) + (second / 3600);
                                String NS = gps_string[4];
                                if (NS.equalsIgnoreCase("S")) {
                                    decimal_lat = -1 * decimal_lat;
                                }

                                double longitude = Double.valueOf(slongitude);
                                degree = Math.floor(longitude / 100);
                                minute = Math.floor(longitude) - (100 * degree);
                                second = longitude - Math.floor(longitude);
                                double decimal_long = degree + (minute / 60) + (second / 3600);
                                String EW = gps_string[6];
                                if (EW.equalsIgnoreCase("W")) {
                                    decimal_long = -1 * decimal_long;
                                }

                                double speed = Double.valueOf(sspeed);
                                double course = Double.valueOf(scourse);

                                longit = decimal_long;
                                latit = decimal_lat;
                                 spd = speed;
                                 crs = course;

                            }

                            if ((header_gps[3] == 'G') && (header_gps[4] == 'G') && (header_gps[5] == 'A')) {

                                String sheight = gps_string[9];
                                double height = Double.valueOf(sheight);
                                hgt = (int) height;

                            }

                            
                        byte [] gps3 =Trak_Compress( longit, latit, spd,  crs, hgt);                                               
                        String s_xor = String.format("%02X", xor);
                        byte[] bcrc = new byte[2];
                        bcrc[0] = byte_rx[1];
                        bcrc[1] = byte_rx[2];
                        String tmp = new String(bcrc);
                        if (s_xor.equalsIgnoreCase(tmp)) {
                            byte [] data_gps = new byte [gps3.length+1];
                            data_gps[0] = 1;
                            System.arraycopy(gps3, 0, data_gps, 1, gps3.length);
                        }
                    }
                } 
            } else {              
                buffer_serial[ptr_serial] = byte_tmp;
                ptr_serial = (ptr_serial + 1) & 0xff;
            }
    }
    /**
     * @param stat_multicast the stat_multicast to set
     */
    public void setStat_multicast(boolean stat_multicast) {
        this.stat_multicast = stat_multicast;
    }
    
    /**
     * method to compress tracks
     * @param longit
     * @param latit
     * @param spd
     * @param crs
     * @param hgt
     * @return trak_tmp
     */
    public byte[] Trak_Compress(double longit,double latit,double spd, double crs,int hgt){

        long longit1 = Math.round((longit / 360.0) * (double) 0xffffff);
        long latit1 = Math.round(latit * 0xffffff / 180.0);
        long spd1 = Math.round(spd * 0xffff / 4000.0);
        long crs1 = Math.round(crs * 0xffff / 360.0);
 
        byte[] trak_tmp = new byte[16];
        trak_tmp[0] = (byte) longit1;
        trak_tmp[1] = (byte) (longit1 >> 8);
        trak_tmp[2] = (byte) (longit1 >> 16);
        trak_tmp[3] = (byte) latit1;
        trak_tmp[4] = (byte) (latit1 >> 8);
        trak_tmp[5] = (byte) (latit1 >> 16);
        trak_tmp[6] = (byte) spd1;
        trak_tmp[7] = (byte) (spd1 >> 8);
        trak_tmp[8] = (byte) crs1;
        trak_tmp[9] = (byte) (crs1 >> 8);
        trak_tmp[10] = (byte) hgt;
        trak_tmp[11] = (byte) (hgt >> 8);
        trak_tmp[12] = (byte) 7;
        trak_tmp[13] = (byte) 0;
        trak_tmp[14] = (byte) 1;
        trak_tmp[15] = (byte) 0;
        
        return trak_tmp; 
    }
}