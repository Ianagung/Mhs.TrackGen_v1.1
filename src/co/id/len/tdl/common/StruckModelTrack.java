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
public class StruckModelTrack {

    private int trackNumber;
    private byte[] dataTrack;
    private int npuNumber;

    /**
     * 
     * @param number
     * @param data 
     */
    public StruckModelTrack(int number, byte[] data) {
        this.trackNumber = number;
        this.dataTrack = data;
    }

    /**
     * 
     * @param number
     * @param data
     * @param npu 
     */
    public StruckModelTrack(int number, byte[] data, int npu) {
        this.trackNumber = number;
        this.dataTrack = data;
        this.npuNumber = npu;
    }

    /**
     * 
     */
    public static Comparator<StruckModelTrack> trackNumComparator = new Comparator<StruckModelTrack>() {
        @Override
        public int compare(StruckModelTrack jc1, StruckModelTrack jc2) {
            return (jc1.getTrackNumber() < jc2.getTrackNumber() ? -1
                    : (jc1.getTrackNumber() == jc2.getTrackNumber() ? 0 : 1));
        }
    };

    /**
     * 
     */
    public static Comparator<StruckModelTrack> npuNumComparator = new Comparator<StruckModelTrack>() {
        @Override
        public int compare(StruckModelTrack jc1, StruckModelTrack jc2) {
            return (jc1.getNpuNumber() < jc2.getNpuNumber() ? -1
                    : (jc1.getNpuNumber() == jc2.getNpuNumber()? 0 : 1));
        }
    };

    /**
     * @return the trackNumber
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     * @param trackNumber the trackNumber to set
     */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return getDataTrack();
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.setDataTrack(data);
    }

    /**
     * @return the dataTrack
     */
    public byte[] getDataTrack() {
        return dataTrack;
    }

    /**
     * @param dataTrack the dataTrack to set
     */
    public void setDataTrack(byte[] dataTrack) {
        this.dataTrack = dataTrack;
    }

    /**
     * @return the npuNumber
     */
    public int getNpuNumber() {
        return npuNumber;
    }

    /**
     * @param npuNumber the npuNumber to set
     */
    public void setNpuNumber(int npuNumber) {
        this.npuNumber = npuNumber;
    }

}
