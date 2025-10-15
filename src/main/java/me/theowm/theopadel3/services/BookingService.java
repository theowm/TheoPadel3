package me.theowm.theopadel3.services;

import me.theowm.theopadel3.entities.Booking;
import me.theowm.theopadel3.exceptions.ResourceNotFoundException;
import me.theowm.theopadel3.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class BookingService implements BookingServiceInterface {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> fetchAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking addNewBooking(Booking booking) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        booking.setCustomer(auth.getName());
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(int id, Booking booking) {
        bookingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Booking", "Id", id));
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(int id) {
        bookingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Booking", "Id", id));
        bookingRepository.deleteById(id);
    }

    //kanske?
    @Override
    public void cancelBooking(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "id", bookingId));

        LocalDateTime now = LocalDateTime.now();
        long daysUntilBooking = ChronoUnit.DAYS.between(now, booking.getBookingDate());

        if (daysUntilBooking < 7) {
            throw new IllegalStateException("Cannot cancel booking within one week of the booking date.");
        }

        booking.setCancelled(true);
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getCancelledBookings() {
        return bookingRepository.findAll().stream()
                .filter(Booking::isCancelled)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isCourtAvailable(int courtId, String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);

        List<Booking> bookingsForCourt = bookingRepository.findAll().stream()
                .filter(b -> b.getCourt().getId() == courtId && !b.isCancelled())
                .toList();

        for (Booking b : bookingsForCourt) {
            if (b.getBookingDate().toLocalDate().isEqual(date)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<Booking> getPastBookings() {
        LocalDateTime now = LocalDateTime.now();

        return bookingRepository.findAll().stream()
                .filter(b -> b.getBookingDate().isBefore(now))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getUpcomingBookings() {
        LocalDateTime now = LocalDateTime.now();

        return bookingRepository.findAll().stream()
                .filter(b -> b.getBookingDate().isAfter(now) && !b.isCancelled())
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getBookingsByCustomer(String customer) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

}
