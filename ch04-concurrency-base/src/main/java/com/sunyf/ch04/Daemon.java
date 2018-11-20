package com.sunyf.ch04;

import java.util.concurrent.TimeUnit;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 守护线程
 * @author: sunyf
 * @create: 2018-11-20 22:01
 **/
public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        // 设置为守护线程。因为JVM中没有非守护线程，所以DaemonRunner会立马终止，不会打印任何结果（finally不会执行）
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("DaemonThread finally run...");
            }
        }
    }


}
