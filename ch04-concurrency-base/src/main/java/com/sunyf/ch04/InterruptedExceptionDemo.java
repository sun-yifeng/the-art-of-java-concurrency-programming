package com.sunyf.ch04;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 中断异常
 * @author: sunyf
 * @create: 2018-11-21 13:00
 **/
public class InterruptedExceptionDemo {

    public static void main(String[] args) {
        // 创建线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "，线程开始了");
                try {
                    // 休眠
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    //
                    System.out.println(Thread.currentThread().getName() + ",抛出中断异常后的状态：" + Thread.currentThread().isInterrupted());
                    //
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "，线程结束了");
            }
        });
        // 启动线程
        thread.start();
        // 中断状态
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送中断通知前的状态：" + thread.isInterrupted());
        thread.interrupt();
        System.out.println("发送中断通知后的状态：" + thread.isInterrupted());
        // 结束任务
        System.out.println("测试结束");
    }

}
