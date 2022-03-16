package team.lte.businessquery.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import team.lte.businessquery.mapper.TableMapper;
import team.lte.commonutils.result.R;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "数据库全表信息")
@CrossOrigin
@RestController
@RequestMapping("/database/info")
public class TableController {

    @Resource
    TableMapper tableMapper;

    @Operation(summary = "获取所有数据表的表名")
    @GetMapping("")
    public R getTables() {
        List<Map<String, String>> list = tableMapper.getTables();
        return R.ok().data("list", list);
    }

    @Operation(summary = "获取目标数据表的结构")
    @GetMapping("{table}")
    public R getTableColumns(
            @ApiParam(value = "待查数据表名", required = true)
            @PathVariable String table) {

        List<Map<String, String>> list = tableMapper.getTableColumns(table);
        return R.ok().data("list", list);
    }

}
