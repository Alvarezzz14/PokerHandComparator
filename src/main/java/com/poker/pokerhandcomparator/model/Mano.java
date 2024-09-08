package com.poker.pokerhandcomparator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Mano {

    private List<Carta> cartas; //Lista de Cartas de la Mano
}
