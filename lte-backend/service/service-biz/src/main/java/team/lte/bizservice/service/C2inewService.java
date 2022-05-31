package team.lte.bizservice.service;

import team.lte.bizservice.entity.po.C2inew;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lte
 * @since 2022-05-31
 */
public interface C2inewService extends IService<C2inew> {
    void createTbc2inew(int x);

    List<C2inew> getPageList(long page, long limit);
}
