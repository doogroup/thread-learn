package com.danq.thread;

import java.util.concurrent.locks.ReentrantLock;

public class 可重入锁ReentrantLock {

    public static void main(String[] args) {

        /**
         * 可重入锁：当某一个线程持有锁后，再次获取同一把锁可以直接获取而不需要抢占。
         */

        ReentrantLock reentrantLock = new ReentrantLock();

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(()->{

                System.out.println("线程" + finalI + "正在尝试获得锁...");
                // 尝试获得锁，获取不到时阻塞等待获取成功
                reentrantLock.lock();
                System.out.println("线程" + finalI + "成功获得锁并再次尝试获得锁");

                reentrantLock.lock();

                System.out.println("线程" + finalI + "第二次获得锁");

                // 获得两次就要释放两次
                reentrantLock.unlock();
                reentrantLock.unlock();


            }).start();
        }

    }

}
