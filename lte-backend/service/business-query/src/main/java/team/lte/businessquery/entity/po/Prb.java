package team.lte.businessquery.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lte
 * @since 2022-04-07
 */
@Getter
@Setter
@TableName("tbprb")
@Schema(description = "PRB干扰数据")
public class Prb implements Serializable {

    // TODO: 输入数据另建DTO类，排除掉id

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    @ColumnWidth(35)
    private String id;

    @Schema(description = "起始时间")
    @ExcelProperty(value = "起始时间", index = 1)
    @ColumnWidth(20)
    @NotNull
    private String startTime;

    @Schema(description = "网元/基站名称")
    @ExcelProperty(value = "网元/基站名称", index = 2)
    @ColumnWidth(20)
    @NotNull
    private String enodebName;

    @Schema(description = "小区描述")
    @ExcelProperty(value = "小区描述", index = 3)
    @ColumnWidth(120)
    @NotNull
    private String sectorDescription;

    @Schema(description = "小区名")
    @ExcelProperty(value = "小区名", index = 4)
    @ColumnWidth(20)
    @NotNull
    private String sectorName;

    @Schema(description = "第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 5)
    @ColumnWidth(10)
    @NotNull
    private Integer noise0;

    @Schema(description = "第1个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第1个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 6)
    @ColumnWidth(10)
    @NotNull
    private Integer noise1;

    @Schema(description = "第2个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第2个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 7)
    @ColumnWidth(10)
    @NotNull
    private Integer noise2;

    @Schema(description = "第3个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第3个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 8)
    @ColumnWidth(10)
    @NotNull
    private Integer noise3;

    @Schema(description = "第4个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第4个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 9)
    @ColumnWidth(10)
    @NotNull
    private Integer noise4;

    @Schema(description = "第5个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第5个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 10)
    @ColumnWidth(10)
    @NotNull
    private Integer noise5;

    @Schema(description = "第6个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第6个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 11)
    @ColumnWidth(10)
    @NotNull
    private Integer noise6;

    @Schema(description = "第7个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第7个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 12)
    @ColumnWidth(10)
    @NotNull
    private Integer noise7;

    @Schema(description = "第8个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第8个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 13)
    @ColumnWidth(10)
    @NotNull
    private Integer noise8;

    @Schema(description = "第9个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第9个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 14)
    @ColumnWidth(10)
    @NotNull
    private Integer noise9;

    @Schema(description = "第10个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第10个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 15)
    @ColumnWidth(10)
    @NotNull
    private Integer noise10;

    @Schema(description = "第11个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第11个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 16)
    @ColumnWidth(10)
    @NotNull
    private Integer noise11;

    @Schema(description = "第12个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第12个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 17)
    @ColumnWidth(10)
    @NotNull
    private Integer noise12;

    @Schema(description = "第13个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第13个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 18)
    @ColumnWidth(10)
    @NotNull
    private Integer noise13;

    @Schema(description = "第14个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第14个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 19)
    @ColumnWidth(10)
    @NotNull
    private Integer noise14;

    @Schema(description = "第15个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第15个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 20)
    @ColumnWidth(10)
    @NotNull
    private Integer noise15;

    @Schema(description = "第16个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第16个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 21)
    @ColumnWidth(10)
    @NotNull
    private Integer noise16;

    @Schema(description = "第17个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第17个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 22)
    @ColumnWidth(10)
    @NotNull
    private Integer noise17;

    @Schema(description = "第18个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第18个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 23)
    @ColumnWidth(10)
    @NotNull
    private Integer noise18;

    @Schema(description = "第19个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第19个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 24)
    @ColumnWidth(10)
    @NotNull
    private Integer noise19;

    @Schema(description = "第20个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第20个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 25)
    @ColumnWidth(10)
    @NotNull
    private Integer noise20;

    @Schema(description = "第21个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第21个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 26)
    @ColumnWidth(10)
    @NotNull
    private Integer noise21;

    @Schema(description = "第22个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第22个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 27)
    @ColumnWidth(10)
    @NotNull
    private Integer noise22;

    @Schema(description = "第23个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第23个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 28)
    @ColumnWidth(10)
    @NotNull
    private Integer noise23;

    @Schema(description = "第24个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第24个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 29)
    @ColumnWidth(10)
    @NotNull
    private Integer noise24;

    @Schema(description = "第25个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第25个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 30)
    @ColumnWidth(10)
    @NotNull
    private Integer noise25;

    @Schema(description = "第26个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第26个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 31)
    @ColumnWidth(10)
    @NotNull
    private Integer noise26;

    @Schema(description = "第27个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第27个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 32)
    @ColumnWidth(10)
    @NotNull
    private Integer noise27;

    @Schema(description = "第28个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第28个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 33)
    @ColumnWidth(10)
    @NotNull
    private Integer noise28;

    @Schema(description = "第29个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第29个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 34)
    @ColumnWidth(10)
    @NotNull
    private Integer noise29;

    @Schema(description = "第30个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第30个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 35)
    @ColumnWidth(10)
    @NotNull
    private Integer noise30;

    @Schema(description = "第31个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第31个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 36)
    @ColumnWidth(10)
    @NotNull
    private Integer noise31;

    @Schema(description = "第32个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第32个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 37)
    @ColumnWidth(10)
    @NotNull
    private Integer noise32;

    @Schema(description = "第33个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第33个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 38)
    @ColumnWidth(10)
    @NotNull
    private Integer noise33;

    @Schema(description = "第34个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第34个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 39)
    @ColumnWidth(10)
    @NotNull
    private Integer noise34;

    @Schema(description = "第35个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第35个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 40)
    @ColumnWidth(10)
    @NotNull
    private Integer noise35;

    @Schema(description = "第36个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第36个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 41)
    @ColumnWidth(10)
    @NotNull
    private Integer noise36;

    @Schema(description = "第37个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第37个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 42)
    @ColumnWidth(10)
    @NotNull
    private Integer noise37;

    @Schema(description = "第38个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第38个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 43)
    @ColumnWidth(10)
    @NotNull
    private Integer noise38;

    @Schema(description = "第39个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第39个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 44)
    @ColumnWidth(10)
    @NotNull
    private Integer noise39;

    @Schema(description = "第40个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第40个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 45)
    @ColumnWidth(10)
    @NotNull
    private Integer noise40;

    @Schema(description = "第41个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第41个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 46)
    @ColumnWidth(10)
    @NotNull
    private Integer noise41;

    @Schema(description = "第42个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第42个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 47)
    @ColumnWidth(10)
    @NotNull
    private Integer noise42;

    @Schema(description = "第43个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第43个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 48)
    @ColumnWidth(10)
    @NotNull
    private Integer noise43;

    @Schema(description = "第44个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第44个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 49)
    @ColumnWidth(10)
    @NotNull
    private Integer noise44;

    @Schema(description = "第45个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第45个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 50)
    @ColumnWidth(10)
    @NotNull
    private Integer noise45;

    @Schema(description = "第46个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第46个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 51)
    @ColumnWidth(10)
    @NotNull
    private Integer noise46;

    @Schema(description = "第47个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第47个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 52)
    @ColumnWidth(10)
    @NotNull
    private Integer noise47;

    @Schema(description = "第48个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第48个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 53)
    @ColumnWidth(10)
    @NotNull
    private Integer noise48;

    @Schema(description = "第49个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第49个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 54)
    @ColumnWidth(10)
    @NotNull
    private Integer noise49;

    @Schema(description = "第50个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第50个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 55)
    @ColumnWidth(10)
    @NotNull
    private Integer noise50;

    @Schema(description = "第51个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第51个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 56)
    @ColumnWidth(10)
    @NotNull
    private Integer noise51;

    @Schema(description = "第52个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第52个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 57)
    @ColumnWidth(10)
    @NotNull
    private Integer noise52;

    @Schema(description = "第53个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第53个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 58)
    @ColumnWidth(10)
    @NotNull
    private Integer noise53;

    @Schema(description = "第54个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第54个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 59)
    @ColumnWidth(10)
    @NotNull
    private Integer noise54;

    @Schema(description = "第55个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第55个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 60)
    @ColumnWidth(10)
    @NotNull
    private Integer noise55;

    @Schema(description = "第56个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第56个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 61)
    @ColumnWidth(10)
    @NotNull
    private Integer noise56;

    @Schema(description = "第57个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第57个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 62)
    @ColumnWidth(10)
    @NotNull
    private Integer noise57;

    @Schema(description = "第58个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第58个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 63)
    @ColumnWidth(10)
    @NotNull
    private Integer noise58;

    @Schema(description = "第59个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第59个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 64)
    @ColumnWidth(10)
    @NotNull
    private Integer noise59;

    @Schema(description = "第60个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第60个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 65)
    @ColumnWidth(10)
    @NotNull
    private Integer noise60;

    @Schema(description = "第61个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第61个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 66)
    @ColumnWidth(10)
    @NotNull
    private Integer noise61;

    @Schema(description = "第62个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第62个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 67)
    @ColumnWidth(10)
    @NotNull
    private Integer noise62;

    @Schema(description = "第63个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第63个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 68)
    @ColumnWidth(10)
    @NotNull
    private Integer noise63;

    @Schema(description = "第64个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第64个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 69)
    @ColumnWidth(10)
    @NotNull
    private Integer noise64;

    @Schema(description = "第65个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第65个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 70)
    @ColumnWidth(10)
    @NotNull
    private Integer noise65;

    @Schema(description = "第66个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第66个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 71)
    @ColumnWidth(10)
    @NotNull
    private Integer noise66;

    @Schema(description = "第67个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第67个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 72)
    @ColumnWidth(10)
    @NotNull
    private Integer noise67;

    @Schema(description = "第68个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第68个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 73)
    @ColumnWidth(10)
    @NotNull
    private Integer noise68;

    @Schema(description = "第69个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第69个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 74)
    @ColumnWidth(10)
    @NotNull
    private Integer noise69;

    @Schema(description = "第70个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第70个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 75)
    @ColumnWidth(10)
    @NotNull
    private Integer noise70;

    @Schema(description = "第71个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第71个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 76)
    @ColumnWidth(10)
    @NotNull
    private Integer noise71;

    @Schema(description = "第72个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第72个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 77)
    @ColumnWidth(10)
    @NotNull
    private Integer noise72;

    @Schema(description = "第73个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第73个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 78)
    @ColumnWidth(10)
    @NotNull
    private Integer noise73;

    @Schema(description = "第74个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第74个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 79)
    @ColumnWidth(10)
    @NotNull
    private Integer noise74;

    @Schema(description = "第75个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第75个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 80)
    @ColumnWidth(10)
    @NotNull
    private Integer noise75;

    @Schema(description = "第76个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第76个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 81)
    @ColumnWidth(10)
    @NotNull
    private Integer noise76;

    @Schema(description = "第77个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第77个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 82)
    @ColumnWidth(10)
    @NotNull
    private Integer noise77;

    @Schema(description = "第78个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第78个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 83)
    @ColumnWidth(10)
    @NotNull
    private Integer noise78;

    @Schema(description = "第79个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第79个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 84)
    @ColumnWidth(10)
    @NotNull
    private Integer noise79;

    @Schema(description = "第80个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第80个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 85)
    @ColumnWidth(10)
    @NotNull
    private Integer noise80;

    @Schema(description = "第81个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第81个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 86)
    @ColumnWidth(10)
    @NotNull
    private Integer noise81;

    @Schema(description = "第82个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第82个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 87)
    @ColumnWidth(10)
    @NotNull
    private Integer noise82;

    @Schema(description = "第83个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第83个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 88)
    @ColumnWidth(10)
    @NotNull
    private Integer noise83;

    @Schema(description = "第84个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第84个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 89)
    @ColumnWidth(10)
    @NotNull
    private Integer noise84;

    @Schema(description = "第85个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第85个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 90)
    @ColumnWidth(10)
    @NotNull
    private Integer noise85;

    @Schema(description = "第86个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第86个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 91)
    @ColumnWidth(10)
    @NotNull
    private Integer noise86;

    @Schema(description = "第87个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第87个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 92)
    @ColumnWidth(10)
    @NotNull
    private Integer noise87;

    @Schema(description = "第88个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第88个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 93)
    @ColumnWidth(10)
    @NotNull
    private Integer noise88;

    @Schema(description = "第89个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第89个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 94)
    @ColumnWidth(10)
    @NotNull
    private Integer noise89;

    @Schema(description = "第90个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第90个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 95)
    @ColumnWidth(10)
    @NotNull
    private Integer noise90;

    @Schema(description = "第91个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第91个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 96)
    @ColumnWidth(10)
    @NotNull
    private Integer noise91;

    @Schema(description = "第92个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第92个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 97)
    @ColumnWidth(10)
    @NotNull
    private Integer noise92;

    @Schema(description = "第93个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第93个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 98)
    @ColumnWidth(10)
    @NotNull
    private Integer noise93;

    @Schema(description = "第94个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第94个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 99)
    @ColumnWidth(10)
    @NotNull
    private Integer noise94;

    @Schema(description = "第95个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第95个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 100)
    @ColumnWidth(10)
    @NotNull
    private Integer noise95;

    @Schema(description = "第96个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第96个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 101)
    @ColumnWidth(10)
    @NotNull
    private Integer noise96;

    @Schema(description = "第97个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第97个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 102)
    @ColumnWidth(10)
    @NotNull
    private Integer noise97;

    @Schema(description = "第98个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第98个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 103)
    @ColumnWidth(10)
    @NotNull
    private Integer noise98;

    @Schema(description = "第99个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    @ExcelProperty(value = "第99个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 104)
    @ColumnWidth(10)
    @NotNull
    private Integer noise99;

}
