/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.thread;

import co.id.len.tdl.variable.DataLink_Constanta;
import co.id.len.tdl.variable.ModelVariabel;
import co.id.len.trackgenerator.main.TrakGenerator;
import java.net.InetAddress;

/**
 * Thread Send Data
 *
 * @author Yudha Panji Rahman
 */

/**
 * 
 * This is Thread Send Data class
 */
public class ThreadSendData implements Runnable {
    
     /**
     * Reference variables
     */
    private final DataLink_Constanta DEF = new DataLink_Constanta();
    private final ModelVariabel mod_var;
    public final TrakGenerator trak_gen;
    private final String threadName;
    public Thread thrd;
    private final boolean send = true;
    private byte[] dataReadyToSend;
    int index;
    byte[] fixed_trak_tx;
    int ntrak;
    byte[] one_trak;
    public int port;
    public InetAddress multicastAddress;

    /**
     *
     * @param TRAK
     */
    public ThreadSendData(TrakGenerator TRAK) {
        threadName = "send";
        this.trak_gen = TRAK;
        mod_var = trak_gen.mod_var;
    }

     /**
     * method to running thread process of thread send data
     */
    @Override
    public void run() {
        try {
            while (send) {
                Thread.sleep(3000);  
                 if (trak_gen.mod_var.getBuffer_trak_tx_size() != 0) {   
                    
                    ntrak = trak_gen.mod_var.getBuffer_trak_tx_size();
                    fixed_trak_tx = new byte[ntrak * 32];
                    for (int i = 0; i < ntrak; i++) {
                        one_trak = (byte[]) trak_gen.mod_var.getBuffer_trak_tx().toArray()[i];
                        System.arraycopy(one_trak, 0, fixed_trak_tx, i * 32, one_trak.length);
                    }
                    ntrak = fixed_trak_tx.length / 32;
                    dataReadyToSend = new byte[fixed_trak_tx.length + 8];
                    dataReadyToSend[0] = (byte) DEF.TOPIC_TG2CORE;
                    dataReadyToSend[1] = (byte) 1;
                    dataReadyToSend[3] = (byte) ntrak;                    
                    System.arraycopy(fixed_trak_tx, 0, dataReadyToSend, 8, fixed_trak_tx.length);

                    try {
                        trak_gen.kirimData(dataReadyToSend);
                    } catch (Exception e) {
                    }
//                    trak_gen.mod_var.resetBuffeRx();
                    trak_gen.mod_var.resetBufferTx();
                }
            }
        } catch (InterruptedException e1) {
        }
    }

    /**
     * method to start the thread
     */
    public void start() {
        if (thrd == null) {
            thrd = new Thread(this, threadName);
            thrd.start();
        }
    }

    /**
     * method to stop the thread
     */
    public void stop() {
        if (thrd != null) {
            thrd.stop();
        } else {
        }
    }
}