package team.lte.businessquery.service.impl;

import team.lte.businessquery.entity.Cell;
import team.lte.businessquery.mapper.CellMapper;
import team.lte.businessquery.service.CellService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
@Service
public class CellServiceImpl extends ServiceImpl<CellMapper, Cell> implements CellService {

    @Override
    public List<String> getSectorName() {
        return baseMapper.getSectorName();
    }
}
