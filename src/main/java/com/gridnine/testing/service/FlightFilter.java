package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

@FunctionalInterface
public interface FlightFilter {
    boolean apply(Flight flight);
}