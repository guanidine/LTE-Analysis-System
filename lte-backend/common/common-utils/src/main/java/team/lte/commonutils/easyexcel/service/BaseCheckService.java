package team.lte.commonutils.easyexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 业务校验Service基类。需要Excel导入数据的数据表，其对应服务类应继承自{@link BaseCheckService}，而非{@link IService}。
 *
 * @param <P> 数据表对应PO类
 */
public interface BaseCheckService<P> extends IService<P> {

    /**
     * 业务校验
     *
     * @param object 待校验对象
     * @return 校验结果，若为空则表示数据正常
     */
    String requirementCheck(P object);

    /**
     * 数据封装。将用于插入的数据对象封装为POJO类。
     *
     * @param objectDTO 原数据
     * @param pClass PO类
     * @param dClass DTO类
     * @param <D> 原数据类型
     * @return 封装后的PO类型数据。
     */
    default <D> P transfer(D objectDTO, Class<P> pClass, Class<D> dClass) {
        if (dClass.equals(pClass)) {
            return (P)objectDTO;
        } else {
            return null;
        }
    }

}
