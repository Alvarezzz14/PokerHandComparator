package com.poker.pokerhandcomparator.utils;

//Convertir Valores de cartas
public class CartaUtils {

    // Convertir valor de carta a número
    public static int convertirValorAEntero(String valor) {
        return switch (valor) {
            case "A", "Ace" -> 14;
            case "K", "King" -> 13;
            case "Q", "Queen" -> 12;
            case "J", "Jack" -> 11;
            default -> {
                try {
                    yield Integer.parseInt(valor); // Números (2-10)
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Valor de carta inválido: " + valor);
                }
            }
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

}
