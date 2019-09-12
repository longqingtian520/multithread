package com.criss.wang.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import com.criss.wang.entity.Wrapper;

public class BlockingQueueTest {

	private ExecutorService exec = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<Runnable>(0), new DiscardPolicy());
	private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

	public static void main(String[] args) throws InterruptedException {
		new BlockingQueueTest().test();
	}

	@PreDestroy
	private void shutdown() {
		exec.shutdown();
	}

	public void test() throws InterruptedException {
		Wrapper<Integer> a = new Wrapper<>();
		a.setT(0);

		exec.submit(() -> {
			try {
				while (true) {
					int b = queue.take();
					System.out.println("b:" + b);
					System.out.println(Thread.currentThread().getName() + "=====>" + a.getT());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		for (int i = 0; i < 13; i++) {
			if (i % 2 == 0) {
				a.setT(a.getT() + 1);
			}
		}

		for (int j = 0; j < 20; j++) {
			queue.put(j);
			System.out.println(j);
		}

		for (int k = 0; k < 12; k++) {
		}
		System.out.println(Thread.currentThread().getName() + "-------->" + a.getT());

	}

}
