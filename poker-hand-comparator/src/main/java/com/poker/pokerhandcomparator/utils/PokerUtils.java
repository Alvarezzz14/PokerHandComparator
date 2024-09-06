package com.poker.pokerhandcomparator.utils;

import com.poker.pokerhandcomparator.model.Carta;

import java.util.List;

public class PokerUtils {

    //Metodo para convertir una lista de cartas a un array de strings con formato "valor+palo"

    public static String[] convertirCartasAString(List<Carta> cartas) {
        return cartas.stream()
                .map(carta -> carta.getValor() + carta.getPalo()) //combinamos el valor y el valo en una sola cadena
                .toArray(String[]::new); //COnvierte la lisata de cadenas en un array de strings
    }

}
