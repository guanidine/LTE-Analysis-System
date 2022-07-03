package team.lte.bizservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.lte.bizservice.entity.po.C2i3;
import team.lte.bizservice.entity.po.C2inew;
import team.lte.bizservice.mapper.C2i3Mapper;
import team.lte.bizservice.service.C2i3Service;
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
public class C2i3ServiceImpl extends ServiceImpl<C2i3Mapper, C2i3> implements C2i3Service {
    @Override
    public void createTbc2i3(double x) {
        this.baseMapper.dropAndCreate(x);
    }
    @Override
    public List<C2i3> getPageList(long page, long limit) {
        Page<C2i3> c2i3Page = new Page<>(page,limit);
        this.page(c2i3Page);
        return c2i3Page.getRecords();
    }

}
