package com.sunyf;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @program: the-art-of-java-concurrency-programming
 * @description: 工作窃取算法：fork/join
 * @author: sunyf
 * @create: 2018-11-26 14:14
 **/
public class ForkJoinDemo extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;

    private int start;
    private int end;

    public ForkJoinDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 大于阀值就分裂
            int middle = (start + end) / 2;
            ForkJoinDemo leftTask = new ForkJoinDemo(start, middle);
            ForkJoinDemo rightTask = new ForkJoinDemo(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待执行完
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并结果
            sum = leftResult + rightResult;
        }

        return sum;
    }

    public static void main(String[] args) {
        //
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 计算任务
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(1, 4);
        // 提交任务
        Future<Integer> result = forkJoinPool.submit(forkJoinDemo);
        //
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
