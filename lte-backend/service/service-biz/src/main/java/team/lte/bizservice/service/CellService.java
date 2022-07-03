package team.lte.bizservice.service;

import team.lte.bizservice.entity.po.Cell;
import team.lte.bizservice.entity.vo.CellEnodeb;
import team.lte.commonutils.easyexcel.service.BaseCheckService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
public interface CellService extends BaseCheckService<Cell> {

    List<String> listSectors();

    List<CellEnodeb> listEnodebs();

}
