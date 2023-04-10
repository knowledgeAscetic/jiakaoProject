package com.wang.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统用户")
public class SysUserVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("账号的状态【0是正常，1是锁定】")
    private Short status;

    @ApiModelProperty("最后一次登录的时间（UNIX时间戳，单位秒）")
    private Long loginTime;
}