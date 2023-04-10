package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.common.util.Streams;
import com.wang.jk.pojo.po.SysRole;
import com.wang.jk.pojo.result.CodeMsg;
import com.wang.jk.pojo.vo.json.DataJsonVo;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.json.ListJsonVo;
import com.wang.jk.pojo.vo.list.SysRoleVo;
import com.wang.jk.pojo.vo.req.list.SysRoleListReqVo;
import com.wang.jk.pojo.vo.req.save.SysRoleReqVo;
import com.wang.jk.service.SysRoleResourceService;
import com.wang.jk.service.SysRoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/sysRoles")
public class SysRolesController extends BaseController<SysRole, SysRoleReqVo> {
    @Autowired
    private SysRoleService service;

    @Autowired
    private SysRoleResourceService resourceService;
    @Override
    protected IService<SysRole> getService() {
        return service;
    }

    @Override
    protected SysRole getPo(SysRoleReqVo sysRoleReqVo) {
        return MapStructs.INSTANCE.reqVo2po(sysRoleReqVo);
    }

    @GetMapping
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_LIST)
    public ListJsonVo<SysRoleVo> list(SysRoleListReqVo roleListReqVo)
    {
        return JsonVos.ok(service.list(roleListReqVo));
    }
    @GetMapping("/list")
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_LIST)
    public DataJsonVo<List<SysRoleVo>> list()
    {
        return JsonVos.ok(Streams.map(service.list(),MapStructs.INSTANCE::po2vo));
    }

    @GetMapping("/resourceIds")
    public DataJsonVo<List<Short>> resourceIds(@RequestParam("id") Integer id)
    {
        return JsonVos.ok(resourceService.list(id));
    }

    @Override
    @RequiresPermissions(Constants.Permisson.SYS_ROLE_REMOVE)
    public JsonVo remove(@NotBlank(message = "id不能为空") String id) {
        return super.remove(id);
    }

    @Override
    @RequiresPermissions(
            value = {
                    Constants.Permisson.SYS_ROLE_ADD,
                    Constants.Permisson.SYS_ROLE_UPDATE
            },
            logical = Logical.AND
    )
    public JsonVo save(@Valid SysRoleReqVo reqVo) {
        return service.saveOrUpdate(reqVo) ? JsonVos.ok(CodeMsg.SAVE_OK) : JsonVos.raise(CodeMsg.SAVE_ERROR);
    }
}
