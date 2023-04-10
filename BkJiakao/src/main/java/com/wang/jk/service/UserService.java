package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.po.SysUser;
import com.wang.jk.pojo.vo.LoginVo;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.pojo.vo.list.SysUserVo;
import com.wang.jk.pojo.vo.req.LoginReqVo;
import com.wang.jk.pojo.vo.req.list.SysUserListReqVo;
import com.wang.jk.pojo.vo.req.save.SysUserReqVo;

public interface UserService extends IService<SysUser> {

    ListVo<SysUserVo> list(SysUserListReqVo reqVo);

    boolean saveOrUpdate(SysUserReqVo reqVo);

    LoginVo login(LoginReqVo reqVo);
}
