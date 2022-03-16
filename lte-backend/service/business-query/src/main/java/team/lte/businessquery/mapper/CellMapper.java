package team.lte.businessquery.mapper;

import team.lte.businessquery.entity.Cell;
import team.lte.businessquery.entity.vo.CellEnodeb;
import team.lte.commonutils.easyexcel.mapper.BaseBatchMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
public interface CellMapper extends BaseBatchMapper<Cell> {

    List<String> getSectors();

    List<CellEnodeb> getEnodebs();

}
