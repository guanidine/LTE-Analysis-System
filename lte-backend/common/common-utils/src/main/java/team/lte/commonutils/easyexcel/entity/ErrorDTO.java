package team.lte.commonutils.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 数据导入错误信息输出DTO
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO implements Serializable {

    @ExcelProperty(index = 0, value = "行号")
    private Integer row;

    @ExcelProperty(index = 1, value = "错误信息")
    @ColumnWidth(50)
    private String msg;

}
