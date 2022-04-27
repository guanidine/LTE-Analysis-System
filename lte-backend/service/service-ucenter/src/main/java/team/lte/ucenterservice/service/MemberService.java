package team.lte.ucenterservice.service;

import team.lte.ucenterservice.entity.po.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import team.lte.ucenterservice.entity.vo.LoginInfoVO;
import team.lte.ucenterservice.entity.vo.LoginVO;
import team.lte.ucenterservice.entity.vo.RegisterVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lte
 * @since 2022-04-23
 */
public interface MemberService extends IService<Member> {

    String login(LoginVO loginVO);

    void register(RegisterVO registerVO);

    void update(RegisterVO updateVO);

    LoginInfoVO getLoginInfo(String id);

}
