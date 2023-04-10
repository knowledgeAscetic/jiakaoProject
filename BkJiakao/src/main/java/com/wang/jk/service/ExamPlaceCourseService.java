package com.wang.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.pojo.po.ExamPlaceCourse;
import com.wang.jk.pojo.po.Image;
import com.wang.jk.pojo.vo.req.list.ExamPlaceCourseListReqVo;
import com.wang.jk.pojo.vo.list.ExamPlaceCourseVo;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.pojo.vo.req.save.ExamPlaceCourseReqVo;

public interface ExamPlaceCourseService
        extends IService<ExamPlaceCourse> {
    ListVo<ExamPlaceCourseVo> list(ExamPlaceCourseListReqVo reqVo);
    boolean saveOrUpdate(ExamPlaceCourseReqVo course, Image cover);
}