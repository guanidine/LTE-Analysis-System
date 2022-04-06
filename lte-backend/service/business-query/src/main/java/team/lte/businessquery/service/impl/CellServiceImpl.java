package team.lte.businessquery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.lte.businessquery.entity.Cell;
import team.lte.businessquery.entity.vo.CellEnodeb;
import team.lte.businessquery.mapper.CellMapper;
import team.lte.businessquery.service.CellService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
@Service
public class CellServiceImpl extends ServiceImpl<CellMapper, Cell> implements CellService {

    @Override
    public List<String> listSectors() {
        return this.getBaseMapper().listSectors();
    }

    @Override
    public List<CellEnodeb> listEnodebs() {
        return this.getBaseMapper().listEnodebs();
    }

    @Override
    public String requirementCheck(Cell object) {
        StringBuilder errMsg = new StringBuilder();
        if (object.getTotletilt() == null && (object.getElecttilt() == null || object.getMechtilt() == null)) {
            errMsg.append("总下倾角不能为空;");
        }
        return errMsg.toString();
    }

}
