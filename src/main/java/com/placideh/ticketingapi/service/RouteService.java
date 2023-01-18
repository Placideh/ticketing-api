package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.Dto.RouteDto;
import com.placideh.ticketingapi.entity.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {
    Route createRoute(Route route);
    Route getRouteByRouteNumber(Integer routeNumber);

    List<Route> getRoutes();

    Route updateRouteById(Integer routeId, RouteDto route);
}
