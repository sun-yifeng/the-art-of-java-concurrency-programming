package com.sunyf.ch01;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 并发测试
 *
 * 1、执行两个1万次的for循环; 一个方法用线程执行，一个用串行方法执行；
 * 2、串行方法执行的时间比较快；
 *
 * @author: sunyf
 * @create: 2018-11-20 13:26
 **/
public class ConcurrencyTest {

    private static final long count = 10000l;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    // 并行执行
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        // 创建一个线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        // 父线程等子线程结束之后才能继续运行
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency: " + time + " ms, b = " + b);
    }

    // 串行执行
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial: " + time + " ms, a = " + a + ", b = " + b);

    }


}
