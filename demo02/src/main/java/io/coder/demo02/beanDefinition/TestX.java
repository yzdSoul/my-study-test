package io.coder.demo02.beanDefinition;

import org.springframework.stereotype.Component;

/**
 * Created by yzd on 2020/11/24
 */

@Component
public class TestX {
    public TestX() {
        System.out.println("X Constructor");
    }
}
