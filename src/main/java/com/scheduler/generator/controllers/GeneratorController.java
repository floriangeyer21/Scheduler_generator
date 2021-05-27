package com.scheduler.generator.controllers;

import com.scheduler.generator.domain.Row;
import com.scheduler.generator.domain.Table;
import com.scheduler.generator.domain.dto.RequestDateDto;
import com.scheduler.generator.service.interfaces.RowService;
import com.scheduler.generator.service.interfaces.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/generate")
public class GeneratorController {
    private final TableService tableService;
    private final RowService rowService;
    private List<Table> localStorage;

    @Autowired
    public GeneratorController(TableService tableService,
                               RowService rowService) {
        this.tableService = tableService;
        this.rowService = rowService;
        localStorage = new ArrayList<>();
    }

    @PostMapping("/table")
    public Table getTable(@RequestBody RequestDateDto requestDateDto) {
        List<Row> rows = rowService.getRows(requestDateDto.getStart(), requestDateDto.getEnd());
        Table table = tableService.createTable(rows);
        localStorage.add(table);
        return table;
    }

    @GetMapping("/all")
    public String getTables() {
        StringBuilder allTables = new StringBuilder();
        for (int i = 0; i < localStorage.size(); i++) {
            if (localStorage.get(i) == null) {
                break;
            }
            allTables.append("Table ").append(i + 1).append("\n");
            List<Row> rows = localStorage.get(i).getRows();
            allTables.append(rows.stream()
                    .map(row -> row.getDateColumn() + " " + row.getDescriptionColumn() + " " + row.getTimeColumn())
                    .collect(Collectors.joining("\n")));
            allTables.append("\n").append("\n");
        }
        return allTables.toString();
    }
}
