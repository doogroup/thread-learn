package com.danq.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class 读写锁ReadWriteLock {

    // 读写锁
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 读锁
    static Lock readLock = readWriteLock.readLock();

    // 写锁
    static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) throws Exception{

        /**
         * 读写锁：读写 互斥 ,写写互斥 ,对于读 比 写频繁的时候 ,优势比较明显
         *
         * tryLock 和 lock 的区别
         *
         * tryLock : 尝试获取锁，获取不到时返回 false
         * lock :   尝试获取锁，获取不到时阻塞等待获取锁
         *
         */

        // 测试 同时多读
        readDobule();

        // 测试 同时读写
        readWriteTest();

        // 测试 同时多写
        writeDobule();
    }

    /**
     * 测试同时写
     */
    private static void writeDobule() throws InterruptedException {

        System.out.println("------测试同时多写-------");

        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(()->{
                // 尝试获取锁
                if(writeLock.tryLock()){
                    System.out.println("线程"+ finalI +" 成功获得写锁");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 释放锁
                    writeLock.unlock();

                } else {
                    System.out.println("线程"+ finalI +" 未获得写锁");
                }

                countDownLatch.countDown();

            }).start();
        }

        countDownLatch.await();

        System.out.println("-----------------------");

    }

    /**
     * 读写锁，测速同时读写
     */
    private static void readWriteTest() throws InterruptedException {
        System.out.println("------测试同时读写-------");

        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(()->{

                if(finalI == 1){

                    // 尝试获取锁
                    if(readLock.tryLock()){
                        System.out.println("线程"+ finalI +" 成功获得读锁");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 释放锁
                        readLock.unlock();

                    } else {
                        System.out.println("线程"+ finalI +" 未获得读锁");
                    }

                } else {

                    // 尝试获取锁
                    if(writeLock.tryLock()){
                        System.out.println("线程"+ finalI +" 成功获得写锁");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 释放锁
                        writeLock.unlock();

                    } else {
                        System.out.println("线程"+ finalI +" 未获得写锁");
                    }



                }

                countDownLatch.countDown();

            }).start();
        }

        countDownLatch.await();

        System.out.println("-----------------------");
    }

    /**
     * 读写锁，允许同时多个读测试
     */
    private static void readDobule() throws InterruptedException {
        System.out.println("------测试同时多读-------");

        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(()->{
                // 尝试获取锁
                if(readLock.tryLock()){
                    System.out.println("线程"+ finalI +" 成功获得读锁");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 释放锁
                    readLock.unlock();

                } else {
                    System.out.println("线程"+ finalI +" 未获得读锁");
                }

                countDownLatch.countDown();

            }).start();
        }

        countDownLatch.await();

        System.out.println("-----------------------");

    }

}
