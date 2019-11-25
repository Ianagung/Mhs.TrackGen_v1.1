/* 
 * Copyright PT Len Industri (Persero) 

 *  
 * TO PT LEN INDUSTRI (PERSERO), AS APPLICABLE, AND SHALL NOT BE USED IN ANY WAY
 * OTHER THAN BEFOREHAND AGREED ON BY PT LEN INDUSTRI (PERSERO), NOR BE REPRODUCED
 * OR DISCLOSED TO THIRD PARTIES WITHOUT PRIOR WRITTEN AUTHORIZATION BY
 * PT LEN INDUSTRI (PERSERO), AS APPLICABLE
 */

package co.id.len.trackgenerator.thread;

import co.id.len.tdl.variable.ModelVariabel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yudha panji
 */

/**
 * 
 * This is Thread Sync Array class
 */
public class ThreadSyncArray implements Runnable {
    
     /**
     * Reference variables
     */
    private ModelVariabel mod_var;
    public Thread thrd;
    private String threadName;
    private boolean suspended = false;
    private boolean sync_data;
    private int idx_trak;

    /**
     *
     * @param model
     */
    public ThreadSyncArray(ModelVariabel model) {
        threadName = "sync";
        sync_data = true;
        this.mod_var = model;
    }

     /**
     * method to running thread process of thread send data
     */
    @Override
    public void run() {

//        while (isSync_data()) {
//            if (mod_var.isMode_real()) {
//                try {
//                   mod_var.getBuffer_trak_tx().clear();
//                    idx_trak = 0;
//
//                    for (int i = 0; i < mod_var.getBuffer_trak_rx().length; i++) {
//                        if (mod_var.getBuffer_trak_rx()[i][23] != (byte) 0 && mod_var.getBuffer_trak_rx()[i][29] !=0 ) {
//                            idx_trak++;
//                            mod_var.getBuffer_trak_rx()[i][19] = (byte) idx_trak;
//                            mod_var.getBuffer_trak_rx()[i][20] = (byte) (idx_trak >> 8);
//                            mod_var.getBuffer_trak_tx().add(mod_var.getBuffer_trak_rx()[i]);
//                        }
//                    }
//
//                    Thread.sleep(1000);
//                    synchronized (this) {
//                        while (isSuspended()) {
//                            try {
//                                wait();
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(ThreadSyncArray.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                    }
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(ThreadSyncArray.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }

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
     * @return the sync_data
     */
    public boolean isSync_data() {
        return sync_data;
    }

    /**
     * @param sync_data the sync_data to set
     */
    public void setSync_data(boolean sync_data) {
        this.sync_data = sync_data;
    }

    /**
     * @return the suspended
     */
    public boolean isSuspended() {
        return suspended;
    }

    /**
     * @param suspended the suspended to set
     */
    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
}