package com.wang.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("列表返回结果")
public class ListVo<T> {
    @ApiModelProperty("总数")
    private long count;

    @ApiModelProperty("总页数")
    private long pages;

    @ApiModelProperty("具体数据")
    private List<T> data;
}
