/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.dataparser;

/**
 * AIS Data Parser
 *
 * @author Yudha Panji Rahman
 */

import co.id.len.trackgenerator.main.TrakGenerator;
import co.id.len.trackgenerator.connection.Readserial;
import co.id.len.trackgenerator.compresser.Compress32;
import co.id.len.trackgenerator.compresser.Compress16;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * This is AISParser class
 */
public class AISParser {

    /**
     * Variable reference*
     */
    Compress16 compress16;
    Compress32 compress32;
    private int mmsi;
    private double longit;
    private double latit;
    private double spd;
    private double crs;
    private int height;
    private byte[] serialBuffer;
    private byte[] byteRx;
    private int pointerSerial = 0;
    private final TrakGenerator trak_gen;
    private int[] indexAisData;
    private int pointerAisData;
    private int actualSze;
    private byte[] ais;
    byte xor;
    private String temporaryAisData;
    private String allAisData;
    private String bit6;
    private String mainDataAis;
    private byte[] allAisByteData;
    private int chrToDecimal;
    private String temporaryMmsi;
    private String temporaryLatitude;
    private String temporaryLongitude;
    private String temporarySpeed;
    private String temporaryCourse;
    private String NS;
    private double latitude;
    private double decimalLatitude;
    private double longitude;
    private double decimalLongitude;
    private double speed;
    private double course;
    byte[] finalAisData;
    byte[] bcrc;
    boolean newData;

    /**
     * The Constructor
     *
     * @param trackGenerator Track Generator
     */
    
    public AISParser(TrakGenerator trackGenerator) {
        /**
         * Variable Reference*
         */
        actualSze = 0;
        serialBuffer = new byte[258];
        byteRx = new byte[3];
        indexAisData = new int[100];
        trak_gen = trackGenerator;
        compress16 = new Compress16();
        compress32 = new Compress32();
        pointerAisData = 0;
    }

    /**
     * Parser AIS Data Method
     *
     * @param byteReceive byte received
     * @throws UnknownHostException
     */
    
    public void ReceiveAIS(byte byteReceive) throws UnknownHostException {
        byteRx[0] = byteRx[1];
        byteRx[1] = byteRx[2];
        byteRx[2] = byteReceive;
        byte byte_tmp = byteRx[0];
        if (byte_tmp == '!') {
            pointerSerial = 0;
        }
        if (byte_tmp == '*') {
            if (((serialBuffer[1] == 'A')
                    && (serialBuffer[2] == 'I')
                    && (serialBuffer[3] == 'V')
                    && (serialBuffer[4] == 'D')
                    && (serialBuffer[5] == 'M'))
                    || ((serialBuffer[1] == 'A')
                    && (serialBuffer[2] == 'I')
                    && (serialBuffer[3] == 'V')
                    && (serialBuffer[4] == 'D')
                    && (serialBuffer[5] == '0'))) {
                serialBuffer[0] = '!';
                if (pointerSerial > 16) {                                       
                    ais = new byte[pointerSerial];
                    System.arraycopy(serialBuffer, 0, ais, 0, ais.length);
                    xor = ais[1];
                    for (int i = 2; i < ais.length; i++) {
                        xor ^= ais[i];
                    }
                    temporaryAisData = new String(ais);
                    String ais_string[] = temporaryAisData.split(",");
                    allAisData = ais_string[5];
                    mainDataAis = "";
                    try {
                        allAisByteData = allAisData.getBytes("UTF-8");
                        for (byte a : allAisByteData) {
                            chrToDecimal = (a & 0xff);
                            if (chrToDecimal >= 48) {
                                chrToDecimal = chrToDecimal - 48;
                                if (chrToDecimal >= 40) {
                                    chrToDecimal = chrToDecimal - 8;
                                }
                                bit6 = "0000000" + Integer.toBinaryString(chrToDecimal);
                                bit6 = bit6.substring(bit6.length() - 6, bit6.length());
                                mainDataAis = mainDataAis + bit6;
                            }
                        }
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(Readserial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    temporaryMmsi = String.valueOf((Integer.parseInt(mainDataAis.substring(8, 8 + 30), 2)));
                    temporaryLatitude = String.valueOf((Integer.parseInt(mainDataAis.substring(89, 89 + 27), 2)));
                    temporaryLongitude = String.valueOf((Integer.parseInt(mainDataAis.substring(61, 61 + 28), 2)));
                    temporarySpeed = String.valueOf((Integer.parseInt(mainDataAis.substring(50, 50 + 10), 2)) / (10));
                    temporaryCourse = String.valueOf((Integer.parseInt(mainDataAis.substring(116, 116 + 12), 2)) / (10));
                    latitude = Double.valueOf(temporaryLatitude);
                    decimalLatitude = latitude / 600000;
                    longitude = Double.valueOf(temporaryLongitude);
                    decimalLongitude = longitude / 600000;
                    NS = ais_string[6];
                    if (NS.equalsIgnoreCase("S")) {
                        decimalLatitude = -1 * decimalLatitude;
                    }                
                    speed = Double.valueOf(temporarySpeed);
                    course = Double.valueOf(temporaryCourse);
                    longit = decimalLongitude;
                    latit = decimalLatitude;
                    spd = speed;
                    crs = course;
                    mmsi = Integer.valueOf(temporaryMmsi);

                    String s_xor = String.format("%02X", xor);
                    bcrc = new byte[2];
                    bcrc[0] = byteRx[1];
                    bcrc[1] = byteRx[2];
                    String tmp = new String(bcrc);
                    if (s_xor.equalsIgnoreCase(tmp)) {
                        indexing_data(mmsi);
                        finalAisData = compress32.Trak_Compress(decimalLongitude, decimalLatitude, speed, course, height, 2,  getPointerAisData() + 1, mmsi );
                        trak_gen.insert_data(finalAisData, getPointerAisData() + 1);
                    }
                }
            }

        } else {
            serialBuffer[pointerSerial] = byte_tmp;
            pointerSerial = (pointerSerial + 1) & 0xff;
        }
    }

    /**
     * method to indexing data
     * @param mmsi_no
     */
    public void indexing_data(int mmsi_no) {
        newData = true;
        for (int i = 0; i < indexAisData.length; i++) {
            if (indexAisData[i] == mmsi_no) {
                pointerAisData = i;
                newData = false;
            }
        }
        if (newData == true) {
            actualSze++;
            pointerAisData = (actualSze - 1);
            indexAisData[getPointerAisData()] = mmsi_no;
        }
    }

    /**
     * @return the pointerAisData
     */
    public int getPointerAisData() {
        return pointerAisData;
    }
}