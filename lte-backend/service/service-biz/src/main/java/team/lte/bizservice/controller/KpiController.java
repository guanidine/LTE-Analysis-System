package team.lte.bizservice.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableSet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import team.lte.bizservice.entity.dto.KpiDTO;
import team.lte.bizservice.entity.po.Kpi;
import team.lte.bizservice.entity.vo.KpiQuery;
import team.lte.bizservice.mapper.KpiMapper;
import team.lte.bizservice.service.KpiService;
import team.lte.bizservice.util.QueryUtils;
import team.lte.commonutils.easyexcel.ExcelServiceBuilder;
import team.lte.commonutils.result.R;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-04-07
 */
@Tag(name = "KPI指标统计数据管理")
@RestController
@RequestMapping("/biz/kpi")
public class KpiController {

    @Resource
    private KpiService kpiService;

    @Resource
    private KpiMapper kpiMapper;

    @Value("${spring.profiles.active}")
    private String env;

    @Operation(summary = "查询KPI指标中某一特定属性的变化")
    @PostMapping("")
    public R listData(@Parameter(description = "查询条件", required = true) @RequestBody KpiQuery kpiQuery) {

        Map<String, Object> map = null;
        try {
            map = kpiService.listData(kpiQuery.getSectorName(), kpiQuery.getBegin(), kpiQuery.getEnd(),
                kpiQuery.getField());
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
        ExcelServiceBuilder.build(QueryUtils.getDbType(env)).exclude(ImmutableSet.<String>builder().add("id").build())
            .downloadFile(response, KpiDTO.class, kpiService);
    }

    @Operation(summary = "将Excel表中数据导入到Kpi")
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void uploadExcel(HttpServletResponse response,
        @Parameter(description = "上传文件", required = true) @RequestPart("file") MultipartFile file) {
        ExcelServiceBuilder.build(QueryUtils.getDbType(env)).uploadFile(response, file, Kpi.class, KpiDTO.class,
            kpiService, kpiMapper);
    }
}
