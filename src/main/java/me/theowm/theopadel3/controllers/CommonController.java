package me.theowm.theopadel3.controllers;

import me.theowm.theopadel3.entities.Court;
import me.theowm.theopadel3.services.BookingService;
import me.theowm.theopadel3.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wigellpadel/v1")
public class CommonController {

    private CourtService courtService;
    private BookingService bookingService;

    @Autowired
    public CommonController(CourtService courtService, BookingService bookingService) {
        this.courtService = courtService;
        this.bookingService = bookingService;
    }

    @GetMapping("/listcourts")
    public ResponseEntity<List<Court>> getAllCourts() {
        return ResponseEntity.ok(courtService.fetchAllCourts());
    }

}
