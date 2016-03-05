package com.andy.example.thrift.impl;

import com.andy.example.thrift.contract.HelloThrift;
import com.andy.example.thrift.contract.TestModel;
import org.apache.thrift.TException;

/**
 * Created by 延泽 on 2/25 0025.
 * 服务实现类
 */
public class HelloThriftImpl implements HelloThrift.Iface {

    private String type;

    public HelloThriftImpl(String type){
        this.type = type;
    }
    public String HelloWorld(String content, TestModel testModel) throws TException {
//        try {
//            Thread.sleep(50);
//            System.out.println(type);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "success";
    }
}
