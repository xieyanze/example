package com.andy.example.thrift.client;

import com.andy.example.thrift.contract.HelloThrift;
import com.andy.example.thrift.contract.TestModel;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by 延泽 on 2/27 0027.
 * NIO client
 */
public class NioClient {
    public static void main(String[] args) {
        TTransport tTransport = null;
        try {
            tTransport = new TFramedTransport(new TSocket("127.0.0.1", 9966));
            TProtocol tProtocol = new TBinaryProtocol(tTransport);
            tTransport.open();
            long start = System.currentTimeMillis();
            HelloThrift.Client client = new HelloThrift.Client(tProtocol);
            for (int i = 0; i < 10000; i++) {
                client.HelloWorld("test", new TestModel(1, "test", "test", "test", "test"));
            }
            System.out.println("execute time:" + (System.currentTimeMillis() - start));
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            assert tTransport != null;
            tTransport.close();
        }
    }
}
