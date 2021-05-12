package com.yzd.java.onjava8.callback;

/**
 * Created by yzd on 2021/5/6
 */
public class Server {
    public void getClientMsg(CSCallBack callBack, String msg) {
        System.out.println("服务端：接收到客户端的信息为："+ msg);

        //模拟服务端处理数据
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("服务端：数据处理成功，返回状态码 200");
        String status = "200";
        callBack.process(status);
    }
}
