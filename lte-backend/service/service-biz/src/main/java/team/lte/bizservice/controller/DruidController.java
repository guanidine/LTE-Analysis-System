package team.lte.bizservice.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.ImmutableMap;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import team.lte.bizservice.entity.vo.DruidUpdate;
import team.lte.commonutils.SpringContextHolder;
import team.lte.commonutils.result.R;

@Tag(name = "数据库配置管理")
@CrossOrigin
@RestController
@RequestMapping("/biz/druid")
public class DruidController {

    @Operation(summary = "连接池监控数据")
    @GetMapping("")
    public R listProperties() {

        DruidDataSource dataSource = SpringContextHolder.getBean(DruidDataSource.class);

        Map<String, Object> map = ImmutableMap.<String, Object>builder().put("URL", dataSource.getUrl())
            .put("InitialSize", dataSource.getInitialSize()).put("MinIdle", dataSource.getMinIdle())
            .put("MaxActive", dataSource.getMaxActive()).put("PoolingCount", dataSource.getPoolingCount())
            .put("PoolingPeak", dataSource.getPoolingPeak()).put("ActiveCount", dataSource.getActiveCount())
            .put("ActivePeak", dataSource.getActivePeak()).put("ExecuteCount", dataSource.getExecuteCount()).build();

        return R.ok().data(map);
    }

    @Operation(summary = "连接池配置更新")
    @PostMapping("update")
    public R updateProperties(@RequestBody DruidUpdate update) throws SQLException {
        // TODO: 保存配置到配置文件，每次读取的时候传个参
        DruidDataSource dataSource = SpringContextHolder.getBean(DruidDataSource.class);
        boolean hasUpdated = update.getInitialSize() != dataSource.getInitialSize()
            || update.getMinIdle() != dataSource.getMinIdle() || update.getMaxActive() != dataSource.getMaxActive();
        if (hasUpdated) {
            dataSource.restart();
            dataSource.setInitialSize(update.getInitialSize());
            dataSource.setMinIdle(update.getMinIdle());
            dataSource.setMaxActive(update.getMaxActive());
            dataSource.init();
        }
        return listProperties();
    }

}
