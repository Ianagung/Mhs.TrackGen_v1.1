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
 * Compress 16 bytes class
 * @author Yudha Panji Rahman
 */

public class Compress16 {
    
    /**Variable Reference **/
    public double longitudeTx ;
    public double latitudeTx ;
    public double speedTx ;
    public double courseTx ;
    public int heightTx ;
    public int attributeTx ;
    private long longitudePreTx;
    private long latitudePreTx;
    private long speedPreTx;
    private long coursePreTx;
    private long longitudePreCompress;
    private long latitudePreCompress;
    private long speedPrecompress;
    private long coursePreCompress;
    private byte[] trackTx;
    private byte[] trackTemporary ;

    /**
     * The Constructor
     */
    public Compress16() {
        trackTx = new byte[16];
        trackTemporary = new byte[16];
    }

    /**
     *  Method To Read Track Tx Data
     * @param trak_data Track Data
     */
    public void ReadTrackTx(byte[] trak_data) {

        longitudePreTx = (trackTx[0] & 0xff) + (trackTx[1] & 0xff) * 0x100 + (trackTx[2] & 0xff) * 0x10000;
        longitudeTx = longitudePreTx * 360.0 / (double) 0xffffff;
        if (longitudeTx > 180) {
            longitudeTx = longitudeTx - 360;
        }

        latitudePreTx = (trackTx[3] & 0xff) + (trackTx[4] & 0xff) * 0x100 + (trackTx[5] & 0xff) * 0x10000;
        latitudeTx = latitudePreTx * 180.0 / 0xffffff;

        if (latitudeTx > 90) {
            latitudeTx = latitudeTx - 180;
        }

        speedPreTx = (trackTx[6] & 0xff) + (trackTx[7] & 0xff) * 0x100;
        speedTx = speedPreTx * 4000.0 / 0xffff;

        coursePreTx = (trackTx[8] & 0xff) + (trackTx[9] & 0xff) * 0x100;
        courseTx = coursePreTx * 360.0 / 0xffff;

        heightTx = (trackTx[10] & 0xff) + (trackTx[11] & 0xff) * 0x100;
        attributeTx = (trackTx[12] & 0xff);
    }
    
/**
 * Method to compress data track
 * @param longit longitude
 * @param latit latitude
 * @param spd speed
 * @param crs course
 * @param hgt height
 * @param source source
 * @return Temporary Track
 */
    public byte[] TrackCompress(double longit, double latit, double spd, double crs, int hgt, int source) {
     
        longitudePreCompress = Math.round((longit / 360.0) * (double) 0xffffff);
        latitudePreCompress = Math.round(latit * 0xffffff / 180.0);
        speedPrecompress = Math.round(spd * 0xffff / 4000.0);
        coursePreCompress = Math.round(crs * 0xffff / 360.0);
      
        trackTemporary[0] = (byte) longitudePreCompress;
        trackTemporary[1] = (byte) (longitudePreCompress >> 8);
        trackTemporary[2] = (byte) (longitudePreCompress >> 16);
        trackTemporary[3] = (byte) latitudePreCompress;
        trackTemporary[4] = (byte) (latitudePreCompress >> 8);
        trackTemporary[5] = (byte) (latitudePreCompress >> 16);
        trackTemporary[6] = (byte) speedPrecompress;
        trackTemporary[7] = (byte) (speedPrecompress >> 8);
        trackTemporary[8] = (byte) coursePreCompress;
        trackTemporary[9] = (byte) (coursePreCompress >> 8);
        trackTemporary[10] = (byte) hgt;
        trackTemporary[11] = (byte) (hgt >> 8);
        trackTemporary[12] = (byte) 7;
        trackTemporary[13] = (byte) 0;
        trackTemporary[14] = (byte) 1;
        trackTemporary[15] = (byte) source;
        return trackTemporary;
    }
}