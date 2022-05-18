package com.zhangyj.spring.common.bean.http;

import cn.hutool.core.lang.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * @author zhangyj
 */
@Getter
@RequiredArgsConstructor
public class RequestDto<R, P> {

    /**
     * 默认请求id
     */
    private final String requestId = UUID.fastUUID().toString();

    /**
     * 请求数据
     */
    private final R data;

    private final CompletableFuture<P> future;
}
