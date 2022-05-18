package com.zhangyj.spring.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 线程工具类
 * @author zhangyj
 */
@Slf4j
public class ThreadUtils {

    public interface DefaultTask {
        /**
         * 执行任务
         */
        void exec();
    }

    public interface ITask {
        /**
         * 执行任务
         * @throws Exception 异常
         */
        void exec() throws Exception;
    }

    public static void loop(ITask task, String errorDesc, Long millSeconds) {
        //noinspection InfiniteLoopStatement
        while (true){
            catchQuiet(task, errorDesc, millSeconds);
        }
    }

    public static void catchQuiet(ITask task, String errorDesc) {
        try {
            task.exec();
        } catch (Exception e) {
            log.error(errorDesc, e);
        }
    }

    public static void catchQuiet(ITask task, String errorDesc, Long millSeconds) {
        try {
            task.exec();
        } catch (Exception e) {
            log.error(errorDesc, e);
            sleepQuiet(millSeconds);
        }
    }

    public static void sleepQuiet(long millSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(millSeconds);
        } catch (InterruptedException e) {
            log.error("线程睡眠异常", e);
        }
    }
}

