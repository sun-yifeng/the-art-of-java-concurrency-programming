package com.sunyf.ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: java线程的优先级
 *
 * 1、java线程的优先级为1-10
 * 2、window的优先级为7、unix的系统2的10次方、部分操作系统忽略java线程的优先级
 *
 * @author: sunyf
 * @create: 2018-11-20 20:37
 **/
public class Priority {

    // 开关
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;


    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<Job>();
        // 新建10个线程
        for (int i = 0; i < 10; i++) {
            // 5线程优先级为1，5个线程优先级为10
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            // 新建任务
            Job job = new Job(priority);
            jobs.add(job);
            // 新建线程
            Thread thread = new Thread(job, "Thread:" + i);
            // 设置线程的优先级
            thread.setPriority(priority);
            thread.start();
        }
        //
        notStart = false;
        // 休眠（表示给定单元粒度的时间段）
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;
        //
        for (Job job : jobs) {
            System.out.println("Job Priority:" + job.priority + ", count:" + job.jobCount);
        }

    }

    // 静态内部类
    public static class Job implements Runnable {

        private int priority;
        private long jobCount;

        // 构造方法
        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }

            while (notEnd) {
                Thread.yield();
                jobCount++;
            }

        }

    }


}

/** out
 Job Priority:1, count:611161
 Job Priority:1, count:611780
 Job Priority:1, count:611836
 Job Priority:1, count:611967
 Job Priority:1, count:611441
 Job Priority:10, count:611239
 Job Priority:10, count:612026
 Job Priority:10, count:611094
 Job Priority:10, count:611182
 Job Priority:10, count:611711
 */
