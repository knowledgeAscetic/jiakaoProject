package com.wang.jk.pojo.vo.req.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExamPlaceCourseListReqVo extends KeywordListReqVo {
    @ApiModelProperty("课程类型")
    private Short type;

    @ApiModelProperty("省份id")
    private Integer provinceId;

    @ApiModelProperty("城市id")
    private Integer cityId;

    @ApiModelProperty("考场id")
    private Integer placeId;
}