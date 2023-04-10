package com.wang.jk.pojo.po;

import com.wang.jk.common.foreign.anno.ForeignField;
import lombok.Data;

@Data
public class PlateRegion {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父区域
     */
    @ForeignField(PlateRegion.class)
    private Integer parentId;
    /**
     * 车牌，比如省份是粤、琼、赣，城市是A、B、C、D
     */
    private String plate;
    /**
     * 拼音
     */
    private String pinyin;
}