package com.danq.thread;

public class 悲观锁synchronized及锁升级 {

    public static void main(String[] args) {

        /**
         * synchronized 升级过程 -》 无锁 VS 偏向锁 VS 轻量级锁 VS 重量级锁
         *
         * https://blog.csdn.net/tongdanping/article/details/79647337 资料
         *
         * 偏向锁：HotSpot 作者发现，常常是一个线程多次获得通一把锁，所以为了避免这种一个人竞争的情况，引入了偏向锁
         * 如果是同一个线程之间获得锁，无需争抢。
         *
         * 轻量级锁：当抢占锁的线程不多，并且持有锁的时间不长时，就不会进行阻塞线程，而是使用自旋的方式让线程等待
         * 这样可以避免线程阻塞导致的CPU状态转换，而自旋会一直循环尝试获得锁。
         *
         * 重量级锁：当抢占锁的线程较多，并且持有锁的时间较长时，自旋的线程多而且次数也多，为了避免这种长时间的CPU空转
         * 就将所有未获得锁的线程阻塞。
         *
         */

        /**
         * Synchronized修饰普通同步方法：锁对象当前实例对象；
         * Synchronized修饰静态同步方法：锁对象是当前的类Class对象；
         * Synchronized修饰同步代码块：锁对象是Synchronized后面括号里配置的对象，这个对象可以是某个对象（xlock），也可以是某个类（Xlock.class）；
         */



    }

}
