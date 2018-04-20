package org.movies.system.areas.reservations.controllers;

import org.movies.system.controllers.BaseController;
import org.movies.system.areas.reservations.models.binding.ReservationBinding;
import org.movies.system.areas.reservations.models.view.ReservationViewDto;
import org.movies.system.areas.projections.services.ProjectionService;
import org.movies.system.areas.reservations.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReservationController extends BaseController {

    private ProjectionService projectionService;

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ProjectionService projectionService, ReservationService reservationService) {
        this.projectionService = projectionService;
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations/add/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView makeReservation(@PathVariable String id, @ModelAttribute ReservationBinding reservationBinding) {
        return this.getCreateReservationView(id, new ReservationBinding());
    }

    @PostMapping("/reservations/add/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView makeReservation(@PathVariable String id, @Valid @ModelAttribute ReservationBinding reservationBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (reservationBinding.getSeats().size() != reservationBinding.getTickets()) {
            bindingResult.rejectValue("seats", "error.reservationBinding", "Incorrect number of seats selected!");
        }

        if (bindingResult.hasErrors()) {
            return this.getCreateReservationView(id, reservationBinding);
        }

        this.reservationService.save(reservationBinding);

        redirectAttributes.addFlashAttribute("success", "added");

        return this.redirect("/");
    }

    @GetMapping("/reservations")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView myReservations() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ReservationViewDto> dtos = this.reservationService.findAllByUserName(username);
        return this.view("reservations/user-reservations","reservations", dtos);
    }

    private ModelAndView getCreateReservationView(@PathVariable String id, @Valid @ModelAttribute ReservationBinding reservationBinding) {
        String[] names = { "projectionId", "projection", "reservationBinding", "hall"  };

        Object[] objects = {
                id,
                this.projectionService.findProjectionView(id),
                reservationBinding,
                this.projectionService.findProjectionView(id).getHall(),
        };

        return this.view("reservations/add-reservation", names, objects);
    }
}
