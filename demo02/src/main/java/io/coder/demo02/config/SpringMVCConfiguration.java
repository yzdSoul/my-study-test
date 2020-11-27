package io.coder.demo02.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import io.coder.demo02.interceptor.FirstInterceptor;
import io.coder.demo02.interceptor.SecondInterceptor;
import io.coder.demo02.interceptor.ThirdInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yzd on 2020/11/19
 */
@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public FirstInterceptor firstInterceptor() {
        return new FirstInterceptor();
    }

    @Bean
    public SecondInterceptor secondInterceptor() {
        return new SecondInterceptor();
    }

    @Bean
    public ThirdInterceptor thirdInterceptor() {
        return new ThirdInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截器一
        registry.addInterceptor(this.firstInterceptor()).addPathPatterns("/**");
        //拦截器二
        registry.addInterceptor(this.secondInterceptor()).addPathPatterns("/users/current_user");
        //拦截器三
        registry.addInterceptor(this.thirdInterceptor()).addPathPatterns("/**");
    }

    //通过 Bean 的方式配置 Servlet、Filter、Listener示例
    @Bean
    public ServletRegistrationBean testServlet01(){
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>(
                new HttpServlet() {
                    @Override
                    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                        logger.info("[doGet][uri: {}]", req.getRequestURI());
                    }
                }
        );
        servletServletRegistrationBean.setUrlMappings(Collections.singleton("/test/01"));
        return servletServletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean testFilter01(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(
                new Filter() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                        logger.info("[doFilter]");
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
        );
        filterFilterRegistrationBean.setUrlPatterns(Collections.singleton("/test/*"));
        return filterFilterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<?> testListener01(){
        return new ServletListenerRegistrationBean<>(new ServletContextListener() {

            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("[contextInitialized]");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
            }
        });
    }

    public FilterRegistrationBean<CorsFilter> corsFilter() {

        //创建 UrlBasedCorsConfigurationSource 配置源，类似 CorsRegistry 注册表
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建 FilterRegistrationBean 对象
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(source)); //创建 CorsFilter 过滤器
        bean.setOrder(0);//设置Order 排序。这个顺序很重要哦，为避免麻烦请设置在最前
        //创建 CorsConfiguration 配置，相当于 CorsRegistration 注册信息
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*"));//允许所有请求来源
        config.setAllowCredentials(true);//允许发送 Cookie
        config.addAllowedMethod("*");//允许所有请求 Method
        config.setAllowedHeaders(Collections.singletonList("*"));//允许所有请求 Header
//        config.setExposedHeaders(Collections.singletonList("*"));//允许所有响应 Header
        config.setMaxAge(1800L);// 有效期 1800秒
        source.registerCorsConfiguration("/**", config);
        return bean;
    }


//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //增加 XML 消息转换器
//        Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
//        xmlBuilder.indentOutput(true);
//        converters.add(new MappingJackson2HttpMessageConverter(xmlBuilder.build()));
//    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建 FastJsonHttpMessageConverter 对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //自定义 FastJson 配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.defaultCharset());//设置默认字符集
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);// 剔除循环引用
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //设置支持的 MediaType
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList((MediaType.APPLICATION_JSON),
                MediaType.APPLICATION_XML));
        //添加到 converters 中
        converters.add(0, fastJsonHttpMessageConverter);//注意，添加到最开头，放在 MappingJackson2XmlHttpMessageConverter 前面

        //增加 XML 消息转换器
        Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
        xmlBuilder.indentOutput(true);
        converters.add(new MappingJackson2HttpMessageConverter(xmlBuilder.build()));
    }
}
