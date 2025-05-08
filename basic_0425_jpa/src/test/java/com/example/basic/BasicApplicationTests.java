package com.example.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.basic.entity.AssemblyMember;
import com.example.basic.repository.AssemblyMemberRepository;

@SpringBootTest
class BasicApplicationTests {
	@Autowired AssemblyMemberRepository amr;
	@Autowired JdbcTemplate jdbcTemplate;

	// 20자 이상 이름입력 테스트
	@Test
	void 이름입력테스트() {
		Optional<AssemblyMember> opt = amr.findById(1);
		AssemblyMember member = null;
		// if(opt.isPresent()) {
		// 	member = opt.get();
		// }

		// member = opt.orElse(new AssemblyMember());
		// System.out.println(member);

		// member = opt.orElseGet(() -> new AssemblyMember());
		// System.out.println(member);

		int id = member.getId();

		assertEquals(1, id);
	}

	@Test
	void contextLoads3() {
		jdbcTemplate.queryForList("select * from emp");
	}

	@Test
	void contextLoads2() {
		jdbcTemplate.queryForList("select * from emp");
	}

}
