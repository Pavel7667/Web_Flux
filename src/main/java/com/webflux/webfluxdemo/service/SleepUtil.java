package com.webflux.webfluxdemo.service;

public class SleepUtil {

    /**
     * Creating custom delay method
     * @param seconds
     */
    public static void sleepSeconds(int seconds){
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
