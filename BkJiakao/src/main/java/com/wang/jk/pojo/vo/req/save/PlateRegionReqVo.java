package com.wang.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlateRegionReqVo {
    @ApiModelProperty(value = "id【大于0代表更新，否则代表添加】")
    private Integer id;

    @ApiModelProperty(value = "名称【不能为空】", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "父区域id【必须大于等于0。如果是省份，父id是0】", required = true)
    @NotNull
    @Min(value = 0, message = "父区域id必须大于等于0")
    private Integer parentId;

    @ApiModelProperty(value = "车牌【不能为空，长度必须是1，比如省份是粤、琼、赣等，城市是A、B、C、D等】", required = true)
    @NotBlank(message = "车牌不能为空")
    @Length(min = 1, max = 1, message = "车牌的长度必须是1")
    private String plate;
}