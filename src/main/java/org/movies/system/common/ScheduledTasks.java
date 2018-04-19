package org.movies.system.common;

import org.movies.system.areas.reservations.entities.Reservation;
import org.movies.system.areas.reservations.services.ReservationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ScheduledTasks {

    private final ReservationService reservationService;

    public ScheduledTasks(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Scheduled(cron = "0 * * * * *")
//    @Scheduled(cron = "0 0 0 * * *") // for every day at midnight
    public void totalReservationsByDate() {
        List<Reservation> totalReservations = this.reservationService.findAll();
        Set<String> users = new HashSet<>();
        for (Reservation reservation : totalReservations) {
            users.add(reservation.getUser().getUsername());
        }

        double profit = totalReservations.stream()
                .mapToDouble(r -> r.getPrice())
                .sum();
        BigDecimal totalProfit = new BigDecimal("0");
        totalProfit = totalProfit.add(new BigDecimal(profit + ""));

        System.out.println(
                "Total reservations at "
                        + LocalDate.now()
                        + " - "
                        + totalReservations.size()
                        + ". Made by "
                        + users.size()
                        + " users. For total profit $ "
                        + totalProfit
        );
    }
}
