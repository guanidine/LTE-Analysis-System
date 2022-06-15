package team.lte.bizservice.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import team.lte.bizservice.entity.po.Prbnew;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * PRB干扰数据（小时级） Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-06-02
 */
public interface PrbnewMapper extends BaseMapper<Prbnew> {
    @Update("select createTbprbnew()")
    void dropAndCreate();
}

