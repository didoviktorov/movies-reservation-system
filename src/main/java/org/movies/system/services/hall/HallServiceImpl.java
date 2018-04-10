package org.movies.system.services.hall;

import org.movies.system.models.entities.Hall;
import org.movies.system.repositories.HallRepository;
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
        return this.hallRepository.findFirstById(id);
    }
}
