/*
 * Copyright PT Len Industri (Persero) 
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */
package co.id.len.tdl.common;

import java.util.Comparator;

/**
 *
 * @author apple
 */
public class StruckModelPolyline {

    private int polyNumber;
    private byte[] dataText;
    
    public StruckModelPolyline(int number, byte[] data){
        this.polyNumber = number;
        this.dataText = data;
    }
    
    public static Comparator<StruckModelPolyline> polyNumComparator = new Comparator<StruckModelPolyline>() {         
    @Override         
    public int compare(StruckModelPolyline jc1, StruckModelPolyline jc2) {             
      return (jc1.getPolyNumber()< jc2.getPolyNumber()? -1 :                     
              (jc1.getPolyNumber()== jc2.getPolyNumber()? 0 : 1));           
    }     
  };
    /**
     * @return the trackNumber
     */
    public int getPolyNumber() {
        return this.polyNumber;
    }

    /**
     * @param trackNumber the trackNumber to set
     */
    public void setPolyNumber(int Number) {
        this.polyNumber = Number;
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return dataText;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.dataText = data;
    }
    
    
}
