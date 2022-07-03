package team.lte.bizservice.mapper;

import team.lte.bizservice.entity.po.Kpi;
import team.lte.bizservice.entity.vo.TimeObject;
import team.lte.commonutils.easyexcel.mapper.BaseBatchMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-04-07
 */
public interface KpiMapper extends BaseBatchMapper<Kpi> {

    List<TimeObject> listData(String sectorName, String begin, String end, String field);

    List<String> listSectors();

}
