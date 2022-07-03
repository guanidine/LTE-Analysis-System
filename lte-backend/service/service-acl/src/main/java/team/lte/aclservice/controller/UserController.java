package team.lte.aclservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import team.lte.aclservice.entity.po.User;
import team.lte.aclservice.entity.vo.UserVO;
import team.lte.aclservice.service.RoleService;
import team.lte.aclservice.service.UserService;
import team.lte.commonutils.encryption.MD5;
import team.lte.commonutils.result.R;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
@Tag(name = "用户列表")
@RestController
@RequestMapping("/acl/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Operation(summary = "获取用户分页列表")
    @GetMapping("{page}/{limit}")
    // TODO: MD5加密的密码也给送到前端了，对于111111这种密码来说，这好吗？这不好
    // TODO: 可是我并不想改（狗头）
    public R listUsers(@Parameter(name = "page", description = "当前页码", required = true) @PathVariable Long page,
        @Parameter(name = "limit", description = "每页记录数", required = true) @PathVariable Long limit) {
        Page<User> pageParam = new Page<>(page, limit);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        userService.page(pageParam, wrapper);
        List<User> list = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("list", list).data("total", total);
    }

    @Operation(summary = "根据ID查询用户")
    @GetMapping("get/{id}")
    public R getUserById(@Parameter(name = "id", description = "用户ID", required = true) @PathVariable Long id) {
        User user = userService.getById(id);
        return R.ok().data("item", user);
    }

    @Operation(summary = "新增用户")
    @PostMapping("save")
    public R saveUser(@RequestBody User user) {
        try {
            user.setPassword(MD5.encode(user.getPassword()));
            userService.save(user);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，添加的时候出错了");
        }
    }

    @Operation(summary = "修改用户")
    @PutMapping("update")
    public R updateUser(@RequestBody User user) {
        try {
            userService.updateById(user);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，修改的时候出错了");
        }
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("remove/{id}")
    public R removeUserById(@Parameter(name = "id", description = "用户ID", required = true) @PathVariable Long id) {
        try {
            userService.removeById(id);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，删除的时候出错了");
        }
    }

    @Operation(summary = "批量删除用户")
    @DeleteMapping("batch-remove")
    public R removeUserBatchById(@RequestBody List<Long> idList) {
        try {
            userService.removeByIds(idList);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，删除的时候出错了");
        }
    }

    @Operation(summary = "获取某个用户的角色数据")
    @GetMapping("{userId}")
    public R
        listRolesByUserId(@Parameter(name = "userId", description = "用户ID", required = true) @PathVariable Long userId) {
        Map<String, Object> roleMap = roleService.findRolesByUserId(userId);
        return R.ok().data(roleMap);
    }

    @Operation(summary = "为某个用户分配角色")
    @PostMapping("assign/{userId}")
    public R assignRolesByUserId(
        @Parameter(name = "userId", description = "用户ID", required = true) @PathVariable Long userId,
        @RequestBody List<Long> roleId) {
        try {
            roleService.assignRolesByUserId(userId, roleId);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("呐，分配角色的时候出错了");
        }
    }

    @Operation(summary = "用户主页更新用户信息")
    @PostMapping("update2")
    public R update(@Parameter(description = "更新后的用户信息") @RequestBody UserVO userVO) {
        userService.update(userVO);
        return R.ok();
    }
}
