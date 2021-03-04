package com.domgo.commons.currthreadutil;

import java.util.concurrent.Semaphore;

/**
 * Semaphoreer控制并发数量,接口限流
 * 
 * @author domgao
 *
 */
public class Semaphoreer {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2);

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "开始执行");
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			}).start();
		}
	}
}
