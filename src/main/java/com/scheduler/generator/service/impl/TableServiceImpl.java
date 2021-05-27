package com.scheduler.generator.service.impl;

import com.scheduler.generator.domain.Row;
import com.scheduler.generator.domain.Table;
import com.scheduler.generator.service.interfaces.TableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    @Override
    public Table createTable(List<Row> rows) {
        return Table.builder()
                .rows(rows)
                .build();

    }
}
