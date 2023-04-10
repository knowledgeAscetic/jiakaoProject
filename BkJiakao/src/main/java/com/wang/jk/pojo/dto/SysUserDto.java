package com.wang.jk.pojo.dto;

import com.wang.jk.pojo.po.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDto extends SysUser {
    private List<String> resources;
}
