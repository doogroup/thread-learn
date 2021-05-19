package com.danq.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class 线程池 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> submit = executorService.submit(() -> {
            System.out.println("进入");
            Thread.sleep(5000);
            System.out.println("退出");
            return "史喜超";
        });

        // 得到结果
        String o = submit.get();

        System.out.println(o);

    }

}
