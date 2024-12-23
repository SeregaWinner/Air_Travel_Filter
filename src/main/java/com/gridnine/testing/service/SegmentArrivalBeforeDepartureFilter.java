package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

/**
 * Фильтр для проверки времени прилета сегментов авиаперелета.
 * Этот фильтр исключает перелеты, где хотя бы один сегмент имеет дату прилета раньше даты вылета.
 */
public class SegmentArrivalBeforeDepartureFilter implements FlightFilter {

    /**
     * Применяет фильтр к заданному перелету.
     *
     * @param flight Перелет, к которому применяется фильтр.
     * @return true, если все сегменты перелета имеют дату прилета не раньше даты вылета;
     * false, если хотя бы один сегмент имеет дату прилета раньше даты вылета.
     */
    @Override
    public boolean apply(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }
}