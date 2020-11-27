package io.coder.demo02.test;

import io.coder.demo02.beanDefinition.TestX;
import io.coder.demo02.beanDefinition.TestY;
import io.coder.demo02.beanDefinition.TestZ;
import io.coder.demo02.config.Appconfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yzd on 2020/11/24
 */
public class Test {
    public static void main(String[] args) {
//        在此处打断点观察
//        org.springframework.context.support.AbstractApplicationContext#invokeBeanFactoryPostProcessors
        System.out.println("START");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(Appconfig.class);
        ac.refresh();
        //正常打印
        System.out.println(ac.getBean(TestY.class));
        //正常打印
        System.out.println(ac.getBean(TestZ.class));
        //异常打印
        //虽然X加了注解，但是被偷梁换柱了，故而异常
        System.out.println(ac.getBean(TestX.class));
    }
}
