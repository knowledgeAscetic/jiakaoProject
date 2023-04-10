package com.mj.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mj.jk.pojo.po.${type};
import com.mj.jk.pojo.vo.req.list.${type}Query;

public interface ${type}Service
        extends IService<${type}> {
    void list(${type}Query query);
}