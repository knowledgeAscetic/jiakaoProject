package com.wang.jk.pojo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginReqVo {
    @ApiModelProperty(value = "用户名【不能为空】", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码【不能为空】", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码【不能为空】", required = true)
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    @ApiModelProperty("记住密码")
    private boolean rememberMe;
}
