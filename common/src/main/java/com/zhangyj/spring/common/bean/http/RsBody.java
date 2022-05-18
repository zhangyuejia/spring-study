package com.zhangyj.spring.common.bean.http;

import com.zhangyj.spring.common.constant.Constant;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangyj
 */
@Data
public class RsBody<T> implements Serializable, Cloneable {

    public static final long serialVersionUID = 6334971409410300128L;

    /**
     * 业务处理码，成功为0，其他为失败
     */
    private String code = "0";

    /**
     * 业务处理消息，默认为成功
     */
    private String message = "成功";

    /**
     * 响应体数据
     */
    private T data;

    public void fail(String message){
        this.code = Constant.DEFAULT_FAIL_CODE;
        this.message = message;
    }

    public void fail(){
        fail(Constant.DEFAULT_FAIL_MESSAGE);
    }
}

