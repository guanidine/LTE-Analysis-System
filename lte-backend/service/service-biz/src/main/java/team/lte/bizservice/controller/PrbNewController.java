package team.lte.bizservice.controller;

import com.google.common.collect.ImmutableSet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.lte.bizservice.entity.dto.PrbDTO;
import team.lte.bizservice.entity.dto.PrbNewDTO;
import team.lte.bizservice.entity.po.Prb;
import team.lte.bizservice.entity.vo.PrbQuery;
import team.lte.bizservice.mapper.PrbMapper;
import team.lte.bizservice.service.PrbService;
import team.lte.bizservice.service.PrbnewService;
import team.lte.commonutils.easyexcel.ExcelServiceBuilder;
import team.lte.commonutils.easyexcel.annotation.DbType;
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
@Tag(name = "PRBNew干扰数据管理")
@RestController
@RequestMapping("/biz/prbnew")
public class PrbNewController {

    @Resource
    private PrbnewService prbnewService;

    @Operation(summary = "将tbPRBnew导出到外部excel文件中")
    @GetMapping("download")
    public void downloadNewExcel(HttpServletResponse response) {
        ExcelServiceBuilder.build(DbType.POSTGRE_SQL)
                .downloadFile(response, PrbNewDTO.class, prbnewService);
    }

}
