package com.danq.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class 乐观锁CAS {

    static Integer integer = new Integer(0);

    public static void add(){
        integer += 1;
    }

    public static void main(String[] args) throws InterruptedException {

        /**
         * 乐观锁的优点就是效率高，因为不加锁，通过校验的方式来判断是否更改数据
         *
         * 假设我要修改变量A
         * 1. 保存现在变量A的值
         * 2. 一顿操作
         * 3. 检查变量A的值和当前看到的变量A的值是否匹配
         * 如果不匹配代表中途被其他线程改了，根据自定义的策略来执行(报错或重试)
         * 如果匹配则代表中途没有改动则可以直接设置值。
         * https://www.cnblogs.com/jyroy/p/11365935.html 资料
         *
         * 在 Java 中，java.util.concurrent.atomic 中都是原子类，都是通过 CAS 实现的。
         * 如 AtomicInteger 、AtomicLong 等
         *
         */

        AtomicInteger atomicInteger = new AtomicInteger();

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    atomicInteger.addAndGet(1); // +1
                    add();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();

        // 正确结果应该是 100
        System.out.println("AtomicInteger 结果:" + atomicInteger.get());
        System.out.println("Integer 结果：" + integer);
    }

}
