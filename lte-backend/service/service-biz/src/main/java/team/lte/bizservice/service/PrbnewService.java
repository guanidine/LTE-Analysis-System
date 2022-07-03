package team.lte.bizservice.service;

import team.lte.bizservice.entity.po.Prbnew;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * PRB干扰数据（小时级） 服务类
 * </p>
 *
 * @author lte
 * @since 2022-06-02
 */
public interface PrbnewService extends IService<Prbnew> {
    void dropAndCreate();
}
