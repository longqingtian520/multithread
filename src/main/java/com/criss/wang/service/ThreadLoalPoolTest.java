package com.criss.wang.service;

import java.awt.geom.Point2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLoalPoolTest {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main1(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(40);
		for(int i = 0; i< 130 ; i++) {
			int num = i;
			exec.execute(() ->{
				System.out.println(Thread.currentThread().getName() + "---------->" + DateUtils.getTimeStr(new Date()) + "------->" + Math.random());
				System.out.println(Thread.currentThread().getName() + "num:" + num + "---------->" + DateUtils.addNUms(num));
			});
		}
		exec.shutdown();
	}

	public static String getTime(Date date) {
		String dateStr = "";
		try {
			return sdf.format(date);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	public static void main(String[] args) throws Exception{
		Point2D.Double point = new Point2D.Double(36.232323, 123.343434);
		long time = new SimpleDateFormat("HHmmss.SSddMMyy").parse("112232.00090719").getTime() + 8 * 3600 * 1000;
		Date date = new Date(time);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		System.out.println(time);
	}

}
