package com.andy.example.thrift.server;

import com.andy.example.thrift.contract.HelloThrift;
import com.andy.example.thrift.impl.HelloThriftImpl;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

/**
 * Created by 延泽 on 2/26 0026.
 * NioServer
 */
public class NIOServer {
    public static void main(String[] args) {
        try {
            TNonblockingServerTransport tNonblockingServerTransport = new TNonblockingServerSocket(9977);
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
            TProcessor processor = new HelloThrift.Processor<>(new HelloThriftImpl("Nio"));

            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(tNonblockingServerTransport);
            tArgs.processor(processor);
            tArgs.protocolFactory(factory);
            //tArgs.maxWorkerThreads(20);
            tArgs.selectorThreads(160);
            tArgs.workerThreads(160);
            TServer server = new TThreadedSelectorServer(tArgs);
            System.out.println("NIO server begin...........");
            server.serve();
            System.out.println("-------------------");
            server.stop();
        } catch (TException e) {
            e.printStackTrace();
        }

    }
}
