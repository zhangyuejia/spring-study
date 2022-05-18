package com.zhangyj.spring.common.bean;

import com.zhangyj.spring.common.bean.http.RequestDto;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 内存队列
 * @author zhangyj
 */
public class Queues {

    public static final BlockingQueue<RequestDto<String, String>> REQ_QUEUE = new LinkedBlockingQueue<>();
}
