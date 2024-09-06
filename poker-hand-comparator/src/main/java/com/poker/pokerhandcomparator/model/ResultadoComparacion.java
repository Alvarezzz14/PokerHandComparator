package com.poker.pokerhandcomparator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ResultadoComparacion {
    private String winnerHand; // hand2 o hand1
    private String winnerHandType;  // ejemplo: Carta Alta, Full house, Escalera, etc
    private List<Carta> compositionWinnerHand; // las cartas que componen l mano ganadora
}
