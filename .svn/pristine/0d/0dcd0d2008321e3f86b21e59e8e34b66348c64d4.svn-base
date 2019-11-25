/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.dataparser;

import co.id.len.trackgenerator.main.TrakGenerator;
import co.id.len.trackgenerator.compresser.Compress32;
import co.id.len.trackgenerator.compresser.Compress16;
import java.net.UnknownHostException;

/**
 *
 * @author yudha
 */

/**
 * 
 * This is RadarParser class
 */
public class RadarParser {

     /**
     * Variable reference*
     */
    Compress16 cprs16;
    Compress32 cprs32;
    double longit;
    double latit;
    double spd;
    double crs;
    int hgt;
    byte[] buffer_serial = new byte[258];
    byte[] byte_rx = new byte[3];
    private int ptr_serial = 0;
    private final TrakGenerator trak_gen;
    private int no_idx;
    private int nomor_kapal;
    private int[] idx_trak_radar;
    private int ptr_data_radar;
    private int jml_aktual;
    byte byte_tmp;
    byte[] rdr;
    byte xor;
    String s6;
    byte[] header_gps;
    String slatitude;
    String slongitude;
    double latitude;
    double degree;
    double minute;
    double second;
    double decimal_lat;
    String NS;
    double longitude;
    double decimal_long;
    String EW;
    String Speed;
    String satuan_speed;
    String course;
    byte[] rdr2;
    String s_xor;
    byte[] bcrc;
    String tmp;
    boolean dataBaru;

    /**
     * The Constructor
     * @param TRAK
     */
    public RadarParser(TrakGenerator TRAK) {
        
        this.idx_trak_radar = new int[100];
        no_idx = 0;
        trak_gen = TRAK;
        cprs16 = new Compress16();
        cprs32 = new Compress32();
    }

    /**
     * Parser Radar Data Method
     * @param byte_rcv
     * @throws UnknownHostException
     */
    public void rcv_radar(byte byte_rcv) throws UnknownHostException {

        byte_rx[0] = byte_rx[1];
        byte_rx[1] = byte_rx[2];
        byte_rx[2] = byte_rcv;

        byte_tmp = byte_rx[0];

        if (byte_tmp == '$') {
            ptr_serial = 0;
        }

        if (byte_tmp == '*') {
            if (((buffer_serial[1] == 'R')
                    && (buffer_serial[2] == 'A')
                    && (buffer_serial[3] == 'T')
                    && (buffer_serial[4] == 'L')
                    && (buffer_serial[5] == 'L'))
                    || ((buffer_serial[1] == 'R')
                    && (buffer_serial[2] == 'A')
                    && (buffer_serial[3] == 'T')
                    && (buffer_serial[4] == 'T')
                    && (buffer_serial[5] == 'M'))) {

                buffer_serial[0] = '$';

                if (ptr_serial > 16) {                                        
                    rdr = new byte[ptr_serial];
                    System.arraycopy(buffer_serial, 0, rdr, 0, rdr.length);

                    xor = rdr[1];
                    for (int i = 2; i < rdr.length; i++) {
                        xor ^= rdr[i];
                    }

                    s6 = new String(rdr);

                    String rdr_string[] = s6.split(",");
                    nomor_kapal = Integer.parseInt(rdr_string[1]);
                    header_gps = rdr_string[0].getBytes();

                    if ((header_gps[3] == 'T') && (header_gps[4] == 'L') && (header_gps[5] == 'L')) {

                        slatitude = rdr_string[2];
                        slongitude = rdr_string[4];

                        latitude = Double.valueOf(slatitude);
                        degree = Math.floor(latitude / 100);
                        minute = Math.floor(latitude) - (100 * degree);
                        second = latitude - Math.floor(latitude);
                        decimal_lat = degree + (minute / 60) + (second / 3600);
                        NS = rdr_string[4];
                        if (NS.equalsIgnoreCase("S")) {
                            decimal_lat = -1 * decimal_lat;
                        }

                        longitude = Double.valueOf(slongitude);
                        degree = Math.floor(longitude / 100);
                        minute = Math.floor(longitude) - (100 * degree);
                        second = longitude - Math.floor(longitude);
                        decimal_long = degree + (minute / 60) + (second / 3600);
                        EW = rdr_string[6];
                        if (EW.equalsIgnoreCase("W")) {
                            decimal_long = -1 * decimal_long;
                        }

                        longit = decimal_long;
                        latit = decimal_lat;

                    }
                    if ((header_gps[3] == 'T') && (header_gps[4] == 'T') && (header_gps[5] == 'M')) {
                        Speed = rdr_string[5];
                        satuan_speed = rdr_string[10];
                        course = rdr_string[6];
                        spd = Double.valueOf(Speed);
                        crs = Double.valueOf(course);
                        switch (satuan_speed) {
                            case "K":
                                spd = spd * (1852 / 1000);
                                break;
                            case "S":
                                spd = spd * (115078 / 100000);
                                break;
                        }

                    }
                    s_xor = String.format("%02X", xor);
                    bcrc = new byte[2];
                    bcrc[0] = byte_rx[1];
                    bcrc[1] = byte_rx[2];
                    tmp = new String(bcrc);

                    if (s_xor.equalsIgnoreCase(tmp)) {
                        selecting_data_radar(nomor_kapal);
                        rdr2 = cprs32.Trak_Compress(longit, latit, spd, crs, hgt, 3,getPtr_data_radar() + 2, 0);
                        trak_gen.insert_data(rdr2, getPtr_data_radar() + 2);
                    }
                }
            }

        } else {             
            buffer_serial[ptr_serial] = byte_tmp;
            ptr_serial = (ptr_serial + 1) & 0xff;
        }
    }

    /**
     * method to selecting data radar
     * @param no_urut
     */
    public void selecting_data_radar(int no_urut) {
        dataBaru = true;
        for (int i = 0; i < idx_trak_radar.length; i++) {
            if (idx_trak_radar[i] == no_urut) {
                ptr_data_radar = i;
                dataBaru = false;
            }
        }
        if (dataBaru == true) {
            jml_aktual++;
            ptr_data_radar = (jml_aktual - 1);
            idx_trak_radar[getPtr_data_radar()] = no_urut;
        }
    }

    /**
     * @return the ptr_data_radar
     */
    public int getPtr_data_radar() {
        return ptr_data_radar;
    }
}