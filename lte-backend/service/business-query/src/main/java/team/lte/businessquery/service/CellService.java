package team.lte.businessquery.service;

import team.lte.businessquery.entity.Cell;
import team.lte.businessquery.entity.vo.CellEnodeb;
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

    List<String> getSectors();

    List<CellEnodeb> getEnodebs();

}
