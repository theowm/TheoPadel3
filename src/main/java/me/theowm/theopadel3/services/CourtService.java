package me.theowm.theopadel3.services;

import me.theowm.theopadel3.entities.Court;
import me.theowm.theopadel3.exceptions.ResourceNotFoundException;
import me.theowm.theopadel3.repositories.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtService implements CourtServiceInterface {

    @Autowired
    private CourtRepository courtRepository;

    @Override
    public List<Court> fetchAllCourts() {
        return courtRepository.findAll();
    }

    @Override
    public Court addNewCourt(Court court) {
        return courtRepository.save(court);
    }

    @Override
    public Court updateCourt(int id, Court court) {
        courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "Id", id));
        return courtRepository.save(court);
    }

    @Override
    public void deleteCourt(int id) {
        courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "Id", id));
        courtRepository.deleteById(id);
    }
}
