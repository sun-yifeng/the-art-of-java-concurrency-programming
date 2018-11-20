package com.sunyf.ch02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 计算器
 * @author: sunyf
 * @create: 2018-11-20 18:10
 **/
public class Counter {

    // 线程安全的变量（用CAS实现的原子操作）
    private AtomicInteger atomicInt = new AtomicInteger(0);

    // 非线程安全的变量
    private int i = 0;

    public static void main(String[] args) {
        // 新建实例
        final Counter cas = new Counter();
        // 新建数组
        List<Thread> list = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        // 新建100个线程
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 调用1w次方法，打印出来的结果应该是: 100 * 1w = 100w
                    for (int j = 0; j < 1000; j++) {
                        cas.noneCount();
                        cas.safeCount();
                    }
                }
            });
            list.add(t);
        }
        // 启动100个线程
        for (Thread thread : list) {
            thread.start();
        }
        // 所有的线程执行完
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 打印结果
        System.out.println("普通的计数器：" + cas.i);
        System.out.println("原子类计数器：" + cas.atomicInt.get());
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

    }

    // 线程安全的方法
    private void safeCount() {
        // 死循环
        for (; ; ) {
            int i = atomicInt.get();
            boolean b = atomicInt.compareAndSet(i, ++i);
            if (b) {
                break;
            }
        }
    }

    // 非线程安全的方法
    private void noneCount() {
        i++;
    }

}

/**
普通的计数器：99858
原子类计数器：100000
耗时：50
*/
