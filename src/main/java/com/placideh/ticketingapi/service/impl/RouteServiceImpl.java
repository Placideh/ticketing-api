package com.placideh.ticketingapi.service.impl;

import com.placideh.ticketingapi.Dto.RouteDto;
import com.placideh.ticketingapi.entity.Route;
import com.placideh.ticketingapi.entity.Status;
import com.placideh.ticketingapi.exception.NotFoundException;
import com.placideh.ticketingapi.repository.RoleRepo;
import com.placideh.ticketingapi.repository.RouteRepo;
import com.placideh.ticketingapi.service.RouteService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteServiceImpl implements RouteService {
    private final RouteRepo routeRepo;
    private final RoleRepo roleRepo;

    public RouteServiceImpl(RouteRepo routeRepo,
                            RoleRepo roleRepo) {
        this.routeRepo = routeRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public Route createRoute(RouteDto routeDto) {
        Route route=Route.builder()
                .name(routeDto.getName().trim().toUpperCase())
                .status(Status.valueOf(routeDto.getStatus().trim().toUpperCase()))
                .build();
        return routeRepo.save(route);
    }

    @Override
    public Route getRouteByRouteNumber(Integer routeNumber) {
        return routeRepo.findByRouteNumber(routeNumber)
                .orElseThrow(()-> new NotFoundException("routeNumber: "+routeNumber+" not found"));
    }

    @Override
    public List<Route> getRoutes() {
        return routeRepo.findAll();
    }

    @Override
    public void updateRouteById(Integer routeId, RouteDto route) {
        routeRepo.findById(routeId)
                .orElseThrow(()-> new NotFoundException("RouteId: "+routeId+" not found"));

         routeRepo.updateRoute(route.getName(),Status.valueOf(route.getStatus().toUpperCase()).ordinal() ,routeId);
    }
}
