package com.scheduler.generator.service.interfaces;

import com.scheduler.generator.domain.Row;
import com.scheduler.generator.domain.Table;

import java.util.List;

public interface TableService {

    Table createTable(List<Row> rows);
}
