package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.po.SysRoleResource;

import java.util.List;

public interface SysRoleResourceService extends IService<SysRoleResource> {
    boolean removeByRoleId(Short id);
    List<Short> list(Integer id);

    List<String> listByRoleIds(List<Short> roleIds);
}
