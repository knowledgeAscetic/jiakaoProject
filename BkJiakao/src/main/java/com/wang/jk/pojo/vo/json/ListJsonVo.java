package com.wang.jk.pojo.vo.json;

import com.wang.jk.pojo.result.CodeMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("响应结果【带列表】")
public class ListJsonVo<T> extends DataJsonVo<List<T>> {
    @ApiModelProperty("总数")
    private Long count;

    public ListJsonVo(List<T> data) {
        super(data);
    }

    public ListJsonVo() {
    }

    public ListJsonVo(boolean ok) {
        super(ok);
    }

    public ListJsonVo(boolean ok, String msg) {
        super(ok, msg);
    }

    public ListJsonVo(int code, String msg) {
        super(code, msg);
    }

    public ListJsonVo(CodeMsg codeMsg) {
        super(codeMsg);
    }

    public ListJsonVo(String msg, List<T> data) {
        super(msg, data);
    }
}
