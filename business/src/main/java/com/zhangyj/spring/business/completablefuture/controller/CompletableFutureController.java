package com.zhangyj.spring.business.completablefuture.controller;

import com.zhangyj.spring.common.bean.Queues;
import com.zhangyj.spring.common.bean.http.RequestDto;
import com.zhangyj.spring.common.bean.http.RsBody;
import com.zhangyj.spring.common.util.RsBodyUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * 批量请求第三方接口，20毫秒1次
 * @author zhangyj
 */
@RestController
public class CompletableFutureController {

    @GetMapping("/getThirdPartyApi")
    public RsBody<String> getThirdPartyApi(@RequestParam("userId") String userId){
        return RsBodyUtil.get(() -> {
            CompletableFuture<String> future = new CompletableFuture<>();
            Queues.REQ_QUEUE.put(new RequestDto<>(userId, future));
            return future.get();
        });
    }
}
