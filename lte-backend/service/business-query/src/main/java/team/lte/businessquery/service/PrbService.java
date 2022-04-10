package team.lte.businessquery.service;

import team.lte.businessquery.entity.po.Prb;
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
public interface PrbService extends BaseCheckService<Prb> {

    Map<String, Object> listData(String enodebName, String begin, String end, String field) throws NoSuchFieldException;

    Map<String, Object> listHourData(String enodebName, String begin, String end, String field) throws NoSuchFieldException;

    List<String> listEnodebs();

}
