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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Excel数据导入导出操作。
 */
@Slf4j
public class ExcelServiceBuilder {

    /** 不采用分页，一次性读取的行数上限 */
    private int noPagingLimit = 1000;
    /** 分页读取时，一页数据行数上限 */
    private int pageNum = 1000;
    /** 数据写入数据库/错误数据写入Excel时，一组数据行数上限 */
    private int groupNum = 500;
    /** 不需要导出的字段 */
    private Set<String> excludeColumnFiledNames = new HashSet<>();

    private ExcelServiceBuilder() {}

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

    /** 不需要导出的字段 */
    public ExcelServiceBuilder excelServiceBuilder(Set<String> excludeColumnFiledNames) {
        this.excludeColumnFiledNames = excludeColumnFiledNames;
        return this;
    }

    /**
     * 将数据表中的数据下载到Excel表中
     *
     * @param response HTTP Response
     * @param dClass 数据表导出时参照的类
     * @param service 数据表对应Service类
     * @param <P> 数据表对应PO类
     * @param <D> 数据表导出时参照的DTO类，决定了导出时的表头、列顺序等
     */
    public <P, D> void downloadFile(HttpServletResponse response, Class<D> dClass, IService<P> service) {

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
                EasyExcelFactory.write(response.getOutputStream(), dClass)
                    .excludeColumnFiledNames(excludeColumnFiledNames).sheet("sheet1").doWrite(service.list());
            } else {
                excelWriter = EasyExcelFactory.write(response.getOutputStream(), dClass)
                    .excludeColumnFiledNames(excludeColumnFiledNames).build();
                WriteSheet writeSheet = EasyExcelFactory.writerSheet("sheet1").build();

                long num = (count / pageNum) + 1;
                for (long i = 1; i <= num; i++) {
                    Page<P> page = new Page<>(i, pageNum, false);
                    IPage<P> iPage = service.page(page);
                    List<P> list = iPage.getRecords();
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

    /**
     * 将Excel中的数据导入到数据表中
     *
     * @param response HTTP Response
     * @param file 传入的Excel文件
     * @param pClass 数据表对应PO类
     * @param dClass Excel表对应的DTO类
     * @param service 数据表对应的Service类
     * @param mapper 数据表对应的Mapper类
     * @param <P> 数据表对应PO类
     * @param <D> Excel导入时参照的的DTO类，Excel包括哪些字段，字段是否有校验均由该DTO类设置
     */
    public <P, D> void uploadFile(HttpServletResponse response, MultipartFile file, Class<P> pClass, Class<D> dClass,
        BaseCheckService<P> service, BaseBatchMapper<P> mapper) {

        ExcelListener<P, D> excelListener = null;
        ExcelWriter excelWriter = null;
        try {
            String filename = URLEncoder.encode(String.valueOf(System.currentTimeMillis()), "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + filename + ".xlsx");
            response.setHeader("FileName", filename + ".xlsx");
            response.setHeader("Access-Control-Expose-Headers", "FileName, Error");

            excelWriter = EasyExcelFactory.write(response.getOutputStream(), ErrorDTO.class).build();

            excelListener = ExcelListener.build(service, mapper, pClass, dClass, excelWriter).groupNum(groupNum);
            EasyExcelFactory.read(file.getInputStream(), dClass, excelListener).sheet().doRead();
            response.setHeader("Error", String.valueOf(excelListener.getError()));
            log.info("EasyExcel----->[ Upload ] Finish!");
        } catch (Exception e) {
            if (excelListener != null) {
                if (excelListener.getError() == 0) {
                    response.setHeader("Error", "-2");
                } else {
                    response.setHeader("Error", String.valueOf(excelListener.getError()));
                }
            }
            log.info("EasyExcel----->[ Upload ] Finish with error!");
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

}
