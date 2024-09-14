package com.rick.manager.module.customer.service;

import com.rick.db.service.BaseServiceImpl;
import com.rick.manager.module.customer.dao.CustomerDAO;
import com.rick.manager.module.customer.entity.Customer;
import org.springframework.stereotype.Service;

/**
 * @author Rick.Xu
 * @date 2024-09-14 14:27:32
 */
@Service
public class CustomerService extends BaseServiceImpl<CustomerDAO, Customer> {

    public CustomerService(CustomerDAO customerDAO) {
        super(customerDAO);
    }
}