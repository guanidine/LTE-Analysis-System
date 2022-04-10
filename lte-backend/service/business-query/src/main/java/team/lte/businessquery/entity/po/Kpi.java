package team.lte.businessquery.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("tbkpi")
@Schema(description = "KPI指标统计数据")
public class Kpi implements Serializable {

    private static final long serialVersionUID = 1L;

    // TODO: 处理NIL数据
    // TODO: 输入数据另建DTO类，排除掉id

    @Schema(description = "ID")
    @ExcelProperty(value = "ID", index = 0)
    @TableId(type = IdType.INPUT)
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

    @Schema(description = "小区")
    @ExcelProperty(value = "小区", index = 3)
    @ColumnWidth(120)
    @NotNull
    private String sectorDescription;

    @Schema(description = "小区名称")
    @ExcelProperty(value = "小区名称", index = 4)
    @ColumnWidth(20)
    @NotNull
    private String sectorName;

    @Schema(description = "RRC连接建立完成次数 (无)")
    @ExcelProperty(value = "RRC连接建立完成次数 (无)", index = 5)
    @ColumnWidth(10)
    @NotNull
    private Integer rrcSuccess;

    @Schema(description = "RRC连接请求次数（包括重发） (无)")
    @ExcelProperty(value = "RRC连接请求次数（包括重发） (无)", index = 6)
    @ColumnWidth(10)
    @NotNull
    private Integer rrcTries;

    @Schema(description = "RRC建立成功率qf (%)")
    @ExcelProperty(value = "RRC建立成功率qf (%)", index = 7)
    @ColumnWidth(10)
    @NotNull
    private Float rrcSuccessRate;

    @Schema(description = "E-RAB建立成功总次数 (无)")
    @ExcelProperty(value = "E-RAB建立成功总次数 (无)", index = 8)
    @ColumnWidth(10)
    @NotNull
    private Integer erabSuccess;

    @Schema(description = "E-RAB建立尝试总次数 (无)")
    @ExcelProperty(value = "E-RAB建立尝试总次数 (无)", index = 9)
    @ColumnWidth(10)
    @NotNull
    private Integer erabTries;

    @Schema(description = "E-RAB建立成功率2 (%)")
    @ExcelProperty(value = "E-RAB建立成功率2 (%)", index = 10)
    @ColumnWidth(10)
    @NotNull
    private Float erabSuccessRate;

    @Schema(description = "eNodeB触发的E-RAB异常释放总次数 (无)")
    @ExcelProperty(value = "eNodeB触发的E-RAB异常释放总次数 (无)", index = 11)
    @ColumnWidth(10)
    @NotNull
    private Integer enodebErabRelease;

    @Schema(description = "小区切换出E-RAB异常释放总次数 (无)")
    @ExcelProperty(value = "小区切换出E-RAB异常释放总次数 (无)", index = 12)
    @ColumnWidth(10)
    @NotNull
    private Integer sectorErabRelease;

    @Schema(description = "E-RAB掉线率(新) (%)")
    @ExcelProperty(value = "E-RAB掉线率 (%)", index = 13)
    @ColumnWidth(10)
    @NotNull
    private Float erabDropRate;

    @Schema(description = "无线接通率ay (%)")
    @ExcelProperty(value = "无线接通率ay (%)", index = 14)
    @ColumnWidth(10)
    @NotNull
    private Float wirelessConnectionRate;

    @Schema(description = "eNodeB发起的S1 RESET导致的UE Context释放次数 (无)")
    @ExcelProperty(value = "eNodeB发起的S1 RESET导致的UE Context释放次数 (无)", index = 15)
    @ColumnWidth(10)
    @NotNull
    private Integer enodebUeRelease;

    @Schema(description = "UE Context异常释放次数 (无)")
    @ExcelProperty(value = "UE Context异常释放次数 (无)", index = 16)
    @ColumnWidth(10)
    @NotNull
    private Integer ueRelease;

    @Schema(description = "UE Context建立成功总次数 (无)")
    @ExcelProperty(value = "UE Context建立成功总次数 (无)", index = 17)
    @ColumnWidth(10)
    @NotNull
    private Integer ueSuccess;

    @Schema(description = "无线掉线率 (%)")
    @ExcelProperty(value = "无线掉线率 (%)", index = 18)
    @ColumnWidth(10)
    @NotNull
    private Float wirelessDropRate;

    @Schema(description = "eNodeB内异频切换出成功次数 (无)")
    @ExcelProperty(value = "eNodeB内异频切换出成功次数 (无)", index = 19)
    @ColumnWidth(10)
    @NotNull
    private Integer intraInterSuccess;

    @Schema(description = "eNodeB内异频切换出尝试次数 (无)")
    @ExcelProperty(value = "eNodeB内异频切换出尝试次数 (无)", index = 20)
    @ColumnWidth(10)
    @NotNull
    private Integer intraInterTries;

    @Schema(description = "eNodeB内同频切换出成功次数 (无)")
    @ExcelProperty(value = "eNodeB内同频切换出成功次数 (无)", index = 21)
    @ColumnWidth(10)
    @NotNull
    private Integer intraIntraSuccess;

    @Schema(description = "eNodeB内同频切换出尝试次数 (无)")
    @ExcelProperty(value = "eNodeB内同频切换出尝试次数 (无)", index = 22)
    @ColumnWidth(10)
    @NotNull
    private Integer intraIntraTries;

    @Schema(description = "eNodeB间异频切换出成功次数 (无)")
    @ExcelProperty(value = "eNodeB间异频切换出成功次数 (无)", index = 23)
    @ColumnWidth(10)
    @NotNull
    private Integer interInterSuccess;

    @Schema(description = "eNodeB间异频切换出尝试次数 (无)")
    @ExcelProperty(value = "eNodeB间异频切换出尝试次数 (无)", index = 24)
    @ColumnWidth(10)
    @NotNull
    private Integer interInterTries;

    @Schema(description = "eNodeB间同频切换出成功次数 (无)")
    @ExcelProperty(value = "eNodeB间同频切换出成功次数 (无)", index = 25)
    @ColumnWidth(10)
    @NotNull
    private Integer interIntraSuccess;

    @Schema(description = "eNodeB间同频切换出尝试次数 (无)")
    @ExcelProperty(value = "eNodeB间同频切换出尝试次数 (无)", index = 26)
    @ColumnWidth(10)
    @NotNull
    private Integer interIntraTries;

    @Schema(description = "eNB内切换成功率 (%)")
    @ExcelProperty(value = "eNB内切换成功率 (%)", index = 27)
    @ColumnWidth(10)
    @NotNull
    private Float intraEnodebSuccessRate;

    @Schema(description = "eNB间切换成功率 (%)")
    @ExcelProperty(value = "eNB间切换成功率 (%)", index = 28)
    @ColumnWidth(10)
    @NotNull
    private Float interEnodebSuccessRate;

    @Schema(description = "同频切换成功率zsp (%)")
    @ExcelProperty(value = "同频切换成功率zsp (%)", index = 29)
    @ColumnWidth(10)
    @NotNull
    private Float intraFrequencySuccessRate;

    @Schema(description = "异频切换成功率zsp (%)")
    @ExcelProperty(value = "异频切换成功率zsp (%)", index = 30)
    @ColumnWidth(10)
    @NotNull
    private Float interFrequencySuccessRate;

    @Schema(description = "切换成功率 (%)")
    @ExcelProperty(value = "切换成功率 (%)", index = 31)
    @ColumnWidth(10)
    @NotNull
    private Float handoverSuccessRate;

    @Schema(description = "小区PDCP层所接收到的上行数据的总吞吐量 (比特)")
    @ExcelProperty(value = "小区PDCP层所接收到的上行数据的总吞吐量 (比特)", index = 32)
    @ColumnWidth(12)
    @NotNull
    private Long upstreamCapacity;

    @Schema(description = "小区PDCP层所发送的下行数据的总吞吐量 (比特)")
    @ExcelProperty(value = "小区PDCP层所发送的下行数据的总吞吐量 (比特)", index = 33)
    @ColumnWidth(12)
    @NotNull
    private Long downstreamCapacity;

    @Schema(description = "RRC重建请求次数 (无)")
    @ExcelProperty(value = "RRC重建请求次数 (无)", index = 34)
    @ColumnWidth(10)
    @NotNull
    private Integer rrcRetries;

    @Schema(description = "RRC连接重建比率 (%)")
    @ExcelProperty(value = "RRC连接重建比率 (%)", index = 35)
    @ColumnWidth(10)
    @NotNull
    private Float rrcRetriesRate;

    @Schema(description = "通过重建回源小区的eNodeB间同频切换出执行成功次数 (无)")
    @ExcelProperty(value = "通过重建回源小区的eNodeB间同频切换出执行成功次数 (无)", index = 36)
    @ColumnWidth(10)
    @NotNull
    private Integer backInterIntraSuccess;

    @Schema(description = "通过重建回源小区的eNodeB间异频切换出执行成功次数 (无)")
    @ExcelProperty(value = "通过重建回源小区的eNodeB间异频切换出执行成功次数 (无)", index = 37)
    @ColumnWidth(10)
    @NotNull
    private Integer backInterInterSuccess;

    @Schema(description = "通过重建回源小区的eNodeB内同频切换出执行成功次数 (无)")
    @ExcelProperty(value = "通过重建回源小区的eNodeB内同频切换出执行成功次数 (无)", index = 38)
    @ColumnWidth(10)
    @NotNull
    private Integer backIntraIntraSuccess;

    @Schema(description = "通过重建回源小区的eNodeB内异频切换出执行成功次数 (无)")
    @ExcelProperty(value = "通过重建回源小区的eNodeB内异频切换出执行成功次数 (无)", index = 39)
    @ColumnWidth(10)
    @NotNull
    private Integer backIntraInterSuccess;

    @Schema(description = "eNB内切换出成功次数 (次)")
    @ExcelProperty(value = "eNB内切换出成功次数 (次)", index = 40)
    @ColumnWidth(10)
    @NotNull
    private Integer intraEnodebSuccess;

    @Schema(description = "eNB内切换出请求次数 (次)")
    @ExcelProperty(value = "eNB内切换出请求次数 (次)", index = 41)
    @ColumnWidth(10)
    @NotNull
    private Integer interEnodebSuccess;
}
