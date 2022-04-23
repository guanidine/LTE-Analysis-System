package team.lte.bizservice.entity.vo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "KPI指标统计数据查询条件")
public class KpiQuery implements Serializable {

    public static final long serialVersionUID = 1L;

    @Schema(description = "小区名称")
    private String sectorName;

    @Schema(description = "起始时间")
    private String begin;

    @Schema(description = "终止时间")
    private String end;

    @Schema(description = "查询属性")
    private String field;

}
