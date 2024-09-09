package com.poker.pokerhandcomparator.utils;

import com.poker.pokerhandcomparator.model.Carta;
import com.poker.pokerhandcomparator.model.Mano;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Convertir Valores de cartas
public class CartaUtils {

    // Convertir valor de carta a número
    public static int convertirValorAEntero(String valor) {
        return switch (valor) {
            case "A", "As" -> 14;
            case "K", "King" -> 13;
            case "Q", "Queen" -> 12;
            case "J", "Jack" -> 11;
            default -> Integer.parseInt(valor);
        };
    }


    // Convierte un número entero al valor textual de la carta
    public static String convertirEnteroAValor(int valor) {
        return switch (valor) {
            case 14 -> "A";
            case 13 -> "K";
            case 12 -> "Q";
            case 11 -> "J";
            default -> String.valueOf(valor);
        };
    }

    //COnvertir valor a Aberviatura
    public static String convertirValorAAbreviatura(String valor) {
        return switch (valor) {
            case "A" -> "As";
            case "King" -> "K";
            case "Queen" -> "Q";
            case "Jack" -> "J";
            default -> valor; // Si es un número, lo dejamos igual
        };
    }
    public static String convertirAbreviaturaAValor(String valor) {
        return switch (valor) {
            case "A" -> "A";
            case "K" -> "King";
            case "Q" -> "Queen";
            case "J" -> "Jack";
            default -> valor; // Si es un número, lo dejamos igual
        };
    }

    //Convertir cadena a un OBJETO Mano
    public static Mano convertirCadenaAMano(String manoStr){
        List<Carta> cartas = Arrays.stream(manoStr.split(" "))
                .map(CartaUtils::crearCarta)
                .collect(Collectors.toList());
        return new Mano(cartas);
    }


    //Crear un objeto Carta apartir de una cadena
    public static Carta crearCarta(String cartaStr) {
        String valor = cartaStr.substring(0, cartaStr.length() -1);
        String palo = cartaStr.substring(cartaStr.length() -1);
        return new Carta(valor, palo);
    }

}
