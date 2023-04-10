package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.po.SysRole;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.pojo.vo.list.SysRoleVo;
import com.wang.jk.pojo.vo.req.list.SysRoleListReqVo;
import com.wang.jk.pojo.vo.req.save.SysRoleReqVo;

public interface SysRoleService extends IService<SysRole> {
    ListVo<SysRoleVo> list(SysRoleListReqVo roleListReqVo);

    boolean saveOrUpdate(SysRoleReqVo reqVo);

}
