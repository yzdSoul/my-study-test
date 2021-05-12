package kafkademo.message;

/**
 * Created by yzd on 2021/4/30
 */
public class Demo01Message {

    public static final String TOPIC = "DEMO_01";

    /**
     * 编号
     */
    private Integer id;

    public static String getTOPIC() {
        return TOPIC;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
