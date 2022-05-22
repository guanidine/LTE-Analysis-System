package team.lte.aclservice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import team.lte.aclservice.entity.po.Permission;
import team.lte.aclservice.service.PermissionService;
import team.lte.commonutils.result.R;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
@Tag(name = "权限管理")
@RestController
@RequestMapping("/acl/permission")
@Slf4j
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @Operation(summary = "查询所有权限")
    @GetMapping
    public R listPermissions() {
        List<Permission> list = permissionService.listPermissions();
        return R.ok().data("list", list);
    }

    @Operation(summary = "获取某一职位的权限")
    @GetMapping("{roleId}")
    public R listPermissionsById(
        @Parameter(name = "roleId", description = "职位ID", required = true) @PathVariable Long roleId) {
        List<Permission> list = permissionService.listPermissionsByRoleId(roleId);
        return R.ok().data("list", list);
    }

    @Operation(summary = "给某一职务分配权限")
    @PostMapping("assign/{roleId}")
    public R assignPermissionsById(
        @Parameter(name = "roleId", description = "职位ID", required = true) @PathVariable Long roleId,
        @RequestBody List<Long> permissionIds) {
        if (permissionService.assignPermissionsByRoleId(roleId, permissionIds)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @Operation(summary = "新增权限")
    @PostMapping("save")
    public R savePermission(@RequestBody Permission permission) {
        if (permissionService.save(permission)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @Operation(summary = "修改权限")
    @PutMapping("update")
    public R updatePermission(@RequestBody Permission permission) {
        if (permissionService.updateById(permission)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @Operation(summary = "递归删除权限")
    @DeleteMapping("remove/{id}")
    public R removePermissionsById(
        @Parameter(name = "roleId", description = "职位ID", required = true) @PathVariable Long id) {
        if (permissionService.removePermissionsById(id)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
