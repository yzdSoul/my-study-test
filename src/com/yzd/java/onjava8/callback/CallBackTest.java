package com.yzd.java.onjava8.callback;

/**
 * Created by yzd on 2021/5/6
 */
public class CallBackTest {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);
        client.sendMsg("Server Hello");
    }
}
