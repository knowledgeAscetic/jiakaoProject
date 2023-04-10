package com.wang.jk.pojo.vo.list;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel("科2科3课程")
@TableName("exam_place_course")
public class ExamPlaceCourseVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("类型【0是课程合集，2是科目2，3是科目3】")
    private Short type;

    @ApiModelProperty("考场id")
    private Integer placeId;

    @ApiModelProperty("简介")
    private String intro;

    @ApiModelProperty("省份id")
    private Long provinceId;

    @ApiModelProperty("城市id")
    private Long cityId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("视频地址")
    private String video;

    @ApiModelProperty("封面地址")
    private String cover;
}