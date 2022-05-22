package team.lte.bizservice.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "连接池配置更新")
public class DruidUpdate implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "初始化连接大小")
    private int initialSize;

    @Schema(description = "最小空闲连接数")
    private int minIdle;

    @Schema(description = "最大连接数")
    private int maxActive;

}
