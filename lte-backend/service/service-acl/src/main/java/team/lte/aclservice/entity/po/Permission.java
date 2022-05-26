package team.lte.aclservice.entity.po;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
@Getter
@Setter
@TableName("acl_permission")
@Schema(description = "权限表")
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "权限id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "所属上级")
    private Long pid;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "类型(1: 菜单, 2: 按钮)")
    private Integer type;

    @Schema(description = "权限值")
    private String permissionValue;

    @Schema(description = "访问路径")
    private String path;

    @Schema(description = "组件路径")
    private String component;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "状态(1: 禁止访问, 0: 正常访问)")
    private Integer isDisabled;

    @Schema(description = "层级")
    @TableField(exist = false)
    private Integer level;

    @Schema(description = "下级")
    @TableField(exist = false)
    private List<Permission> children;

    @Schema(description = "是否选中")
    @TableField(exist = false)
    private boolean isSelect;

    @Schema(description = "逻辑删除(1: 已删除, 0: 未删除)")
    @TableLogic
    private Integer isDeleted;

}
