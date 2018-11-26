package com.sunyf.ch04;

import java.util.concurrent.TimeUnit;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 理解中断
 * @author: sunyf
 * @create: 2018-11-21 11:22
 **/
public class InterruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        // 新建后台线程1
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        // 新建后台线程2
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        // 启动线程
        sleepThread.start();
        busyThread.start();
        // main线程休眠5秒，让两个线程充分运行
        TimeUnit.SECONDS.sleep(5);
        // 中断通知（信号、标志位）
        sleepThread.interrupt();
        busyThread.interrupt();
        // 打印
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread  interrupted is " + busyThread.isInterrupted());
        // 防止两个线程立马退出？
        SleepUtils.second(2);
    }

    // 休眠的线程
    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    // 不停运行的线程
    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                ;
            }
        }

    }

}

/** out
 SleepThread interrupted is false
 BusyThread  interrupted is true
 java.lang.InterruptedException: sleep interrupted
 */
