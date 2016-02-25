package com.andy.example.thrift;

import com.andy.example.thrift.contract.HelloThrift;
import com.andy.example.thrift.contract.TestModel;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by 延泽 on 2/26 0026.
 * client
 */
public class Client {
    public static void main(String[] args) {
        TTransport tTransport = new TSocket("127.0.0.1", 9966);
        TBinaryProtocol tBinaryProtocol = new TBinaryProtocol(tTransport);
        HelloThrift.Client client = new HelloThrift.Client(tBinaryProtocol);
        try {
            tTransport.open();
            client.HelloWorld("test", new TestModel(1, "test", "test", "test", "test"));
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            tTransport.close();
        }
    }
}
