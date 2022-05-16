package com.zhangyj.spring.business.controller.asyncrsp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * 测试返回Callable和普通对象的区别
 * @author zhangyj
 */
@RestController
public class AsyncResponseController {

    @GetMapping("/asyncResponse")
    public Callable<String> getAsyncResponse(){
        return () -> {
            if(true){
                throw new RuntimeException("此路不通");
            }
            return "你好";
        };
    }


    @GetMapping("/syncResponse")
    public String getSyncResponse(){
        if(true){
            throw new RuntimeException("此路不通");
        }
        return "你好1";
    }
}
