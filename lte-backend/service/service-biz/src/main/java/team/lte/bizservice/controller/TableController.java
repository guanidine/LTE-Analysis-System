package team.lte.bizservice.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import team.lte.bizservice.mapper.TableMapper;
import team.lte.commonutils.result.R;

@Tag(name = "数据库全表信息")
@RestController
@RequestMapping("/biz/database")
public class TableController {

    @Resource
    TableMapper tableMapper;

    @Operation(summary = "获取所有数据表的表名")
    @GetMapping("")
    public R listTables() {
        List<Map<String, String>> list = tableMapper.listTables("lte");
        return R.ok().data("list", list);
    }

    @Operation(summary = "获取目标数据表的结构")
    @GetMapping("{table}")
    public R listTableColumns(@Parameter(description = "待查数据表名", required = true) @PathVariable String table) {

        List<Map<String, String>> list = tableMapper.listTableColumns(table);
        return R.ok().data("list", list);
    }

    @Operation(summary = "获取目标数据表的物理文件路径")
    @GetMapping("{table}/file")
    public R listTableFile(@Parameter(description = "待查数据表名", required = true) @PathVariable String table) {

        String file = tableMapper.listTableFile(table);
        return R.ok().data("file", file);
    }

    @Operation(summary = "获取目标数据表的物理大小")
    @GetMapping("{table}/size")
    public R listTableSize(@Parameter(description = "待查数据表大小", required = true) @PathVariable String table) {

        String size = tableMapper.listTableSize(table);
        return R.ok().data("size", size);
    }
}
