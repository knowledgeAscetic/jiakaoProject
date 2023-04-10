package com.wang.jk.pojo.po;

import com.wang.jk.common.foreign.anno.ForeignField;
import lombok.Data;

@Data
public class Image {
    /**
     * 主键
     */
    private Long id;

    private String uri;
    /**
     * 拥有者的id
     */
    @ForeignField(ExamPlace.class)
    private Long ownerId;
    /**
     * 拥有者的类型，比如考场、驾校
     */
    private Short type;
}