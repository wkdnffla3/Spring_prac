package com.example.basic.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.basic.entity.Player;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)//널 아닌거만 출력해
public class TeamDTO {
    
    int teamId;
    String teamName;
    List<PlayerDTO> players ;
    // @OneToMany
    // Player player;// 이렇게 써야 player 와 연관 관계를 맺는 것이다.


    //Lazy 관련 문제 해결 방법
    // fetch Eager 2) @Transactional 3) fetch join (JPQL) @ Entitiy graph
    // fetch 에 lazy 와 eager 가 있다. eager 로 바꾼다. eager 는 좋지 않는 방식이다. 왜?
   
    // @OneToMany(mappedBy = "team",fetch = FetchType.EAGER) // player 와 team 이 연결되는데 () 안데 mappedBy 속성을 이용해서 상대방에서 나를 어떤 변수로 사용하고 있는지 써줘야 된다.
    // 상대방 player 에서 사용하는 변수명
    // 안넣으면 이상하게 동작을 해버린다.
    // 양방향으로 연관 관계를 맺게 된다.
    
}
