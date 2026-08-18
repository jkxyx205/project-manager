package com.rick.manager.core;

import com.rick.db.plugin.dao.core.TableGenerator;
import com.rick.manager.module.customer.entity.Customer;
import com.rick.manager.module.customer.entity.Record;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author Rick.Xu
 * @date 2023/5/27 18:41
 */
@SpringBootTest
public class TableGeneratorTest {

    @Autowired
    private TableGenerator tableGenerator;

    @Test
    public void generateTable() {
//        tableGenerator.createTable(CodeDescription.class);
//        tableGenerator.createTable(ComplexModel.class);
//        tableGenerator.createTable(Message.class);
        tableGenerator.createTable(Customer.class);
        tableGenerator.createTable(Record.class);
    }
}
