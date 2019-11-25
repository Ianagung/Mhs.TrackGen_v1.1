/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.connection;

import co.id.len.tdl.variable.ModelVariabel;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import co.id.len.trackgenerator.dataparser.GPSParser;
import co.id.len.trackgenerator.dataparser.AISParser;
import co.id.len.trackgenerator.dataparser.RadarParser;
import co.id.len.trackgenerator.main.TrakGenerator;
import org.len.ccad.messagecontroller.MessageController;

/**
 * Read Serial Class
 *
 * @author yudha panji rahman
 */
public class Readserial {

    /**
     * Reference variables
     */
    private GPSParser gps_thrd;
    private AISParser ais_thrd;
    private RadarParser radar_thrd;
    public String tis_mode;
    private SerialConnection conn_gps;
    double longit;
    double latit;
    double spd;
    double crs;
    int hgt;
    byte[] buffer_serial = new byte[258];
    byte[] byte_rx = new byte[3];
    private TrakGenerator trak_gen;
    public final int TOPIK_TIS2CORE = 1;
    public final int TOPIK_CORE2TIS = 2;
    public final int TOPIK_CC2CORE = 3;
    public final int TOPIK_CORE2CC = 4;
    private MessageController messageController;
    private ModelVariabel modelVariabel;

    /**
     * method to read serial connection
     *
     * @param trk
     * @param mode
     */
    public Readserial(TrakGenerator trk, String mode) {
        trak_gen = trk;
        switch (mode) {
            case "ais":
                ais_thrd = new AISParser(trak_gen);
                break;
            case "gps":
                gps_thrd = new GPSParser(trak_gen);
                break;
            case "radar":
                radar_thrd = new RadarParser(trak_gen);
                break;
        }
    }

    public Readserial(String mode, MessageController messageController1, ModelVariabel mod_var) {
        this.messageController = messageController1;
        this.modelVariabel = mod_var;
        switch (mode) {
            case "ais":
//                ais_thrd = new AISParser(trak_gen);
                break;
            case "gps":
//                gps_thrd = new GPSParser(trak_gen);
                gps_thrd = new GPSParser(messageController1, mod_var);
                break;
            case "radar":
//                radar_thrd = new RadarParser(trak_gen);
                break;
        }
    }

    /**
     * This method is used to get data from serial connection
     *
     * @param con_gps
     */
    public void initialKoneksi(SerialConnection con_gps) {
        try {

            this.conn_gps = con_gps;

            conn_gps.serialport.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(jssc.SerialPortEvent event) {
                    if (event.isRXCHAR() && event.getEventValue() > 0) {
                        try {
                            if (conn_gps.serialport.getInputBufferBytesCount() > 0) {
                                byte[] data_serial_gps = conn_gps.serialport.readBytes();
                                for (int i = 0; i < data_serial_gps.length; i++) {
                                    byte byte_data = data_serial_gps[i];

                                    switch (tis_mode) {
                                        case "RADAR":
                                            radar_thrd.rcv_radar(byte_data);
                                            break;
                                        case "GPS":
                                            gps_thrd.ReceiveGPS(byte_data);
//                                            System.out.println("Ada data GPS");
                                            break;
                                        case "AIS":
                                            ais_thrd.ReceiveAIS(byte_data);
                                            break;
                                    }

                                }
                            }
                        } catch (SerialPortException ex) {
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(Readserial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });

        } catch (SerialPortException ex) {

        }
    }
}
