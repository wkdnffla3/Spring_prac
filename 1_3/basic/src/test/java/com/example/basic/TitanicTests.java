package com.example.basic;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.entity.AssemblyMember;
import com.example.basic.entity.Titanic;
import com.example.basic.repository.AssemblyMemberRepository;
import com.example.basic.repository.TitanicRepository;

@SpringBootTest
class TitanicTests {
	// j unit 
	@Autowired AssemblyMemberRepository amr;
	@Autowired TitanicRepository titanicRepository;

	@Test
	void 탑승항구(){
		List<Titanic> list = titanicRepository.findByEmbarked("Q");
		// System.out.println(list);
		list.forEach(t->System.out.println(t));
	}

	@Test
	void 혼자탐(){
	   List<Titanic> list = titanicRepository.findBySibSpAndParch(0, 0);
		// System.out.println(list);
		list.forEach(t ->System.out.println(t) );
	}

	@Test 
	void 이름(){
		List<Titanic> list = titanicRepository.findByNameStartingWith("Smith");
		list.forEach(t->System.out.println(t));
	}

	@Test 
	void 생존자나이순(){
		List<Titanic> list = titanicRepository.findByPclassAndSurvivedOrderByAgeDesc((long)3,(long)1);
		list.forEach(t->System.out.println(t));
	}

	@Test
	void 국회이터1건조회(){
		Optional<AssemblyMember> opt = amr.findById(1);
		// opt.orElseGet(null)
		AssemblyMember member = null;
		if(opt. isPresent()){// 데이터가 없을수도 있기 때문에 if 로 물어본다.
			member = opt.get();
		}else {
			member = new AssemblyMember();
		}
		System.out.println(member);
	}
	
	@Test
	void contextLoads() {
	}

}
