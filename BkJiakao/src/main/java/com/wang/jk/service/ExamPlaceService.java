package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.vo.ProvinceVo;
import com.wang.jk.pojo.po.ExamPlace;
import com.wang.jk.pojo.vo.list.ExamPlaceVo;
import com.wang.jk.pojo.vo.req.list.ExamPlaceListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;

import java.util.List;

public interface ExamPlaceService
        extends IService<ExamPlace> {
    ListVo<ExamPlaceVo> list(ExamPlaceListReqVo reqVo);
    List<ProvinceVo> listRegionExamPlaces();
    List<ExamPlace> list(Integer provinId, Integer cityId);
}
