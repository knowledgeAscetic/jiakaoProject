package com.wang.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ExamPlaceCourseReqVo {
    @ApiModelProperty(value = "id【大于0代表更新，否则代表添加】")
    private Integer id;

    @ApiModelProperty(value = "名称【不能为空】", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "价格【必须大于等于0】", required = true)
    @Min(value = 0, message = "价格必须大于等于0")
    private BigDecimal price;

    @ApiModelProperty(value = "类型【0是课程合集，2是科目2，3是科目3】", required = true)
    @NotNull
    @Range(min = 0, max = 3, message = "0是课程合集，2是科目2，3是科目3")
    private Short type;

    @ApiModelProperty(value = "考场id【必须大于等于1】", required = true)
    @NotNull
    @Min(value = 1, message = "考场id必须大于等于1")
    private Integer placeId;

    @ApiModelProperty("封面地址")
    private String cover;

    @ApiModelProperty("简介")
    private String intro;
}