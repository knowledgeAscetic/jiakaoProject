package com.wang.jk.pojo.vo.req.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class KeywordListReqVo extends ListReqVo {
    @ApiModelProperty("搜索用的关键词")
    private String keyword;
}
