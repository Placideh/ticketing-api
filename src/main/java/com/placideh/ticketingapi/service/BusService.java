package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.entity.Bus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusService {

    Bus createBus(Bus bus);

    List<Bus> getBuses();

    Bus getBusByPlateNumber(String plateNumber);

    Bus updateBusByPlateNumber(String plateNumber,Integer seatSize);
}
