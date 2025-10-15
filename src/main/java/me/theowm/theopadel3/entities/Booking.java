package me.theowm.theopadel3.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PADELBOOKINGS")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String customer;

    @Column(name = "player_amount")
    private int playerAmount;

    @JsonIgnoreProperties("bookings")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "court_id", referencedColumnName = "id")
    private Court court;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "total_price_sek")
    private int totalPriceSek;

    @Column(name = "total_price_euro")
    private int totalPriceEuro;

    private boolean cancelled = false;

    public Booking() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTotalPriceSek() {
        return totalPriceSek;
    }

    public void setTotalPriceSek(int totalPriceSek) {
        this.totalPriceSek = totalPriceSek;
    }

    public int getTotalPriceEuro() {
        return totalPriceEuro;
    }

    public void setTotalPriceEuro(int totalPriceEuro) {
        this.totalPriceEuro = totalPriceEuro;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}