package team.lte.businessquery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.lte.businessquery.entity.po.Kpi;
import team.lte.businessquery.entity.vo.KpiQuery;
import team.lte.businessquery.mapper.KpiMapper;
import team.lte.businessquery.service.KpiService;
import team.lte.commonutils.easyexcel.ExcelServiceBuilder;
import team.lte.commonutils.result.R;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-04-07
 */
@Tag(name = "KPI指标统计数据管理")
@CrossOrigin
@RestController
@RequestMapping("/business-query/kpi")
public class KpiController {

    @Resource
    private KpiService kpiService;

    @Resource
    private KpiMapper kpiMapper;

    @Operation(summary = "查询KPI指标中某一特定属性的变化")
    @PostMapping("")
    public R listData(@Parameter(description = "查询条件", required = true) @RequestBody KpiQuery kpiQuery) {

        Map<String, Object> map = null;
        try {
            map = kpiService.listData(
                    kpiQuery.getSectorName(), kpiQuery.getBegin(), kpiQuery.getEnd(), kpiQuery.getField());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return R.ok().data(map);
    }

    @Operation(summary = "KPI表中有记录的小区列表")
    @GetMapping("all-sector")
    public R listSectors() {

        List<String> list = kpiService.listSectors();

        return R.ok().data("list", list);
    }

    @Operation(summary = "将Kpi导出到Excel表")
    @GetMapping("download")
    public void downloadExcel(HttpServletResponse response) {
        ExcelServiceBuilder.build().downloadFile(response, Kpi.class, kpiService);
    }

    @Operation(summary = "将Excel表中数据导入到Kpi")
    @PostMapping(value = "upload", headers = "content-type=multipart/form-data")
    @ResponseBody
    public void uploadExcel(HttpServletResponse response,
                            @Parameter(description = "上传文件", required = true) @RequestPart("file") MultipartFile file) {
        ExcelServiceBuilder.build().uploadFile(response, file, Kpi.class, kpiService, kpiMapper);
    }
}
