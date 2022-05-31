package team.lte.bizservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.math3.distribution.NormalDistribution;
import team.lte.bizservice.entity.po.C2inew;
import team.lte.bizservice.mapper.C2inewMapper;
import team.lte.bizservice.service.C2inewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-05-31
 */
@Service
public class C2inewServiceImpl extends ServiceImpl<C2inewMapper, C2inew> implements C2inewService {


    @Override
    public void createTbc2inew(int x) {
        this.baseMapper.dropAndCreate(x);
        List<C2inew> c2inewList = this.list();

        for (C2inew obj: c2inewList) {
            Double c2i_mean = obj.getC2iMean();
            Double std = obj.getStd();
            if (c2i_mean == null || std == null) {
                continue;
            }
            if (std == 0) {
                obj.setPrbC2i9(Double.NaN);
                obj.setPrbAbs6(Double.NaN);
            } else {
                NormalDistribution d = new NormalDistribution(c2i_mean, std);
                obj.setPrbC2i9(d.cumulativeProbability(9));
                obj.setPrbAbs6(d.cumulativeProbability(-6,6));
            }
        }

        this.baseMapper.updateBatch(c2inewList);
    }

    @Override
    public List<C2inew> getPageList(long page, long limit) {
        Page<C2inew> c2inewPage = new Page<>(page,limit);
        this.page(c2inewPage);
        return c2inewPage.getRecords();
    }
}
