package me.theowm.theopadel3.services;

import me.theowm.theopadel3.entities.Booking;

import java.util.List;

public interface BookingServiceInterface {
    List<Booking> fetchAllBookings();
    Booking addNewBooking(Booking booking);
    Booking updateBooking(int id, Booking booking);
    void deleteBooking(int id);
    void cancelBooking(int id);
    List<Booking> getCancelledBookings();
    boolean isCourtAvailable(int courtId, String dateStr);
    List<Booking> getPastBookings();
    List<Booking> getUpcomingBookings();
    List<Booking> getBookingsByCustomer(String customer);
}
