package com.example.basic;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.entity.ServiceCenter;
import com.example.basic.repository.ServiceCenterRepository;
import com.example.basic.repository.TitanicRepository;

@SpringBootTest
class ServiceCenterTests {
	@Autowired ServiceCenterRepository serviceCenterRepository;

	@Test
	void test1() {
		// ?id=1&prdName=변경될제품명
		// ServiceCenter sc = new ServiceCenter();
		// sc.setCustomer("변경될 이름");
		// sc.setPrdName("변경될 제품명");
		// sc.setId(1);

		// List<ServiceCenter> list = 
		// 		serviceCenterRepository.findAll();

		// // Java의 고질적인 문제, Null을 해결하기 위해 만들어놓은 자료형
		// Optional<ServiceCenter> opt =
		// 		serviceCenterRepository.findById(1000);
		
		// // 1번 사용 방법
		// if(opt.isPresent()) {
		// 	ServiceCenter sc1 = opt.get();
		// }

		// // 2번 사용 방법
		// ServiceCenter sc2 = opt.orElse(new ServiceCenter());

		// // 3번 사용 방법 (효율적)
		// ServiceCenter sc3 = opt.orElseGet(() -> { return new ServiceCenter(); });

		int a = 1;
		int b = 2;
		if(a != 1 & ++b == 2) {  // 효율적
			
		}
		System.out.println(a + ", " + b);
		// if(a == 1 || b == 2) {  // 효율적

		// }

		// if(a == 1 & b == 2) {  // 비효율적 (뒤 조건을 항상 실행)
			
		// }
		// if(a == 1 | b == 2) {  // 비효율적 (뒤 조건을 항상 실행)

		// }
		
	}

}
