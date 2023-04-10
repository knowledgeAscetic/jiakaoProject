package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.enhance.MpPage;
import com.wang.jk.common.enhance.MpQueryWrapper;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Uploads;
import com.wang.jk.mapper.ExamPlaceCourseMapper;
import com.wang.jk.pojo.po.ExamPlaceCourse;
import com.wang.jk.pojo.po.Image;
import com.wang.jk.pojo.vo.req.list.ExamPlaceCourseListReqVo;
import com.wang.jk.pojo.vo.list.ExamPlaceCourseVo;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.pojo.vo.req.save.ExamPlaceCourseReqVo;
import com.wang.jk.service.ExamPlaceCourseService;
import com.wang.jk.service.ExamPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ExamPlaceCourseServiceImpl
        extends ServiceImpl<ExamPlaceCourseMapper, ExamPlaceCourse>
        implements ExamPlaceCourseService {
    @Autowired
    private ExamPlaceService placeService;

    @Override
    @Transactional(readOnly = true)
    public ListVo<ExamPlaceCourseVo> list(ExamPlaceCourseListReqVo reqVo) {
        MpQueryWrapper<ExamPlaceCourseVo> wrapper = new MpQueryWrapper<>();
        Integer placeId = reqVo.getPlaceId();
        Integer provinceId = reqVo.getProvinceId();
        Integer cityId = reqVo.getCityId();
        Short type = reqVo.getType();
        // 类型
        if (type != null && type >= 0) {
            wrapper.eq("c.type", type);
        }
        // 考场 -> 城市 -> 省份
        if (placeId != null && placeId > 0) {
            wrapper.eq("c.place_id", placeId);
        } else  if (cityId != null && cityId > 0) {
            wrapper.eq("p.city_id", cityId);
        } else if (provinceId != null && provinceId > 0) {
            wrapper.eq("p.province_id", provinceId);
        }

        // 关键词
        wrapper.like(reqVo.getKeyword(), "c.name", "c.intro");
        return baseMapper
                .select(new MpPage<>(reqVo), wrapper)
                .buildVo();
    }

    @Override
    public boolean saveOrUpdate(ExamPlaceCourseReqVo course, Image cover) {
        if (cover != null) {
            // 删除旧的图片
            Image image = new Image();
            image.setUri(course.getCover());
            Uploads.remove(image);
            course.setCover(cover.getUri());
        }
        return super.saveOrUpdate(MapStructs.INSTANCE.reqVo2po(course));
    }

    @Override
    public boolean removeById(Serializable id) {
        ExamPlaceCourse course = baseMapper.selectById(id);
        if (!super.removeById(id)) return false;
        removeCoverAndVideo(course);
        return true;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        List<ExamPlaceCourse> courses = baseMapper.selectBatchIds(idList);
        if (!super.removeByIds(idList)) return false;
        for (ExamPlaceCourse course : courses) {
            removeCoverAndVideo(course);
        }
        return true;
    }

    private void removeCoverAndVideo(ExamPlaceCourse course) {
        // 删除封面
        Image image = new Image();
        image.setUri(course.getCover());
        Uploads.remove(image);
        // TODO 删除视频
    }
}