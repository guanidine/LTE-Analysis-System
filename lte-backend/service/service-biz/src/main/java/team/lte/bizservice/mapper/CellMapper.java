package team.lte.bizservice.mapper;

import team.lte.bizservice.entity.po.Cell;
import team.lte.bizservice.entity.vo.CellEnodeb;
import team.lte.commonutils.easyexcel.mapper.BaseBatchMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
public interface CellMapper extends BaseBatchMapper<Cell> {

    List<String> listSectors();

    List<CellEnodeb> listEnodebs();

}
