package team.lte.bizservice.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "PRB干扰数据查询条件")
public class PrbQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "网元/基站名称")
    private String enodebName;

    @Schema(description = "起始时间")
    private String begin;

    @Schema(description = "终止时间")
    private String end;

    @Schema(description = "查询属性")
    private String field;

}
