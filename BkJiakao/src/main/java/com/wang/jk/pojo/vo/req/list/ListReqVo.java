package com.wang.jk.pojo.vo.req.list;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListReqVo {
    private static final int DEFAULT_PAGE_SIZE = 10;

    @ApiModelProperty("页码")
    private long page;

    @ApiModelProperty("每页的大小")
    private long size;

    public long getSize() {
        return size < 1 ? DEFAULT_PAGE_SIZE : size;
    }

    public long getPage() {
        return Math.max(page, 1);
    }
}
