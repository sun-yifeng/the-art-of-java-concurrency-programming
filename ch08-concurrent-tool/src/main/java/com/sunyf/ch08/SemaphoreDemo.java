package com.sunyf.ch08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 信号量控制
 * @author: sunyf
 * @create: 2018-11-27 14:27
 **/
public class SemaphoreDemo {

    private static final int THREAD_COUNT = 30; // 最大30个线程

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10); // 控制在10个线程

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) { // 外层：30个线程
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 内层控制在10个线程
                        s.acquire(); // 获取
                        System.out.println("save data");
                        s.release(); // 释放
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
    }

}
