package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.po.DictType;
import com.wang.jk.pojo.vo.list.DictTypeVo;
import com.wang.jk.pojo.vo.req.list.DictTypeListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;

public interface DictTypeService
        extends IService<DictType> {
    ListVo<DictTypeVo> list(DictTypeListReqVo reqVo);
}
