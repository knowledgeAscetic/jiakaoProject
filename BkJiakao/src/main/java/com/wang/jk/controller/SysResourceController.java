package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.common.util.Streams;
import com.wang.jk.pojo.po.SysResource;
import com.wang.jk.pojo.vo.json.DataJsonVo;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.list.SysResourceTreeVo;
import com.wang.jk.pojo.vo.list.SysResourceVo;
import com.wang.jk.pojo.vo.req.save.SysResourceReqVo;
import com.wang.jk.service.SysResourceService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/sysResources")
public class SysResourceController extends BaseController<SysResource, SysResourceReqVo> {
    @Autowired
    private SysResourceService service;
    @Override
    protected IService<SysResource> getService() {
        return service;
    }

    @Override
    protected SysResource getPo(SysResourceReqVo sysResourceReqVo) {
        return MapStructs.INSTANCE.reqVo2po(sysResourceReqVo);
    }

    @GetMapping("/list")
    @RequiresPermissions(Constants.Permisson.SYS_RESOURCE_LIST)
    public DataJsonVo<List<SysResourceVo>> list()
    {
        return JsonVos.ok(Streams.map(service.list(), MapStructs.INSTANCE::po2vo));
    }

    @Override
    @RequiresPermissions(
            value = {
                    Constants.Permisson.SYS_RESOURCE_ADD,
                    Constants.Permisson.SYS_RESOURCE_UPDATE
            },
            logical = Logical.AND
    )
    public JsonVo save(@Valid SysResourceReqVo sysResourceReqVo) {
        return super.save(sysResourceReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permisson.SYS_RESOURCE_REMOVE)
    public JsonVo remove(@NotBlank(message = "id不能为空") String id) {
        return super.remove(id);
    }

    @GetMapping("/parents")
    public DataJsonVo<List<SysResourceVo>> parents()
    {
        return JsonVos.ok(service.listParents());
    }

    @GetMapping("/trees")
    public DataJsonVo<List<SysResourceTreeVo>> listTrees()
    {
        return JsonVos.ok(service.listTrees());
    }
}
