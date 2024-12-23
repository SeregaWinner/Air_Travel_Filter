package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Утилитный класс для создания тестовых наборов авиаперелетов.
 * Этот класс предоставляет статический метод для генерации различных типов перелетов
 * с различными условиями.
 */
public class FlightBuilder {

    /**
     * Создает список тестовых перелетов.
     *
     * @return Список перелетов, включая обычные рейсы, многосегментные рейсы,
     * рейсы с вылетом в прошлом и другие нестандартные случаи.
     */
    public static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                // Обычный полет продолжительностью два часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                // Обычный многосегментный рейс
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                // Рейс, вылетающий в прошлом
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                // Рейс, который вылетает до прибытия
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                // Рейс продолжительностью более двух часов наземного времени
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                // Еще один рейс с наземным временем более двух часов
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    /**
     * Создает новый перелет на основе переданных дат и времени.
     *
     * @param dates Даты и время вылета и прилета в формате: [вылет1, прибытие1, вылет2, прибытие2,...].
     *              Должно быть четное количество дат.
     * @return Новый объект типа Flight, состоящий из сегментов.
     * @throws IllegalArgumentException Если передано нечетное количество дат.
     */
    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "вы должны передать четное количество дат");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}
