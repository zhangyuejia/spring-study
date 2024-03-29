package com.zhangyj.spring.business.newcontroller.controller;

import com.zhangyj.spring.business.completablefuture.bean.UserInfo;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.File;

@RestController
public class NewController {

    @RequestMapping("/testBodyStr")
    public String testBodyStr(){
        return "Hello. This is new controller";
    }

    @RequestMapping("/test")
    public void test(){
        System.out.println(1);
    }

    @RequestMapping("/testBodyInt")
    public Integer testBodyInt(){
        return 1;
    }

    @RequestMapping("/testBodyObj")
    public UserInfo testBodyObj(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("1");
        userInfo.setUserName("kk1");
        return userInfo;
    }

    @RequestMapping("/testBodyValid")
    public UserInfo testBodyValid(@RequestBody @Validated UserInfo userInfo){
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId(userInfo.getUserId());
        userInfo1.setUserName(userInfo.getUserName());
        return userInfo;
    }

    @RequestMapping("/testBodyException")
    public UserInfo testBodyException(){
        if (true) {
            throw new RuntimeException("这是异常信息");
        }
        return null;
    }

}
