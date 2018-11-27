package com.sunyf.ch08;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 交换器
 * @author: sunyf
 * @create: 2018-11-27 14:47
 **/
public class ExchangerDemo {

    private static final Exchanger<String> exch = new Exchanger<String>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        //
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";
                    exch.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    String B = "银行流水B";
                    String A = exch.exchange("B");
                    // A不等于B
                    System.out.println(A.equals(B));
                    System.out.println("A:" + A + ",B:" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }

}
