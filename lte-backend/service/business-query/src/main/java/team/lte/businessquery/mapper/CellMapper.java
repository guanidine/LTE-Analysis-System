package team.lte.businessquery.mapper;

import team.lte.businessquery.entity.Cell;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
public interface CellMapper extends BaseMapper<Cell> {

    List<String> getSectorName();

}
