package com.zhangyj.spring.business.completablefuture.task;

import com.zhangyj.spring.common.bean.Queues;
import com.zhangyj.spring.business.completablefuture.bean.UserInfo;
import com.zhangyj.spring.business.completablefuture.service.UserInfoService;
import com.zhangyj.spring.common.bean.http.RequestDto;
import com.zhangyj.spring.common.util.ThreadUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 消费者线程
 * @author zhangyj
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerTask {

    private final UserInfoService userInfoService;

    @PostConstruct
    public void init(){
        @SuppressWarnings("AlibabaThreadShouldSetName")
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        threadPool.execute(() -> ThreadUtils.loop(() -> {
            List<RequestDto<String, String>> list = new ArrayList<>();
            Queues.REQ_QUEUE.drainTo(list, 1000);
            if(CollectionUtils.isEmpty(list)){
                ThreadUtils.sleepQuiet(1000);
            }
            try {
                List<UserInfo> userInfos = userInfoService.getUserInfo(list.stream().map(RequestDto::getData).collect(Collectors.toList()));
                if(CollectionUtils.isEmpty(list)){
                    return;
                }
                Map<String, UserInfo> userInfoMap = userInfos.stream().collect(Collectors.toMap(UserInfo::getUserId, Function.identity()));
                for (RequestDto<String, String> dto : list) {
                    UserInfo userInfo = userInfoMap.get(dto.getData());
                    String result = userInfo == null? "查无此人": userInfo.getUserName();
                    dto.getFuture().complete(result);
                }
            }catch (Exception e){
                list.forEach(r -> r.getFuture().completeExceptionally(e));
            }
            ThreadUtils.sleepQuiet(20L);
        }, "消费异常", 1000L));
    }
}
