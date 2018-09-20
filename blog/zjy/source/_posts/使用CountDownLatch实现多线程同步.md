title: 使用CountDownLatch实现多线程同步
author: zjy
date: 2018-09-20 13:32:40
tags:
---
## 大批量数据处理–分批多线程
	需求：如果有一批大量的数据需要处理，单线程显然会显得很慢，我们把这一大批数据分为多个小批，然后多线程同步处理， 
    并等所有子线程都处理完毕之后再继续执行下面的操作。使用CountDownLatch。
    
## 原理
	利用 
     new CountDownLatch(100);//给定count初始值100
     CountDownLatch.await();//如果count不为0，则会阻塞当前线程，为0，当前线程继续执行
     CountDownLatch.countDown();//调用一次，count--;
     每个子线程执行完毕调用减一方法，主线程使用await等待。最终所有子线程执行完毕，主线程继续执行
     
## 代码如下
		package com.jbns.easymaster.service.report.util;

        import java.util.Random;
        import java.util.concurrent.CountDownLatch;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        import java.util.concurrent.TimeUnit;

        public class ThreadUtil {
            public static void main(String[] args) {
                ExecutorService executor = Executors.newCachedThreadPool();

                CountDownLatch latch = new CountDownLatch(100);
                long begin = System.currentTimeMillis();
                for (int i = 0; i < 100; i++) {
                    Worker w1 = new Worker(latch, "" + (i + 1) + "");
                    executor.execute(w1);
                }

                //如果是当前主线程直接写latch.await.否则再起个父线程
                /*WorkerOver workerOver = new WorkerOver(latch);
                executor.execute(workerOver);*/

                System.out.println("主线程在等待所有子线程处理完毕");
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("所有子线程执行完毕，继续执行主线程,耗时:" + (System.currentTimeMillis() - begin));
                executor.shutdown();
            }
        }

        class WorkerOver implements Runnable {

            private CountDownLatch downLatch;

            public WorkerOver(CountDownLatch downLatch) {
                this.downLatch = downLatch;
            }

            public void run() {
                System.out.println("主线程在等待所有子线程处理完毕");
                try {
                    this.downLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("所有子线程执行完毕，继续执行主线程");
            }
        }

        class Worker implements Runnable {

            private CountDownLatch downLatch;
            private String name;

            public Worker(CountDownLatch downLatch, String name) {
                this.downLatch = downLatch;
                this.name = name;
            }

            public void run() {
                this.startWork();
                try {
                    //TODO 写各个子线程需要处理的业务逻辑(这里就是每条数据的具体业务处理)
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                System.out.println("子线程:" + this.name + "处理完毕！");
                this.downLatch.countDown();
            }

            public void startWork() {
                System.out.println("子线程：" + this.name + " ,开始处理业务逻辑!");
            }
        }