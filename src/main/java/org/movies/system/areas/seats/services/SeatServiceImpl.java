package org.movies.system.areas.seats.services;

import org.movies.system.areas.seats.entities.Seat;
import org.movies.system.areas.seats.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    @Override
    public void save(Seat seat) {
        this.seatRepository.save(seat);
    }

    @Override
    public Seat findById(String id) {
        return this.seatRepository.findFirstById(id);
    }
}
