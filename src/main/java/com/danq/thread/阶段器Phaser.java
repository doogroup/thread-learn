package com.danq.thread;

import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadPoolExecutor;

public class 阶段器Phaser {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 阶段器的作用：其实和栅栏是相似的，区别在于栅栏只有一个阶段，阶段器有多个阶段，就比如流水线的工作，只有当第一阶段完成后第二阶段才能执行
         *
         * 实现，其实只是定义一个阶段数和工人数，当工人都汇报后 阶段+ 1，然后停止阻塞继续执行 ..
         *
         */

        // 可以传入参数，告诉阶段器有几个工人在工作，也可以通过 register() 方法去注册工人，注册几个就有几个工人。
        MyPhaser phaser = new MyPhaser(3);

        for (int i = 0; i < 3; i++) {
            // 注册工人，也可以直接在构造函数传参直接告诉有几个工人
            phaser.register();
            int finalI = i;
            new Thread(()->{

                System.out.println("工人 " + finalI + "号准备就绪");

                // 每次调用该方法就代表有一个工人完成了一个阶段，可以多次执行，每次执行都代表完成了一个新阶段
                // 当一个工人完成后需要等待其他所有工人都完成了该阶段后才能继续执行
                phaser.arriveAndAwaitAdvance();

                System.out.println("进入第一阶段" + finalI);

                // 等待所有工人完成第二阶段
                phaser.arriveAndAwaitAdvance();

                System.out.println("进入第二阶段" + finalI);

                // .. 可以继续执行多个阶段


            }).start();
        }

        /**
         * 其他方法：
         *
         * arriveAndDeregister ： 当前线程的工人退出工作，比如总共有3个工人在工作，那么每个阶段都需要三个人都完成后才能继续下一个阶段，但是有可能我的第二阶段只需要
         * 两个人去完成就ok，就可以通过该方法让一个工人辞职，那么第二阶段只需要两个人到达就可以继续执行。
         *
         * getPhaser ： 得到当前的阶段
         *
         * getRegisteredParties ： 得到当前的工人数量(可以通过 register 注册(每调用一次就 +1 个工人) 也可以直接构造函数传入)
         *
         * register: 注册一个工人，每次调用就注册一个
         *
         * bulkRegister ： 批量注册工人，传入多少，就有几个工人
         *
         * arrive：汇报工人完成一个阶段，只是汇报，不会等待其他工人完成该阶段
         *
         * arriveAndAwaitAdvance： 汇报工人完成一个阶段，并且阻塞等待其他所有工人都完成该阶段后再执行
         *
         * 其他方法可以去下面资料中了解
         *
         * 资料：https://www.cnblogs.com/yanliang12138/p/12797975.html#_label3
         */


        System.out.println("大家都搞定了");

    }

    /**
     * 可以通过实现 onAdvance 方法，实现在不同阶段完成不同的任务，phase 的值就是不同阶段的值，比如 1 就是第一阶段
     */
    public static class MyPhaser extends Phaser{

        public MyPhaser() {
        }

        public MyPhaser(int parties) {
            super(parties);
        }

        public MyPhaser(Phaser parent) {
            super(parent);
        }

        public MyPhaser(Phaser parent, int parties) {
            super(parent, parties);
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            return super.onAdvance(phase, registeredParties);
        }
    }

}
