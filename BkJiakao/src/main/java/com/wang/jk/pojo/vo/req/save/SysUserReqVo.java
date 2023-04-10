package com.wang.jk.pojo.vo.req.save;

import com.wang.jk.common.validator.BoolNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("系统用户")
public class SysUserReqVo {
    @ApiModelProperty("id【大于0代表更新，否则代表添加】")
    private Integer id;

    @ApiModelProperty(value = "昵称【不能为空】", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "用户名【不能为空】", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码【不为空就是更新密码，为空就是不修改密码】")
    private String password;

    @ApiModelProperty("账号的状态【0是正常，1是锁定。默认是0】")
    @BoolNumber(message = "账号的状态只能是0或1")
    private Short status;

    @ApiModelProperty("角色id【多个id之间用逗号,隔开】")
    private String roleIds;
}