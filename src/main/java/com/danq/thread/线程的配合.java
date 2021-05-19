package com.danq.thread;

public class 线程的配合 {

    private static PackageInfo packageInfo = new PackageInfo();

    public static void main(String[] args) {

        new Thread(()->{

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            packageInfo.notifyAll();

        }).start();

        for (int i = 0; i < 3; i++) {

            new Thread(()->{

                try {
                    packageInfo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("非常ok");

            }).start();

        }

    }

}
