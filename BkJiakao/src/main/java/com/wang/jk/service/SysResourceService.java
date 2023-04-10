package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.po.SysResource;
import com.wang.jk.pojo.vo.list.SysResourceTreeVo;
import com.wang.jk.pojo.vo.list.SysResourceVo;

import java.util.List;

public interface SysResourceService extends IService<SysResource> {
    List<SysResourceVo> listParents();

    List<SysResourceTreeVo> listTrees();


    List<String> listByResourceIds(List<Short> map);
}
