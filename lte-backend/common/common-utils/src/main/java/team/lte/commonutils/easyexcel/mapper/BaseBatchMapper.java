package team.lte.commonutils.easyexcel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据批量导入（或更新）Mapper基类。需要Excel导入数据的数据表，其对应Mapper接口应继承自{@link BaseBatchMapper}，而非{@link BaseMapper}。
 *
 * @param <T>
 */
public interface BaseBatchMapper<T> extends BaseMapper<T> {

    /**
     * 真·批量插入
     *
     * @param list 待插入数据
     */
    void insertBatch(@Param("list") List<T> list);

    /**
     * 真·批量插入或更新
     *
     * @param list 待插入数据
     */
    void insertOrUpdateBatch(@Param("list") List<T> list);

}
