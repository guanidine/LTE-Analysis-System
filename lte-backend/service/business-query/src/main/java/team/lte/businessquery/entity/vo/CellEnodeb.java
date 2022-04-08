package team.lte.businessquery.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CellEnodeb {
    @Schema(description = "小区所属基站标识")
    private String enodebid;

    @Schema(description = "基站名称")
    private String enodebName;
}
