package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.util.Streams;
import com.wang.jk.mapper.SysUserRoleMapper;
import com.wang.jk.pojo.po.SysUserRole;
import com.wang.jk.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public List<Short> listByUserId(Integer id) {
        MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysUserRole::getRoleId);
        wrapper.eq(SysUserRole::getUserId,id);
        List<Object> ids = baseMapper.selectObjs(wrapper);
        return Streams.map(ids,(d)->{
            return ((Integer)d).shortValue();
        });
    }
}
