package com.scheduler.generator.service.impl;

import com.scheduler.generator.domain.Expression;
import com.scheduler.generator.domain.Row;
import com.scheduler.generator.service.interfaces.RowService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RowServiceImpl implements RowService {
    public static final String LUNCH_BREAK = "Lunch break";

    @Override
    public Row createRow(LocalDate date, LocalTime time, List<Expression> keyWords) {
        return Row.builder()
                .dateColumn(generateDate(date))
                .descriptionColumn(generateDescription(keyWords))
                .timeColumn(generateTime(time))
                .build();
    }

    @Override
    public Row createRow(LocalDate date, LocalTime time, Expression expression) {
        return Row.builder()
                .dateColumn(generateDate(date))
                .descriptionColumn(expression.getExpression())
                .timeColumn(generateTime(time))
                .build();
    }

    @Override
    public List<Row> getRows(LocalDate start, LocalDate end) {
        List<Row> rows = new ArrayList<>();
        List<Expression> expressions = new ArrayList<>();
        for (; start.isBefore(end); start = start.plusDays(1)) {
            rows.addAll(createRowByDay(start, expressions));
        }
        return rows;
    }

    private List<Row> createRowByDay(LocalDate date, List<Expression> expressions) {
        List<Row> rows = new ArrayList<>();
        int randomInt = generateRandomNumber(3, 6);
        int lunchOffset = generateRandomNumber(1, 2);
        Expression lunchExpression = Expression.builder()
                .expression(LUNCH_BREAK)
                .build();
        for (; randomInt > 0; randomInt--) {
            if (randomInt == lunchOffset) {
                rows.add(createRow(date, LocalTime.of(10, 30), lunchExpression));
                continue;
            }
            rows.add(createRow(date, LocalTime.of(10, 30), expressions));
        }
        return rows;
    }


    private String generateDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private String generateDescription(List<Expression> keyWords) {
        //String[] words = new String[generateRandomNumber(3, 8)];
        String firstWord = generateFirstWord();
        return firstWord;
    }

    private String generateTime(LocalTime time) {
        return time.toString() + "-" + time.plusHours(2).toString();
    }

    private int generateRandomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    private String generateFirstWord() {
        List<String> words = new ArrayList<>();
        words.add("fix");
        words.add("working on");
        words.add("fixed");
        words.add("fixing");
        words.add("push");
        words.add("Fix");
        return words.get(generateRandomNumber(0, 5));
    }
}
