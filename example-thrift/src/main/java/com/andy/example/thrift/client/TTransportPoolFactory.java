package com.andy.example.thrift.client;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Objects;

/**
 * Created by 延泽 on 2/29 0029.
 * transportPool
 */
public class TTransportPoolFactory extends BaseKeyedPooledObjectFactory<String, TTransport> {
    @Override
    public TTransport create(String s) throws Exception {
        if (Objects.equals(s, "simple")) {
            return new TSocket("127.0.0.1", 9966);
        }
        return new TFramedTransport(new TSocket("127.0.0.1", 9977));
    }

    @Override
    public PooledObject<TTransport> wrap(TTransport tTransport) {
        return new DefaultPooledObject<>(tTransport);
    }
}
