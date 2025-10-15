package me.theowm.theopadel3.controllers;

import me.theowm.theopadel3.entities.Booking;
import me.theowm.theopadel3.entities.Court;
import me.theowm.theopadel3.services.BookingService;
import me.theowm.theopadel3.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wigellpadel/v1")
public class AdminController {


    private CourtService courtService;
    private BookingService bookingService;

    @Autowired
    public AdminController(CourtService courtService, BookingService bookingService) {
        this.courtService = courtService;
        this.bookingService = bookingService;
    }

    @PostMapping("/addcourt")
    public ResponseEntity<Court> addNewCourt(@RequestBody Court court) {
        return new ResponseEntity<>(courtService.addNewCourt(court), HttpStatus.CREATED);
    }

    @DeleteMapping("/remcourt/{id}")
    public ResponseEntity<String> deleteCourtById(@PathVariable int id) {
        courtService.deleteCourt(id);
        return ResponseEntity.ok("Court with id " + id + " was deleted.");
    }

    @PutMapping("/updatecourt/{courtId}")
    public ResponseEntity<Court> updateCourt(@PathVariable int courtId, @RequestBody Court court) {
        return ResponseEntity.ok(courtService.updateCourt(courtId, court));
    }

    @GetMapping("/listcanceled")
    public ResponseEntity<List<Booking>> listCanceledBookings() {
        List<Booking> canceledBookings = bookingService.getCancelledBookings();

        if (canceledBookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(canceledBookings);
    }

    @GetMapping("/listpast")
    public ResponseEntity<List<Booking>> listPastBookings() {
        List<Booking> pastBookings = bookingService.getPastBookings();

        if (pastBookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pastBookings);
    }

    @GetMapping("/listupcoming")
    public ResponseEntity<List<Booking>> listUpcomingBookings() {
        List<Booking> upcomingBookings = bookingService.getUpcomingBookings();

        if (upcomingBookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(upcomingBookings);
    }

}
