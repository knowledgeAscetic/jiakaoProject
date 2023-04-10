package com.wang.jk.common.mapStruct;

import com.wang.jk.pojo.dto.SysUserDto;
import com.wang.jk.pojo.po.*;
import com.wang.jk.pojo.vo.LoginVo;
import com.wang.jk.pojo.vo.list.*;
import com.wang.jk.pojo.vo.req.save.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
    Date2Millis.Date2MillisFormatter.class
})
public interface MapStructs {
    MapStructs INSTANCE = Mappers.getMapper(MapStructs.class);

    DictItem reqVo2po(DictItemReqVo reqVo);
    DictType reqVo2po(DictTypeReqVo reqVo);
    ExamPlace reqVo2po(ExamPlaceReqVo reqVo);
    ExamPlaceCourse reqVo2po(ExamPlaceCourseReqVo reqVo);
    PlateRegion reqVo2po(PlateRegionReqVo reqVo);
    SysResource reqVo2po(SysResourceReqVo reqVo);
    SysRole reqVo2po(SysRoleReqVo reqVo);
    SysUser reqVo2po(SysUserReqVo reqVo);
    
    ExamPlaceVo po2vo(ExamPlace po);
    DictItemVo po2vo(DictItem po);
    DictTypeVo po2vo(DictType po);
    ExamPlaceCourseVo po2vo(ExamPlaceCourse po);
    PlateRegionVo po2vo(PlateRegion po);
    SysResourceVo po2vo(SysResource po);
    SysRoleVo po2vo(SysRole po);
    @Mapping(source = "loginTime",
            target = "loginTime",
            qualifiedBy = Date2Millis.class)
    SysUserVo po2vo(SysUser po);
    LoginVo po2loginVo(SysUser po);

    SysUserDto po2Dto(SysUser po);
}
