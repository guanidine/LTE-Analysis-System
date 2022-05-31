package team.lte.bizservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import team.lte.bizservice.entity.po.C2inew;
import team.lte.bizservice.service.C2inewService;
import team.lte.commonutils.result.R;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-05-31
 */
@RestController
@RequestMapping("/bizservice/c2-inew")
public class C2inewController {
    @Resource
    private C2inewService c2inewService;

    @Operation(summary = "创建tbc2inew")
    @PostMapping("/create")
    public R getTbc2inewList(@Parameter(description = "分析并创建tbc2inew", required = true)
                             @RequestParam(name = "x")
                                     int x) {
        c2inewService.createTbc2inew(x);
        return R.ok();
    }

    @Operation(summary = "获取tbc2inew对应页面数据")
    @GetMapping("/list/{page}/{limit}")
    public R getTbc2inewList(@Parameter(description = "页号", required = true) @PathVariable(value = "page") long page,
                             @Parameter(description = "页大小", required = true) @PathVariable(value = "limit") long limit) {
        List<C2inew> list = c2inewService.getPageList(page,limit);
        return R.ok().data("total",list.size()).data("data",list);
    }


}

