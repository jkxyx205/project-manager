package com.rick.manager.module.product.dao;

import com.rick.db.plugin.dao.core.EntityCodeDAOImpl;
import com.rick.manager.module.product.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * @author Rick.Xu
 * @date 2024-09-08 20:42:04
 */
@Repository
public class ProductDAO extends EntityCodeDAOImpl<Product, Long> {

}