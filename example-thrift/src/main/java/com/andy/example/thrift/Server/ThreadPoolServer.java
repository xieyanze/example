package com.andy.example.thrift.server;

import com.andy.example.thrift.contract.HelloThrift;
import com.andy.example.thrift.impl.HelloThriftImpl;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by 延泽 on 2/26 0026.
 * Pool server
 */
public class ThreadPoolServer {
    public static void main(String[] args) {
        try {
            TServerTransport serverTransport = new TServerSocket(9966);
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
            TProcessor processor = new HelloThrift.Processor<>(new HelloThriftImpl("Simple"));

            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            tArgs.processor(processor);
            tArgs.protocolFactory(factory);
           // tArgs.maxWorkerThreads(5);

            TThreadPoolServer server = new TThreadPoolServer(tArgs);

            System.out.println("server begin...........");
            server.serve();
            System.out.println("-------------------");
            server.stop();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
