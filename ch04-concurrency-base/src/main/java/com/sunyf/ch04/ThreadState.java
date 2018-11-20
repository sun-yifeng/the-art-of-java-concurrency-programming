package com.sunyf.ch04;

import java.util.concurrent.TimeUnit;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 线程状态
 *
 * 运行程序后，使用jstack查看 线程的状态
 *
 * @author: sunyf
 * @create: 2018-11-20 21:37
 **/
public class ThreadState {

    public static void main(String[] args) {
        //
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        //
        new Thread(new Waiting(), "WaitingThread").start();
        //
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();

    }

    // 一直等待，死循环
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            // 死循环，一直休眠
            while (true) {
                SleepUtils.second(100l);
            }
        }

    }

    // 一直等待，锁住Class
    static class Waiting implements Runnable {
        @Override
        public void run() {
            // 死循环，一直锁住
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 一直等待，锁住Class
    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100l);
                }
            }
        }
    }

    static class SleepUtils {
        public static final void second(long seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
