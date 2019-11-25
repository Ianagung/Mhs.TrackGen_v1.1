/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.jssctester;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ian
 */

/**
 * 
 * This is GPSbuilder class
 */
public class GPSbuilder {
    
     /**
     * Reference variables
     */
    private byte[] hasil;
    private ByteArrayOutputStream bout;
    
    public GPSbuilder(){
    bout = new ByteArrayOutputStream();
    hasil = new byte[200];
    }
    
    /**
     * 
     * @param a
     */
    public void inisial(byte a){
        setHasil(bout.toByteArray());
        bout = new ByteArrayOutputStream();
        bout.write(a);
    }

    /**
     *
     * @param b
     */
    public void append(byte b){
        bout.write(b);
    }
    
    /**
     *
     * @param c
     */
    public void append(byte[] c){
        try {
            if(c.length > 1){
            }
            bout.write(c);
        } catch (IOException ex) {
            Logger.getLogger(GPSbuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @return the hasil
     */
    public byte[] getHasil() {
        return hasil;
    }

    /**
     * @param hasil the hasil to set
     */
    public void setHasil(byte[] hasil) {
        this.hasil = hasil;
    }  
}