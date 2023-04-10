package com.wang.jk.common.util;

import com.wang.jk.common.exception.CommonException;
import com.wang.jk.pojo.result.CodeMsg;
import com.wang.jk.pojo.vo.json.DataJsonVo;
import com.wang.jk.pojo.vo.json.ListJsonVo;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.list.ListVo;

public class JsonVos {
    public static JsonVo ok(CodeMsg codeMsg) {
        return new JsonVo(codeMsg);
    }

    public static <T> DataJsonVo<T> ok(T data) {
        return new DataJsonVo<>(data);
    }

    public static <T> ListJsonVo<T> ok(ListVo<T> resVo) {
        ListJsonVo<T> r = new ListJsonVo<>(resVo.getData());
        r.setCount(resVo.getCount());
        return r;
    }

    public static JsonVo ok() {
        return new JsonVo();
    }

    public static JsonVo error(CodeMsg codeMsg) {
        return new JsonVo(codeMsg);
    }

    public static JsonVo error() {
        return new JsonVo(false);
    }

    public static JsonVo error(String msg) {
        return new JsonVo(false, msg);
    }

    public static JsonVo error(Integer code, String msg) {
        return new JsonVo(code, msg);
    }

    public static <T> T raise(String msg) throws CommonException {
        throw new CommonException(msg);
    }

    public static <T> T raise(CodeMsg codeMsg) throws CommonException {
        throw new CommonException(codeMsg);
    }
}
