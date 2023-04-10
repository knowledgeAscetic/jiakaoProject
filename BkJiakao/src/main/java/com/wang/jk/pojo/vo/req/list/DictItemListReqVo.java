package com.wang.jk.pojo.vo.req.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictItemListReqVo extends KeywordListReqVo {
    @ApiModelProperty("数据字典类型id")
    private Integer typeId;
}
