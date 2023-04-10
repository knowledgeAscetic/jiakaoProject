package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.jk.common.enhance.MpPage;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.mapper.ExamPlaceMapper;
import com.wang.jk.pojo.vo.ProvinceVo;
import com.wang.jk.pojo.po.ExamPlace;
import com.wang.jk.pojo.vo.list.ExamPlaceVo;
import com.wang.jk.pojo.vo.req.list.ExamPlaceListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.service.ExamPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamPlaceServiceImpl
        extends ServiceImpl<ExamPlaceMapper, ExamPlace>
        implements ExamPlaceService {
    @Override
    @Transactional(readOnly = true)
    public ListVo<ExamPlaceVo> list(ExamPlaceListReqVo reqVo) {
        MpLambdaQueryWrapper<ExamPlace> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(), ExamPlace::getName, ExamPlace::getAddress);
        wrapper.eqId(reqVo.getProvinceId(), ExamPlace::getProvinceId);
        wrapper.eqId(reqVo.getCityId(), ExamPlace::getCityId);
        return baseMapper
                .selectPage(new MpPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProvinceVo> listRegionExamPlaces() {
        return baseMapper.selectRegionExamPlaces();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamPlace> list(Integer provinceId, Integer cityId) {
        MpLambdaQueryWrapper<ExamPlace> wrapper = new MpLambdaQueryWrapper<>();
        if (cityId != null && cityId > 0) {
            wrapper.eq(ExamPlace::getCityId, cityId);
            return baseMapper.selectList(wrapper);
        } else if (provinceId != null && provinceId > 0) {
            wrapper.eq(ExamPlace::getProvinceId, provinceId);
            return baseMapper.selectList(wrapper);
        }
        return null;
    }


}
