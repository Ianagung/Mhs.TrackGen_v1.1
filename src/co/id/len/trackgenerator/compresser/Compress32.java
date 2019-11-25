/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.trackgenerator.compresser;

/**
 * Compress 32 Byte class
 *
 * @author Yudha Panji Rahman
 */
public class Compress32 {

    /**
     * Variable Reference *
     */
    public double longitudeTx;
    public double latitudeTx;
    public double speedTx;
    public double courseTx;
    public int heighTx;
    public int attributeTx;
    private long longitudePreTx;
    private long latitudePreTx;
    private long speedPreTx;
    private long coursePreTx;
    private long longitudePreCompress;
    private long latitudePreCompress;
    private long speedPreCompress;
    private long coursePreCompress;
    private byte[] trakTx;
    private byte[] trackTemporary;
    private int attb = 1;

    /**
     * The Constructor
     */
    public Compress32() {
        trakTx = new byte[32];
        trackTemporary = new byte[32];
    }

    /**
     * method read track
     *
     * @param trak_data Track data
     */
    public void read_trak_tx(byte[] trak_data) {
        trakTx = trak_data;
        longitudePreTx = (trakTx[0] & 0xff) + (trakTx[1] & 0xff) * 0x100 + (trakTx[2] & 0xff) * 0x10000 + (trakTx[3] & 0xff) * 0x1000000;
        longitudeTx = longitudePreTx * 360.0 / 4294967295.0;
        if (longitudeTx > 180) {
            longitudeTx = longitudeTx - 360;
        }
        latitudePreTx = (trakTx[4] & 0xff) + (trakTx[5] & 0xff) * 0x100 + (trakTx[6] & 0xff) * 0x10000 + (trakTx[7] & 0xff) * 0x1000000;
        latitudeTx = latitudePreTx * 180.0 / 4294967295.0;
        if (latitudeTx > 90) {
            latitudeTx = latitudeTx - 180;
        }
        speedPreTx = (trakTx[8] & 0xff) + (trakTx[9] & 0xff) * 0x100 + (trakTx[10] & 0xff) * 0x10000;
        speedTx = speedPreTx * 4000.0 / 0xffff;
        coursePreTx = (trakTx[11] & 0xff) + (trakTx[12] & 0xff) * 0x100 + (trakTx[13] & 0xff) * 0x10000;
        courseTx = coursePreTx * 360.0 / 0xffff;
        heighTx = (trakTx[14] & 0xff) + (trakTx[15] & 0xff) * 0x100 + (trakTx[16] & 0xff) * 0x10000;
        attributeTx = (trakTx[15] & 0xff) + (trakTx[16] & 0xff) * 0x100;
    }

    /**
     * Method Compress Data
     *
     * @param longit longitude
     * @param latit Latitude
     * @param spd Speed
     * @param crs Course
     * @param hgt Height
     * @param source Source
     * @param trackNumber Track Number
     * @param mmsi
     * @return
     */
    public byte[] Trak_Compress(double longit, double latit, double spd, double crs, int hgt, int source, int trackNumber, int mmsi) {
    
        longitudePreCompress = Math.round((longit / 360.0) * 4294967295.0);
        latitudePreCompress = Math.round(latit * 4294967295.0 / 180.0);
        speedPreCompress = Math.round(spd * 0xffff / 4000.0);
        coursePreCompress = Math.round(crs * 0xffff / 360.0);
        
        byte[] trak_tmp = new byte[32];
        trak_tmp[0] = (byte) longitudePreCompress;
        trak_tmp[1] = (byte) (longitudePreCompress >> 8);
        trak_tmp[2] = (byte) (longitudePreCompress >> 16);
        trak_tmp[3] = (byte) (longitudePreCompress >> 24);
        trak_tmp[4] = (byte) latitudePreCompress;
        trak_tmp[5] = (byte) (latitudePreCompress >> 8);
        trak_tmp[6] = (byte) (latitudePreCompress >> 16);
        trak_tmp[7] = (byte) (latitudePreCompress >> 24);
        trak_tmp[8] = (byte) speedPreCompress;
        trak_tmp[9] = (byte) (speedPreCompress >> 8);
        trak_tmp[10] = (byte) (speedPreCompress >> 16);
        trak_tmp[11] = (byte) coursePreCompress;
        trak_tmp[12] = (byte) (coursePreCompress >> 8);
        trak_tmp[13] = (byte) (coursePreCompress >> 16);
        trak_tmp[14] = (byte) hgt;
        trak_tmp[15] = (byte) (hgt >> 8);
        trak_tmp[16] = (byte) (hgt >> 16);
        trak_tmp[17] = (byte) attb;
        trak_tmp[18] = (byte) (attb >> 8);
        trak_tmp[19] = (byte) 0;
        trak_tmp[20] = (byte) (0 >> 8);
        trak_tmp[21] = (byte) trackNumber;
        trak_tmp[22] = (byte) (trackNumber >> 8);
        trak_tmp[23] = (byte) source;
        trak_tmp[24] = (byte) mmsi;
        trak_tmp[25] = (byte) (mmsi >> 8);
        trak_tmp[26] = (byte) (mmsi >> 16);
        trak_tmp[27] = (byte) (mmsi >> 24);
        trak_tmp[28] = (byte) 0;
        trak_tmp[29] = (byte) 1;
        trak_tmp[30] = (byte) 0;
        trak_tmp[31] = (byte) 0;
        return trak_tmp;
    }
}