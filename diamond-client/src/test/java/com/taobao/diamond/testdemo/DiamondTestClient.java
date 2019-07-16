package com.taobao.diamond.testdemo;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListenerAdapter;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author hzhuqun
 * @create 2019-06-19 16:13
 **/

public class DiamondTestClient {
    public static void main(String[] args) {
        final DiamondManager manager = new DefaultDiamondManager("DEFAULT_GROUP", "switch",
                new ManagerListenerAdapter() {//填写你服务端后台保存过的group和dataId
                    public void receiveConfigInfo(String configInfo) {
                        System.out.println("*************************  start  *************************");
                        System.out.println("switch changed config: " + configInfo);
                        System.out.println("*************************  end  *************************");
                    }
                    public Executor getExecutor() {
                        return null;
                    }
                });


        final DiamondManager manager1 = new DefaultDiamondManager("DEFAULT_GROUP", "data1",
                new ManagerListenerAdapter() {//填写你服务端后台保存过的group和dataId
                    public void receiveConfigInfo(String configInfo) {
                        System.out.println("*************************  start  *************************");
                        System.out.println("data1 changed config: " + configInfo);
                        System.out.println("*************************  end  *************************");
                    }
                    public Executor getExecutor() {
                        return null;
                    }
                });

        final DiamondManager manager2 = new DefaultDiamondManager("DEFAULT_GROUP", "data2",
                new ManagerListenerAdapter() {//填写你服务端后台保存过的group和dataId
                    public void receiveConfigInfo(String configInfo) {
                        System.out.println("*************************  start  *************************");
                        System.out.println("data2 changed config: " + configInfo);
                        System.out.println("*************************  end  *************************");
                    }
                    public Executor getExecutor() {
                        return null;
                    }
                });

        Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
                System.out.println("==================  start  ====================");
                System.out.println("Scheduled query config info !!");
                String switchConfigureInfomation = manager.getAvailableConfigureInfomationFromSnapshot(5000);
                System.out.println("switch config: " + switchConfigureInfomation);
                String data1ConfigureInfomation = manager1.getAvailableConfigureInfomationFromSnapshot(5000);
                System.out.println("data1 config: " + data1ConfigureInfomation);
                String data2ConfigureInfomation = manager2.getAvailableConfigureInfomation(5000);
                System.out.println("data2 config: " + data2ConfigureInfomation);
                System.out.println("==================  end  ====================");
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 20, TimeUnit.SECONDS);


    }

}