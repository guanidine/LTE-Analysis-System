package team.lte.bizservice.mapper;

import org.apache.ibatis.annotations.Param;
import team.lte.bizservice.entity.po.C2inew;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-05-31
 */
public interface C2inewMapper extends BaseMapper<C2inew> {

    void dropAndCreate(@Param(value = "x") int x);

    void updateBatch(@Param(value= "list") List<C2inew> list);
}
