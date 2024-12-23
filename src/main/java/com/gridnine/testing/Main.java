package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.*;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<Flight> flights = FlightBuilder.createFlights();

        // Фильтрация: Вылет до текущего момента времени
        FlightFilterManager filterManager = new FlightFilterManager();
        filterManager.addFilter(new DepartureTimeFilter(LocalDateTime.now()));
        List<Flight> flightsDepartedInPast = filterManager.apply(flights);
        System.out.println("Перелеты, вылетающие до текущего момента:");
        flightsDepartedInPast.forEach(System.out::println);

        // Фильтрация: Сегменты с датой прилета раньше даты вылета
        filterManager = new FlightFilterManager();
        filterManager.addFilter(new SegmentArrivalBeforeDepartureFilter());
        List<Flight> flightsWithArrivalBeforeDeparture = filterManager.apply(flights);
        System.out.println("\nПерелеты, где сегменты имеют дату прилета раньше даты вылета:");
        flightsWithArrivalBeforeDeparture.forEach(System.out::println);

        // Фильтрация: Общее время на земле превышает два часа
        filterManager = new FlightFilterManager();
        filterManager.addFilter(new GroundTimeExceedsTwoHoursFilter());
        List<Flight> flightsWithExcessGroundTime = filterManager.apply(flights);
        System.out.println("\nПерелеты, где общее время на земле превышает два часа:");
        flightsWithExcessGroundTime.forEach(System.out::println);
    }
}


