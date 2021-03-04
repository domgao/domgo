package com.domgo.commons.currthreadutil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；可重用的
 * 
 * @author domgao
 *
 */
public class CyclicBarrierer {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(8);

		for (int i = 0; i < 8; i++) {
			int finalI = i;
			new Thread(() -> {
				try {
					Thread.sleep(finalI * 1000L);
					System.out.println(Thread.currentThread().getName() + "准备就绪");
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}

				System.out.println("开始比赛");
			}).start();
		}
	}

}
