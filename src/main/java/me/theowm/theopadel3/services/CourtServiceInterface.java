package me.theowm.theopadel3.services;

import me.theowm.theopadel3.entities.Court;

import java.util.List;

public interface CourtServiceInterface {
    List<Court> fetchAllCourts();
    Court addNewCourt(Court court);
    Court updateCourt(int id, Court court);
    void deleteCourt(int id);
}
