package com.rick.manager.module.supplier.service;

import com.rick.db.service.BaseServiceImpl;
import com.rick.manager.module.supplier.dao.SupplierDAO;
import com.rick.manager.module.supplier.entity.Supplier;
import org.springframework.stereotype.Service;

/**
 * @author Rick.Xu
 * @date 2024-09-08 19:05:08
 */
@Service
public class SupplierService extends BaseServiceImpl<SupplierDAO, Supplier> {

    public SupplierService(SupplierDAO supplierDAO) {
        super(supplierDAO);
    }
}