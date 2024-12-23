package com.gridnine.testing.service;

import static org.junit.jupiter.api.Assertions.*;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterManagerTest {

    private FlightFilterManager filterManager;

    @BeforeEach
    public void setUp() {
        filterManager = new FlightFilterManager();
    }

    @Test
    public void testApplyWithNoFilters() {
        List<Flight> flights = createTestFlights();
        List<Flight> filteredFlights = filterManager.apply(flights);
        assertEquals(flights.size(), filteredFlights.size(), "Список перелетов должен остаться неизменным при отсутствии фильтров");
    }

    @Test
    public void testApplyWithDepartureTimeFilter() {
        filterManager.addFilter(new DepartureTimeFilter(LocalDateTime.now()));

        List<Flight> flights = createTestFlights();
        List<Flight> filteredFlights = filterManager.apply(flights);

        // Проверяем, что перелеты с вылетом в прошлом были отфильтрованы
        assertTrue(filteredFlights.stream().noneMatch(flight ->
                flight.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
        ), "Перелеты с вылетом в прошлом должны быть исключены");
    }

    @Test
    public void testApplyWithSegmentArrivalBeforeDepartureFilter() {
        filterManager.addFilter(new SegmentArrivalBeforeDepartureFilter());

        List<Flight> flights = createTestFlights();
        List<Flight> filteredFlights = filterManager.apply(flights);

        // Проверяем, что перелеты с сегментами, где время прилета раньше времени вылета, были отфильтрованы
        assertTrue(filteredFlights.stream().noneMatch(flight ->
                flight.getSegments().stream().anyMatch(segment ->
                        segment.getArrivalDate().isBefore(segment.getDepartureDate())
                )
        ), "Перелеты с сегментами, где время прилета раньше времени вылета, должны быть исключены");
    }

    @Test
    public void testApplyWithGroundTimeExceedsTwoHoursFilter() {
        filterManager.addFilter(new GroundTimeExceedsTwoHoursFilter());

        List<Flight> flights = createTestFlights();
        List<Flight> filteredFlights = filterManager.apply(flights);

        // Проверяем, что перелеты с общим временем на земле более двух часов были отфильтрованы
        assertTrue(filteredFlights.stream().noneMatch(flight -> {
            long totalGroundTime = 0;
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                totalGroundTime += flight.getSegments().get(i + 1).getDepartureDate()
                        .until(flight.getSegments().get(i).getArrivalDate(), java.time.temporal.ChronoUnit.MINUTES);
            }
            return totalGroundTime > 120; // 120 минут = 2 часа
        }), "Перелеты с общим временем на земле более двух часов должны быть исключены");
    }

    private List<Flight> createTestFlights() {
        LocalDateTime now = LocalDateTime.now();
        return new ArrayList<>(List.of(
                new Flight(List.of(new Segment(now.plusHours(1), now.plusHours(3)))), // В будущем
                new Flight(List.of(new Segment(now.minusHours(1), now.plusHours(1)))), // В прошлом
                new Flight(List.of(new Segment(now.plusHours(2), now.plusHours(4)))), // В будущем
                new Flight(List.of(new Segment(now.plusHours(3), now.plusHours(2)))) // Прилет раньше вылета
        ));
    }
}