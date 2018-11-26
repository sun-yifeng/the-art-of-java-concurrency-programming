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
public class InterruptionInJava1 implements Runnable {
    
    public static void main(String[] args) throws InterruptedException {
        // 新建线程
        Thread testThread = new Thread(new InterruptionInJava1(), "InterruptionInJava1");
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
                // TODO 修改代码，在状态判断中如上，添加一个return就okay了
                return;
            } else {
                System.out.println("not yet interrupted");
            }
        }
    }
}

/** out
not yet interrupted
not yet interrupted
not yet interrupted
main end
Yes,I am interrupted, but I am still running
*/
