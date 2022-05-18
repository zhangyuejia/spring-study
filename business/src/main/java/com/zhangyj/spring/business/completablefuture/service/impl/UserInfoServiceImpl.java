package com.zhangyj.spring.business.completablefuture.service.impl;

import com.zhangyj.spring.business.completablefuture.bean.UserInfo;
import com.zhangyj.spring.business.completablefuture.service.UserInfoService;
import com.zhangyj.spring.common.util.ThreadUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyj
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public List<UserInfo> getUserInfo(List<String> userIds) {
        // 模拟请求接口时间
        ThreadUtils.sleepQuiet(200);
        return userIds.stream().map(userId -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setUserName("name" + userId);
            return userInfo;
        }).collect(Collectors.toList());
    }
}
