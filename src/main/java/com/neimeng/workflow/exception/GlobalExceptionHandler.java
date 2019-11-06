package com.neimeng.workflow.exception;

import com.neimeng.workflow.entity.Response;
import com.neimeng.workflow.entity.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Response.failure("Internal Server Exception:" + e.getMessage());
    }

    /**
     * 验证参数异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidatorException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        ObjectError error = errors.get(0);
        Object[] arguments = error.getArguments();
        String errorFields = ((DefaultMessageSourceResolvable)arguments[0]).getDefaultMessage();
        String msg = error.getDefaultMessage();
        return Response.failure("参数校验异常，参数：" + errorFields + "，错误信息：" + msg);
    }

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public Response handleBusinessException(GlobalException e) {
        log.error(e.getMessage(), e);
        return new Response(ResponseCode.GLOBAL_ERROR, e.getMessage());
    }

}
