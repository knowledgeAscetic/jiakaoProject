package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.common.util.Streams;
import com.wang.jk.pojo.po.DictType;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.list.DictTypeVo;
import com.wang.jk.pojo.vo.req.save.DictTypeReqVo;
import com.wang.jk.pojo.vo.req.list.DictTypeListReqVo;
import com.wang.jk.pojo.vo.json.DataJsonVo;
import com.wang.jk.pojo.vo.json.ListJsonVo;
import com.wang.jk.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/dictTypes")
@Api(tags = "数据字典类型")
public class DictTypeController extends BaseController<DictType, DictTypeReqVo> {
    @Autowired
    private DictTypeService service;

    @Override
    @RequiresPermissions(
            value = {
                    Constants.Permisson.DICT_TYPE_ADD,
                    Constants.Permisson.DICT_TYPE_UPDATE
            },
            logical = Logical.AND
    )
    public JsonVo save(@Valid DictTypeReqVo dictTypeReqVo) {
        return super.save(dictTypeReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permisson.DICT_TYPE_REMOVE)
    public JsonVo remove(@NotBlank(message = "id不能为空") String id) {
        return super.remove(id);
    }

    @GetMapping("/list")
    @ApiOperation("查询所有")

    public DataJsonVo<List<DictTypeVo>> list() {
        return JsonVos.ok(Streams.map(service.list(), MapStructs.INSTANCE::po2vo));
    }

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permisson.DICT_TYPE_LIST)
    public ListJsonVo<DictTypeVo> list(DictTypeListReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @Override
    protected IService<DictType> getService() {
        return service;
    }

    @Override
    protected DictType getPo(DictTypeReqVo reqVo) {
        return MapStructs.INSTANCE.reqVo2po(reqVo);
    }
}
