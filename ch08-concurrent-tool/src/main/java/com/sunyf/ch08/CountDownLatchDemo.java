package com.sunyf.ch08;

import java.util.concurrent.CountDownLatch;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 门闩工具，代替join方法
 * @author: sunyf
 * @create: 2018-11-26 15:19
 **/
public class CountDownLatchDemo {

    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await(); //await,不是wait
        System.out.println(3);

    }

}
