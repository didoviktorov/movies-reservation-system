package org.movies.system.services.cinema;

import org.movies.system.models.entities.Cinema;
import org.movies.system.models.entities.Hall;
import org.movies.system.models.entities.Seat;
import org.movies.system.repositories.CinemaRepository;
import org.movies.system.services.hall.HallService;
import org.movies.system.services.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CinemaServiceImpl implements CinemaService {

    private static final int DEFAULT_HALLS_NUMBER = 2;

    private static final int DEFAULT_HALL_SEATS = 100;

    private CinemaRepository cinemaRepository;

    private HallService hallService;

    private SeatService seatService;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository, HallService hallService, SeatService seatService) {
        this.cinemaRepository = cinemaRepository;
        this.hallService = hallService;
        this.seatService = seatService;
    }

    @Override
    public Long cinemaCount() {
        return this.cinemaRepository.count();
    }

    @Override
    public void save(Cinema city) {
        this.cinemaRepository.save(city);
    }

    @Override
    public List<Cinema> findAll() {
        return this.cinemaRepository.findAll();
    }

    @Override
    public Cinema findById(String id) {
        return this.cinemaRepository.findFirstById(id);
    }

    @Override
    public void seedCinemas() {
        List<String> cinemasCities = new ArrayList<>() {{
            add("Varna");
            add("Sofia");
            add("Plovdiv");
        }};

        List<String> imgUrls = new ArrayList<>() {{
            add("/img/varna.jpg");
            add("/img/sofia.jpg");
            add("/img/plovdiv.jpg");
        }};

        List<String> addresses = new ArrayList<>() {{
            add("Varna, bul. Vladislav Varnenchik 186");
            add("Sofia, West Park, bul. Todor Aleksandrov 64");
            add("Plovdiv, bul. Ruski 54 Trade Center Mall Markovo Tepe");
        }};
        for (int i = 0; i < cinemasCities.size(); i++) {
            Cinema currentCinema = new Cinema();
            currentCinema.setName(cinemasCities.get(i));
            currentCinema.setImageUrl(imgUrls.get(i));
            currentCinema.setAddress(addresses.get(i));

            for (int k = 0; k < DEFAULT_HALLS_NUMBER; k++) {
                Hall currentHall = new Hall();
                currentHall.setName("Hall " + (k + 1));
                for (int j = 1; j <= DEFAULT_HALL_SEATS; j++) {
                    Seat currentSeat = new Seat();
                    currentSeat.setOccupied(false);
                    currentSeat.setSeatNumber(j);
                    currentHall.getSeats().add(currentSeat);
                    this.seatService.save(currentSeat);
                    this.hallService.save(currentHall);
                }
                currentCinema.getHalls().add(currentHall);
            }

            this.cinemaRepository.save(currentCinema);
        }
    }
}
