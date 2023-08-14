package com.zhangyj.spring.business.completablefuture.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

/**
 * @author zhangyj
 */
@Getter
@Setter
public class UserInfo {

    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @NotNull(message = "用户名不能为空")
    private String userName;
}
