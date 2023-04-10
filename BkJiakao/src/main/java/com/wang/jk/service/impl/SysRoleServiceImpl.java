package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.enhance.MpPage;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.mapper.SysRoleMapper;
import com.wang.jk.pojo.po.SysRole;
import com.wang.jk.pojo.po.SysRoleResource;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.pojo.vo.list.SysRoleVo;
import com.wang.jk.pojo.vo.req.list.SysRoleListReqVo;
import com.wang.jk.pojo.vo.req.save.SysRoleReqVo;
import com.wang.jk.service.SysRoleResourceService;
import com.wang.jk.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleResourceService service;

    @Override
    @Transactional(readOnly = true)
    public ListVo<SysRoleVo> list(SysRoleListReqVo reqVo) {
        MpLambdaQueryWrapper<SysRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(),SysRole::getName);
        wrapper.orderByDesc(SysRole::getId);
        return baseMapper.selectPage(new MpPage<>(reqVo),wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    public boolean saveOrUpdate(SysRoleReqVo reqVo) {
        SysRole sysRole = MapStructs.INSTANCE.reqVo2po(reqVo);

        if (!saveOrUpdate(sysRole))return false;
        service.removeByRoleId(sysRole.getId());
        if(StringUtils.isEmpty(reqVo.getResourceIds()))return true;


        String[] split = reqVo.getResourceIds().split(",");
        ArrayList<SysRoleResource> sysUserRoles = new ArrayList<>();
        for (String s : split) {
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setResourceId(Short.valueOf(s));
            sysRoleResource.setRoleId(sysRole.getId());
            sysUserRoles.add(sysRoleResource);
        }
        service.saveBatch(sysUserRoles);
        return true;
    }



    @Override
    public List<SysRole> list() {
        MpLambdaQueryWrapper<SysRole> w = new MpLambdaQueryWrapper<>();
        w.orderByDesc(SysRole::getId);
        return list(w);
    }
}
