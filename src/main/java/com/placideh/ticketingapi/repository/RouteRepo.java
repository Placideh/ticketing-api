package com.placideh.ticketingapi.repository;

import com.placideh.ticketingapi.entity.Route;
import com.placideh.ticketingapi.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface RouteRepo extends JpaRepository<Route,Integer> {
    Optional<Route> findByRouteNumber(Integer routeNumber);

    @Modifying
    @Transactional
    @Query(
            value="update routes set route_name=?1,status=?2 where route_id=?3",
            nativeQuery=true
    )
    void updateRoute(String name, Integer status, Integer routeId);

    Optional<Route> findByName(String name);
}
