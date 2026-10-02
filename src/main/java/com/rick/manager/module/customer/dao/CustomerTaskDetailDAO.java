package com.rick.manager.module.customer.dao;

import com.rick.db.plugin.dao.core.EntityDAOImpl;
import com.rick.db.service.support.Params;
import com.rick.manager.module.customer.entity.CustomerTaskDetail;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Rick.Xu
 * @date 2026/9/30
 */
@Repository
public class CustomerTaskDetailDAO extends EntityDAOImpl<CustomerTaskDetail, Long> {

    public List<CustomerTaskDetail> selectByTaskIds(Collection<Long> taskIds) {
        if (taskIds == null || taskIds.isEmpty()) {
            return Collections.emptyList();
        }
        return selectByParamsWithoutCascade(Params.builder(1).pv("ids", taskIds).build(),
                "task_id IN (:ids) and is_deleted = 0");
    }

    /**
     * 逻辑删除某计划任务下全部明细
     */
    public int deleteLogicallyByTaskId(Long taskId) {
        return update("is_deleted", new Object[]{true, taskId},
                "task_id = ? and is_deleted = 0");
    }
}
