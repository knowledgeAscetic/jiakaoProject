package com.wang.jk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.jk.pojo.vo.ProvinceVo;
import com.wang.jk.pojo.po.ExamPlace;

import java.util.List;

public interface ExamPlaceMapper
        extends BaseMapper<ExamPlace> {
    List<ProvinceVo> selectRegionExamPlaces();
}
