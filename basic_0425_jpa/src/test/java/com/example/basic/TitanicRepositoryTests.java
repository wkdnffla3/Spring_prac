package com.example.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.repository.TitanicRepository;

@SpringBootTest
class TitanicRepositoryTests {
	@Autowired TitanicRepository tr;

	@Test
	void test1() {
		
	}

}
