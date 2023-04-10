package com.wang.jk.pojo.vo.json;

import com.wang.jk.pojo.result.CodeMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("响应结果【带数据】")
public class DataJsonVo<T> extends JsonVo {
    @ApiModelProperty("具体数据")
    private T data;

    public DataJsonVo(String msg, T data) {
        this(data);
        setMsg(msg);
    }

    public DataJsonVo(T data) {
        this.data = data;
        setCode(CODE_OK);
    }

    public DataJsonVo() {
    }

    public DataJsonVo(boolean ok) {
        super(ok);
    }

    public DataJsonVo(boolean ok, String msg) {
        super(ok, msg);
    }

    public DataJsonVo(int code, String msg) {
        super(code, msg);
    }

    public DataJsonVo(CodeMsg codeMsg) {
        super(codeMsg);
    }
}
