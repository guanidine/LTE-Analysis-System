package team.lte.commonutils.easyexcel.listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.write.metadata.WriteSheet;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import team.lte.commonutils.easyexcel.annotation.DbType;
import team.lte.commonutils.easyexcel.entity.ErrorDTO;
import team.lte.commonutils.easyexcel.mapper.BaseBatchMapper;
import team.lte.commonutils.easyexcel.service.BaseCheckService;
import team.lte.commonutils.easyexcel.service.PropertyCheckService;

/**
 * EasyExcel数据导入监听器。
 *
 * @param <P> 数据表对应PO类
 * @param <D> 传入数据对应DTO类
 */
@Slf4j
@Getter
public class ExcelListener<P, D> extends AnalysisEventListener<D> {

    /** 数据库方言 */
    private Consumer<List<P>> insertConsumer;

    /** 数据写入数据库/错误数据写入Excel时，一组数据行数上限 */
    private int groupNum = 500;

    /** 错误数据集 */
    private final List<ErrorDTO> errList = new ArrayList<>();

    /** 正确数据集 */
    private final List<P> successList = new ArrayList<>();

    /** Service类 */
    private final BaseCheckService<P> baseCheckService;

    /** Mapper类 */
    private final BaseBatchMapper<P> baseBatchMapper;

    /** PO类和DTO类 */
    private final Class<P> pClass;
    private final Class<D> dClass;

    private final ExcelWriter excelWriter;
    private final WriteSheet writeSheet;
    /** 出错的数据行数 */
    private int error = 0;

    private ExcelListener(BaseCheckService<P> baseCheckService, BaseBatchMapper<P> baseBatchMapper, Class<P> pClass,
        Class<D> dClass, ExcelWriter excelWriter, DbType dbType) {
        this.baseCheckService = baseCheckService;
        this.baseBatchMapper = baseBatchMapper;
        this.pClass = pClass;
        this.dClass = dClass;
        this.excelWriter = excelWriter;
        writeSheet = EasyExcelFactory.writerSheet().build();
        this.insertConsumer = switch (dbType) {
            // 实现了触发器后则应写成：case POSTGRE_SQL -> baseBatchMapper::insertBatch;
            case POSTGRE_SQL -> baseBatchMapper::insertOrUpdateBatchPostgres;
            case GAUSS -> baseBatchMapper::insertOrUpdateBatchGauss;
            case OTHER -> baseBatchMapper::insertBatch;
        };
    }

    public static <T, E> ExcelListener<T, E> build(BaseCheckService<T> baseCheckService,
        BaseBatchMapper<T> baseBatchMapper, Class<T> tClass, Class<E> eClass, ExcelWriter excelWriter, DbType dbType) {
        return new ExcelListener<>(baseCheckService, baseBatchMapper, tClass, eClass, excelWriter, dbType);
    }

    /** 数据写入数据库/错误数据写入Excel时，一组数据行数上限 */
    public ExcelListener<P, D> groupNum(int groupNum) {
        this.groupNum = groupNum;
        return this;
    }

    @Override
    public void invoke(D d, AnalysisContext analysisContext) {
        String errMsg;
        try {
            errMsg = PropertyCheckService.check(d);
        } catch (NoSuchFieldException exception) {
            errMsg = "解析数据出错";
            exception.printStackTrace();
        }
        if (StringUtils.isNotEmpty(errMsg)) {
            // 属性校验出错
            errList.add(new ErrorDTO(analysisContext.readRowHolder().getRowIndex(), errMsg));
        } else {
            P p = baseCheckService.transfer(d, pClass, dClass);
            errMsg = baseCheckService.requirementCheck(p);
            if (StringUtils.isNotEmpty(errMsg)) {
                // 业务校验出错
                errList.add(new ErrorDTO(analysisContext.readRowHolder().getRowIndex(), errMsg));
            } else {
                successList.add(p);
            }
        }

        if (successList.size() >= groupNum) {
            insertConsumer.accept(successList);
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
            insertConsumer.accept(successList);
        }
        if (!errList.isEmpty()) {
            error += errList.size();
            excelWriter.write(errList, writeSheet);
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        if (dClass != null) {
            try {
                Map<Integer, String> indexNameMap = getIndexNameMap(dClass);
                if (!headMap.equals(indexNameMap)) {
                    throw new ExcelAnalysisException("解析Excel出错，请传入正确格式的Excel;");
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<Integer, String> getIndexNameMap(Class<D> dClass) throws NoSuchFieldException {
        Map<Integer, String> result = new HashMap<>();
        Field field;
        Field[] fields = dClass.getDeclaredFields();
        for (Field item : fields) {
            field = dClass.getDeclaredField(item.getName());
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
