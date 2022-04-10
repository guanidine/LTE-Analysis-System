package team.lte.businessquery.mapper;

import team.lte.businessquery.entity.po.Prb;
import team.lte.businessquery.entity.vo.TimeObject;
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
public interface PrbMapper extends BaseBatchMapper<Prb> {

    List<TimeObject> listData(String enodebName, String begin, String end, String field);

    List<TimeObject> listHourData(String enodebName, String begin, String end, String field);

    List<String> listEnodebs();

}
