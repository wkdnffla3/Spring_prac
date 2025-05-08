package com.example.basic.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.dto.PlayerDTO;
import com.example.basic.dto.TeamDTO;
import com.example.basic.entity.AssemblyMember;
import com.example.basic.entity.Emp;
import com.example.basic.entity.Major;
import com.example.basic.entity.Player;
import com.example.basic.entity.ServiceCenter;
import com.example.basic.entity.Team;
import com.example.basic.repository.AssemblyMemberRepository;
import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.MajorRepository;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.ServiceCenterRepository;
import com.example.basic.repository.TeamRepository;

@Controller
public class JpaController {
  @Autowired
  ServiceCenterRepository serviceCenterRepository;

  @Autowired
  MajorRepository majorRepository;

  @Autowired
  AssemblyMemberRepository amr;

  @Autowired TeamRepository teamRepository;
  @Autowired PlayerRepository playerRepository;

  @Autowired EmpRepository empRepository;

  @GetMapping("/emp")
  @ResponseBody
  public List<Emp> emp() {
    List<Emp> list = empRepository.findAll();
    return list;
  }

  @GetMapping("/player")
  @ResponseBody
  public List<PlayerDTO> player() {
    List<Player> list = 
        playerRepository.findAll();
    //                 메소드 참조
    List<PlayerDTO> list2 = 
        list.stream().map(Player::toDto).toList();
    
    // List<PlayerDTO> list2 = list.stream().map((player) -> {
    //   PlayerDTO dto = new PlayerDTO();
    //   dto.setPlayerId(player.getPlayerId());
    //   dto.setPlayerName(player.getPlayerName());
    //   Team t = player.getTeam();
    //   TeamDTO dto2 = new TeamDTO();
    //   dto2.setTeamId(t.getTeamId());
    //   dto2.setTeamName(t.getTeamName());
    //   dto.setTeam(dto2);
    //   return dto;
    // }).toList();

    return list2;
  }

  @GetMapping("/team")
  @ResponseBody
  public List<TeamDTO> team() {
    List<Team> list = 
      teamRepository.findAll();
    
    List<TeamDTO> list2 = 
        list.stream().map(Team::toDto).toList();

    // List<TeamDTO> list2 = list.stream().map((team) -> {
    //   TeamDTO dto = new TeamDTO();
    //   dto.setTeamId(  team.getTeamId()  );
    //   dto.setTeamName(  team.getTeamName()  );
    //   List<Player> ps = team.getPlayers();
    //   List<PlayerDTO> ps2 = 
    //     ps.stream().map((player) -> {
    //       PlayerDTO dto2 = new PlayerDTO();
    //       dto2.setPlayerId( player.getPlayerId() );
    //       dto2.setPlayerName( player.getPlayerName() );
    //       return dto2;
    //     }).toList();
    //   dto.setPlayers(ps2);
    //   return dto;
    // }).toList();
    return list2;
  }

  @GetMapping("/assembly_member")
  @ResponseBody
  public List<AssemblyMember> assemblyMember(
    @RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer count
  ) {
    // data.domain
    Order order = Order.desc("id");
    Sort sort = Sort.by(order);
    Pageable pageable = PageRequest.of(page - 1, count, sort);
    // data.domain
    Page<AssemblyMember> list = amr.findAll(pageable);
    return list.getContent();
  }

  @GetMapping("/major/list")
  @ResponseBody
  public List<Major> majorList() {
    List<Major> list = majorRepository.findAll();
    return list;
  }

  @GetMapping("/major/add")
  @ResponseBody
  public Major majorAdd(
      @ModelAttribute Major major) {
    Date date = new Date();
    major.setEbtbDate(date);
    Major result = majorRepository.save(major);
    return result;
  }


  @GetMapping("/sc/delete")
  @ResponseBody
  public String scRemove(@ModelAttribute ServiceCenter sc) {
    serviceCenterRepository.delete(sc);
    return "삭제 완료";
  }

  @GetMapping("/sc/modify")
  @ResponseBody
  public ServiceCenter scModify(
    //      ?id=xx&name=ooo   MvcMock
      @ModelAttribute ServiceCenter sc) {
    // 수정을 위해 DB의 데이터를 조회
    java.util.Optional<ServiceCenter> opt = 
        serviceCenterRepository.findById(sc.getId());

    ServiceCenter savedSc = opt.get();
    // 기존의 방문일자를 새로 저장할 객체에 입력
    sc.setVstDate(  savedSc.getVstDate()  );
    // 기존의 예약일자를 새로 저장할 객체에 입력
    sc.setPurDate(  savedSc.getPurDate()  );
    
    ServiceCenter result = 
        serviceCenterRepository.save(sc);
    return result;
  }

  @GetMapping("/sc/add")
  @ResponseBody
  public ServiceCenter scAdd(
      @ModelAttribute ServiceCenter sc) {
    LocalDateTime ldt = LocalDateTime.now();
    sc.setPurDate(ldt);

    java.util.Date date = new Date();
    sc.setVstDate(date);

    ServiceCenter result = serviceCenterRepository.save(sc);
    return result;
  }

  @GetMapping("/sc/list")
  @ResponseBody
  public List<ServiceCenter> scList() {
    List<ServiceCenter> list = 
        serviceCenterRepository.findAll();
    return list;
  }
}
