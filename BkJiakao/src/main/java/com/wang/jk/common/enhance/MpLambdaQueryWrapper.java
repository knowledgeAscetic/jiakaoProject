package com.wang.jk.common.enhance;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

public class MpLambdaQueryWrapper<T> extends LambdaQueryWrapper<T> {
    public MpLambdaQueryWrapper<T> eqId(Long id, SFunction<T, ?> func) {
        if (id == null || id <= 0) return this;
        return (MpLambdaQueryWrapper<T>) eq(func, id);
    }

    public MpLambdaQueryWrapper<T> eqId(Integer id, SFunction<T, ?> func) {
        if (id == null || id <= 0) return this;
        return (MpLambdaQueryWrapper<T>) eq(func, id);
    }

    public MpLambdaQueryWrapper<T> eqId(Short id, SFunction<T, ?> func) {
        if (id == null || id <= 0) return this;
        return (MpLambdaQueryWrapper<T>) eq(func, id);
    }

    @SafeVarargs
    public final MpLambdaQueryWrapper<T> like(Object val, SFunction<T, ?>... funcs) {
        if (val == null) return this;
        String str = val.toString();
        if (str.length() == 0) return this;
        return (MpLambdaQueryWrapper<T>) nested(w -> {
            for (SFunction<T, ?> func : funcs) {
                w.like(func, str).or();
            }
        });
    }
}
