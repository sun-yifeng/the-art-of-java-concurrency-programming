package com.sunyf.ch04;

/**
 * @program: the-art-of-java-concurrency-programming
 *
 * @description: Java中interrupt的使用
 * 原文：https://www.cnblogs.com/jenkov/p/juc_interrupt.html
 *
 * @author: sunyf
 * @create: 2018-11-21 11:50
 **/
public class InterruptionInJava3 implements Runnable {

    // volatile开关
    private volatile static boolean on = false;
    
    public static void main(String[] args) throws InterruptedException {
        // 新建线程
        Thread testThread = new Thread(new InterruptionInJava3(), "InterruptionInJava4");
        // start thread
        testThread.start();
        // main线程休眠1秒
        Thread.sleep(1000);

        // 开关
        InterruptionInJava3.on = true;
        // main线程结束
        System.out.println("main end");

    }

    @Override
    public void run() {
        while(!on){
            try {
                // TODO 当遇到线程阻塞时，就会很无奈了
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                System.out.println("caught exception: "+e);
            }
        }
    }
}
