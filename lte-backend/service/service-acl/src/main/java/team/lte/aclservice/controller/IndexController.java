package team.lte.aclservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import team.lte.aclservice.entity.dto.Route;
import team.lte.aclservice.entity.dto.RouteMeta;
import team.lte.aclservice.entity.vo.UserVO;
import team.lte.aclservice.service.IndexService;
import team.lte.commonutils.result.R;

@Tag(name = "用户登录管理")
@RestController
@RequestMapping("/acl/index")
public class IndexController {

    @Resource
    private IndexService indexService;

    @Operation(summary = "根据token获取用户信息")
    @GetMapping("info")
    public R info() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    @Operation(summary = "获取用户职务")
    @GetMapping("menu")
    public R getMenu() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> list = indexService.getMenu(username);
        return R.ok().data("list", list);
    }

    @Operation(summary = "获取用户可访问的路由")
    @GetMapping("asyncRoutes")
    public R getAsyncRoutes() {
        List<JSONObject> list = new ArrayList<>();
        // {
        // path: '/query',
        // name: '业务查询',
        // meta: { title: '业务查询', icon: 'el-icon-s-help', roles: ['管理员'] }
        // },
        // Route route = new Route();
        // route.setPath("/query");
        // route.setName("业务查询");
        // route.setMeta(new RouteMeta().setTitle("业务查询").setRoles(new String[] {"管理员"}));
        // list.add(new JSONObject((Map<String, Object>) route));
        JSONObject o = new JSONObject();
        o.put("path", "/query");
        o.put("name", "业务查询");
        o.put("meta", "{ title: '业务查询', icon: 'el-icon-s-help', roles: ['管理员'] }");
        list.add(o);
        return R.ok().data("list", list);
    }

    @Operation(summary = "登出")
    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }

    @Operation(summary = "注册")
    @PostMapping("register")
    public R register(@Parameter(description = "注册信息") @RequestBody UserVO userVO) {
        indexService.register(userVO);
        return R.ok();
    }

}
