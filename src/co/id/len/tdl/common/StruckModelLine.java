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
public class StruckModelLine {

    private int lineNumber;
    private byte[] dataText;
    
    public StruckModelLine(int number, byte[] data){
        this.lineNumber = number;
        this.dataText = data;
    }
    
    public static Comparator<StruckModelLine> lineNumComparator = new Comparator<StruckModelLine>() {         
    @Override         
    public int compare(StruckModelLine jc1, StruckModelLine jc2) {             
      return (jc1.getLineNumber()< jc2.getLineNumber()? -1 :                     
              (jc1.getLineNumber()== jc2.getLineNumber()? 0 : 1));           
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
     * @return the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    
}
