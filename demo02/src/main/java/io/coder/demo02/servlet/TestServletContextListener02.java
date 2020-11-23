package io.coder.demo02.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by yzd on 2020/11/23
 */
//添加 @ServletComponentScan 注解 开启扫描
@WebListener
public class TestServletContextListener02 implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("[contextInitialized]");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
