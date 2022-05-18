package com.zhangyj.spring.common.util;



import com.zhangyj.spring.common.bean.http.RsBody;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangyj
 */
@Slf4j
public class RsBodyUtil {

    public interface ITask {
        /**
         * 执行任务
         * @throws Exception 异常
         */
        void exec() throws Exception;
    }

    public interface IDataTask<T> {
        /**
         * 执行任务
         * @return T
         * @throws Exception 异常
         */
        T exec() throws Exception;
    }

    public static RsBody<Boolean> get(ITask task){
        RsBody<Boolean> rsBody = new RsBody<>();
        try {
            task.exec();
        } catch (Exception e) {
            log.error("抛出异常", e);
            rsBody.fail(e.getMessage());
        }
        return rsBody;
    }

    public static  <T> RsBody<T> get(IDataTask<T> task){
        return get(task, null);
    }

    public static  <T> RsBody<T> get(IDataTask<T> task, String defaultErrorMsg){
        RsBody<T> rsBody = new RsBody<>();
        try {
            rsBody.setData(task.exec());
        } catch (Exception e) {
            log.error("抛出异常", e);
            if(defaultErrorMsg != null){
                rsBody.fail(defaultErrorMsg);
            }else {
                rsBody.fail(e.getCause() != null? e.getCause().getMessage(): e.getMessage());
            }
        }
        return rsBody;
    }
}
