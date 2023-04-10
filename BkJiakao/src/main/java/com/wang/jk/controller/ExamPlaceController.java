package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.pojo.vo.ProvinceVo;
import com.wang.jk.pojo.po.ExamPlace;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.list.ExamPlaceVo;
import com.wang.jk.pojo.vo.req.save.ExamPlaceReqVo;
import com.wang.jk.pojo.vo.req.list.ExamPlaceListReqVo;
import com.wang.jk.pojo.vo.json.DataJsonVo;
import com.wang.jk.pojo.vo.json.ListJsonVo;
import com.wang.jk.service.ExamPlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/examPlaces")
@Api(tags = "考场")
public class ExamPlaceController extends BaseController<ExamPlace, ExamPlaceReqVo> {
    @Autowired
    private ExamPlaceService service;

    @Override
    @RequiresPermissions(
            value = {
                    Constants.Permisson.EXAM_PLACE_UPDATE,
                    Constants.Permisson.EXAM_PLACE_ADD
            },
            logical = Logical.AND
    )
    public JsonVo save(@Valid ExamPlaceReqVo examPlaceReqVo) {
        return super.save(examPlaceReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permisson.EXAM_PLACE_REMOVE)
    public JsonVo remove(@NotBlank(message = "id不能为空") String id) {
        return super.remove(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permisson.EXAM_PLACE_LIST)
    public ListJsonVo<ExamPlaceVo> list(ExamPlaceListReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @GetMapping("/regions")
    @ApiOperation("查询所有区域下的考场")
    public DataJsonVo<List<ProvinceVo>> listRegionExamPlaces() {
        return JsonVos.ok(service.listRegionExamPlaces());
    }

    @Override
    protected IService<ExamPlace> getService() {
        return service;
    }

    @Override
    protected ExamPlace getPo(ExamPlaceReqVo reqVo) {
        return MapStructs.INSTANCE.reqVo2po(reqVo);
    }
}
