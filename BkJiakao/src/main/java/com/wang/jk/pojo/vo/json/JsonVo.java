package com.wang.jk.pojo.vo.json;

import com.wang.jk.pojo.result.CodeMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("响应结果")
public class JsonVo {
    protected static final int CODE_OK = CodeMsg.OPERATE_OK.getCode();
    public static final int CODE_ERROR = CodeMsg.BAD_REQUEST.getCode();

    @ApiModelProperty("代码【0代表成功，非0代表错误】")
    private Integer code;

    @ApiModelProperty("描述信息")
    private String msg;

    public JsonVo() {
        this(true);
    }

    public JsonVo(boolean ok) {
        this(ok, null);
    }

    public JsonVo(boolean ok, String msg) {
        this(ok ? CODE_OK : CODE_ERROR, msg);
    }

    public JsonVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonVo(CodeMsg codeMsg) {
        this(codeMsg.getCode(), codeMsg.getMsg());
    }
}
