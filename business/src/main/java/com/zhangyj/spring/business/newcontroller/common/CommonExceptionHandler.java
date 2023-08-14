package com.zhangyj.spring.business.newcontroller.common;

import com.zhangyj.spring.common.bean.http.RsBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RsBody<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.info("抛出异常", e);
        BindingResult bindingResult = e.getBindingResult();
//        String message = bindingResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        RsBody<?> objectRsBody = new RsBody<>();
        objectRsBody.setMessage(bindingResult.getFieldError().getDefaultMessage());
        return objectRsBody;
    }

    @ExceptionHandler({Exception.class})
    public RsBody<?> handleException(Exception e){
        log.info("抛出异常", e);
        RsBody<?> objectRsBody = new RsBody<>();
        objectRsBody.setMessage(e.getMessage());
        return objectRsBody;
    }
}
