package com.scheduler.generator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Row {
    private String dateColumn;
    private String descriptionColumn;
    private String timeColumn;
}
