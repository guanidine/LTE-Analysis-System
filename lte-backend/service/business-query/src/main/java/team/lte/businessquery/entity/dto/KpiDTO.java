package team.lte.businessquery.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KpiDTO {

    // TODO: 处理NIL数据

    @ExcelProperty(value = "起始时间", index = 0)
    @ColumnWidth(20)
    @NotNull
    private String startTime;

    @ExcelProperty(value = "网元/基站名称", index = 1)
    @ColumnWidth(20)
    @NotNull
    private String enodebName;

    @ExcelProperty(value = "小区", index = 2)
    @ColumnWidth(120)
    @NotNull
    private String sectorDescription;

    @ExcelProperty(value = "小区名称", index = 3)
    @ColumnWidth(20)
    @NotNull
    private String sectorName;

    @ExcelProperty(value = "RRC连接建立完成次数 (无)", index = 4)
    @ColumnWidth(10)
    @NotNull
    private Integer rrcSuccess;

    @ExcelProperty(value = "RRC连接请求次数（包括重发） (无)", index = 5)
    @ColumnWidth(10)
    @NotNull
    private Integer rrcTries;

    @ExcelProperty(value = "RRC建立成功率qf (%)", index = 6)
    @ColumnWidth(10)
    @NotNull
    private Float rrcSuccessRate;

    @ExcelProperty(value = "E-RAB建立成功总次数 (无)", index = 7)
    @ColumnWidth(10)
    @NotNull
    private Integer erabSuccess;

    @ExcelProperty(value = "E-RAB建立尝试总次数 (无)", index = 8)
    @ColumnWidth(10)
    @NotNull
    private Integer erabTries;

    @ExcelProperty(value = "E-RAB建立成功率2 (%)", index = 9)
    @ColumnWidth(10)
    @NotNull
    private Float erabSuccessRate;

    @ExcelProperty(value = "eNodeB触发的E-RAB异常释放总次数 (无)", index = 10)
    @ColumnWidth(10)
    @NotNull
    private Integer enodebErabRelease;

    @ExcelProperty(value = "小区切换出E-RAB异常释放总次数 (无)", index = 11)
    @ColumnWidth(10)
    @NotNull
    private Integer sectorErabRelease;

    @ExcelProperty(value = "E-RAB掉线率 (%)", index = 12)
    @ColumnWidth(10)
    @NotNull
    private Float erabDropRate;

    @ExcelProperty(value = "无线接通率ay (%)", index = 13)
    @ColumnWidth(10)
    @NotNull
    private Float wirelessConnectionRate;

    @ExcelProperty(value = "eNodeB发起的S1 RESET导致的UE Context释放次数 (无)", index = 14)
    @ColumnWidth(10)
    @NotNull
    private Integer enodebUeRelease;

    @ExcelProperty(value = "UE Context异常释放次数 (无)", index = 15)
    @ColumnWidth(10)
    @NotNull
    private Integer ueRelease;

    @ExcelProperty(value = "UE Context建立成功总次数 (无)", index = 16)
    @ColumnWidth(10)
    @NotNull
    private Integer ueSuccess;

    @ExcelProperty(value = "无线掉线率 (%)", index = 17)
    @ColumnWidth(10)
    @NotNull
    private Float wirelessDropRate;

    @ExcelProperty(value = "eNodeB内异频切换出成功次数 (无)", index = 18)
    @ColumnWidth(10)
    @NotNull
    private Integer intraInterSuccess;

    @ExcelProperty(value = "eNodeB内异频切换出尝试次数 (无)", index = 19)
    @ColumnWidth(10)
    @NotNull
    private Integer intraInterTries;

    @ExcelProperty(value = "eNodeB内同频切换出成功次数 (无)", index = 20)
    @ColumnWidth(10)
    @NotNull
    private Integer intraIntraSuccess;

    @ExcelProperty(value = "eNodeB内同频切换出尝试次数 (无)", index = 21)
    @ColumnWidth(10)
    @NotNull
    private Integer intraIntraTries;

    @ExcelProperty(value = "eNodeB间异频切换出成功次数 (无)", index = 22)
    @ColumnWidth(10)
    @NotNull
    private Integer interInterSuccess;

    @ExcelProperty(value = "eNodeB间异频切换出尝试次数 (无)", index = 23)
    @ColumnWidth(10)
    @NotNull
    private Integer interInterTries;

    @ExcelProperty(value = "eNodeB间同频切换出成功次数 (无)", index = 24)
    @ColumnWidth(10)
    @NotNull
    private Integer interIntraSuccess;

    @ExcelProperty(value = "eNodeB间同频切换出尝试次数 (无)", index = 25)
    @ColumnWidth(10)
    @NotNull
    private Integer interIntraTries;

    @ExcelProperty(value = "eNB内切换成功率 (%)", index = 26)
    @ColumnWidth(10)
    @NotNull
    private Float intraEnodebSuccessRate;

    @ExcelProperty(value = "eNB间切换成功率 (%)", index = 27)
    @ColumnWidth(10)
    @NotNull
    private Float interEnodebSuccessRate;

    @ExcelProperty(value = "同频切换成功率zsp (%)", index = 28)
    @ColumnWidth(10)
    @NotNull
    private Float intraFrequencySuccessRate;

    @ExcelProperty(value = "异频切换成功率zsp (%)", index = 29)
    @ColumnWidth(10)
    @NotNull
    private Float interFrequencySuccessRate;

    @ExcelProperty(value = "切换成功率 (%)", index = 30)
    @ColumnWidth(10)
    @NotNull
    private Float handoverSuccessRate;

    @ExcelProperty(value = "小区PDCP层所接收到的上行数据的总吞吐量 (比特)", index = 31)
    @ColumnWidth(12)
    @NotNull
    private Long upstreamCapacity;

    @ExcelProperty(value = "小区PDCP层所发送的下行数据的总吞吐量 (比特)", index = 32)
    @ColumnWidth(12)
    @NotNull
    private Long downstreamCapacity;

    @ExcelProperty(value = "RRC重建请求次数 (无)", index = 33)
    @ColumnWidth(10)
    @NotNull
    private Integer rrcRetries;

    @ExcelProperty(value = "RRC连接重建比率 (%)", index = 34)
    @ColumnWidth(10)
    @NotNull
    private Float rrcRetriesRate;

    @ExcelProperty(value = "通过重建回源小区的eNodeB间同频切换出执行成功次数 (无)", index = 35)
    @ColumnWidth(10)
    @NotNull
    private Integer backInterIntraSuccess;

    @ExcelProperty(value = "通过重建回源小区的eNodeB间异频切换出执行成功次数 (无)", index = 36)
    @ColumnWidth(10)
    @NotNull
    private Integer backInterInterSuccess;

    @ExcelProperty(value = "通过重建回源小区的eNodeB内同频切换出执行成功次数 (无)", index = 37)
    @ColumnWidth(10)
    @NotNull
    private Integer backIntraIntraSuccess;

    @ExcelProperty(value = "通过重建回源小区的eNodeB内异频切换出执行成功次数 (无)", index = 38)
    @ColumnWidth(10)
    @NotNull
    private Integer backIntraInterSuccess;

    @ExcelProperty(value = "eNB内切换出成功次数 (次)", index = 39)
    @ColumnWidth(10)
    @NotNull
    private Integer intraEnodebSuccess;

    @ExcelProperty(value = "eNB内切换出请求次数 (次)", index = 40)
    @ColumnWidth(10)
    @NotNull
    private Integer interEnodebSuccess;
}
