package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.enhance.MpPage;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.mapper.DictItemMapper;
import com.wang.jk.pojo.po.DictItem;
import com.wang.jk.pojo.vo.list.DictItemVo;
import com.wang.jk.pojo.vo.req.list.DictItemListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.service.DictItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DictItemServiceImpl
        extends ServiceImpl<DictItemMapper, DictItem>
        implements DictItemService {
    @Override
    @Transactional(readOnly = true)
    public ListVo<DictItemVo> list(DictItemListReqVo reqVo) {
        MpLambdaQueryWrapper<DictItem> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(),
                DictItem::getName,
                DictItem::getValue);
        wrapper.eqId(reqVo.getTypeId(), DictItem::getTypeId);
        wrapper.orderByDesc(DictItem::getId);
        return baseMapper
                .selectPage(new MpPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }
}
