package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/whiskies")
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/year/{year}")
    public List<Whisky> findAllWhiskiesInYear(@PathVariable int year){
        return whiskyRepository.getWhiskiesForYear(year);
    }

    @GetMapping(value = "/region/{region}")
    public List<Whisky> getWhiskiesByRegion(@PathVariable String region){
        return whiskyRepository.getWhiskiesByRegion(region);
    }

    @GetMapping(value = "/agecheck/{distillery}/{age}")
//    if we change the method to take in a distillery id rather than a string of the name, then we will need to amend this too.
    public List<Whisky> getWhiskiesByDistilleryRegion(@PathVariable String distillery, @PathVariable int age){
        return whiskyRepository.getWhiskiesFromDistilleryAged(distillery, age);
    }

}
