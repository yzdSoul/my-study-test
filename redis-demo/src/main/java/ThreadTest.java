import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yzd on 2021/11/30
 */
public class ThreadTest {

    private static final int threadNum = 10;
    private static CountDownLatch cd1 = new CountDownLatch(threadNum);

    static class DelayMessage implements Runnable {

        @Override
        public void run() {
            try {
                cd1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AppTest appTest = new AppTest();
            try {
                appTest.consumerDelayMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        AppTest appTest = new AppTest();
        appTest.productionDelayMessage();
        for (int i = 0; i < threadNum; i++) {
            new Thread(new DelayMessage()).start();
            cd1.countDown();
        }
    }

}
