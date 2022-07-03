package team.lte.bizservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import team.lte.bizservice.entity.po.Prb;
import team.lte.bizservice.entity.vo.TimeObject;
import team.lte.bizservice.mapper.PrbMapper;
import team.lte.bizservice.service.PrbService;
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
public class PrbServiceImpl extends ServiceImpl<PrbMapper, Prb> implements PrbService {

    @Override
    public Map<String, Object> listData(String enodebName, String begin, String end, String field)
        throws NoSuchFieldException {
        List<TimeObject> data =
            getBaseMapper().listData(enodebName, begin, end, QueryUtils.getFieldName(field, Prb.class));

        return QueryUtils.extractData(data);
    }

    @Override
    public Map<String, Object> listHourData(String enodebName, String begin, String end, String field)
        throws NoSuchFieldException {
        List<TimeObject> data =
            getBaseMapper().listHourData(enodebName, begin, end, QueryUtils.getFieldName(field, Prb.class));

        return QueryUtils.extractData(data);
    }

    @Override
    public List<String> listEnodebs() {
        return getBaseMapper().listEnodebs();
    }

    @Override
    public String requirementCheck(Prb object) {
        return null;
    }

    @Override
    public <D> Prb transfer(D objectDTO, Class<Prb> prbClass, Class<D> dClass) {
        Prb prb = new Prb();
        BeanUtils.copyProperties(objectDTO, prb);
        prb.setId(MD5.encode(prb.getStartTime() + prb.getSectorName()));
        return prb;
    }
}
