package team.lte.businessquery.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import team.lte.commonutils.result.R;

@Api(tags = "用户登录")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @Operation(summary = "登录")
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @Operation(summary = "登录用户信息")
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar",
            "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
