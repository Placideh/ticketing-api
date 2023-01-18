package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.Dto.RouteDto;
import com.placideh.ticketingapi.entity.Route;
import com.placideh.ticketingapi.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/routes")
@Api(value = "Route endpoints")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/")
    @ApiOperation(value="create a route")
    public ResponseEntity<Map<String, Route>> createRoute(Route route){
        Map<String,Route> message=new HashMap<>();
        Route createdRoute= routeService.createRoute(route);
        message.put("success",createdRoute);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/{routeNumber}")
    @ApiOperation(value="get a single route")
    public ResponseEntity<Map<String, Route>> getRouteByRouteNumber(@PathVariable Integer routeNumber){
        Map<String,Route> message=new HashMap<>();
        Route route= routeService.getRouteByRouteNumber(routeNumber);
        message.put("success",route);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ApiOperation(value="get all route")
    public ResponseEntity<Map<String, List<Route>>> getRoutes(){
        Map<String,List<Route>> message=new HashMap<>();
        List<Route> routes= routeService.getRoutes();
        message.put("success",routes);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/{routeId}")
    @ApiOperation(value="update a route")
    public ResponseEntity<Map<String, Route>> updateRoute(Integer routeId, RouteDto route){
        Map<String,Route> message=new HashMap<>();
        Route updatedRoute= routeService.updateRouteById(routeId,route);
        message.put("success",updatedRoute);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
