package com.criss.wang.service;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

	private static final Exchanger<String> exgr = new Exchanger<String>();

	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);


	public static void main(String[] arg) {
		threadPool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					String A = "A信息";
					String B = exgr.exchange(A);
					System.out.println(Thread.currentThread().getName() + "old:A信息；new:" + B);
				}catch(Exception e) {

				}
			}
		});

		threadPool.execute(new Runnable(){
			@Override
			public void run() {
				try {
					String B = "B信息";
					String A = exgr.exchange(B);

					System.out.println(Thread.currentThread().getName() + "old:B信息；new:" + A);
				}catch(Exception e) {

				}

			}
		});

		threadPool.shutdown();
	}
}
