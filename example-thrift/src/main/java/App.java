
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
 * Created by 延泽 on 2/25 0025..
 * server
 */
public class App {

    private int id;


    public static void main(String[] args) {
        try {
            TServerTransport serverTransport = new TServerSocket(9966);
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
            TProcessor processor = new HelloThrift.Processor<>(new HelloThriftImpl());

            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            tArgs.processor(processor);
            tArgs.protocolFactory(factory);

            TServer server = new TThreadPoolServer(tArgs);
            System.out.println("server begin...........");
            server.serve();
            System.out.println("-------------------");
            server.stop();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
