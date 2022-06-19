package com.ironhack.teamservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TeamServiceApplicationTests {

	@Autowired
	private TeamServiceApplication teamServiceApplication;

	@Test
	void contextLoads() {
		assertNotNull(teamServiceApplication);
	}

	@Test
	void main() {
		TeamServiceApplication.main(new String[] {});
	}

}
