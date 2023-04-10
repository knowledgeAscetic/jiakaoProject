package com.wang.jk.pojo.vo.req.save;

import com.wang.jk.common.validator.BoolNumber;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DictItemReqVo {
    @ApiModelProperty(value = "id【大于0代表更新，否则代表添加】")
    private Integer id;

    @ApiModelProperty(value = "名称【不能为空】", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "值【不能为空】", required = true)
    @NotBlank(message = "值不能为空")
    private String value;

    @ApiModelProperty(value = "序号【必须大于等于0。默认是0】")
    @Min(value = 0, message = "序号必须大于等于0")
    private Integer sn;

    @ApiModelProperty(value = "数据字典类型id【必须大于等于1】", required = true)
    @NotNull(message = "typeId不能为空")
    @Min(value = 1, message = "数据字典类型id必须大于等于1")
    private Integer typeId;

    @ApiModelProperty(value = "是否禁用【1是禁用，0是不禁用。默认是0】")
    @BoolNumber(message = "是否禁用只能是0或1")
    private Short disabled;
}