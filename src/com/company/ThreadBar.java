package com.company;
public class ThreadBar implements Runnable {
    volatile boolean dissolved;
    final ProgressBar.HelperClass progressRef;
    static final int width = 90;
    public ThreadBar(ProgressBar.HelperClass ref, long  Delay) {
        progressRef = ref;
        dissolved = false;
    }
    public void refreshProgressBar() {
        StringBuilder builder = new StringBuilder("\r ");
        int percent = (int) Math.floor(100.0 * progressRef.current / progressRef.TOTAL_COUNT);
        for (int i = 0; i < width; i++) {
            if (i < (percent -10  )) builder.append("-");
            else builder.append(" ");
        }
        builder.append("%s");
        if (percent >= 100) {
            builder.append("%n");
        }
        System.out.printf(builder.toString(), " "+ percent + "%  ");
    }
    void sleep(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    void dissolved() {
        dissolved = true;
    }
    @Override
    public void run() {
        while (dissolved){

        }
    }
}