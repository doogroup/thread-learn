package com.danq.thread;

import java.util.concurrent.Semaphore;

public class 信号量Semaphore {

    public static void main(String[] args) {

        /**
         * 信号量的作用：比如厕所有5个坑位，如果满的话就需要等待有人出来之后才能允许新人进去
         * 每次执行时都需要 + 1，执行完后 -1 也就是厕所进去和出去。如果满人了 其他线程就会等待，如果
         * 出来人就会允许新的线程进去执行
         */

        final Semaphore semp = new Semaphore(5);

        for (int j = 0; j < 10; j++) {
            int finalJ = j;
            new Thread(()->{
                try {
                    semp.acquire();
                    System.out.println("Accessing: " + finalJ);

                    Thread.sleep((long) (Math.random() * 10000));

                    // 访问完后，释放

                    semp.release();
                    System.out.println("---释放；" + finalJ + "可用许可：" + semp.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
