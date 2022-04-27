package team.lte.ucenterservice.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import team.lte.commonutils.encryption.MD5;
import team.lte.commonutils.jwt.JwtUtils;
import team.lte.servicebase.exception.LteException;
import team.lte.ucenterservice.entity.po.Member;
import team.lte.ucenterservice.entity.vo.LoginInfoVO;
import team.lte.ucenterservice.entity.vo.LoginVO;
import team.lte.ucenterservice.entity.vo.RegisterVO;
import team.lte.ucenterservice.mapper.MemberMapper;
import team.lte.ucenterservice.service.MemberService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-04-23
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public String login(LoginVO loginVO) {
        String mobile = loginVO.getMobile();
        String passwd = loginVO.getPasswd();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(passwd)) {
            throw new LteException(20001, "手机号和密码不能为空");
        }

        Member member = getBaseMapper().selectOne(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));
        if (member == null) {
            throw new LteException(20001, "用户不存在");
        }
        if (!MD5.encode(passwd).equals(member.getPasswd())) {
            throw new LteException(20001, "手机号或密码错误");
        }
        if (member.getIsDisabled()) {
            throw new LteException(20001, "该用户尚未激活，请咨询管理员");
        }

        return JwtUtils.getJwtToken(String.valueOf(member.getId()), member.getNickname());
    }

    @Override
    public void register(RegisterVO registerVO) {
        String nickname = registerVO.getNickname();
        String mobile = registerVO.getMobile();
        String passwd = registerVO.getPasswd();

        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(passwd)) {
            throw new LteException(20001, "昵称、手机号、密码不能为空");
        }
        Long count = getBaseMapper().selectCount(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));
        if (count.intValue() > 0) {
            throw new LteException(20001, "该用户已注册");
        }

        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPasswd(MD5.encode(passwd));
        member.setIsDisabled(true);
        member.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        this.save(member);
    }

    @Override
    public void update(RegisterVO updateVO) {
        String nickname = updateVO.getNickname();
        String mobile = updateVO.getMobile();
        String passwd = updateVO.getPasswd();

        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(passwd)) {
            throw new LteException(20001, "昵称、手机号、密码不能为空");
        }
        Member member = getBaseMapper().selectOne(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));
        if (member == null) {
            throw new LteException(20001, "该用户不存在");
        }

        member.setNickname(nickname);
        member.setPasswd(MD5.encode(passwd));
        this.updateById(member);
    }

    @Override
    public LoginInfoVO getLoginInfo(String id) {
        Member member = getBaseMapper().selectById(Integer.valueOf(id));
        LoginInfoVO loginInfoVO = new LoginInfoVO();
        BeanUtils.copyProperties(member, loginInfoVO);
        return loginInfoVO;
    }
}
