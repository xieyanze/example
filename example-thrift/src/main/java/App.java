import com.andy.example.thrift.client.ThriftClient;

/**
 * Created by 延泽 on 2/25 0025..
 * server
 */
public class App {
    static Integer total = 0;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                ThriftClient client = new ThriftClient();
                while (true) {
                    client.execute("nio");
                    synchronized (total) {
                        total++;
                    }
                }
            }).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    double tps = total / ((System.currentTimeMillis() - start) / 1000);
                    System.out.println("total:" + total + "tps:" + tps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
