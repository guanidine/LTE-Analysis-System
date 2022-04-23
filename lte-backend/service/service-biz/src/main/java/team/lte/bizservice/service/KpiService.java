package team.lte.bizservice.service;

import team.lte.bizservice.entity.po.Kpi;
import team.lte.commonutils.easyexcel.service.BaseCheckService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lte
 * @since 2022-04-07
 */
public interface KpiService extends BaseCheckService<Kpi> {

    Map<String, Object> listData(String sectorName, String begin, String end, String field) throws NoSuchFieldException;

    List<String> listSectors();

}
