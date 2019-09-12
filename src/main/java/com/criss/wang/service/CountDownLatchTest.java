package com.criss.wang.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

	public static void main(String[] arg) throws Exception{
		latchTest();
	}

	private static void latchTest() throws Exception {
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(300);
		ExecutorService exce = Executors.newFixedThreadPool(300);
		for (int i = 0; i < 300; i++) {
			exce.submit(()->{
				try {
					start.await();
					// 测试执行具体的方法
					testLoad();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					end.countDown();
				}
			});
		}
		start.countDown();
		end.await();
		exce.shutdown();
	}

	private static void testLoad() {
		System.out.println("x");
	}

}
