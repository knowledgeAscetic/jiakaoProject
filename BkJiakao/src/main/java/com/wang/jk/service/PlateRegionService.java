package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.vo.ProvinceVo;
import com.wang.jk.pojo.po.PlateRegion;
import com.wang.jk.pojo.vo.list.PlateRegionVo;
import com.wang.jk.pojo.vo.req.list.CityListReqVo;
import com.wang.jk.pojo.vo.req.list.ProvinceListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;

import java.util.List;

public interface PlateRegionService
        extends IService<PlateRegion> {
    ListVo<PlateRegionVo> listProvinces(ProvinceListReqVo reqVo);
    ListVo<PlateRegionVo> listCities(CityListReqVo reqVo);

    List<PlateRegionVo> listProvinces();
    List<ProvinceVo> listRegions();
}
