package team.lte.security.entity;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户实体类。
 *
 * @author Guanidine Beryllium
 */
@Data
@Schema(description = "用户实体类")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    private String name;

    @Schema(description = "用户头像")
    private String avatar;

}
