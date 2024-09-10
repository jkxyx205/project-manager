package com.rick.manager.module.supplier.controller;

import com.rick.db.plugin.dao.core.EntityDAOManager;
import com.rick.manager.common.api.BaseFormController;
import com.rick.manager.common.exception.ResourceNotFoundException;
import com.rick.manager.module.supplier.entity.Supplier;
import com.rick.manager.module.supplier.service.SupplierService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author Rick.Xu
 * @date 2024-09-08 19:05:08
 */
@Controller
@RequestMapping("suppliers")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SupplierController extends BaseFormController<Supplier, SupplierService> {

    public SupplierController(SupplierService supplierService) {
        super(supplierService, "demos/student/edit-thymeleaf");
    }

    @GetMapping("{id}")
    @ResponseBody
    public Supplier findById(@PathVariable Long id) {
        Optional<Supplier> byId = baseService.findById(id);
        return getEntityFromOptional(byId, id);
    }

    protected Supplier getEntityFromOptional(Optional<Supplier> optional, Object key) {
        return optional.orElseThrow(() -> getResourceNotFoundException(key));
    }

    protected ResourceNotFoundException getResourceNotFoundException(Object key) {
        return new ResourceNotFoundException(comment() + " id = " + key + "不存在");
    }

    protected String comment() {
        return EntityDAOManager.getTableMeta(baseService.getBaseDAO().getEntityClass()).getTable().comment();
    }
}