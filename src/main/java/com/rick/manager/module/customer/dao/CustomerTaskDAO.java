package com.rick.manager.module.customer.dao;

import com.rick.db.plugin.dao.core.EntityDAOImpl;
import com.rick.db.service.support.Params;
import com.rick.manager.module.customer.entity.CustomerTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rick.Xu
 * @date 2026/9/30
 */
@Repository
public class CustomerTaskDAO extends EntityDAOImpl<CustomerTask, Long> {

    public List<CustomerTask> selectByInstanceId(Long instanceId) {
        return selectByParamsWithoutCascade(Params.builder(1).pv("instanceId", instanceId).build(),
                "instance_id = :instanceId and is_deleted = 0");
    }

    /**
     * 逻辑删除某客户下全部计划任务
     */
    public int deleteLogicallyByInstanceId(Long instanceId) {
        return update("is_deleted", new Object[]{true, instanceId},
                "instance_id = ? and is_deleted = 0");
    }
}
