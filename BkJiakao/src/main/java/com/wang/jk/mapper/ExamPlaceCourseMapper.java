package com.wang.jk.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wang.jk.common.enhance.MpPage;
import com.wang.jk.pojo.po.ExamPlaceCourse;
import com.wang.jk.pojo.vo.list.ExamPlaceCourseVo;
import org.apache.ibatis.annotations.Param;

public interface ExamPlaceCourseMapper
        extends BaseMapper<ExamPlaceCourse> {
    MpPage<ExamPlaceCourseVo> select(MpPage<ExamPlaceCourseVo> page,
                                     @Param(Constants.WRAPPER) Wrapper<ExamPlaceCourseVo> wrapper);
}