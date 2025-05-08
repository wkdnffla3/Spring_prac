package com.example.basic.entity;

import com.example.basic.dto.PlayerDTO;
import com.example.basic.dto.TeamDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;


// 양방향 연관 관계에서는 순환 참조 오류를 주ㅠ의해야됨
// Restcontroller @ controller @ Response BOdy ->Json 순환참조의 개념만 가져간다.
@Entity
@Data
// @ToString(exclude = "team")
public class Player {
    @Id
    int playerId;
    String playerName;

    @JsonIgnore
    //team은 player 를 부르는데 player는 팀을 부르지 않게
    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;
    public PlayerDTO toDto() {
        TeamDTO teamDto = (team != null) ?
          new TeamDTO(team.getTeamId(), team.getTeamName(), null) : null;
         return new PlayerDTO(
          playerId, playerName, teamDto
        );
      }
     
      public PlayerDTO toDtoWithoutTeam() {
        return new PlayerDTO(playerId, playerName, null);
      }
     


}
