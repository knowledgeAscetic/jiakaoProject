package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.cache.Caches;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.common.util.Streams;
import com.wang.jk.pojo.po.SysUser;
import com.wang.jk.pojo.result.CodeMsg;
import com.wang.jk.pojo.vo.LoginVo;
import com.wang.jk.pojo.vo.json.DataJsonVo;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.json.ListJsonVo;
import com.wang.jk.pojo.vo.list.SysUserVo;
import com.wang.jk.pojo.vo.req.LoginReqVo;
import com.wang.jk.pojo.vo.req.list.SysUserListReqVo;
import com.wang.jk.pojo.vo.req.save.SysUserReqVo;
import com.wang.jk.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/sysUsers")
@RestController
public class SysUserController extends BaseController<SysUser, SysUserReqVo> {
    @Autowired
    private UserService service;

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permisson.SYS_USER_LIST)
    public ListJsonVo<SysUserVo> list(SysUserListReqVo reqVo,@RequestHeader("Token")String token) {
        System.out.println(token);
        return JsonVos.ok(service.list(reqVo));
    }
    @GetMapping("/roleIds")
    public DataJsonVo<List<SysUserVo>> roleIds(Integer id){
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(id);
        List<SysUser> sysUsers = service.listByIds(integers);
        return JsonVos.ok(Streams.map(sysUsers, MapStructs.INSTANCE::po2vo));
    }

    @PostMapping("/saveAll")
    public JsonVo saveAll(SysUserReqVo reqVo)
    {
        boolean save = service.saveOrUpdate(reqVo);
        return save?JsonVos.ok():JsonVos.error();
    }

    @Override
    @RequiresPermissions(
            value = {
                    Constants.Permisson.SYS_USER_ADD,
                    Constants.Permisson.SYS_USER_UPDATE
            },
            logical = Logical.AND
    )
    public JsonVo save(@Valid SysUserReqVo reqVo) {
        return super.save(reqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permisson.SYS_USER_REMOVE)
    public JsonVo remove(@NotBlank(message = "id不能为空") String id) {
        return super.remove(id);
    }

    @PostMapping("/login")
    public DataJsonVo<LoginVo> login(LoginReqVo reqVo, HttpServletRequest request)
    {

        if(!CaptchaUtil.ver(reqVo.getCaptcha(),request))
        {
            JsonVos.raise(CodeMsg.WRONG_CAPTCHA);
        }
        return JsonVos.ok(service.login(reqVo));
    }
    @PostMapping("/logout")
    public JsonVo logout(@RequestHeader("Token")String token)
    {
        Caches.remove(token);
        return JsonVos.ok();
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        CaptchaUtil.out(request,response);
    }
    @Override
    protected IService<SysUser> getService() {
        return service;
    }

    @Override
    protected SysUser getPo(SysUserReqVo sysUserReqVo) {
        return MapStructs.INSTANCE.reqVo2po(sysUserReqVo);
    }
}
