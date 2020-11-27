package cn.coder.yzd.dubbo.demo.xml.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo-xml-provider.xml")
public class DubboDemoXmlProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoXmlProviderApplication.class, args);
    }

}
