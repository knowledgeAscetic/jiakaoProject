package com.wang.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.jk.common.mapStruct.MapStructs;
import com.wang.jk.common.util.Constants;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.common.util.Uploads;
import com.wang.jk.pojo.po.ExamPlaceCourse;
import com.wang.jk.pojo.po.Image;
import com.wang.jk.pojo.result.CodeMsg;
import com.wang.jk.pojo.vo.req.save.ExamPlaceCourseReqVo;
import com.wang.jk.pojo.vo.req.list.ExamPlaceCourseListReqVo;
import com.wang.jk.pojo.vo.json.ListJsonVo;
import com.wang.jk.pojo.vo.json.JsonVo;
import com.wang.jk.pojo.vo.list.ExamPlaceCourseVo;
import com.wang.jk.service.ExamPlaceCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/examPlaceCourses")
@Api(tags = "科2科3课程")
public class ExamPlaceCourseController extends BaseController<ExamPlaceCourse, ExamPlaceCourseReqVo> {
    @Autowired
    private ExamPlaceCourseService service;

    @Override
    @RequiresPermissions(
            {
                    Constants.Permisson.EXAM_PLACE_COURSE_ADD,
                    Constants.Permisson.EXAM_PLACE_COURSE_UPDATE
            }
    )
    public JsonVo save(@Valid ExamPlaceCourseReqVo examPlaceCourseReqVo) {
        return super.save(examPlaceCourseReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permisson.EXAM_PLACE_COURSE_REMOVE)
    public JsonVo remove(@NotBlank(message = "id不能为空") String id) {
        return super.remove(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permisson.EXAM_PLACE_COURSE_LIST)
    public ListJsonVo<ExamPlaceCourseVo> list(ExamPlaceCourseListReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @PostMapping("/saveAll")
    @ApiOperation("添加或更新（带有封面）")
    public JsonVo saveAll(ExamPlaceCourseReqVo courseReqVo, MultipartFile coverFile) throws Exception {
        Image image = Uploads.uploadImage(coverFile);
        if (service.saveOrUpdate(courseReqVo, image)) {
            return JsonVos.ok();
        }
        return JsonVos.raise(CodeMsg.SAVE_ERROR);
    }

    @PostMapping("/uploadVideo")
    @ApiOperation("上传视频")
    public JsonVo uploadVideo(MultipartFile videoFile, String id) throws Exception {
        String video = Uploads.uploadVideo(videoFile);
        if (video != null) {
            ExamPlaceCourse course = service.getById(id);
            if (course.getVideo() != null) {
                Uploads.remove(course.getVideo());
            }
            course.setVideo(video);
            if (service.saveOrUpdate(course)) {
                return JsonVos.ok(course);
            }
        }
        return JsonVos.raise(CodeMsg.SAVE_ERROR);
    }

    @Override
    protected IService<ExamPlaceCourse> getService() {
        return service;
    }

    @Override
    protected ExamPlaceCourse getPo(ExamPlaceCourseReqVo reqVo) {
        return MapStructs.INSTANCE.reqVo2po(reqVo);
    }
}
