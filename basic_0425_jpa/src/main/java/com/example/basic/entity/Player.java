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

@Entity @Data
@ToString(exclude = "team")
public class Player {
  @Id
  int playerId;
  String playerName;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "team_id")
  Team team;

  public PlayerDTO toDTO() {
    return null;
  }

  public PlayerDTO toDto() {
    TeamDTO teamDto = (team != null) ?
        new TeamDTO(team.getTeamId(), team.getTeamName(), null) : 
        null;
      return new PlayerDTO(playerId, playerName, teamDto
    );
  }

  public PlayerDTO toDtoWithoutTeam() {
    return new PlayerDTO(playerId, playerName, null);
  }
 
} 