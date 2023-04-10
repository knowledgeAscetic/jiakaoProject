package com.wang.jk.pojo.vo.req.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CityListReqVo extends KeywordListReqVo {
    @ApiModelProperty("省份id")
    private Integer parentId;
}
