package com.example.basic.dto;

import java.util.List;

import com.example.basic.entity.Player;
import com.example.basic.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)// NULL 아닌거만 출력해
public class PlayerDTO {
    int playerId;
    String playerName;

    //team은 player 를 부르는데 player는 팀을 부르지 않게
    TeamDTO team;
    

}


