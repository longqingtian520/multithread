package com.criss.wang.service;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BankWaterService implements Runnable {

	private CyclicBarrier c = new CyclicBarrier(4, this);

	private Executor executor = Executors.newFixedThreadPool(4);

	private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

	private void count() {
		for (int i = 0; i < 4; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "====>" + 1);
					map.put(Thread.currentThread().getName(), 1);
					try {
						c.await();
					} catch (Exception e) {

					}
				}
			});
		}
	}

	@Override
	public void run() {
		int result = 0;
		for (Entry<String, Integer> sheet : map.entrySet()) {
			result += sheet.getValue();
		}
		map.put("result", result);
		System.out.println(result);

	}

	public static void main(String[] a) {
		new BankWaterService().count();
	}

}
