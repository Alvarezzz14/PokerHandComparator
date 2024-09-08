package com.poker.pokerhandcomparator.exceptions.utils;

import com.poker.pokerhandcomparator.model.Carta;

import java.util.List;

public class ManoSizeUtils {

    public static void validarTamanoMano(List<Carta> cartas) {
        if (cartas == null ||cartas.size() != 5) {
            throw new InvalidHandSizeException("La mano debe contener exactamente 5 cartas");
        }
    }
}
