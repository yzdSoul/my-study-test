import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yzd on 2021/11/30
 *
 * https://mp.weixin.qq.com/s/doO1LCuQmQO9pQKE8EEfRg
 *
 * Redis 延时任务，高手养成篇
 */
public class AppTest {

    /**
     * @return
     * @throws IOException
     */
    public static JedisCluster jedisCluster() throws IOException {
        //创建jedisCluster
        HashSet<HostAndPort> nodes = new HashSet<>();
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        nodes.add(new HostAndPort("redis-cluster-g5-01.online.mofeed.yuekecs.com", 7005));
        //        int expireSecond = 300;
//        cluster.set("yzd-test", "yzd-demo","NX", "EX", expireSecond);
//        String result = cluster.get("yzd-test");
//        System.out.println(result);
//        cluster.close();
        return new JedisCluster(nodes,2000,2000,20,"9icaishi.net",config);
    }

    public void productionDelayMessage() throws IOException {
        for (int i = 0; i < 5; i++) {
            //延迟3秒
            Calendar call = Calendar.getInstance();
            call.add(Calendar.SECOND, 3);
            int secound3later = (int) (call.getTimeInMillis() / 1000);
            AppTest.jedisCluster().zadd("OrderId", secound3later, "OID00000001" + i);
            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "OID00000001" + i);
        }
    }

    public void consumerDelayMessage() throws IOException {
        JedisCluster cluster = AppTest.jedisCluster();
        while (true) {
            Set<Tuple> items = cluster.zrangeByScoreWithScores("OrderId", 0, 1);
            if (items == null || items.isEmpty()) {
                System.out.println("当前没有等待任务");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            int score = (int) ((Tuple) items.toArray()[0]).getScore();
            Calendar calendar = Calendar.getInstance();
            int nowSecond = (int) (calendar.getTimeInMillis() / 1000);
            if (nowSecond >= score) {
                String orderId = ((Tuple) items.toArray()[0]).getElement();
                cluster.zrem("OrderId", orderId);
                System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为："+ orderId);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        AppTest appTest = new AppTest();
        appTest.productionDelayMessage();
        appTest.consumerDelayMessage();
    }
}
