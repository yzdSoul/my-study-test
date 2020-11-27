package io.coder.demo02.test;

import io.coder.demo02.beanDefinition.TestY;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * Created by yzd on 2020/11/24
 */
public class TestBeanFactoryPostPorcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //转换为子类，因为父类没有添加beanDefintion对象的api
        DefaultListableBeanFactory defaultbf = (DefaultListableBeanFactory) beanFactory;
        //添加一个beanDefinition对象，方便测试动态添加
        GenericBeanDefinition y = new GenericBeanDefinition();
        y.setBeanClass(TestY.class);
        //添加一个beanDefinition对象，原本这个Y没有被spring扫描到
        defaultbf.registerBeanDefinition("y", y);

        //得到一个已经被扫描出来的beanDefintion对象x
        //因为X本来就被扫描出来了，所以是直接从map中获取
        BeanDefinition x = defaultbf.getBeanDefinition("TestX");
        //修改这个X的beanDefintion对象的class为Z
        //原本这个x代表的class为X.class；现在为Z.class
        x.setBeanClassName("io.coder.demo02.beanDefinition.TestZ");
    }
}
