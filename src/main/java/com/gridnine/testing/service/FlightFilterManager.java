package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер фильтров для обработки списка авиаперелетов.
 * Этот класс управляет набором фильтров и применяет их к списку перелетов,
 * возвращая отфильтрованный список.
 */
public class FlightFilterManager {
    private List<FlightFilter> filters = new ArrayList<>();

    /**
     * Добавляет новый фильтр в менеджер фильтров.
     *
     * @param filter Фильтр, который будет применяться к перелетам.
     */
    public void addFilter(FlightFilter filter) {
        filters.add(filter);
    }

    /**
     * Применяет все добавленные фильтры к списку перелетов.
     *
     * @param flights Список перелетов, к которому будут применяться фильтры.
     * @return Список отфильтрованных перелетов, соответствующих всем условиям фильтров.
     */
    public List<Flight> apply(List<Flight> flights) {
        List<Flight> filteredFlights = flights;
        for (FlightFilter filter : filters) {
            filteredFlights = filter(filteredFlights, filter);
        }
        return filteredFlights;
    }

    /**
     * Фильтрует список перелетов на основе заданного фильтра.
     *
     * @param flights Список перелетов, который необходимо отфильтровать.
     * @param filter  Фильтр, который будет применен к перелетам.
     * @return Список перелетов, которые соответствуют условиям фильтра.
     */
    private List<Flight> filter(List<Flight> flights, FlightFilter filter) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (filter.apply(flight)) {
                result.add(flight);
            }
        }
        return result;
    }
}