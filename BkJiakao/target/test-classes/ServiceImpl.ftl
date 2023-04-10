package com.mj.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mj.jk.common.enhance.MpPage;
import com.mj.jk.common.enhance.MpLambdaQueryWrapper;
import com.mj.jk.mapper.${type}Mapper;
import com.mj.jk.pojo.po.${type};
import com.mj.jk.pojo.vo.req.list.${type}Query;
import com.mj.jk.service.${type}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class ${type}ServiceImpl
        extends ServiceImpl<${type}Mapper, ${type}>
        implements ${type}Service {
    @Autowired
    private ${type}Mapper mapper;

    @Override
    @Transactional(readOnly = true)
    public void list(${type}Query query) {
        MpQueryWrapper<${type}> wrapper = new MpQueryWrapper<>();

        baseMapper.selectPage(new MpPage<>(query), wrapper).updateQuery();
    }
}