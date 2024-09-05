package com.poker.pokerhandcomparator.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Carta {

    private String valor;  // Valor de la Carta: "2", "8", "K", "A"
    private String palo;  // Palo de la Carta: "H", "D", "S", "C"

}
