package com.example.basic;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.basic.entity.AssemblyMember;
import com.example.basic.entity.Player;
import com.example.basic.entity.Team;
import com.example.basic.repository.AssemblyMemberRepository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.TeamRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class DatabaseTests {
	// j unit 
	// @Autowired AssemblyMemberRepository amr;
	@Autowired TeamRepository teamRepository;
	@Autowired PlayerRepository playerRepository;
	
	@Test
	 @Transactional // 이거 떄문에 eager 없어도 된다. 
	 // 접속을 계속 유지해라 라고 유지하는 코드이다.
	 // 1개의 db 커넥션으로 취급한다.
	void contextLoads2(){
		List<Team> list = teamRepository.findAll();
		// 양방향 참조 때문에 stack overflow가 발생한다.

		// 안늦게 만들어 준다.
		// for (Player p : list){

		// }
		// list.stream().forEach((player)->{
		// 	System.out.println(" test  code :" + player);
		// });
		list.forEach((team) ->{
			System.out.println(team);
			// System.out.printf("%s %s %s" , team.getTeamName(), team.getTeamName(), team.getTeamName());
			// System.out.println(team.getTeamName() + " : " + team.getPlayers().size());
			//왜 팀을 통째로 출력 안시키나?
			// player 는 외래키 정보를 가지고 있다. 따라서 player 는 team 정보를 조회가 가능하다.
			// 하지만 Team에는 그런게 없다.
			// ManyToOne 에서 team 은 천천히 조회가 가능한데.
			// OneToMany 에서 player 정보 조회가 어렵다.
			// OneToMany는 아껴서 사용 
		});
	}

	@Test
	@Transactional
	void contextLoads(){
		List<Player> list = playerRepository.findAll();
		// for (Player p : list){

		// }
		list.stream().forEach((player)->{
			System.out.println(" test  code :" + player);
		});
	}

	
	
	// @Test
	// void contextLoads() {
	// }

}
