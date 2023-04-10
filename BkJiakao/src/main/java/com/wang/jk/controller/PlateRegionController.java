package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.pojo.vo.ProvinceVo;
import com.wang.jk.pojo.po.PlateRegion;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.list.PlateRegionVo;
import com.wang.jk.pojo.vo.req.save.PlateRegionReqVo;
import com.wang.jk.pojo.vo.req.list.CityListReqVo;
import com.wang.jk.pojo.vo.req.list.ProvinceListReqVo;
import com.wang.jk.pojo.vo.json.DataJsonVo;
import com.wang.jk.pojo.vo.json.ListJsonVo;
import com.wang.jk.service.PlateRegionService;
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
@RequestMapping("/plateRegions")
@Api(tags = "区域")
public class PlateRegionController extends BaseController<PlateRegion, PlateRegionReqVo> {
    @Autowired
    private PlateRegionService service;

    @GetMapping("/list")
    @ApiOperation("查询所有的区域（省份+城市）")
    public DataJsonVo<List<ProvinceVo>> listRegions() {
        return JsonVos.ok(service.listRegions());
    }

    @GetMapping("/provinces/list")
    @ApiOperation("查询所有的省份")
    public DataJsonVo<List<PlateRegionVo>> listProvinces() {
        return JsonVos.ok(service.listProvinces());
    }

    @GetMapping("/provinces")
    @ApiOperation("分页查询省份")
    public ListJsonVo<PlateRegionVo> listProvinces(ProvinceListReqVo reqVo) {
        return JsonVos.ok(service.listProvinces(reqVo));
    }

    @Override
    @RequiresPermissions(
            value = {
                    Constants.Permisson.CITY_ADD,
                    Constants.Permisson.CITY_UPDATE
            },
            logical = Logical.AND
    )
    public JsonVo save(@Valid PlateRegionReqVo plateRegionReqVo) {
        return super.save(plateRegionReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permisson.CITY_REMOVE)
    public JsonVo remove(@NotBlank(message = "id不能为空") String id) {
        return super.remove(id);
    }

    @GetMapping("/cities")
    @ApiOperation("分页查询城市")
    @RequiresPermissions(Constants.Permisson.CITY_LIST)
    public ListJsonVo<PlateRegionVo> listCities(CityListReqVo reqVo) {
        return JsonVos.ok(service.listCities(reqVo));
    }

    @Override
    protected IService<PlateRegion> getService() {
        return service;
    }

    @Override
    protected PlateRegion getPo(PlateRegionReqVo reqVo) {
        return MapStructs.INSTANCE.reqVo2po(reqVo);
    }
}
