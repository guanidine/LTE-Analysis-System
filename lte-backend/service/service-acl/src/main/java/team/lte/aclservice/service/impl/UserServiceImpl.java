package team.lte.aclservice.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import team.lte.aclservice.entity.po.User;
import team.lte.aclservice.entity.vo.UserVO;
import team.lte.aclservice.mapper.UserMapper;
import team.lte.aclservice.service.UserService;
import team.lte.commonutils.encryption.MD5;
import team.lte.servicebase.exception.LteException;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void update(UserVO userVO) {
        String name = userVO.getName();
        String password = userVO.getPassword();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            throw new LteException(20001, "用户名和密码不能为空");
        }
        User member =
            getBaseMapper().selectOne(new LambdaQueryWrapper<User>().eq(User::getName, userVO.getOriginName()));
        if (member == null) {
            throw new LteException(20001, "该用户不存在");
        }

        member.setName(name);
        member.setPassword(MD5.encode(password));
        this.updateById(member);
    }

    @Override
    public User selectByName(String name) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<User>().eq(User::getName, name));
    }

}
