package team.lte.ucenterservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lte
 * @since 2022-04-23
 */
@Getter
@Setter
@TableName("ucenter_member")
@Schema(description = "用户表")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "密码")
    private String passwd;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Boolean isDisabled;

    @Schema(description = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @Schema(description = "更新时间")
    private LocalDateTime gmtModified;

}
