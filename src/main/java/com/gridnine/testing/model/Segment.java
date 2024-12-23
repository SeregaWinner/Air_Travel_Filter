package com.gridnine.testing.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, представляющий сегмент авиаперелета.
 * Сегмент включает дату и время вылета и прилета.
 */
public class Segment {
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

    /**
     * Конструктор для создания нового сегмента.
     *
     * @param departure Дата и время вылета.
     * @param arrival   Дата и время прилета.
     */
    public Segment(LocalDateTime departure, LocalDateTime arrival) {
        this.departureDate = departure;
        this.arrivalDate = arrival;
    }

    /**
     * Получает дату и время вылета.
     *
     * @return Дата и время вылета.
     */
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    /**
     * Получает дату и время прилета.
     *
     * @return Дата и время прилета.
     */
    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Возвращает строковое представление сегмента в формате [yyyy-MM-dd'T'HH:mm|yyyy-MM-dd'T'HH:mm].
     *
     * @return Строковое представление сегмента.
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}
