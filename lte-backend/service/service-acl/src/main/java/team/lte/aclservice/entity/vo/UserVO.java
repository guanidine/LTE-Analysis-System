package team.lte.aclservice.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录VO")
public class UserVO {

    @Schema(description = "原用户名")
    private String originName;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "密码")
    private String password;

}
