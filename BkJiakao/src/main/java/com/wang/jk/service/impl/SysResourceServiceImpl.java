package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.Streams;
import com.wang.jk.mapper.SysResourceMapper;
import com.wang.jk.pojo.po.SysResource;
import com.wang.jk.pojo.vo.list.SysResourceTreeVo;
import com.wang.jk.pojo.vo.list.SysResourceVo;
import com.wang.jk.service.SysResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
    @Override
    public List<SysResourceVo> listParents() {
        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.ne(SysResource::getType, Constants.SysResourceType.BTN);
        wrapper.orderByAsc(SysResource::getType).orderByAsc(SysResource::getId);
        return Streams.map(baseMapper.selectList(wrapper), MapStructs.INSTANCE::po2vo);
    }

    @Override
    public List<SysResourceTreeVo> listTrees() {
        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.orderByAsc(SysResource::getType);
        List<SysResource> sysResources = baseMapper.selectList(wrapper);

        List<SysResourceTreeVo> treeVos = new ArrayList<>();
        Map<Short, SysResourceTreeVo> map = new HashMap<>();
        for (SysResource resource : sysResources) {
            Short type = resource.getType();
            if(type==Constants.SysResourceType.DIR)
            {
                SysResourceTreeVo sysResourceTreeVo = po2treeVo(resource);
                treeVos.add(sysResourceTreeVo);
                map.put(sysResourceTreeVo.getId(),sysResourceTreeVo);
            } else {
                SysResourceTreeVo sysResourceTreeVo = po2treeVo(resource);
                SysResourceTreeVo vo = map.get(resource.getParentId());
                List<SysResourceTreeVo> children = vo.getChildren();
                if(children==null){
                    vo.setChildren(new ArrayList<>());
                    children=vo.getChildren();
                }
                children.add(sysResourceTreeVo);

                map.put(sysResourceTreeVo.getId(),sysResourceTreeVo);
            }
        }
        return treeVos;
    }

    @Override
    public List<String> listByResourceIds(List<Short> map) {
        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.in(SysResource::getId,map);
        wrapper.select(SysResource::getPermission);
        List<Object> resources = baseMapper.selectObjs(wrapper);
        return Streams.map(resources,resource->{
            return (String)resource;
        });
    }


    private SysResourceTreeVo po2treeVo(SysResource po)
    {
        SysResourceTreeVo vo = new SysResourceTreeVo();
        vo.setId(po.getId());
        vo.setTitle(po.getName());
        return vo;
    }
}
