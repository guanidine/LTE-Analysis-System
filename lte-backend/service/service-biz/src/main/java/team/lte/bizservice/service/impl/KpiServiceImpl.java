package team.lte.bizservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import team.lte.bizservice.entity.po.Kpi;
import team.lte.bizservice.entity.vo.TimeObject;
import team.lte.bizservice.mapper.KpiMapper;
import team.lte.bizservice.service.KpiService;
import team.lte.bizservice.util.QueryUtils;
import team.lte.commonutils.encryption.MD5;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-04-07
 */
@Service
public class KpiServiceImpl extends ServiceImpl<KpiMapper, Kpi> implements KpiService {

    @Override
    public Map<String, Object> listData(String sectorName, String begin, String end, String field)
        throws NoSuchFieldException {
        List<TimeObject> data =
            getBaseMapper().listData(sectorName, begin, end, QueryUtils.getFieldName(field, Kpi.class));

        return QueryUtils.extractData(data);
    }

    @Override
    public List<String> listSectors() {

        return getBaseMapper().listSectors();
    }

    @Override
    public String requirementCheck(Kpi object) {
        return null;
    }

    @Override
    public <D> Kpi transfer(D objectDTO, Class<Kpi> kpiClass, Class<D> dClass) {
        Kpi kpi = new Kpi();
        BeanUtils.copyProperties(objectDTO, kpi);
        kpi.setId(MD5.encode(kpi.getStartTime() + kpi.getSectorName()));
        return kpi;
    }
}
