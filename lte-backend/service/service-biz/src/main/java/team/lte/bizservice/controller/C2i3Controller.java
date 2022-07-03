package team.lte.bizservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import team.lte.bizservice.entity.po.C2i3;
import team.lte.bizservice.entity.po.C2inew;
import team.lte.bizservice.service.C2i3Service;
import team.lte.bizservice.service.C2inewService;
import team.lte.commonutils.result.R;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-05-31
 */
@Tag(name="生成小区三元组")
@RestController
@RequestMapping("/biz/c2i3")
public class C2i3Controller {
    @Resource
    C2i3Service c2i3Service;

    @Operation(summary = "创建tbc2i3")
    @PostMapping("/create")
    public R createTbc2i3(@Parameter(description = "分析并创建tbc2inew", required = true)
                             @RequestParam(name = "x")
                                     double x) {
        c2i3Service.createTbc2i3(x);
        return R.ok();
    }

    @Operation(summary = "获取tbc2i3对应页面数据")
    @GetMapping("/list")
    public R getTbc2i3List(@Parameter(description = "页号", required = true) @RequestParam(value = "page") long page,
                             @Parameter(description = "页大小", required = true) @RequestParam(value = "limit") long limit) {
        List<C2i3> list = c2i3Service.getPageList(page,limit);

        return R.ok().data("data",list);
    }

    @Operation(summary = "获取tbc2i3的总数量")
    @GetMapping("/total")
    public R getTbc2i3List() {
        return R.ok().data("total",c2i3Service.count());
    }
}

