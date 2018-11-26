package com.sunyf.ch04;

import java.util.concurrent.TimeUnit;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 工具类
 * @author: sunyf
 * @create: 2018-11-21 11:33
 **/
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
