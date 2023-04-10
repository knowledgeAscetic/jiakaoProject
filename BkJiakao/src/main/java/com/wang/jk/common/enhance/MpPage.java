package com.wang.jk.common.enhance;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.jk.common.util.Streams;
import com.wang.jk.pojo.vo.req.list.ListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;

import java.util.function.Function;

public class MpPage<T> extends Page<T> {
    private final ListReqVo reqVo;

    public MpPage(ListReqVo reqVo) {
        // 当pageNo < 1，会修正为1
        super(reqVo.getPage(), reqVo.getSize());
        this.reqVo = reqVo;
    }

    private <A> ListVo<A> commonBuildVo() {
        // 当前页码
        reqVo.setPage(getCurrent());
        ListVo<A> vo = new ListVo<>();
        // 总页数
        vo.setPages(getPages());
        // 总记录数
        vo.setCount(getTotal());
        return vo;
    }

    public ListVo<T> buildVo() {
        ListVo<T> vo = commonBuildVo();
        // 当前这页的数据
        vo.setData(getRecords());
        return vo;
    }

    public <V> ListVo<V> buildVo(Function<T, V> function) {
        ListVo<V> vo = commonBuildVo();
        // 当前这页的数据
        vo.setData(Streams.map(getRecords(), function));
        return vo;
    }
}
