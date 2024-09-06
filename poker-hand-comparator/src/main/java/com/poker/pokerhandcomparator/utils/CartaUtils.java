package com.poker.pokerhandcomparator.utils;

//Convertir Vslores de cartas
public class CartaUtils {

    //Convertir valor de carta a numero
    public static int convertirValorAEntero(String valor) {
        return switch (valor) {
            case "A" -> 14;
            case "K" -> 13;
            case "Q" -> 12;
            case "J" -> 11;
            default -> Integer.parseInt(valor); // NUmeros (2-10)
        };
    }

    //Convierte un numero entero al valor textual de la carta
    public static String convertirEnteroAValor(int valor) {
        return switch (valor) {
            case 14 -> "A";
            case 13 -> "K";
            case 12 -> "Q";
            case 11 -> "J";
            default -> String.valueOf(valor);
        };
    }
}
