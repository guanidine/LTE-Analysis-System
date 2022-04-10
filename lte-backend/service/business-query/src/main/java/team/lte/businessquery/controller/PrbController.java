package team.lte.businessquery.controller;

import com.google.common.collect.ImmutableSet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.lte.businessquery.entity.dto.PrbDTO;
import team.lte.businessquery.entity.po.Prb;
import team.lte.businessquery.entity.vo.PrbQuery;
import team.lte.businessquery.mapper.PrbMapper;
import team.lte.businessquery.service.PrbService;
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
@Tag(name = "PRB干扰数据管理")
@CrossOrigin
@RestController
@RequestMapping("/business-query/prb")
public class PrbController {

    @Resource
    private PrbService prbService;

    @Resource
    private PrbMapper prbMapper;

    @Operation(summary = "查询某项PRB干扰数据每15分钟的变化情况")
    @PostMapping("")
    public R listData(@Parameter(description = "查询条件", required = true) @RequestBody PrbQuery prbQuery) {

        Map<String, Object> map = null;
        try {
            map = prbService.listData(prbQuery.getEnodebName(), prbQuery.getBegin(), prbQuery.getEnd(),
                prbQuery.getField());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return R.ok().data(map);
    }

    @Operation(summary = "查询某项PRB干扰数据每小时的变化情况")
    @PostMapping("hour")
    public R listHourData(@Parameter(description = "查询条件", required = true) @RequestBody PrbQuery prbQuery) {

        Map<String, Object> map = null;
        try {
            map = prbService.listHourData(prbQuery.getEnodebName(), prbQuery.getBegin(), prbQuery.getEnd(),
                prbQuery.getField());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return R.ok().data(map);
    }

    @Operation(summary = "PRB表中有记录的基站列表")
    @GetMapping("all-enodeb")
    public R listEnodebs() {

        List<String> list = prbService.listEnodebs();

        return R.ok().data("list", list);
    }

    @Operation(summary = "将PRB导出到Excel表")
    @GetMapping("download")
    public void downloadExcel(HttpServletResponse response) {
        ExcelServiceBuilder.build().pageNum(10000).excelServiceBuilder(ImmutableSet.<String>builder().add("id").build())
            .downloadFile(response, PrbDTO.class, prbService);
    }

    @Operation(summary = "将Excel表中数据导入到PRB")
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void uploadExcel(HttpServletResponse response,
        @Parameter(description = "上传文件", required = true) @RequestPart("file") MultipartFile file) {
        ExcelServiceBuilder.build().uploadFile(response, file, Prb.class, PrbDTO.class, prbService, prbMapper);
    }
}
