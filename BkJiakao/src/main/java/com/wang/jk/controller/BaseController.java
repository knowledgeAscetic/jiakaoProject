package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.pojo.result.CodeMsg;
import com.wang.jk.pojo.vo.json.JsonVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@Validated
public abstract class BaseController<P, ReqV> {
    protected abstract IService<P> getService();
    protected abstract P getPo(ReqV reqV);

    @PostMapping("/save")
    @ApiOperation("添加或更新")
    public JsonVo save(@Valid ReqV reqV) {
        return getService().saveOrUpdate(getPo(reqV))
                ? JsonVos.ok(CodeMsg.SAVE_OK)
                : JsonVos.raise(CodeMsg.SAVE_ERROR);
    }

    @PostMapping("/remove")
    @ApiOperation("删除一条或多条数据")
    public JsonVo remove(@NotBlank(message = "id不能为空") @RequestParam String id) {
        return getService().removeByIds(Arrays.asList(id.split(",")))
                ? JsonVos.ok(CodeMsg.REMOVE_OK)
                : JsonVos.raise(CodeMsg.REMOVE_ERROR);
    }
}
