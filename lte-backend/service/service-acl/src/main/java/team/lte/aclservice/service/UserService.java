package team.lte.aclservice.service;

import com.baomidou.mybatisplus.extension.service.IService;

import team.lte.aclservice.entity.po.User;
import team.lte.aclservice.entity.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
public interface UserService extends IService<User> {

    void update(UserVO userVO);

    User selectByName(String name);

}
