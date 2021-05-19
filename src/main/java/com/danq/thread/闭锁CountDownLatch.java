package com.danq.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class 闭锁CountDownLatch {

    static volatile Integer number = 0;

    public static void add(){
        number+=1;
    }

    public static void main(String[] args) throws InterruptedException {

        /**
         * 闭锁的作用，等待所有人都干完活之后在继续，就像所有工人都干完活后汇报给老板
         */

        AtomicInteger atomicInteger = new AtomicInteger();

        // 有 10 个人在工作，只有10个人全部都干完活后 await 才放开
        CountDownLatch latch = new CountDownLatch(10);

        for (int j = 0; j < 10; j++) {
            new Thread(()->{
                for (int i = 0; i < 10; i++) {
//                    atomicInteger.addAndGet(1);
                    add();
                }
                // 工人 j 说我干完了
                latch.countDown();
            }).start();
        }

        // 等待所有工人都干完活
        latch.await();

//        System.out.println("结果:" + atomicInteger.get());
        System.out.println("结果:" + number);

    }

}
