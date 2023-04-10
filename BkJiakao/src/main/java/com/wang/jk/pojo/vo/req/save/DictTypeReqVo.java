package com.wang.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DictTypeReqVo {
    @ApiModelProperty(value = "id【大于0代表更新，否则代表添加】")
    private Integer id;

    @ApiModelProperty(value = "名称【不能为空】", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "值【不能为空】", required = true)
    @NotBlank(message = "值不能为空")
    private String value;

    @ApiModelProperty("简介")
    private String intro;
}