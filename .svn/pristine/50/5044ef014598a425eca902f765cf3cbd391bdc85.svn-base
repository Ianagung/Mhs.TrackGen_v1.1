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
public class StruckModelText {

    private int source;
    private int textNumber;
    private byte[] dataText;

    public StruckModelText(int number, byte[] data) {
        this.textNumber = number;
        this.dataText = data;
    }

    public StruckModelText(int number, byte[] data, int sumber) {
        this.textNumber = number;
        this.dataText = data;
        this.source = sumber;
    }

    public static Comparator<StruckModelText> textNumComparator = new Comparator<StruckModelText>() {
        @Override
        public int compare(StruckModelText jc1, StruckModelText jc2) {
            return (jc1.getTextNumber() < jc2.getTextNumber() ? -1
                    : (jc1.getTextNumber() == jc2.getTextNumber() ? 0 : 1));
        }
    };
    
    public static Comparator<StruckModelText> textSourceComparator = new Comparator<StruckModelText>() {
        @Override
        public int compare(StruckModelText jc1, StruckModelText jc2) {
            return (jc1.getSource() < jc2.getSource() ? -1
                    : (jc1.getSource() == jc2.getSource() ? 0 : 1));
        }
    };

    /**
     * @return the trackNumber
     */
    public int getTextNumber() {
        return this.textNumber;
    }

    /**
     * @param trackNumber the trackNumber to set
     */
    public void setTextNumber(int Number) {
        this.textNumber = Number;
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

    /**
     * @return the source
     */
    public int getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(int source) {
        this.source = source;
    }

}
