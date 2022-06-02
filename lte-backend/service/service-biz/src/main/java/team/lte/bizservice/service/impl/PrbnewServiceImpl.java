package team.lte.bizservice.service.impl;

import team.lte.bizservice.entity.po.Prbnew;
import team.lte.bizservice.mapper.PrbnewMapper;
import team.lte.bizservice.service.PrbnewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * PRB干扰数据（小时级） 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-06-02
 */
@Service
public class PrbnewServiceImpl extends ServiceImpl<PrbnewMapper, Prbnew> implements PrbnewService {
    public void dropAndCreate() {
        this.baseMapper.dropAndCreate();
    }
}
