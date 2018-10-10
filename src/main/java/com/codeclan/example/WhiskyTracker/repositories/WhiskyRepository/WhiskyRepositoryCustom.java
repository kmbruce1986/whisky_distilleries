package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;

import java.util.List;

public interface WhiskyRepositoryCustom {

    List<Whisky> getWhiskiesForYear(int year);
    List<Whisky> getWhiskiesByRegion(String region);
    List<Whisky> getWhiskiesFromDistilleryAged(String distillery, int age);

}
