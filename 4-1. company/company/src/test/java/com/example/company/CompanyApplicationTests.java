package com.example.company;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.company.entity.Countries;
import com.example.company.entity.Departments;
import com.example.company.entity.Employees;
import com.example.company.entity.Locations;
import com.example.company.repository.EmployeesRepository;
import com.example.company.repository.LocationsRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class CompanyApplicationTests {
	@Autowired EmployeesRepository employeesRepository;

	@Autowired LocationsRepository locationsRepository;

	@Test 
	@Transactional
	void 문제4번()
	{
		List<Locations>  locs =locationsRepository.findByStateProvince("W");
		Locations loc = locs.get(0);

		List<Departments> depts =loc.getDepartments();

		for(int i = 0 ; i<depts.size(); i++){
			Departments dept = depts.get(i);
			System.out.println(dept.getDepartmentName() + dept.getEmps().size());
		}

	}

	@Test
	void 사원번호() {
		Optional <Employees> opt = employeesRepository.findById(140);

		Employees emp = opt.orElse((new Employees()));

		String lastName = emp.getLastName();
		String firstName = emp.getFirstName();

		Departments dept = emp.getDepartments();
		Locations loc = dept.getLocations();
		Countries countries = loc.getCountries();
		String countryName = countries.getCountryName();

	}

}
