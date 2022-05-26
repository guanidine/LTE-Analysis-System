package team.lte.aclservice.entity.po;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author lte
 * @since 2022-04-29
 */
@Getter
@Setter
@TableName("acl_role_permission")
@Schema(description = "角色权限关系表")
public class RolePermission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "权限id")
    private Long permissionId;

    @Schema(description = "逻辑删除(1: 已删除, 0: 未删除)")
    @TableLogic
    private Integer isDeleted;

}
