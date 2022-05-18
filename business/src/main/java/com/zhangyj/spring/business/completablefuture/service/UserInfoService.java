package com.zhangyj.spring.business.completablefuture.service;

import com.zhangyj.spring.business.completablefuture.bean.UserInfo;

import java.util.List;

/**
 * @author zhangyj
 */
public interface UserInfoService {

    /**
     * 模拟请求第三方用户信息接口（支持批量）
     * @param userIds 用户ID
     * @return 接口响应
     */
    List<UserInfo> getUserInfo(List<String> userIds);
}
