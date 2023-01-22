package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.Dto.RouteDto;
import com.placideh.ticketingapi.entity.Route;
import com.placideh.ticketingapi.service.RouteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/routes")
@SecurityRequirement(name = "bearerAuth")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Route>> createRoute(@Valid @RequestBody RouteDto route){
        Map<String,Route> message=new HashMap<>();
        Route createdRoute= routeService.createRoute(route);
        message.put("success",createdRoute);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/{routeNumber}")
    public ResponseEntity<Map<String, Route>> getRouteByRouteNumber(@PathVariable Integer routeNumber){
        Map<String,Route> message=new HashMap<>();
        Route route= routeService.getRouteByRouteNumber(routeNumber);
        message.put("success",route);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, List<Route>>> getRoutes(){
        Map<String,List<Route>> message=new HashMap<>();
        List<Route> routes= routeService.getRoutes();
        message.put("success",routes);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("{routeId}")
    public ResponseEntity updateRoute(@PathVariable Integer routeId,@Valid @RequestBody RouteDto route){
         routeService.updateRouteById(routeId,route);
        return new ResponseEntity<>( HttpStatus. NO_CONTENT);
    }
}
