package team.lte.commonutils.easyexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 业务校验Service基类。需要Excel导入数据的数据表，其对应服务类应继承自{@link BaseCheckService}，而非{@link IService}。
 *
 * @param <T> 数据表对应POJO类
 */
public interface BaseCheckService<T> extends IService<T> {

    /**
     * 业务校验
     *
     * @param object 待校验对象
     * @return 校验结果，若为空则表示数据正常
     */
    String requirementCheck(T object);

}
