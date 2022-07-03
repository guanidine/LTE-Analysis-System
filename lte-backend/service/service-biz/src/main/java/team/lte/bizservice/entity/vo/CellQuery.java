package team.lte.bizservice.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "小区配置信息查询条件")
public class CellQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "小区ID")
    private String sectorId;

    @Schema(description = "小区名称")
    private String sectorName;

    @Schema(description = "小区所属基站标识")
    private Integer enodebid;

    @Schema(description = "基站名称")
    private String enodebName;

}
