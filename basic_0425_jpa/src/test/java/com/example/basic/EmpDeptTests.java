package com.example.basic;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.entity.Dept;
import com.example.basic.entity.Emp;
import com.example.basic.repository.EmpRepository;

@SpringBootTest
class EmpDeptTests {
	@Autowired EmpRepository empRepository;

	@Test
	void contextLoad3() {
		List<Emp> list = 
			empRepository.findByEnameContainingAndJobContaining("N", "N");
		System.out.println(list);
		// list.stream().map().toList();
	}

	@Test
	void contextLoad2() {
		List<Emp> list = 
			empRepository.findByEname("KING");
		System.out.println(list);
	}

	@Test
	void 부서검색() {
		Dept dept = new Dept();
		dept.setDeptno((byte) 20);
		List<Emp> list = 
			empRepository.findByDept(dept);
		System.out.println(list);
	}
}
