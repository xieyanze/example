package com.andy.example.thrift.client;

import com.andy.example.thrift.contract.HelloThrift;
import com.andy.example.thrift.contract.TestModel;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

/**
 * Created by 延泽 on 2/28 0028.
 * ThriftClient
 */
public class ThriftClient extends AbstractJavaSamplerClient {
    protected TTransport tTransport;
    protected TProtocol tProtocol;
    HelloThrift.Client client;

    static TTransportPoolFactory tTransportPoolFactory = new TTransportPoolFactory();
    static GenericKeyedObjectPool<String, TTransport> pool = new GenericKeyedObjectPool<>(tTransportPoolFactory);

    static {
        pool.setMaxTotal(1000);
        pool.setMaxIdlePerKey(500);
    }

    public String execute(String type) {
        String result = null;
        try {
            tTransport = pool.borrowObject(type);
            tProtocol = new TBinaryProtocol(tTransport);
            client = new HelloThrift.Client(tProtocol);
            if (!tTransport.isOpen())
                tTransport.open();
            result = client.HelloWorld("test", new TestModel(1, "test", "test", "test", "test"));
            pool.returnObject(type, tTransport);
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                pool.invalidateObject(type, tTransport);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        String type = javaSamplerContext.getParameter("type");
        String result = execute(type);
        sr.setResponseData(result, "utf-8");
        sr.setSuccessful(true);
        sr.sampleEnd();
        return sr;
    }
}
