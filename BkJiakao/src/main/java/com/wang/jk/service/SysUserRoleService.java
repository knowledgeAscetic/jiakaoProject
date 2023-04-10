package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.po.SysUserRole;

import java.util.List;

public interface SysUserRoleService  extends IService<SysUserRole> {
    List<Short> listByUserId(Integer id);

}
