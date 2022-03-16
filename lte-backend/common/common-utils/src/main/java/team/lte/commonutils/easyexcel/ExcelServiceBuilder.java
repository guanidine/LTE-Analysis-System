package team.lte.commonutils.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import team.lte.commonutils.easyexcel.entity.ErrorDTO;
import team.lte.commonutils.easyexcel.listener.ExcelListener;
import team.lte.commonutils.easyexcel.mapper.BaseBatchMapper;
import team.lte.commonutils.easyexcel.service.BaseCheckService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel数据导入导出操作。
 */
@Slf4j
public class ExcelServiceBuilder {

    /** 不采用分页，一次性读取的行数上限 */
    private int noPagingLimit = 10000;
    /** 分页读取时，一页数据行数上限 */
    private int pageNum = 500;
    /** 数据写入数据库/错误数据写入Excel时，一组数据行数上限 */
    private int groupNum = 1000;

    private ExcelServiceBuilder() {
    }

    public static ExcelServiceBuilder build() {
        return new ExcelServiceBuilder();
    }

    /** 不采用分页，一次性读取的行数上限 */
    public ExcelServiceBuilder noPagingLimit(int noPagingLimit) {
        this.noPagingLimit = noPagingLimit;
        return this;
    }

    /** 分页读取时，一页数据行数上限 */
    public ExcelServiceBuilder pageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    /** 数据写入数据库/错误数据写入Excel时，一组数据行数上限 */
    public ExcelServiceBuilder groupNum(int groupNum) {
        this.groupNum = groupNum;
        return this;
    }

    public <T> void downloadFile(HttpServletResponse response, Class<T> clazz, IService<T> service) {

        ExcelWriter excelWriter = null;
        try {
            String filename = URLEncoder.encode(String.valueOf(System.currentTimeMillis()), "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + filename + ".xlsx");
            response.setHeader("FileName", filename + ".xlsx");
            response.setHeader("Access-Control-Expose-Headers", "FileName");

            // 分页查询，可用于处理超大规模的数据表
            long count = service.count();

            if (count <= noPagingLimit) {
                EasyExcelFactory
                        .write(response.getOutputStream(), clazz)
                        .sheet()
                        .doWrite(service.list());
            } else {
                excelWriter = EasyExcelFactory.write(response.getOutputStream(), clazz).build();
                WriteSheet writeSheet = EasyExcelFactory.writerSheet("tbCell").build();

                long num = (count / pageNum) + 1;
                for (long i = 1; i <= num; i++) {
                    Page<T> page = new Page<>(i, pageNum);
                    IPage<T> iPage = service.page(page);
                    List<T> list = iPage.getRecords();
                    if (!list.isEmpty()) {
                        excelWriter.write(list, writeSheet);
                    }
                }
            }
            log.info("EasyExcel----->[ Download ] Finish {} rows!", count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    public <T> void uploadFile(HttpServletResponse response, MultipartFile file, Class<T> clazz,
                               BaseCheckService<T> service, BaseBatchMapper<T> mapper) {

        ExcelListener<T> excelListener = null;
        ExcelWriter excelWriter = null;
        try {
            String filename = URLEncoder.encode(String.valueOf(System.currentTimeMillis()), "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + filename + ".xlsx");
            response.setHeader("FileName", filename + ".xlsx");
            response.setHeader("Access-Control-Expose-Headers", "FileName, Error");

            excelWriter = EasyExcelFactory.write(response.getOutputStream(), ErrorDTO.class).build();

            excelListener = ExcelListener.build(service, mapper, clazz, excelWriter).groupNum(groupNum);
            EasyExcelFactory.read(file.getInputStream(), clazz, excelListener).sheet().doRead();
            response.setHeader("Error", String.valueOf(excelListener.getError()));
            excelWriter.finish();
            log.info("EasyExcel----->[ Upload ] Finish!");
        } catch (Exception e) {
            if (excelListener != null) {
                response.setHeader("Error", String.valueOf(excelListener.getError()));
            }
            if (excelWriter != null) {
                excelWriter.finish();
            }
            e.printStackTrace();
        }
    }

}
