package com.sunyf.ch04;

import java.util.concurrent.TimeUnit;

/**
 * @program: the-art-of-java-concurrency-programming
 *
 * @description: Java中interrupt的使用
 * 原文：https://www.cnblogs.com/jenkov/p/juc_interrupt.html
 *
 * @author: sunyf
 * @create: 2018-11-21 11:50
 **/
public class InterruptionInJava implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        // 新建线程
        Thread testThread = new Thread(new InterruptionInJava(), "InterruptionInJava");
        // start thread
        testThread.start();
        // main线程休眠1秒
        Thread.sleep(1000);
        // interrupt thread
        testThread.interrupt();
        // main线程结束
        System.out.println("main end");

    }

    @Override
    public void run() {
        while (true) {
            // Thread.currentThread()是执行当前代码指令的线程testThread
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Yes,I am interrupted, but I am still running");

            } else {
                System.out.println("not yet interrupted");
            }
        }
    }
}

/***
 结果显示，被中断后，仍然不停打印Yes, I am interrupted, but I am still running
 */
