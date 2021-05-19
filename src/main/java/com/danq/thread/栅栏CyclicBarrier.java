package com.danq.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class 栅栏CyclicBarrier {

    public static void main(String[] args) {

        /**
         * 栅栏的作用：所有线程都通过之后一起继续执行，比如说有一个任务是两个阶段的
         * 大家干完一个阶段在干一个阶段，ABC 都干完活后，在一起干下一个阶段
         */

        // 创建一个栅栏，然后告诉他有几个工人 这里是 3 个
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int j = 0; j < 3; j++) {
            int finalJ = j;
            new Thread(()->{
                try {

                    // 第一阶段
                    System.out.println("进入了哦" +finalJ);

                    // 等待大家都干完活后
                    cyclicBarrier.await();

                    // 第二阶段
                    System.out.println("大家都过了哦" + finalJ);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
