package com.zhangyj.spring.business.newcontroller.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CommonWebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
//        converters.add(0, new MappingJackson2HttpMessageConverter());
//        // 解决统一返回体，处理响应内容为String导致的类型转换失败问题
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof StringHttpMessageConverter);
    }

}