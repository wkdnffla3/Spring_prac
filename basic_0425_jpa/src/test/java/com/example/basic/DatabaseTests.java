package com.example.basic;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.basic.entity.Player;
import com.example.basic.entity.Team;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.TeamRepository;

@SpringBootTest
class DatabaseTests {
	@Autowired PlayerRepository playerRepository;
	@Autowired TeamRepository teamRepository;
	
	// OneToMany Lazy
	@Test
	@Transactional // 1개의 DB 연결로 취급
	void contextLoad2() {
		List<Team> list = teamRepository.findAll();
		
		list.forEach((team) -> {
			// toString 무한반복, 순환참조 문제

			System.out.println(team);
		});
	}

	@Test
	void contextLoads() {
		List<Player> list = playerRepository.findAll();
		list.stream().forEach((player) -> {
			System.out.println(
				// 외래키로 값을 이미 가짐
				player.getTeam().getTeamName());
		});
	}

}
