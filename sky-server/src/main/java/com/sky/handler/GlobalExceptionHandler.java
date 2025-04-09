package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
//这是 Spring Boot 提供的一个增强注解，用于定义全局的异常处理器、数据绑定和数据预处理。
// 配合 @ExceptionHandler 注解，可以捕获全局范围内控制器抛出的异常，并以统一格式返回给前端。
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final HttpMessageConverters messageConverters;

    public GlobalExceptionHandler(HttpMessageConverters messageConverters) {
        this.messageConverters = messageConverters;
    }

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    //处理SQL异常
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
