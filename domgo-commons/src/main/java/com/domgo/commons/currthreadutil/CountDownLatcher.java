package com.domgo.commons.currthreadutil;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch一般用于某个线程等待若干个其他线程执行完任务之后，它才执行；不可重复使用
 * 
 * @author domgao
 *
 */
public class CountDownLatcher {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(8);
		new Thread(() -> {
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("800米比赛结束，准备清空跑道并继续跨栏比赛");
		}).start();

		for (int i = 0; i < 8; i++) {
			int finalI = i;
			new Thread(() -> {
				try {
					Thread.sleep(finalI * 1000L);
					System.out.println(Thread.currentThread().getName() + "到达终点");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
			}).start();
		}
	}

}
