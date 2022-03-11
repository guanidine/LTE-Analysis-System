package team.lte.businessquery.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.lte.businessquery.entity.Cell;
import team.lte.businessquery.entity.vo.CellQuery;
import team.lte.businessquery.service.CellService;
import team.lte.commonutils.result.R;

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

    @Autowired
    private CellService cellService;

    @Operation(summary = "小区配置信息查询", description = "分页查询tbCell表中的小区配置信息")
    @GetMapping("{page}/{limit}")
    public R pageList(
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

    @Operation(summary = "小区配置信息条件查询", description = "有条件地分页查询tbCell表中的小区配置信息")
    @PostMapping("condition/{page}/{limit}")
    public R pageListConditioned(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable long page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable long limit,
            @ApiParam(value = "查询条件")
            @RequestBody(required = false) CellQuery cellQuery) {

        Page<Cell> pageParam = new Page<>(page, limit);
        QueryWrapper<Cell> wrapper = new QueryWrapper<>();

        String sectorName = cellQuery.getSectorName();
        String sectorId = cellQuery.getSectorId();
        if (StringUtils.isNotEmpty(sectorName)) {
            wrapper.like("sector_name", sectorName);
        }
        if (StringUtils.isNotEmpty(sectorId)) {
            wrapper.like("sector_id", sectorId);
        }

        cellService.page(pageParam, wrapper);
        List<Cell> list = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("total", total).data("list", list);
    }

    @Operation(summary = "所有小区列表")
    @GetMapping("all")
    public R getSectorName() {

        List<String> list = cellService.getSectorName();

        return R.ok().data("list", list);
    }

}

