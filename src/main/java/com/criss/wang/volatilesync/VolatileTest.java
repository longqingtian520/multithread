package com.criss.wang.volatilesync;

public class VolatileTest extends Thread {
	public  static void main(String[] args) {
		MyThread[] threads = new MyThread[100];
		for (int i = 0; i < 100; i++) {
			threads[i] = new MyThread();
		}

		for (int i = 0; i < 100; i++) {
			threads[i].start();
		}
	}
}

class MyThread extends Thread {
	 public static int count;

	public synchronized static void addCount() {
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println("count=" + count);
	}

	@Override
	public void run() {
		addCount();

	}
}
