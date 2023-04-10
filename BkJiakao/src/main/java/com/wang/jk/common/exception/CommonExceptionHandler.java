package com.wang.jk.common.exception;

import com.wang.jk.common.util.JsonVos;
import com.wang.jk.common.util.Streams;
import com.wang.jk.pojo.result.CodeMsg;
import com.wang.jk.pojo.vo.json.JsonVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public JsonVo handleThrowable(Throwable t) {
        log.error("【出现异常】", t);

        if (t instanceof CommonException) {
            CommonException ce = (CommonException) t;
            return JsonVos.error(ce.getCode(), ce.getMessage());
        } else if (t instanceof BindException) {
            return handle((BindException) t);
        } else if (t instanceof ConstraintViolationException) {
            return handle((ConstraintViolationException) t);
        } else if (t instanceof AuthorizationException) {
            return JsonVos.error(CodeMsg.LACK_PERMISSION);
        }
        Throwable cause = t;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        if (cause != t) {
            return handleThrowable(cause);
        }
        return JsonVos.error();
    }

    private JsonVo handle(BindException be) {
        List<ObjectError> errors = be.getBindingResult().getAllErrors();
        List<String> msgs = Streams.map(errors, ObjectError::getDefaultMessage);
        String msg = StringUtils.collectionToDelimitedString(msgs, ", ");
        return JsonVos.error(CodeMsg.VALIDATE_ERROR.getCode(), msg);
    }

    private JsonVo handle(ConstraintViolationException cve) {
        List<String> msgs = Streams.map(cve.getConstraintViolations(), ConstraintViolation::getMessage);
        String msg = StringUtils.collectionToDelimitedString(msgs, ", ");
        return JsonVos.error(CodeMsg.VALIDATE_ERROR.getCode(), msg);
    }
}
