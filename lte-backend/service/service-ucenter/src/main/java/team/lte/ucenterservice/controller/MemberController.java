package team.lte.ucenterservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import team.lte.commonutils.jwt.JwtUtils;
import team.lte.commonutils.result.R;
import team.lte.ucenterservice.entity.vo.LoginInfoVO;
import team.lte.ucenterservice.entity.vo.LoginVO;
import team.lte.ucenterservice.entity.vo.RegisterVO;
import team.lte.ucenterservice.service.MemberService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lte
 * @since 2022-04-23
 */
@Tag(name = "登录管理")
@CrossOrigin
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @Operation(description = "登录")
    @PostMapping("login")
    public R login(@Parameter(description = "登录信息") @RequestBody LoginVO loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @Operation(description = "注册")
    @PostMapping("register")
    public R register(@Parameter(description = "注册信息") @RequestBody RegisterVO registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @Operation(description = "注销")
    @PostMapping("logout")
    public R logout() {
        return R.ok();
    }

    @Operation(description = "更新用户信息")
    @PostMapping("update")
    public R update(@Parameter(description = "更新后的用户信息") @RequestBody RegisterVO updateVo) {
        memberService.update(updateVo);
        return R.ok();
    }

    @Operation(description = "根据token获取登录信息")
    @GetMapping("info")
    public R getLoginInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getIdByToken(request);
        LoginInfoVO loginInfoVo = memberService.getLoginInfo(memberId);
        return R.ok().data("item", loginInfoVo);
    }

}
