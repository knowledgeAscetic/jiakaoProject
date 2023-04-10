package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.po.DictItem;
import com.wang.jk.pojo.vo.list.DictItemVo;
import com.wang.jk.pojo.vo.req.list.DictItemListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;

public interface DictItemService
        extends IService<DictItem> {
    ListVo<DictItemVo> list(DictItemListReqVo reqVo);
}
