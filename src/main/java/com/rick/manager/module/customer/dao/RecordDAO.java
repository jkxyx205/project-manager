package com.rick.manager.module.customer.dao;

import com.rick.common.util.Maps;
import com.rick.db.plugin.dao.core.EntityDAOImpl;
import com.rick.manager.module.customer.entity.Record;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rick.Xu
 * @date 2026-09-14 14:27:32
 */
@Repository
public class RecordDAO extends EntityDAOImpl<Record, Long> {

    public int delete(Long instanceId, Record.CategoryEnum category) {
        //return delete(Maps.of("instanceId", instanceId, "category", category), "instance_id = :instanceId and category = :category and is_deleted = 0");

        // 逻辑删除
        return update("is_deleted", new Object[]{true, instanceId, category}, "instance_id = ? and category = ? and is_deleted = 0");
    }

    public Record selectByInstanceId(Long instanceId, Record.CategoryEnum category) {
        List<Record> records = selectByParams(Maps.of("instanceId", instanceId, "category", category), "instance_id = :instanceId and category = :category and is_deleted = 0");
        if (CollectionUtils.isEmpty(records)) {
            return Record.builder().build();
        }
        return records.get(0);
    }
}