package team.lte.businessquery.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CellEnodeb {
    @ApiModelProperty("小区所属基站标识")
    private String enodebid;

    @ApiModelProperty("基站名称")
    private String enodebName;
}
