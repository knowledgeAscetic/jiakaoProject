package com.wang.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SysResourceReqVo {
    @ApiModelProperty(value = "id【大于0代表更新，否则代表添加】")
    private Short id;

    @ApiModelProperty(value = "名称【不能为空】", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty("链接")
    private String uri;

    @ApiModelProperty("权限标识")
    private String permission;

    @ApiModelProperty(value = "类型【0是目录，1是菜单，2是按钮】", required = true)
    @NotNull
    @Range(min = 0, max = 2, message = "0是目录，1是菜单，2是按钮")
    private Short type;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty(value = "序号【必须大于等于0。默认是0】")
    @Min(value = 0, message = "序号必须大于等于0")
    private Short sn;

    @ApiModelProperty(value = "父资源id【为空或者必须大于等于1】")
    @Min(value = 1, message = "父资源id必须大于等于1")
    private Short parentId;
}