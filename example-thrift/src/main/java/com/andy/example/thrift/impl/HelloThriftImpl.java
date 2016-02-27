package com.andy.example.thrift.impl;

import com.andy.example.thrift.contract.HelloThrift;
import com.andy.example.thrift.contract.TestModel;
import org.apache.thrift.TException;

/**
 * Created by 延泽 on 2/25 0025.
 * 服务实现类
 */
public class HelloThriftImpl implements HelloThrift.Iface {
    public String HelloWorld(String content, TestModel testModel) throws TException {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("content:" + content);
        return "success";
    }
}
