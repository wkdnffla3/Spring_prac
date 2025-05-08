package com.example.basic.entity;

import java.util.List;

import com.example.basic.dto.PlayerDTO;
import com.example.basic.dto.TeamDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Entity @Data
public class Team {
  @Id
  int teamId;
  String teamName;

  //     상대방 Player에서 사용하는 변수명
  @OneToMany(
    mappedBy = "team")
  List<Player> players;


  public TeamDTO toDto() {
    List<PlayerDTO> playerDtoList = null;
 
    playerDtoList = players.stream()
                    .map(Player::toDtoWithoutTeam).toList();
 
    TeamDTO dto = new TeamDTO();
    dto.setTeamId(teamId);
    dto.setTeamName(teamName);
    dto.setPlayers(playerDtoList);
 
    return dto;
  }
 
}