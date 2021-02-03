package com.yzd.java.kuangstudy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by yzd on 2021/2/3
 */
public class TestThread02 extends Thread{

    private String url;
    private String name;

    public TestThread02(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载的文件名为：" + name);
    }
//多线程下载文件
    public static void main(String[] args) {
        TestThread02 t1 = new TestThread02("https://pic4.zhimg.com/80/v2-e704fd365cc441198578ba173a327217_400x224.png","抖音1.png");
        TestThread02 t2 = new TestThread02("https://pic4.zhimg.com/80/v2-e704fd365cc441198578ba173a327217_400x224.png","抖音2.png");
        TestThread02 t3 = new TestThread02("https://pic4.zhimg.com/80/v2-e704fd365cc441198578ba173a327217_400x224.png","抖音3.png");
            t1.start();
            t2.start();
            t3.start();
    }

}

class WebDownloader {
    public void downloader(String url,String name) {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
