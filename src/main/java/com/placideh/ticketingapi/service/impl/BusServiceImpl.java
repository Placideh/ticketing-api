package com.placideh.ticketingapi.service.impl;

import com.placideh.ticketingapi.entity.Bus;
import com.placideh.ticketingapi.exception.NotFoundException;
import com.placideh.ticketingapi.repository.BusRepo;
import com.placideh.ticketingapi.service.BusService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BusServiceImpl implements BusService {

    private final BusRepo busRepo;

    public BusServiceImpl(BusRepo busRepo) {
        this.busRepo = busRepo;
    }

    @Override
    public Bus createBus(Bus bus) {
        return busRepo.save(bus);
    }

    @Override
    public List<Bus> getBuses() {
        return busRepo.findAll();
    }

    @Override
    public Bus getBusByPlateNumber(String plateNumber) {
        return busRepo.findByPlateNumber(plateNumber)
                .orElseThrow(()-> new NotFoundException("plateNumber: "+plateNumber+" not found "));
    }

    @Override
    public Bus updateBusByPlateNumber(String plateNumber, Integer seatSize) {
        return busRepo.updateBus(seatSize,plateNumber)
                .orElseThrow(()-> new NotFoundException("plateNumber: "+plateNumber+" not found "));
    }
}
