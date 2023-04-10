package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.util.Streams;
import com.wang.jk.mapper.SysRoleResourceMapper;
import com.wang.jk.pojo.po.SysRoleResource;
import com.wang.jk.service.SysResourceService;
import com.wang.jk.service.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {
    @Autowired
    private SysResourceService service;

    @Override
    public boolean removeByRoleId(Short id) {

        MpLambdaQueryWrapper<SysRoleResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(SysRoleResource::getRoleId,id);
        return baseMapper.delete(wrapper)>0?true:false;
    }
    public List<Short> list(Integer id){
        MpLambdaQueryWrapper<SysRoleResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysRoleResource::getResourceId);
        wrapper.eq(SysRoleResource::getRoleId,id);
        List<Object> objects = baseMapper.selectObjs(wrapper);
        return Streams.map(objects, d->{
            return ((Integer) d).shortValue();
        });
    }

    @Override
    public List<String> listByRoleIds(List<Short> roleIds) {
        MpLambdaQueryWrapper<SysRoleResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.in(SysRoleResource::getRoleId,roleIds);
        wrapper.select(SysRoleResource::getResourceId);
        List<Object> objects = baseMapper.selectObjs(wrapper);
        List<Short> map = Streams.map(objects, (d) ->
        {
            return ((Integer) d).shortValue();
        });
        map = new HashSet<Short>(map).stream().collect(Collectors.toList());
        return service.listByResourceIds(map);
    }
}
