package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.pojo.po.DictItem;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.list.DictItemVo;
import com.wang.jk.pojo.vo.req.save.DictItemReqVo;
import com.wang.jk.pojo.vo.req.list.DictItemListReqVo;
import com.wang.jk.pojo.vo.json.ListJsonVo;
import com.wang.jk.service.DictItemService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dictItems")
@Api(tags = "数据字典条目")
public class DictItemController extends BaseController<DictItem, DictItemReqVo> {
    @Autowired
    private DictItemService service;

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permisson.DICT_ITEM_LIST)
    public ListJsonVo<DictItemVo> list(DictItemListReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @Override
    protected IService<DictItem> getService() {
        return service;
    }

    @Override
    protected DictItem getPo(DictItemReqVo reqVo) {
        return MapStructs.INSTANCE.reqVo2po(reqVo);
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permisson.DICT_ITEM_ADD,
            Constants.Permisson.DICT_ITEM_UPDATE
    },logical = Logical.AND
    )
    public JsonVo save(DictItemReqVo reqVo) {
        return super.save(reqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permisson.DICT_ITEM_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }
}
