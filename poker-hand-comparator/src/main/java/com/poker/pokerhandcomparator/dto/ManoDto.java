package com.poker.pokerhandcomparator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


//DTO que resperesenta la entrada JSON de las dos manos de Poker
@Data
public class ManoDto {

    //Primera y segunda mano, como cadenas de texto
    private String hand1;
    private String hand2;

}
