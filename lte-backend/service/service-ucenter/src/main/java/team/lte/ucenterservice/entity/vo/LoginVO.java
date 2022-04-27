package team.lte.ucenterservice.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录VO")
public class LoginVO {

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "密码")
    private String passwd;

}
