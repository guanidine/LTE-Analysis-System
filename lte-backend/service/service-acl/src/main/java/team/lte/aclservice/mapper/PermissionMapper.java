package team.lte.aclservice.mapper;

import team.lte.aclservice.entity.po.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-04-28
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> listPermissionValuesByUserId(Long id);

    List<String> listPermissionValues();

    List<Permission> listPermissionsByUserId(Long userId);

}
