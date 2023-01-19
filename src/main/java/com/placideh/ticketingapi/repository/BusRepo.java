package com.placideh.ticketingapi.repository;

import com.placideh.ticketingapi.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface BusRepo extends JpaRepository<Bus,Integer> {
    @Override
    Optional<Bus> findById(Integer integer);

    Optional<Bus> findByPlateNumber(String plateNumber);

    @Modifying
    @Transactional
    @Query(
            value="update buses set seat_size=?1 where plate_number=?2",
            nativeQuery=true
    )
 void updateBus(Integer seatSize,  String plateNumber);

}
