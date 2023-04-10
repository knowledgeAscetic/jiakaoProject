package com.wang.jk.pojo.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {
    /**
     * 主键
     */
    private Short id;
    /**
     * 角色名称
     */
    private String name;
}