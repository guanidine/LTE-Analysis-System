package team.lte.commonutils.easyexcel.listener;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import team.lte.commonutils.easyexcel.entity.ErrorDTO;
import team.lte.commonutils.easyexcel.mapper.BaseBatchMapper;
import team.lte.commonutils.easyexcel.service.BaseCheckService;
import team.lte.commonutils.easyexcel.service.PropertyCheckService;

import java.lang.reflect.Field;
import java.util.*;

/**
 * EasyExcel数据导入监听器。
 *
 * @param <T> 数据表对应POJO类
 */
@Slf4j
@Getter
public class ExcelListener<T> extends AnalysisEventListener<T> {

    /** 数据写入数据库/错误数据写入Excel时，一组数据行数上限 */
    private int groupNum = 1000;

    /** 错误数据集 */
    private final List<ErrorDTO> errList = new ArrayList<>();

    /** 等待校验的数据集 */
    private final List<T> successList = new ArrayList<>();

    /** Service类 */
    private final BaseCheckService<T> baseCheckService;

    /** Mapper类 */
    private final BaseBatchMapper<T> baseBatchMapper;

    /** 数据表对应POJO类的反射 */
    private final Class<T> clazz;

    private final ExcelWriter excelWriter;
    private final WriteSheet writeSheet;
    /** 出错的数据行数 */
    private int error = 0;

    private ExcelListener(BaseCheckService<T> baseCheckService, BaseBatchMapper<T> baseBatchMapper, Class<T> clazz,
        ExcelWriter excelWriter) {
        this.baseCheckService = baseCheckService;
        this.baseBatchMapper = baseBatchMapper;
        this.clazz = clazz;
        this.excelWriter = excelWriter;
        writeSheet = EasyExcelFactory.writerSheet().build();
    }

    public static <T> ExcelListener<T> build(BaseCheckService<T> baseCheckService, BaseBatchMapper<T> baseBatchMapper,
        Class<T> clazz, ExcelWriter excelWriter) {
        return new ExcelListener<>(baseCheckService, baseBatchMapper, clazz, excelWriter);
    }

    /** 数据写入数据库/错误数据写入Excel时，一组数据行数上限 */
    public ExcelListener<T> groupNum(int groupNum) {
        this.groupNum = groupNum;
        return this;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        String errMsg;
        try {
            errMsg = PropertyCheckService.check(t);
        } catch (NoSuchFieldException e) {
            errMsg = "解析数据出错";
            e.printStackTrace();
        }
        if (StringUtils.isNotEmpty(errMsg)) {
            // 属性校验出错
            errList.add(new ErrorDTO(analysisContext.readRowHolder().getRowIndex(), errMsg));
        } else {
            errMsg = baseCheckService.requirementCheck(t);
            if (StringUtils.isNotEmpty(errMsg)) {
                // 业务校验出错
                errList.add(new ErrorDTO(analysisContext.readRowHolder().getRowIndex(), errMsg));
            } else {
                successList.add(t);
            }
        }

        if (successList.size() >= groupNum) {
            baseBatchMapper.insertOrUpdateBatch(successList);
            successList.clear();
        }
        if (errList.size() >= groupNum) {
            error += groupNum;
            excelWriter.write(errList, writeSheet);
            errList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (!successList.isEmpty()) {
            baseBatchMapper.insertOrUpdateBatch(successList);
        }
        if (!errList.isEmpty()) {
            error += errList.size();
            excelWriter.write(errList, writeSheet);
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if (clazz != null) {
            try {
                Map<Integer, String> indexNameMap = getIndexNameMap(clazz);
                if (!headMap.equals(indexNameMap)) {
                    throw new ExcelAnalysisException("解析Excel出错，请传入正确格式的Excel;");
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<Integer, String> getIndexNameMap(Class<T> clazz) throws NoSuchFieldException {
        Map<Integer, String> result = new HashMap<>();
        Field field;
        Field[] fields = clazz.getDeclaredFields();
        for (Field item : fields) {
            field = clazz.getDeclaredField(item.getName());
            field.setAccessible(true);
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty != null) {
                int index = excelProperty.index();
                String[] values = excelProperty.value();
                StringBuilder value = new StringBuilder();
                for (String v : values) {
                    value.append(v);
                }
                result.put(index, value.toString());
            }
        }
        return result;
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        error = -1;
        throw exception;
    }

}
