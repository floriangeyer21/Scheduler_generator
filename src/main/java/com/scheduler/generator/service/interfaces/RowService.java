package com.scheduler.generator.service.interfaces;

import com.scheduler.generator.domain.Expression;
import com.scheduler.generator.domain.Row;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RowService {
    Row createRow(LocalDate date, LocalTime time, List<Expression> keyWords);

    Row createRow(LocalDate date, LocalTime time, Expression expression);

    List<Row> getRows(LocalDate start, LocalDate end);
}
