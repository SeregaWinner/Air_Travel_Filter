package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

/**
 * Фильтр для проверки общего времени, проведенного на земле, для авиаперелета.
 * Этот фильтр исключает перелеты, где общее время на земле превышает два часа.
 */
public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {

    /**
     * Применяет фильтр к заданному перелету.
     *
     * @param flight Перелет, к которому применяется фильтр.
     * @return true, если общее время на земле не превышает два часа;
     * false, если общее время на земле превышает два часа.
     */
    @Override
    public boolean apply(Flight flight) {
        long totalGroundTime = 0;

        for (int i = 0; i < flight.getSegments().size() - 1; i++) {
            totalGroundTime += flight.getSegments().get(i + 1).getDepartureDate()
                    .until(flight.getSegments().get(i).getArrivalDate(), java.time.temporal.ChronoUnit.MINUTES);
        }

        return totalGroundTime <= 120; // 120 минут = 2 часа
    }
}
