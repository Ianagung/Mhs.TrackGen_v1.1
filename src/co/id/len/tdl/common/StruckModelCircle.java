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
public class StruckModelCircle {

    private int circleNumber;
    private byte[] dataText;
    
    public StruckModelCircle(int number, byte[] data){
        this.circleNumber = number;
        this.dataText = data;
    }
    
    public static Comparator<StruckModelCircle> circleNumComparator = new Comparator<StruckModelCircle>() {         
    @Override         
    public int compare(StruckModelCircle jc1, StruckModelCircle jc2) {             
      return (jc1.getCircleNumber()< jc2.getCircleNumber()? -1 :                     
              (jc1.getCircleNumber()== jc2.getCircleNumber()? 0 : 1));           
    }     
  };


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

    /**
     * @return the circleNumber
     */
    public int getCircleNumber() {
        return circleNumber;
    }

    /**
     * @param circleNumber the circleNumber to set
     */
    public void setCircleNumber(int circleNumber) {
        this.circleNumber = circleNumber;
    }
    
    
}
