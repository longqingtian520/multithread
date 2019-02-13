package com.criss.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.criss.wang.config.TaskExecutorConfig;
import com.criss.wang.service.AsyncTaskExecutor;

@RestController
@RequestMapping("/task")
public class TaskExecutorController {

    @Autowired
    private TaskExecutorConfig taskExecutorConfig;

    @Autowired
    private AsyncTaskExecutor asyncTaskExecutor;

    @RequestMapping(value = "/async", method = RequestMethod.GET)
    public String taskExecutorAsync(){
        System.out.println("主线程开始执行任务");

        asyncTaskExecutor.asyncTask();
        asyncTaskExecutor.asyncTaskB();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束执行任务");
        return "success";
    }

    @RequestMapping(value = "/executor", method = RequestMethod.GET)
    public String taskExecutor(){
        for (int i = 0; i < 10; i++) {
            try {
                asyncTaskExecutor.doTask1(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }


    //内部类调用线程
    @RequestMapping(value = "/executorB", method = RequestMethod.GET)
    public String taskExecutorB(){

        taskExecutorConfig.asyncServiceExecutor().execute(new taskDemo("threadDemo"));
        return "success";
    }

    class taskDemo implements Runnable{
        private String param;

        public taskDemo(String param){
            this.param = param;
        }

        @Override
        public void run() {
            if ("threadDemo".equals(param)){
                System.out.println("thread run");
            }
        }
    }


}
