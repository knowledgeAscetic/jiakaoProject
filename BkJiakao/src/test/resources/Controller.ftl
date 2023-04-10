package com.mj.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mj.jk.common.util.Rs;
import com.mj.jk.pojo.po.${type};
import com.mj.jk.pojo.vo.req.list.${type}Query;
import com.mj.jk.pojo.result.R;
import com.mj.jk.service.${type}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${type?uncap_first}s")
public class ${type}Controller extends BaseController<${type}> {
    @Autowired
    private ${type}Service service;

    @GetMapping
    public JSONResult list(${type}Query query) {
        service.list(query);
        return JSONResults.result(query);
    }

    @Override
    protected IService<${type}> getService() {
        return service;
    }
}
