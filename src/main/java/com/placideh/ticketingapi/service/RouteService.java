package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.Dto.RouteDto;
import com.placideh.ticketingapi.entity.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {
    Route createRoute(RouteDto route);
    Route getRouteByRouteNumber(Integer routeNumber);

    List<Route> getRoutes();

    void updateRouteById(Integer routeId, RouteDto route);
}
