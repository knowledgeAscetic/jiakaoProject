package com.wang.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("区域")
public class PlateRegionVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父区域id【如果是省份，父id是0】")
    private Integer parentId;

    @ApiModelProperty("车牌【比如省份是粤、琼、赣等，城市是A、B、C、D等】")
    private String plate;
}