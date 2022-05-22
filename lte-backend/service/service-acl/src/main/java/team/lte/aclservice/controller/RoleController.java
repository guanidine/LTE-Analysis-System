package team.lte.aclservice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import team.lte.aclservice.entity.po.Role;
import team.lte.aclservice.service.RoleService;
import team.lte.commonutils.result.R;

/**
 * <p>
 * 职务表 前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
@Tag(name = "职务管理")
@RestController
@RequestMapping("/acl/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @Operation(summary = "获取职务分页列表")
    @GetMapping("{page}/{limit}")
    public R listRoles(@Parameter(name = "page", description = "当前页码", required = true) @PathVariable Long page,
        @Parameter(name = "limit", description = "每页记录数", required = true) @PathVariable Long limit) {
        Page<Role> pageParam = new Page<>(page, limit);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        roleService.page(pageParam, wrapper);
        return R.ok().data("list", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @Operation(summary = "获取职务")
    @GetMapping("get/{id}")
    public R getRoleById(@Parameter(name = "id", description = "职位ID", required = true) @PathVariable Long id) {
        Role role = roleService.getById(id);
        return R.ok().data("item", role);
    }

    @Operation(summary = "新增职务")
    @PostMapping("save")
    public R saveRole(@RequestBody Role role) {
        try {
            roleService.save(role);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，添加的时候出错了");
        }
    }

    @Operation(summary = "修改职务")
    @PutMapping("update")
    public R updateRole(@RequestBody Role role) {
        try {
            roleService.updateById(role);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，修改的时候出错了");
        }
    }

    @Operation(summary = "删除职务")
    @DeleteMapping("remove/{id}")
    public R removeRoleById(@Parameter(name = "id", description = "职位ID", required = true) @PathVariable Long id) {
        try {
            roleService.removeById(id);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，删除的时候出错了");
        }
    }

    @Operation(summary = "批量删除职务")
    @DeleteMapping("batch-remove")
    public R removeRoleBatchById(@RequestBody List<Long> idList) {
        try {
            roleService.removeByIds(idList);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，删除的时候出错了");
        }
    }
}
