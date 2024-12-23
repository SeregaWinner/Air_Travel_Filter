package com.gridnine.testing.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, представляющий авиаперелет.
 * Перелет состоит из одного или нескольких сегментов.
 */
public class Flight {
    private List<Segment> segments;

    /**
     * Конструктор для создания нового перелета.
     *
     * @param segments Список сегментов, составляющих перелет.
     */
    public Flight(List<Segment> segments) {
        this.segments = segments;
    }

    /**
     * Получает список сегментов, составляющих перелет.
     *
     * @return Список сегментов.
     */
    public List<Segment> getSegments() {
        return segments;
    }

    /**
     * Возвращает строковое представление перелета, состоящее из строковых представлений его сегментов,
     * разделенных пробелами.
     *
     * @return Строковое представление перелета.
     */
    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}