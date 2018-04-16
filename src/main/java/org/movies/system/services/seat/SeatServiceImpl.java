package org.movies.system.services.seat;

import org.movies.system.models.entities.Seat;
import org.movies.system.repositories.SeatRepository;
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
