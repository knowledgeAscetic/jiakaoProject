package com.wang.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("数据字典条目")
public class DictItemVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("序号")
    private Integer sn;

    @ApiModelProperty("数据字典类型id")
    private Integer typeId;

    @ApiModelProperty("是否禁用【1是禁用，0是不禁用】")
    private Short disabled;
}