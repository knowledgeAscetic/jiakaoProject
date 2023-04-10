package com.wang.jk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.jk.pojo.vo.ProvinceVo;
import com.wang.jk.pojo.po.PlateRegion;

import java.util.List;

public interface PlateRegionMapper
        extends BaseMapper<PlateRegion> {
    List<ProvinceVo> selectRegions();
}
