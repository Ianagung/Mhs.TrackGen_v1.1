/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.dataparser;

import co.id.len.tdl.common.StructTrack;
import co.id.len.tdl.variable.ModelVariabel;
import co.id.len.trackgenerator.main.TrakGenerator;
import co.id.len.trackgenerator.compresser.Compress32;
import co.id.len.trackgenerator.compresser.Compress16;
import co.id.len.trackgenerator.message.GpsNmeaRcvMessage;
import co.id.len.trackgenerator.message.GpsRcvMessage;
import java.net.UnknownHostException;
import org.len.ccad.messagecontroller.MessageController;

/**
 * GPS Parser
 *
 * @author yudha panji rahman
 */
/**
 *
 * This is GPSParser class
 */
public class GPSParser {

    /**
     * Variable reference*
     */
    Compress16 compress16;
    Compress32 compress32;
    double longit;
    double latit;
    double spd;
    double crs;
    int hgt;
    byte[] serialBuffer = new byte[258];
    byte[] byteRx = new byte[3];
    private int pointerSerial = 0;
//    private final TrakGenerator trackGenerator;
    private byte byteTemporary;
    private byte[] gps;
    private byte xor;
    byte[] header_gps;
    private String temporaryGpsData;
    private String slatitude;
    private String slongitude;
    private String sspeed;
    private String scourse;
    private double latitude;
    private double degree;
    private double minute;
    private double second;
    private double decimal_lat;
    private String NS;
    private double longitude;
    private double decimal_long;
    private String EW;
    private double speed;
    private double course;
    private String sheight;
    private double height;
    private byte[] gps3;
    private String s_xor;
    byte[] bcrc;
    String tmp;
    private MessageController messageController;
    private StructTrack structTrack;
    private ModelVariabel mod_var;

    /**
     * The Constructor
     *
     * @param trackGenerator Track Generator
     */
    public GPSParser(TrakGenerator trackGenerator) {
//        this.trackGenerator = trackGenerator;
        compress16 = new Compress16();
        compress32 = new Compress32();
    }

    public GPSParser(MessageController messageController1, ModelVariabel modelVariabel) {
        this.messageController = messageController1;
        this.mod_var = modelVariabel;
        compress16 = new Compress16();
        compress32 = new Compress32();
    }

    /**
     * GPS Data Parser Method
     *
     * @param receiveByte Receive Byte
     * @throws UnknownHostException
     */
    public void ReceiveGPS(byte receiveByte) throws UnknownHostException {
        byteRx[0] = byteRx[1];
        byteRx[1] = byteRx[2];
        byteRx[2] = receiveByte;
        byteTemporary = byteRx[0];
        if (byteTemporary == '$') {
            pointerSerial = 0;
        }
        if (byteTemporary == '*') {
            if (((serialBuffer[1] == 'G')
                    && (serialBuffer[2] == 'P')
                    && (serialBuffer[3] == 'R')
                    && (serialBuffer[4] == 'M')
                    && (serialBuffer[5] == 'C'))
                    || ((serialBuffer[1] == 'G')
                    && (serialBuffer[2] == 'P')
                    && (serialBuffer[3] == 'G')
                    && (serialBuffer[4] == 'G')
                    && (serialBuffer[5] == 'A'))) {
                serialBuffer[0] = '$';
                if (pointerSerial > 16) {
                    gps = new byte[pointerSerial];
                    System.arraycopy(serialBuffer, 0, gps, 0, gps.length);
                    xor = gps[1];
                    for (int i = 2; i < gps.length; i++) {
                        xor ^= gps[i];
                    }
                    temporaryGpsData = new String(gps);
                    String gps_string[] = temporaryGpsData.split(",");
                    header_gps = gps_string[0].getBytes();
                    if ((header_gps[3] == 'R') && (header_gps[4] == 'M') && (header_gps[5] == 'C')) {
                        slatitude = gps_string[3];
                        slongitude = gps_string[5];
                        sspeed = gps_string[7];
                        scourse = gps_string[8];

                        if (slatitude.isEmpty()) {
                            latitude = 0;
                        } else {
                            latitude = Double.valueOf(slatitude);
                        }

                        degree = Math.floor(latitude / 100);
                        minute = Math.floor(latitude) - (100 * degree);
                        second = latitude - Math.floor(latitude);
//                        decimal_lat = degree + (minute / 60) + (second / 3600);
                        decimal_lat = degree + ((minute + second) / 60);
                        NS = gps_string[4];
                        if (NS.equalsIgnoreCase("S")) {
                            decimal_lat = -1 * decimal_lat;
                        }

                        if (slongitude.isEmpty()) {
                            longitude = 0;
                        } else {
                            longitude = Double.valueOf(slongitude);
                        }

                        degree = Math.floor(longitude / 100);
                        minute = Math.floor(longitude) - (100 * degree);
                        second = longitude - Math.floor(longitude);
//                        decimal_long = degree + (minute / 60) + (second / 3600);
                        decimal_long = degree + ((minute+second )/ 60);
                        EW = gps_string[6];
                        if (EW.equalsIgnoreCase("W")) {
                            decimal_long = -1 * decimal_long;
                        }

                        if (sspeed.isEmpty()) {
                            speed = 0;
                        } else {
                            speed = Double.valueOf(sspeed);
                        }

                        if (scourse.isEmpty()) {
                            course = 0;
                        } else {
                            course = Double.valueOf(scourse);
                        }

                        longit = decimal_long;
                        latit = decimal_lat;
                        spd = speed;
                        crs = course;

                    }

                    if ((header_gps[3] == 'G') && (header_gps[4] == 'G') && (header_gps[5] == 'A')) {
                        sheight = gps_string[9];
                        if (sheight.isEmpty()) {
                            height = 0;
                        } else {
                            height = Double.valueOf(sheight);
                        }

                        hgt = (int) height;
                    }

                    s_xor = String.format("%02X", xor);
                    bcrc = new byte[2];
                    bcrc[0] = byteRx[1];
                    bcrc[1] = byteRx[2];
                    tmp = new String(bcrc);

                    if (s_xor.equalsIgnoreCase(tmp)) {
//                        gps3 = compress32.Trak_Compress(longit, latit, spd, crs, hgt, 1, 0, 0);
//                        trackGenerator.insert_data(gps3, 0);
                        //Data diubah ke bentuk Struck Track untuk kemudian dikirim via Message Controller
                        //harus diubah asal NPU
                        structTrack = new StructTrack();
                        structTrack.setCourse(crs);
                        structTrack.setHeight(hgt);
                        structTrack.setLatitude(latit);
                        structTrack.setLongitude(longit);
                        structTrack.setSpeed(spd);
                        structTrack.setNumber(0);
                        structTrack.setMmsi(0);
                        structTrack.setOwner(mod_var.getOwnNpu());
                        structTrack.setAttribute(123);

                        //Data bentuk Struck Track utk ke CCD dan Core
                        messageController.produceMessage(new GpsRcvMessage(structTrack.getBytesTrack()));

                        //Data bentuk String NMEA GPS ke Core
                        String gpsNmea = new String(gps) + "*" + tmp;
                        //data GPRMC atau GPGGA
                        byte[] dataGpsNmea = gpsNmea.getBytes();
                        messageController.produceMessage(new GpsNmeaRcvMessage(dataGpsNmea));
                    }
                }
            }

        } else {

            serialBuffer[pointerSerial] = byteTemporary;
            pointerSerial = (pointerSerial + 1) & 0xff;

        }
    }
}
