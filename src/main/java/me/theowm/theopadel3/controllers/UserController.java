package me.theowm.theopadel3.controllers;

import me.theowm.theopadel3.entities.Booking;
import me.theowm.theopadel3.entities.Court;
import me.theowm.theopadel3.exceptions.ResourceNotFoundException;
import me.theowm.theopadel3.services.BookingService;
import me.theowm.theopadel3.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/wigellpadel/v1")
public class UserController {

    private CourtService courtService;
    private BookingService bookingService;

    @Autowired
    public UserController(CourtService courtService, BookingService bookingService) {
        this.courtService = courtService;
        this.bookingService = bookingService;
    }

    @GetMapping("/checkavailability/{courtId}/{date}")
    public ResponseEntity<String> checkCourtAvailability(@PathVariable int courtId, @PathVariable String date) {

        try {
            boolean available = bookingService.isCourtAvailable(courtId, date);
            if (available) {
                return ResponseEntity.ok("Court " + courtId + " is available on " + date);
            } else {
                return ResponseEntity.ok("Court " + courtId + " is NOT available on " + date);
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use yyyy-MM-dd");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Court with id " + courtId + " not found.");
        }
    }

    @PostMapping("/booking/bookcourt")
    public ResponseEntity<Booking> bookCourt(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.addNewBooking(booking), HttpStatus.CREATED);
    }

    @DeleteMapping("/cancelbooking")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("Booking with id " + bookingId + " was cancelled");
    }

    @PutMapping("/updatebooking/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int bookingId, @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, booking));
    }

    @GetMapping("/mybookings")
    public ResponseEntity<List<Booking>> getMyBookings(Principal principal) {
        String username = principal.getName();
        List<Booking> myBookings = bookingService.getBookingsByCustomer(username);

        if (myBookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(myBookings);
    }

}
