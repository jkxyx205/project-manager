package com.rick.manager.module.customer.dao;

import com.rick.db.plugin.dao.core.EntityCodeDAOImpl;
import com.rick.manager.module.customer.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author Rick.Xu
 * @date 2024-09-14 14:27:32
 */
@Repository
public class CustomerDAO extends EntityCodeDAOImpl<Customer, Long> {

}