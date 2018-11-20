package com.sunyf.ch01;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 死锁
 * @author: sunyf
 * @create: 2018-11-20 14:58
 **/
public class DeadLockDemo {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        // 运行死锁线程：什么都打印不出来！！！
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {

        // 线程1: 外层锁住A(然后眠2秒）E4，内层锁住B
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        // TODO 如果不休眠2秒钟，有可能不会死锁！！！
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 锁B
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });

        // 线程2：外层锁住B，内存锁住A
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

}
