package com.rick.manager.module.customer.dao;

import com.rick.common.util.Maps;
import com.rick.db.plugin.dao.core.EntityCodeDAOImpl;
import com.rick.manager.module.customer.entity.Customer;
import com.rick.manager.module.customer.entity.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Rick.Xu
 * @date 2024-09-14 14:27:32
 */
@Repository
@RequiredArgsConstructor
public class CustomerDAO extends EntityCodeDAOImpl<Customer, Long> {

    final RecordDAO recordDAO;

    @Override
    public int insertOrUpdate(Customer customer) {
        int rows = super.insertOrUpdate(customer);
        Record purchasePlan = customer.getPurchasePlan();
        purchasePlan.setInstanceId(customer.getId());

        Record backgroundInvestigation = customer.getBackgroundInvestigation();
        backgroundInvestigation.setInstanceId(customer.getId());

        if (!recordDAO.existsByParams(Maps.of("instanceId", customer.getId(), "category", purchasePlan.getCategory(), "content", purchasePlan.getContent()), "instance_id = :instanceId and category = :category and content = :content and is_deleted = 0")) {
            recordDAO.delete(customer.getId(), purchasePlan.getCategory());
            recordDAO.insert(purchasePlan);
        }

        if (!recordDAO.existsByParams(Maps.of("instanceId", customer.getId(),"category", backgroundInvestigation.getCategory(), "content", backgroundInvestigation.getContent()), "instance_id = :instanceId and category = :category and content = :content and is_deleted = 0")) {
            recordDAO.delete(customer.getId(), backgroundInvestigation.getCategory());
            recordDAO.insert(backgroundInvestigation);
        }

        return rows;
    }

    @Override
    public Optional<Customer> selectById(Long id) {
        Optional<Customer> optional = super.selectById(id);
        if (optional.isPresent()) {
            Record purchasePlan = recordDAO.selectByInstanceId(id, Record.CategoryEnum.PURCHASE_PLAN);
            Record backgroundInvestigation = recordDAO.selectByInstanceId(id, Record.CategoryEnum.BACKGROUND_INVESTIGATION);
            Customer customer = optional.get();
            customer.setPurchasePlan(purchasePlan);
            customer.setBackgroundInvestigation(backgroundInvestigation);
        }
        return optional;
    }
}