package com.andy.example.grpc.helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by 延泽 on 3/5 0005.
 * helloWorld
 */
public class HelloWorldServer {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldServer.class);

    private Server server;

    private void start() throws IOException {
        int port = 9988;
        server = ServerBuilder.forPort(port).addService(GreeterGrpc.bindService(new GreeterImpl())).build().start();
        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            HelloWorldServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }


    private class GreeterImpl implements GreeterGrpc.Greeter {

        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            logger.info("request:" + request.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
