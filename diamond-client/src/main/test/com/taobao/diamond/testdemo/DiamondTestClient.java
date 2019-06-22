package com.taobao.diamond.testdemo;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.ManagerListenerAdapter;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;

import java.util.concurrent.Executor;

/**
 * @author hzhuqun
 * @create 2019-06-19 16:13
 **/

public class DiamondTestClient {
    public static void main(String[] args) {
        DiamondManager manager = new DefaultDiamondManager("DEFAULT_GROUP", "switch",
                new ManagerListenerAdapter() {//填写你服务端后台保存过的group和dataId
                    public void receiveConfigInfo(String configInfo) {
                        System.out.println("changed config: " + configInfo);
                    }
                    public Executor getExecutor() {
                        return null;
                    }
                });
        //设置diamond-server服务的端口
      //  manager.getDiamondConfigure().setPort(8080);

        String availableConfigureInfomation = manager.getAvailableConfigureInfomation(5000);
        System.out.println("start config: " + availableConfigureInfomation);
    }

}