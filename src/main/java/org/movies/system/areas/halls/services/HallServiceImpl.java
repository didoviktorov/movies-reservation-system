package org.movies.system.areas.halls.services;

import org.movies.system.exceptions.BadRequestException;
import org.movies.system.areas.halls.entities.Hall;
import org.movies.system.areas.halls.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HallServiceImpl implements HallService {

    private HallRepository hallRepository;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }


    @Override
    public void save(Hall hall) {
        this.hallRepository.save(hall);
    }

    @Override
    public Hall findById(String id) {
        Hall hall = this.hallRepository.findFirstById(id);
        this.checkHall(hall);
        return hall;
    }

    @Override
    public Hall findByName(String name) {
        return this.hallRepository.findFirstByName(name);
    }

    private void checkHall(Hall hall) {
        if (hall == null) {
            throw new BadRequestException();
        }
    }
}
