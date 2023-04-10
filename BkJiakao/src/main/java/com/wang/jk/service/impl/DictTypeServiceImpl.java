package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.enhance.MpPage;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.mapper.DictTypeMapper;
import com.wang.jk.pojo.po.DictType;
import com.wang.jk.pojo.vo.list.DictTypeVo;
import com.wang.jk.pojo.vo.req.list.DictTypeListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.service.DictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DictTypeServiceImpl
        extends ServiceImpl<DictTypeMapper, DictType>
        implements DictTypeService {
    @Override
    @Transactional(readOnly = true)
    public ListVo<DictTypeVo> list(DictTypeListReqVo reqVo) {
        MpLambdaQueryWrapper<DictType> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(),
                DictType::getName,
                DictType::getValue,
                DictType::getIntro);
        wrapper.orderByDesc(DictType::getId);
        return baseMapper
                .selectPage(new MpPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DictType> list() {
        MpLambdaQueryWrapper<DictType> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.orderByDesc(DictType::getId);
        return baseMapper.selectList(wrapper);
    }
}
