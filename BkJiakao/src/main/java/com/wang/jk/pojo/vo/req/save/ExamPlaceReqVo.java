package com.wang.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ExamPlaceReqVo {
    @ApiModelProperty(value = "id【大于0代表更新，否则代表添加】")
    private Integer id;

    @ApiModelProperty(value = "名称【不能为空】", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "省份id【必须大于等于1】", required = true)
    @NotNull
    @Min(value = 1, message = "省份id必须大于等于1")
    private Integer provinceId;

    @ApiModelProperty(value = "城市id【必须大于等于1】", required = true)
    @NotNull
    @Min(value = 1, message = "城市id必须大于等于1")
    private Integer cityId;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("纬度")
    private BigDecimal latitude;

    @ApiModelProperty("经度")
    private BigDecimal longitude;
}