package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

/**
 * Фильтр для проверки времени вылета авиаперелета.
 * Этот фильтр исключает перелеты, которые вылетают до указанного времени.
 */
public class DepartureTimeFilter implements FlightFilter {
    private LocalDateTime now;

    /**
     * Конструктор для создания фильтра по времени вылета.
     *
     * @param now Дата и время, до которых должны происходить вылеты.
     */
    public DepartureTimeFilter(LocalDateTime now) {
        this.now = now;
    }

    /**
     * Применяет фильтр к заданному перелету.
     *
     * @param flight Перелет, к которому применяется фильтр.
     * @return true, если все сегменты перелета вылетают после указанного времени;
     * false, если хотя бы один сегмент вылетает до указанного времени.
     */
    @Override
    public boolean apply(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getDepartureDate().isBefore(now));
    }
}