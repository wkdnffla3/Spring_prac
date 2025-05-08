package com.example.basic.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// import org.apache.catalina.servlets.DefaultServlet.SortManager.Order;
// import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.basic.BasicApplication;
import com.example.basic.dto.PlayerDTO;
import com.example.basic.dto.TeamDTO;
import com.example.basic.entity.AssemblyMember;
import com.example.basic.entity.Emp;
import com.example.basic.entity.Player;
import com.example.basic.entity.ServiceCenter;
import com.example.basic.entity.Team;
import com.example.basic.repository.AssemblyMemberRepository;
import com.example.basic.repository.EmpRopository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.ServiceCenterRepository;
import com.example.basic.repository.TeamRepository;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/jpa") // 앞으로 접속을 할때 /jpa/sc/add 로 들어와야 된다.

public class JpaController {

    private final BasicApplication basicApplication;
    @Autowired
    ServiceCenterRepository serviceCenterRepository;
    @Autowired AssemblyMemberRepository amr;

    @Autowired TeamRepository teamRepository;
    @Autowired PlayerRepository playerRepository;
    @Autowired EmpRopository empRopository;

    @GetMapping("/emp")
    @ResponseBody // 얘네 DTO로 바꿔보자.
    public List<Emp> emp(){
        List<Emp> list = empRopository.findAll();

        return list;
    }
    

    @GetMapping("/player")
    @ResponseBody
    public List<PlayerDTO> player(){
        List<Player> list = playerRepository.findAll();
        // List<PlayerDTO> list2 = list.stream().map((player)->{
        //     PlayerDTO dto = new PlayerDTO();
        //     dto.setPlayerId(player.getPlayerId());
        //     dto.setPlayerName(player.getPlayerName());
            
        //     Team t = player.getTeam();
        //     TeamDTO dto2 = new TeamDTO();

        //     dto2.setTeamId(t.getTeamId());
        //     dto2.setTeamName(t.getTeamName());
            
        //     dto.setTeam(dto2);

        //     return dto;

        // }).toList();
        //                  메소드 참조 라고한다.
        List<PlayerDTO> list2 = list.stream().map(Player::toDto).toList();


        return list2;

    }
    
    
    
    @GetMapping("/team")
    @ResponseBody
    public List<TeamDTO> team(){
        List<Team> result = teamRepository.findAll();
        // result.stream().map((team)->{
        //     //team 을 DTO로 만들어 준다.
        //     TeamDTO dto = new TeamDTO();
        //     dto.setTeamId(team.getTeamId());
        //     dto.setTeamName(team.getTeamName());

        //     List<Player> ps = team.getPlayers();
        //     List<PlayerDTO> ps2 = ps.stream().map((player)->{
        //         PlayerDTO dto2 = new PlayerDTO();
        //         dto2.setPlayerId( player.getPlayerId() );
        //         dto2.setPlayerName( player.getPlayerName() );
        //         return dto2;
        //     }).toList();
        //     dto.setPlayers(ps2);
        //     return dto;
        // }).toList();//하나의 공식처럼 씌여진다.
        List<TeamDTO> list2 = result.stream().map(Team::toDto).toList();
        return list2;

    }




    @GetMapping("/assembly_member")
    @ResponseBody
    public List<AssemblyMember> assemblyMember(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int count
    ){
        Order order = Order.desc("id");
        Sort sort = Sort.by(order);
        //data.domain import 하는거 
        Pageable pageable = PageRequest.of(page-1,count,sort);
        Page<AssemblyMember> list =amr.findAll(pageable);
        return list.getContent();
    }

    JpaController(BasicApplication basicApplication) {
        this.basicApplication = basicApplication;
    }

    @GetMapping("/sc/delete")
    @ResponseBody
    public String scRemove(@ModelAttribute ServiceCenter sc) {
        serviceCenterRepository.delete(sc);
        return "삭제 완료";
    }

    @GetMapping("/sc/delete2")
    @ResponseBody
    public String scRemove(@RequestParam Integer id) {
        serviceCenterRepository.deleteById(id);
        return "삭제 완료";
    }

    @GetMapping("/sc/modify")
    @ResponseBody
    public ServiceCenter scModify(
            @ModelAttribute ServiceCenter sc) {
        // 예약일자와 방문일자에 대한 정보가 다 사라진다. 그냥 하게 되면
        // 기존에 저장되어있던 정보를 조회하고 saveㅎ ㅏ려는 정보에 덮어쓰기를 해야된다.basicApplication
        // 수정을 할 때 모든 내용을 덮어쓰기 해버린다.basicApplication

        java.util.Optional<ServiceCenter> opt = serviceCenterRepository.findById(sc.getId());

        ServiceCenter savedSC = opt.get();
        sc.setVstDate(savedSC.getVstDate());
        sc.setPurDate(savedSC.getPurDate());

        ServiceCenter result = serviceCenterRepository.save(sc);
        return result;

    }

    @GetMapping("/sc/list")
    @ResponseBody // json으로 보길 원한다.
    public List<ServiceCenter> scList() {
        List<ServiceCenter> list = serviceCenterRepository.findAll();
        return list;
    }

    @GetMapping("/sc/add")
    @ResponseBody
    public ServiceCenter scAdd(@ModelAttribute ServiceCenter sc) {
        LocalDateTime localDateTime = LocalDateTime.now();
        sc.setPurDate(localDateTime);
        Date date = new Date();
        sc.setVstDate(date);
        ServiceCenter result = serviceCenterRepository.save(sc);
        return result;

    }
}
