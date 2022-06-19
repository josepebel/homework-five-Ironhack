package com.ironhack.trainersservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TrainersServiceApplicationTests {

	@Autowired
	private TrainersServiceApplication trainersServiceApplication;

	@Test
	void contextLoads() {
		assertNotNull(trainersServiceApplication);
	}

	@Test
	void main() {
		TrainersServiceApplication.main(new String[] {});
	}
}
