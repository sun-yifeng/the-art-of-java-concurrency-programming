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
public class InterruptionInJava2 implements Runnable {

    // volatile开关
    private volatile static boolean on = false;
    
    public static void main(String[] args) throws InterruptedException {
        // 新建线程
        Thread testThread = new Thread(new InterruptionInJava2(), "InterruptionInJava2");
        // start thread
        testThread.start();
        // main线程休眠1秒
        Thread.sleep(1000);

        // 开关
        InterruptionInJava2.on = true;
        // main线程结束
        System.out.println("main end");

    }

    @Override
    public void run() {
        //但现实中，我们可能需要做的更通用，不禁又要发出天问，如何中断线程？答案是添加一个开关。
        while(!on){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Yes,I am interrupted,but I am still running");
            }else{
                System.out.println("not yet interrupted");
            }
        }
    }
}

/*** out
 not yet interrupted
 not yet interrupted
 not yet interrupted
 not yet interrupted
 main end
 */