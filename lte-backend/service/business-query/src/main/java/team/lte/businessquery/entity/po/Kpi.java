package team.lte.businessquery.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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

    @Schema(description = "ID")
    private String id;

    @Schema(description = "起始时间")
    private String startTime;

    @Schema(description = "网元/基站名称")
    private String enodebName;

    @Schema(description = "小区")
    private String sectorDescription;

    @Schema(description = "小区名称")
    private String sectorName;

    @Schema(description = "RRC连接建立完成次数 (无)")
    private Integer rrcSuccess;

    @Schema(description = "RRC连接请求次数（包括重发） (无)")
    private Integer rrcTries;

    @Schema(description = "RRC建立成功率qf (%)")
    private Float rrcSuccessRate;

    @Schema(description = "E-RAB建立成功总次数 (无)")
    private Integer erabSuccess;

    @Schema(description = "E-RAB建立尝试总次数 (无)")
    private Integer erabTries;

    @Schema(description = "E-RAB建立成功率2 (%)")
    private Float erabSuccessRate;

    @Schema(description = "eNodeB触发的E-RAB异常释放总次数 (无)")
    private Integer enodebErabRelease;

    @Schema(description = "小区切换出E-RAB异常释放总次数 (无)")
    private Integer sectorErabRelease;

    @Schema(description = "E-RAB掉线率(新) (%)")
    private Float erabDropRate;

    @Schema(description = "无线接通率ay (%)")
    private Float wirelessConnectionRate;

    @Schema(description = "eNodeB发起的S1 RESET导致的UE Context释放次数 (无)")
    private Integer enodebUeRelease;

    @Schema(description = "UE Context异常释放次数 (无)")
    private Integer ueRelease;

    @Schema(description = "UE Context建立成功总次数 (无)")
    private Integer ueSuccess;

    @Schema(description = "无线掉线率 (%)")
    private Float wirelessDropRate;

    @Schema(description = "eNodeB内异频切换出成功次数 (无)")
    private Integer intraInterSuccess;

    @Schema(description = "eNodeB内异频切换出尝试次数 (无)")
    private Integer intraInterTries;

    @Schema(description = "eNodeB内同频切换出成功次数 (无)")
    private Integer intraIntraSuccess;

    @Schema(description = "eNodeB内同频切换出尝试次数 (无)")
    private Integer intraIntraTries;

    @Schema(description = "eNodeB间异频切换出成功次数 (无)")
    private Integer interInterSuccess;

    @Schema(description = "eNodeB间异频切换出尝试次数 (无)")
    private Integer interInterTries;

    @Schema(description = "eNodeB间同频切换出成功次数 (无)")
    private Integer interIntraSuccess;

    @Schema(description = "eNodeB间同频切换出尝试次数 (无)")
    private Integer interIntraTries;

    @Schema(description = "eNB内切换成功率 (%)")
    private Float intraEnodebSuccessRate;

    @Schema(description = "eNB间切换成功率 (%)")
    private Float interEnodebSuccessRate;

    @Schema(description = "同频切换成功率zsp (%)")
    private Float intraFrequencySuccessRate;

    @Schema(description = "异频切换成功率zsp (%)")
    private Float interFrequencySuccessRate;

    @Schema(description = "切换成功率 (%)")
    private Float handoverSuccessRate;

    @Schema(description = "小区PDCP层所接收到的上行数据的总吞吐量 (比特)")
    private Long upstreamCapacity;

    @Schema(description = "小区PDCP层所发送的下行数据的总吞吐量 (比特)")
    private Long downstreamCapacity;

    @Schema(description = "RRC重建请求次数 (无)")
    private Integer rrcRetries;

    @Schema(description = "RRC连接重建比率 (%)")
    private Float rrcRetriesRate;

    @Schema(description = "通过重建回源小区的eNodeB间同频切换出执行成功次数 (无)")
    private Integer backInterIntraSuccess;

    @Schema(description = "通过重建回源小区的eNodeB间异频切换出执行成功次数 (无)")
    private Integer backInterInterSuccess;

    @Schema(description = "通过重建回源小区的eNodeB内同频切换出执行成功次数 (无)")
    private Integer backIntraIntraSuccess;

    @Schema(description = "通过重建回源小区的eNodeB内异频切换出执行成功次数 (无)")
    private Integer backIntraInterSuccess;

    @Schema(description = "eNB内切换出成功次数 (次)")
    private Integer intraEnodebSuccess;

    @Schema(description = "eNB内切换出请求次数 (次)")
    private Integer interEnodebSuccess;
}
