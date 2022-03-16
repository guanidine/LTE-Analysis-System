package team.lte.businessquery.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Cell查询对象", description = "小区配置信息查询条件")
@Data
public class CellQuery implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小区ID，模糊查询")
    private String sectorId;

    @ApiModelProperty(value = "小区名称")
    private String sectorName;

    @ApiModelProperty("小区所属基站标识")
    private Integer enodebid;

    @ApiModelProperty("基站名称")
    private String enodebName;

}
