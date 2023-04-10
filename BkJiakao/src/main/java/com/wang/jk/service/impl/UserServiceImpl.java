package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.cache.Caches;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.enhance.MpPage;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.mapper.UserMapper;
import com.wang.jk.pojo.dto.SysUserDto;
import com.wang.jk.pojo.po.SysUser;
import com.wang.jk.pojo.po.SysUserRole;
import com.wang.jk.pojo.result.CodeMsg;
import com.wang.jk.pojo.vo.LoginVo;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.pojo.vo.list.SysUserVo;
import com.wang.jk.pojo.vo.req.LoginReqVo;
import com.wang.jk.pojo.vo.req.list.SysUserListReqVo;
import com.wang.jk.pojo.vo.req.save.SysUserReqVo;
import com.wang.jk.service.SysRoleResourceService;
import com.wang.jk.service.SysUserRoleService;
import com.wang.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysRoleResourceService roleResourceService;
    @Override
    public ListVo<SysUserVo> list(SysUserListReqVo reqVo) {
        MpLambdaQueryWrapper<SysUser> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(),SysUser::getNickname,SysUser::getUsername);
        wrapper.orderByDesc(SysUser::getId);
        return baseMapper.selectPage(new MpPage<>(reqVo),wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(SysUserReqVo reqVo) {
        SysUser sysUser = MapStructs.INSTANCE.reqVo2po(reqVo);

        if (!saveOrUpdate(sysUser))return false;


        if(StringUtils.isEmpty(reqVo.getRoleIds()))return true;
        if(reqVo.getId()!=null&&reqVo.getId()>0) {
            Caches.remove(Caches.getUserToToken(reqVo.getId()));
            MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
            wrapper.eq(SysUserRole::getUserId, sysUser.getId());
            userRoleService.remove(wrapper);
        }


        String[] split = reqVo.getRoleIds().split(",");
        ArrayList<SysUserRole> sysUserRoles = new ArrayList<>();
        for (String s : split) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(Short.parseShort(s));
            sysUserRole.setUserId(sysUser.getId());
            sysUserRoles.add(sysUserRole);
        }
        userRoleService.saveBatch(sysUserRoles);
        return true;
    }

    @Override
    public LoginVo login(LoginReqVo reqVo) {
        MpLambdaQueryWrapper<SysUser> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(SysUser::getPassword,reqVo.getPassword());
        wrapper.eq(SysUser::getUsername,reqVo.getUsername());
        SysUser sysUser = baseMapper.selectOne(wrapper);
        if (sysUser==null)
        {
            return JsonVos.raise(CodeMsg.WRONG_PASSWORD);
        }
        else if(sysUser.getStatus()== Constants.SysUserStatus.LOCKED)
        {
            return JsonVos.raise(CodeMsg.LOCKED_ACCOUNT);
        }

        //更新时间
        sysUser.setLoginTime(new Date());
        baseMapper.updateById(sysUser);

        SysUserDto sysUserDto = MapStructs.INSTANCE.po2Dto(sysUser);

        String token = UUID.randomUUID().toString();
        LoginVo loginVo = MapStructs.INSTANCE.po2loginVo(sysUser);
        loginVo.setToken(token);

        List<Short> roleIds=userRoleService.listByUserId(sysUserDto.getId());
        if (CollectionUtils.isEmpty(roleIds)){
            sysUserDto.setResources(new ArrayList<>());
            Caches.put(token,sysUserDto);
            return loginVo;
        }
        List<String> strings = roleResourceService.listByRoleIds(roleIds);
        sysUserDto.setResources(strings);
        Caches.put(token,sysUserDto);
        return loginVo;
    }
}
