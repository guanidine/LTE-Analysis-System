package team.lte.bizservice.mapper;

import org.apache.ibatis.annotations.Param;
import team.lte.bizservice.entity.po.C2i3;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-05-31
 */
public interface C2i3Mapper extends BaseMapper<C2i3> {
    void dropAndCreate(@Param(value = "x") double x);
}
