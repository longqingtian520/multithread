package com.criss.wang.service;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import org.springframework.util.StringUtils;

public class MultiThread {

	public static void main(String[] args) {
		ThreadMXBean beans = ManagementFactory.getThreadMXBean();
		ThreadInfo[] infos = beans.dumpAllThreads(false, false);
		for(ThreadInfo ti : infos) {
			System.out.println("thread-id:" + ti.getThreadId() + ", thread-name:" + ti.getThreadName());
		}

		String str = "aa,bb,cc";
		str = str.replace("aa,", "");
		str = str.replace("bb,", "");
		str = str.replace("cc", "");
		System.out.println(str);
		if(StringUtils.isEmpty(str)) {
			System.out.println("空");
		}
	}

}
