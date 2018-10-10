package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskiesByYear() {

		List<Whisky> thisYearsWhiskies = whiskyRepository.getWhiskiesForYear(2018);

	}

	@Test
	public void canGetDistilleryByRegion() {
		List<Distillery> speysideDistilleries = distilleryRepository.findDistilleriesByRegion("Speyside");
	}

	@Test
	public void canGetWhiskyByRegion() {
		List<Whisky> speysideWhiskies = whiskyRepository.getWhiskiesByRegion("Speyside");
	}

	@Test
	public void canGetWhiskyByDistilleryAndAge() {
		Distillery distillery = new Distillery("Macallan", "Speyside");
		List<Whisky> anniversaryMalt = whiskyRepository.getWhiskiesFromDistilleryAged(distillery, 25);
	}
}
