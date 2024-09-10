package com.rick.manager.module.product.service;

import com.rick.db.service.BaseServiceImpl;
import com.rick.manager.module.product.dao.ProductDAO;
import com.rick.manager.module.product.entity.Product;
import org.springframework.stereotype.Service;

/**
 * @author Rick.Xu
 * @date 2024-09-08 20:42:04
 */
@Service
public class ProductService extends BaseServiceImpl<ProductDAO, Product> {

    public ProductService(ProductDAO productDAO) {
        super(productDAO);
    }
}