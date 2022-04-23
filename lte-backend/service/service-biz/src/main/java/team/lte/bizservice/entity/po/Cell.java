package team.lte.bizservice.entity.po;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 小区配置信息
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
@Getter
@Setter
@TableName("tbcell")
@Schema(description = "小区配置信息")
public class Cell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "城市/地区名称")
    @ExcelProperty(value = "城市/地区名称", index = 0)
    @ColumnWidth(10)
    private String city;

    @Schema(description = "小区ID")
    @ExcelProperty(value = "小区ID", index = 1)
    @TableId(type = IdType.INPUT)
    @ColumnWidth(10)
    @NotNull
    private String sectorId;

    @Schema(description = "小区名称")
    @ExcelProperty(value = "小区名称", index = 2)
    @ColumnWidth(20)
    @NotNull
    private String sectorName;

    @Schema(description = "小区所属基站标识")
    @ExcelProperty(value = "小区所属基站标识", index = 3)
    @ColumnWidth(10)
    @NotNull
    private Integer enodebid;

    @Schema(description = "基站名称")
    @ExcelProperty(value = "基站名称", index = 4)
    @ColumnWidth(20)
    @NotNull
    private String enodebName;

    @Schema(description = "小区配置的频点编号")
    @ExcelProperty(value = "小区配置的频点编号", index = 5)
    @ColumnWidth(10)
    @NotNull
    private Integer earfcn;

    @Schema(description = "物理小区标识")
    @ExcelProperty(value = "物理小区标识", index = 6)
    @ColumnWidth(10)
    private Integer pci;

    @Schema(description = "主同步信号标识")
    @ExcelProperty(value = "主同步信号标识", index = 7)
    @ColumnWidth(10)
    private Integer pss;

    @Schema(description = "辅同步信号标识")
    @ExcelProperty(value = "辅同步信号标识", index = 8)
    private Integer sss;

    @Schema(description = "跟踪区编码")
    @ExcelProperty(value = "跟踪区编码", index = 9)
    @ColumnWidth(10)
    private Integer tac;

    @Schema(description = "供应商")
    @ExcelProperty(value = "供应商", index = 10)
    @ColumnWidth(10)
    private String vendor;

    @Schema(description = "经度")
    @ExcelProperty(value = "经度", index = 11)
    @ColumnWidth(10)
    @NotNull
    @DecimalMax("180")
    @DecimalMin("0")
    private Float longitude;

    @Schema(description = "纬度")
    @ExcelProperty(value = "纬度", index = 12)
    @ColumnWidth(10)
    @NotNull
    @DecimalMax("90")
    @DecimalMin("0")
    private Float latitude;

    @Schema(description = "基站类型")
    @ExcelProperty(value = "基站类型", index = 13)
    @ColumnWidth(10)
    private String style;

    @Schema(description = "小区天线方位角")
    @ExcelProperty(value = "小区天线方位角", index = 14)
    @ColumnWidth(10)
    @NotNull
    private Float azimuth;

    @Schema(description = "小区天线高度")
    @ExcelProperty(value = "小区天线高度", index = 15)
    @ColumnWidth(10)
    private Float height;

    @Schema(description = "小区天线电下倾角")
    @ExcelProperty(value = "小区天线电下倾角", index = 16)
    @ColumnWidth(10)
    private Float electtilt;

    @Schema(description = "小区天线机械下倾角")
    @ExcelProperty(value = "小区天线机械下倾角", index = 17)
    @ColumnWidth(10)
    private Float mechtilt;

    @Schema(description = "总下倾角")
    @ExcelProperty(value = "总下倾角", index = 18)
    @ColumnWidth(10)
    private Float totletilt;

}
