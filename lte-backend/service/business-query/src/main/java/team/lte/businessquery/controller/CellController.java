package team.lte.businessquery.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.lte.businessquery.entity.Cell;
import team.lte.businessquery.entity.vo.CellEnodeb;
import team.lte.businessquery.entity.vo.CellQuery;
import team.lte.businessquery.mapper.CellMapper;
import team.lte.businessquery.service.CellService;
import team.lte.commonutils.result.R;
import team.lte.commonutils.easyexcel.ExcelServiceBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
@Api(tags = "小区配置信息管理")
@CrossOrigin
@RestController
@RequestMapping("/business-query/cell")
public class CellController {

    @Resource
    private CellService cellService;

    @Resource
    private CellMapper cellMapper;

    @Operation(summary = "小区配置信息查询")
    @GetMapping("{page}/{limit}")
    public R getPageList(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable long page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable long limit) {

        Page<Cell> pageParam = new Page<>(page, limit);

        cellService.page(pageParam, null);
        List<Cell> list = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("total", total).data("list", list);
    }

    @Operation(summary = "小区配置信息条件查询")
    @PostMapping("condition/{page}/{limit}")
    public R getPageListConditioned(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable long page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable long limit,
            @ApiParam(value = "查询条件")
            @RequestBody(required = false) CellQuery cellQuery) {

        Page<Cell> pageParam = new Page<>(page, limit);
        LambdaQueryWrapper<Cell> wrapper = new LambdaQueryWrapper<>();

        String sectorName = cellQuery.getSectorName();
        String sectorId = cellQuery.getSectorId();
        Integer enodebid = cellQuery.getEnodebid();
        String enodebName = cellQuery.getEnodebName();
        if (StringUtils.isNotEmpty(sectorName)) {
            wrapper.like(Cell::getSectorName, sectorName);
        }
        if (StringUtils.isNotEmpty(sectorId)) {
            wrapper.like(Cell::getSectorId, sectorId);
        }
        if (enodebid != null) {
            wrapper.like(Cell::getEnodebid, enodebid);
        }
        if (StringUtils.isNotEmpty(enodebName)) {
            wrapper.like(Cell::getEnodebName, enodebName);
        }

        cellService.page(pageParam, wrapper);
        List<Cell> list = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("total", total).data("list", list);
    }

    @Operation(summary = "所有小区列表")
    @GetMapping("all-sector")
    public R getSectors() {

        List<String> list = cellService.getSectors();

        return R.ok().data("list", list);
    }

    @Operation(summary = "所有基站列表")
    @GetMapping("all-enodeb")
    public R getEnodebs() {

        List<CellEnodeb> list = cellService.getEnodebs();

        return R.ok().data("list", list);
    }

    @Operation(summary = "将Cell导出到Excel表")
    @GetMapping("download")
    public void downloadExcel(HttpServletResponse response) {
        ExcelServiceBuilder.build().downloadFile(response, Cell.class, cellService);
    }

    @Operation(summary = "将Excel表中数据导入到Cell")
    @PostMapping(value = "upload", headers = "content-type=multipart/form-data")
    @ResponseBody
    public void uploadExcel(HttpServletResponse response,
                            @ApiParam(value = "上传文件", required = true)
                            @RequestPart("file") MultipartFile file) {
        ExcelServiceBuilder.build().uploadFile(response, file, Cell.class, cellService, cellMapper);
    }
}

