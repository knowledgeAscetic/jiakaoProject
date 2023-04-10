package com.wang.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.promeg.pinyinhelper.Pinyin;
import com.wang.jk.common.enhance.MpPage;
import com.wang.jk.common.enhance.MpLambdaQueryWrapper;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Streams;
import com.wang.jk.mapper.PlateRegionMapper;
import com.wang.jk.pojo.vo.ProvinceVo;
import com.wang.jk.pojo.po.PlateRegion;
import com.wang.jk.pojo.vo.list.PlateRegionVo;
import com.wang.jk.pojo.vo.req.list.CityListReqVo;
import com.wang.jk.pojo.vo.req.list.KeywordListReqVo;
import com.wang.jk.pojo.vo.req.list.ProvinceListReqVo;
import com.wang.jk.pojo.vo.list.ListVo;
import com.wang.jk.service.PlateRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlateRegionServiceImpl
        extends ServiceImpl<PlateRegionMapper, PlateRegion>
        implements PlateRegionService {
    private static final String PINYIN_SEP = "_";

    @Override
    public boolean save(PlateRegion entity) {
        entity.setPinyin(Pinyin.toPinyin(entity.getName(), PINYIN_SEP));
        return super.save(entity);
    }

    @Override
    public boolean updateById(PlateRegion entity) {
        entity.setPinyin(Pinyin.toPinyin(entity.getName(), PINYIN_SEP));
        return super.updateById(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProvinceVo> listRegions() {
        return baseMapper.selectRegions();
    }

    @Override
    @Transactional(readOnly = true)
    public ListVo<PlateRegionVo> listProvinces(ProvinceListReqVo reqVo) {
        MpLambdaQueryWrapper<PlateRegion> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(PlateRegion::getParentId, 0);
        return list(reqVo, wrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public ListVo<PlateRegionVo> listCities(CityListReqVo reqVo) {
        MpLambdaQueryWrapper<PlateRegion> wrapper = new MpLambdaQueryWrapper<>();
        Integer pid = reqVo.getParentId();
        if (pid == null || pid < 1) {
            // parent_id > 0
            wrapper.gt(PlateRegion::getParentId, 0);
        } else {
            wrapper.eq(PlateRegion::getParentId, pid);
        }
        return list(reqVo, wrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlateRegionVo> listProvinces() {
        MpLambdaQueryWrapper<PlateRegion> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(PlateRegion::getParentId, 0);
        wrapper.orderByAsc(PlateRegion::getPinyin);
        return Streams.map(baseMapper.selectList(wrapper), MapStructs.INSTANCE::po2vo);
    }

    private ListVo<PlateRegionVo> list(KeywordListReqVo reqVo, MpLambdaQueryWrapper<PlateRegion> wrapper) {
        // 条件
        wrapper.like(reqVo.getKeyword(),
                PlateRegion::getName,
                PlateRegion::getPinyin,
                PlateRegion::getPlate);
        wrapper.orderByDesc(PlateRegion::getId);

        // 查询
        return baseMapper
                .selectPage(new MpPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }
}
