package com.example.basic;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.entity.AssemblyMember;
import com.example.basic.repository.AssemblyMemberRepository;

@SpringBootTest
class BasicApplicationTests {
	// j unit 
	@Autowired AssemblyMemberRepository amr;

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
