package team.lte.aclservice.service.impl;

import team.lte.aclservice.entity.po.UserRole;
import team.lte.aclservice.mapper.UserRoleMapper;
import team.lte.aclservice.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户职务关系表 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-04-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
