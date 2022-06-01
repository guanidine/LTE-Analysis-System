package team.lte.bizservice.service;

import team.lte.bizservice.entity.po.C2i3;
import com.baomidou.mybatisplus.extension.service.IService;
import team.lte.bizservice.entity.po.C2inew;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lte
 * @since 2022-05-31
 */
public interface C2i3Service extends IService<C2i3> {
    void createTbc2i3(double x);
    List<C2i3> getPageList(long page, long limit);
}
