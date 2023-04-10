package com.wang.jk.pojo.po;

import com.wang.jk.common.foreign.anno.ForeignField;
import lombok.Data;

@Data
public class DictItem {
    /**
     * 数据字典条目
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private String value;
    /**
     * 序号
     */
    private Integer sn;
    /**
     * 类型
     */
    @ForeignField(DictType.class)
    private Integer typeId;
    private Short disabled;
}